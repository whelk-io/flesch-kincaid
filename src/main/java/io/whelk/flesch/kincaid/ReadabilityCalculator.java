package io.whelk.flesch.kincaid;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import edu.stanford.nlp.simple.Document;
import edu.stanford.nlp.simple.Sentence;
import edu.stanford.nlp.simple.Token;
import io.whelk.hy.phen.Hyphenator;
import lombok.var;
import lombok.experimental.UtilityClass;

/**
 * 
 * @author Zack Teater
 * @version 0.0.18
 *
 */
@UtilityClass
public class ReadabilityCalculator {
  
  private static final List<POSTag> invalidWordTags = Arrays.asList(POSTag.UNKNOWN, POSTag.POS);

  /**
   * {@code content} parsed to {@code List<Sentence>} and overloaded to
   * {@link #calculateReadingEase(List) calculateReadingEase(List&lt;Sentence&gt;)}
   * 
   * @param content to lex
   * @return
   */
  public static double calculateReadingEase(String content) {
    return calculateReadingEase(tokenize(content));
  }
  
  /**
   * In the Flesch reading-ease test, higher scores indicate material that is easier to read; lower
   * numbers mark passages that are more difficult to read. The formula for the Flesch reading-ease
   * score (FRES) test is:
   * 
   * <pre>
   * 206.835 - 1.015 * (totalWords / totalSentences) - 84.6 * (totalSyllables / totalWords)
   * </pre>
   * 
   * Scores can be interpreted as shown in the table below.
   * 
   * <br><br>
   * <table summary="score and school level mapping">
   *    <tr>
   *        <td><b>Score</b></td>
   *        <td><b>School Level</b></td>
   *        <td><b>Notes</b></td>
   *    </tr><tr>
   *        <td>100.0-90.0</td>
   *        <td>5th grade</td>
   *        <td>Very easy to read. Easily understood by an average 11-year-old student.</td>
   *    </tr><tr>
   *        <td>90.0-80.0</td>
   *        <td>6th grade</td>
   *        <td>Easy to read. Conversational English for consumers.</td>
   *    </tr><tr>
   *        <td>80.0-70.0</td>
   *        <td>7th grade</td>
   *        <td>Fairly easy to read.</td>
   *    </tr><tr>
   *        <td>70.0-60.0</td>
   *        <td>8th &amp; 9th grade</td>
   *        <td>Plain English. Easily understood by 13- to 15-year-old students.</td>
   *    </tr><tr>
   *        <td>60.0-50.0</td>
   *        <td>10th to 12th grade</td>
   *        <td>Fairly difficult to read.</td>
   *    </tr><tr>
   *        <td>50.0-30.0</td>
   *        <td>College</td>
   *        <td>Difficult to read.</td>
   *    </tr><tr>
   *        <td>30.0-0.0</td>
   *        <td>College Graduate</td>
   *        <td>Very difficult to read. Best understood by university graduates.</td>
   *    </tr>
   * </table>
   * 
   * @param sentences to lex
   * @return
   */
  public static double calculateReadingEase(List<Sentence> sentences) {
    var words = tokenize(sentences);
    
    double totalSentences = sentences.size();
    double totalWords = words.size();
    double totalSyllables = countSyllables(words);
    
    return 206.835 - 1.015 * (totalWords / totalSentences) - 84.6 * (totalSyllables / totalWords);
  }
  
  /**
   * {@code content} parsed to {@code List<Sentence>} and overloaded to
   * {@link #calculateGradeLevel(List) calculateGradeLevel(List&lt;Sentence&gt;)}
   * 
   * @param content to lex
   * @return
   */
  public static double calculateGradeLevel(String content) {
    return calculateGradeLevel(tokenize(content));
  }
  
  /**
   * These readability tests are used extensively in the field of education. The "Flesch–Kincaid
   * Grade Level Formula" instead presents a score as a U.S. grade level, making it easier for
   * teachers, parents, librarians, and others to judge the readability level of various books and
   * texts. It can also mean the number of years of education generally required to understand this
   * text, relevant when the formula results in a number greater than 10. The grade level is
   * calculated with the following formula:
   * 
   * <pre>
   * 0.39 * (totalWords / totalSentences) + 11.8 * (totalSyllables / totalWords) - 15.59;
   * </pre>
   * 
   * The result is a number that corresponds with a U.S. grade level. The sentence, "The Australian
   * platypus is seemingly a hybrid of a mammal and reptilian creature" is an 11.3 as it has 24
   * syllables and 13 words. The different weighting factors for words per sentence and syllables
   * per word in each scoring system mean that the two schemes are not directly comparable and
   * cannot be converted. The grade level formula emphasises sentence length over word length. By
   * creating one-word strings with hundreds of random characters, grade levels may be attained that
   * are hundreds of times larger than high school completion in the United States. Due to the
   * formula's construction, the score does not have an upper bound.
   * 
   * The lowest grade level score in theory is −3.40, but there are few real passages in which every
   * sentence consists of a single one-syllable word. Green Eggs and Ham by Dr. Seuss comes close,
   * averaging 5.7 words per sentence and 1.02 syllables per word, with a grade level of −1.3. (Most
   * of the 50 used words are monosyllabic; "anywhere", which occurs eight times, is the only
   * exception.)
   * 
   * @param sentences to lex
   * @return reading grade level
   */
  public static double calculateGradeLevel(List<Sentence> sentences) {
    var words = tokenize(sentences);
    
    double totalSentences = sentences.size();
    double totalWords = words.size();
    double totalSyllables = countSyllables(words);
    
    return 0.39 * (totalWords / totalSentences) + 11.8 * (totalSyllables / totalWords) - 15.59;
  }
  
  static List<Sentence> tokenize(String content) {
    return content != null ? new Document(content).sentences() : Collections.emptyList();
  }
  
  static List<Token> tokenize(List<Sentence> sentences) { 
    return sentences != null ? 
           sentences
              .stream()
              .map(Sentence::tokens)
              .flatMap(List<Token>::stream)
              .filter(ReadabilityCalculator::isWord)
              .collect(Collectors.toList()) : 
           Collections.emptyList();
  }

  static boolean isWord(Token token) { 
    return token != null && !invalidWordTags.contains(POSTag.parse(token.posTag()));
  }
  
  static double countSyllables(List<Token> tokens) {
    return tokens
            .stream()
            .map(Token::originalText)
            .mapToDouble(ReadabilityCalculator::countSyllables)
            .sum();
  }
  
  static double countSyllables(String word) {
    if (word == null || word.trim().isEmpty())
      return 0;
    
    return Hyphenator.hyphen(word).syllables().size();
  }
  
}
