package io.whelk.flesch.kincaid;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import org.junit.Test;
import edu.stanford.nlp.simple.Sentence;
import edu.stanford.nlp.simple.Token;

public class PennTreebankValidatorTest {

  @Test
  public void testIsWord_withNullToken() {
    Token token = null;
    var result = PennTreebankValidator.isWord(token);
    assertFalse(result);
  }

  @Test
  public void testIsWord_withUnknownToken() {
    var sentence = new Sentence(ReadabilityCalculatorTest.DEFAULT_SENTENCE);
    var token = new Token(sentence, 9);

    var result = PennTreebankValidator.isWord(token);
    assertFalse(result);
  }

  @Test
  public void testIsWord_withValidToken() {
    var sentence = new Sentence(ReadabilityCalculatorTest.DEFAULT_SENTENCE);
    var token = new Token(sentence, 0);

    var result = PennTreebankValidator.isWord(token);
    assertTrue(result);
  }
  
}
