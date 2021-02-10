package io.whelk.flesch.kincaid;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import edu.stanford.nlp.simple.Sentence;
import edu.stanford.nlp.simple.Token;

class PennTreebankValidatorTest {

  @Test
  void testIsWord_withNullToken() {
    Token token = null;
    var result = PennTreebankValidator.isWord(token);
    assertFalse(result);
  }

  @Test
  void testIsWord_withUnknownToken() {
    var sentence = new Sentence(ReadabilityCalculatorTest.DEFAULT_SENTENCE);
    var token = new Token(sentence, 9);

    var result = PennTreebankValidator.isWord(token);
    assertFalse(result);
  }

  @Test
  void testIsWord_withValidToken() {
    var sentence = new Sentence(ReadabilityCalculatorTest.DEFAULT_SENTENCE);
    var token = new Token(sentence, 0);

    var result = PennTreebankValidator.isWord(token);
    assertTrue(result);
  }

}
