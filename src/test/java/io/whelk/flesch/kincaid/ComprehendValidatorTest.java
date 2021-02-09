package io.whelk.flesch.kincaid;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.amazonaws.services.comprehend.model.PartOfSpeechTag;
import com.amazonaws.services.comprehend.model.PartOfSpeechTagType;
import com.amazonaws.services.comprehend.model.SyntaxToken;

import org.junit.jupiter.api.Test;

class ComprehendValidatorTest {

  @Test
  void testIsWord_withNullSyntaxToken() {
    SyntaxToken syntaxToken = null;

    var result = ComprehendValidator.isWord(syntaxToken);
    assertFalse(result);
  }

  @Test
  void testIsWord_withSyntaxTokenNullPOS() {
    var syntaxToken = new SyntaxToken();
    syntaxToken.setPartOfSpeech(null);

    var result = ComprehendValidator.isWord(syntaxToken);
    assertFalse(result);
  }

  @Test
  void testIsWord_withSyntaxTokenNullPOSTag() {
    var syntaxToken = new SyntaxToken();
    var posTag = new PartOfSpeechTag();
    posTag.setTag(null);
    syntaxToken.setPartOfSpeech(posTag);

    var result = ComprehendValidator.isWord(syntaxToken);
    assertFalse(result);
  }

  @Test
  void testIsWord_withUnknownSyntaxToken() {
    var syntaxToken = new SyntaxToken();
    var posTag = new PartOfSpeechTag();
    posTag.setTag("foo");
    syntaxToken.setPartOfSpeech(posTag);

    var result = ComprehendValidator.isWord(syntaxToken);
    assertFalse(result);
  }

  @Test
  void testIsWord_withValidSyntaxToken() {
    var syntaxToken = new SyntaxToken();
    var posTag = new PartOfSpeechTag();
    posTag.setTag(PartOfSpeechTagType.NOUN.name());
    syntaxToken.setPartOfSpeech(posTag);

    var result = ComprehendValidator.isWord(syntaxToken);
    assertTrue(result);
  }
}
