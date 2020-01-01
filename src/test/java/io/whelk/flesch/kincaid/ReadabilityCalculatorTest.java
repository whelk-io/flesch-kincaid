package io.whelk.flesch.kincaid;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import org.junit.Test;
import edu.stanford.nlp.simple.Document;
import edu.stanford.nlp.simple.Sentence;
import edu.stanford.nlp.simple.Token;
import lombok.var;

public class ReadabilityCalculatorTest {

  private static final String DEFAULT_SENTENCE = "The quick brown fox jumps over the lazy dog.";
  private static final String CINDERLLA_EXCERPT =
      "A rich man's wife became sick, and when she felt that her end was drawing near, "
          + "she called her only daughter to her bedside and said, \"Dear child, remain pious "
          + "and good, and then our dear God will always protect you, and I will look down on "
          + "you from heaven and be near you.\" With this she closed her eyes and died. "
          + "The girl went out to her mother's grave every day and wept, and she remained pious "
          + "and good. When winter came the snow spread a white cloth over the grave, and when "
          + "the spring sun had removed it again, the man took himself another wife. This wife "
          + "brought two daughters into the house with her. They were beautiful, with fair faces, "
          + "but evil and dark hearts. Times soon grew very bad for the poor stepchild.";

  @Test
  public void testCalculateReadingEase_withNullContent() {
    String content = null;
    var result = ReadabilityCalculator.calculateReadingEase(content);

    assertThat(result, is(Double.NaN));
  }

  @Test
  public void testCalculateReadingEase_withNillContent() {
    String content = "";
    var result = ReadabilityCalculator.calculateReadingEase(content);

    assertThat(result, is(Double.NaN));
  }

  @Test
  public void testCalculateReadingEase_withContent() {
    String content = DEFAULT_SENTENCE;
    var result = ReadabilityCalculator.calculateReadingEase(content);

    assertThat(result, is(103.70000000000002));
  }

  @Test
  public void testCalculateReadingEase_withCinderalla() {
    String content = CINDERLLA_EXCERPT;
    var result = ReadabilityCalculator.calculateReadingEase(content);

    assertThat(result, is(80.13934306569344));
  }
  
  @Test
  public void testCalculateReadingEase_withSentences() { 
    var sentences = new Document(CINDERLLA_EXCERPT).sentences();
    var result = ReadabilityCalculator.calculateReadingEase(sentences);
    
    assertThat(result, is(80.13934306569344));
  }

  @Test
  public void testCalculateGradeLevel_withNullContent() {
    String content = null;
    var result = ReadabilityCalculator.calculateGradeLevel(content);

    assertThat(result, is(Double.NaN));
  }

  @Test
  public void testCalculateGradeLevel_withNillContent() {
    String content = "";
    var result = ReadabilityCalculator.calculateGradeLevel(content);

    assertThat(result, is(Double.NaN));
  }

  @Test
  public void testCalculateGradeLevel_withContent() {
    String content = DEFAULT_SENTENCE;
    var result = ReadabilityCalculator.calculateGradeLevel(content);

    assertThat(result, is(1.0311111111111124));
  }

  @Test
  public void testCalculateGradeLevel_withCinderalla() {
    String content = CINDERLLA_EXCERPT;
    var result = ReadabilityCalculator.calculateGradeLevel(content);

    assertThat(result, is(6.943587069864442));
  }
  
  @Test
  public void testCalculateGradeLevel_withSentences() { 
    var sentences = new Document(CINDERLLA_EXCERPT).sentences();
    var result = ReadabilityCalculator.calculateGradeLevel(sentences);
    
    assertThat(result, is(6.943587069864442));
  }

  @Test
  public void testTokenizeContent_withNullContent() {
    String content = null;
    List<Sentence> result = ReadabilityCalculator.tokenize(content);

    assertNotNull(result);
    assertTrue(result.isEmpty());
  }

  @Test
  public void testTokenizeContent_withNillContent() {
    String content = "";
    List<Sentence> result = ReadabilityCalculator.tokenize(content);

    assertNotNull(result);
    assertTrue(result.isEmpty());
  }

  @Test
  public void testTokenizeContent_withContent() {
    String content = DEFAULT_SENTENCE;
    List<Sentence> result = ReadabilityCalculator.tokenize(content);

    assertNotNull(result);
    assertFalse(result.isEmpty());
    assertThat(result.size(), is(1));
    assertThat(result.get(0).text(), is(DEFAULT_SENTENCE));
  }

  @Test
  public void testTokenizeSentences_withNullList() {
    List<Sentence> sentences = null;
    var result = ReadabilityCalculator.tokenize(sentences);

    assertNotNull(result);
    assertTrue(result.isEmpty());
  }

  @Test
  public void testTokenizeSentences_withNillList() {
    List<Sentence> sentences = Collections.emptyList();
    var result = ReadabilityCalculator.tokenize(sentences);

    assertNotNull(result);
    assertTrue(result.isEmpty());
  }

  @Test
  public void testTokenizeSentences_withList() {
    Sentence sentence = new Sentence(DEFAULT_SENTENCE);
    List<Sentence> sentences = Arrays.asList(sentence);

    var result = ReadabilityCalculator.tokenize(sentences);
    assertNotNull(result);
    assertFalse(result.isEmpty());
    assertThat(result.size(), is(9));
    assertThat(result.get(0).originalText(), is("The"));
    assertThat(result.get(1).originalText(), is("quick"));
    assertThat(result.get(2).originalText(), is("brown"));
    assertThat(result.get(3).originalText(), is("fox"));
    assertThat(result.get(4).originalText(), is("jumps"));
    assertThat(result.get(5).originalText(), is("over"));
    assertThat(result.get(6).originalText(), is("the"));
    assertThat(result.get(7).originalText(), is("lazy"));
    assertThat(result.get(8).originalText(), is("dog"));
  }

  @Test
  public void testIsWord_withNullToken() {
    var result = ReadabilityCalculator.isWord(null);
    assertFalse(result);
  }

  @Test
  public void testIsWord_withUnknownToken() {
    Sentence sentence = new Sentence(DEFAULT_SENTENCE);
    Token token = new Token(sentence, 9);

    var result = ReadabilityCalculator.isWord(token);
    assertFalse(result);
  }

  @Test
  public void testIsWord_withValidToken() {
    Sentence sentence = new Sentence(DEFAULT_SENTENCE);
    Token token = new Token(sentence, 0);

    var result = ReadabilityCalculator.isWord(token);
    assertTrue(result);
  }

  @Test
  public void testCountSyllables_withNullWord() {
    String word = null;
    var result = ReadabilityCalculator.countSyllables(word);

    assertThat(result, is(0.0));
  }

  @Test
  public void testCountSyllables_withNillWord() {
    String word = "";
    var result = ReadabilityCalculator.countSyllables(word);

    assertThat(result, is(0.0));
  }

  @Test
  public void testCountSyllables_withEmptyWord() {
    String word = " ";
    var result = ReadabilityCalculator.countSyllables(word);

    assertThat(result, is(0.0));
  }

  @Test
  public void testCountSyllables_withQuickWord() {
    String word = "quick";
    var result = ReadabilityCalculator.countSyllables(word);

    assertThat(result, is(1.0));
  }

  @Test
  public void testCountSyllables_withSilentE() {
    String word = "dine";
    var result = ReadabilityCalculator.countSyllables(word);

    assertThat(result, is(1.0));
  }

  @Test
  public void testCountSyllables_withSilentES() {
    String word = "bikes";
    var result = ReadabilityCalculator.countSyllables(word);

    assertThat(result, is(1.0));
  }

  @Test
  public void testCountSyllables_withDipthong() {
    String word = "table";
    var result = ReadabilityCalculator.countSyllables(word);

    assertThat(result, is(2.0));
  }

}
