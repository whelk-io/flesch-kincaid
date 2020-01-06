package io.whelk.flesch.kincaid;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import com.amazonaws.services.comprehend.model.SyntaxToken;
import edu.stanford.nlp.simple.Document;
import edu.stanford.nlp.simple.Sentence;
import edu.stanford.nlp.simple.Token;
import lombok.experimental.UtilityClass;

@UtilityClass
public class Tokenizer {

  /**
   * Convert raw text into n-1 sentences.
   * 
   * @param content
   * @return
   */
  public static List<Sentence> tokenizeContent(String content) {
    return content != null ? new Document(content).sentences() : Collections.emptyList();
  }
  
  /**
   * Convert sentences to tokens and filter out non-word tokens.
   * 
   * @param sentences
   * @return
   */
  public static List<String> tokenizeSentences(List<Sentence> sentences) { 
    return sentences != null ? 
           sentences
              .stream()
              .map(Sentence::tokens)
              .flatMap(List<Token>::stream)
              .filter(PennTreebankValidator::isWord)
              .map(Token::originalText)
              .collect(Collectors.toList()) : 
           Collections.emptyList();
  }
  
  /**
   * Convert syntaxTokens to tokens and filter out non-word tokens.
   * 
   * @param sentences
   * @return
   */
  public static List<String> tokenizeSyntaxTokens(List<SyntaxToken> syntaxTokens) { 
    return syntaxTokens != null ?
           syntaxTokens
             .stream()
             .filter(ComprehendValidator::isWord)
             .map(SyntaxToken::getText)
             .collect(Collectors.toList()) :
           Collections.emptyList();
  }
}
