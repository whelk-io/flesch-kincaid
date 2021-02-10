package io.whelk.flesch.kincaid;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import com.amazonaws.services.comprehend.model.PartOfSpeechTag;
import com.amazonaws.services.comprehend.model.PartOfSpeechTagType;
import com.amazonaws.services.comprehend.model.SyntaxToken;

import org.junit.jupiter.api.Test;

import edu.stanford.nlp.simple.Sentence;

class TokenizerTest {

  @Test
  void testTokenizeContent_withNullContent() {
    String content = null;
    var result = Tokenizer.tokenizeContent(content);

    assertNotNull(result);
    assertTrue(result.isEmpty());
  }

  @Test
  void testTokenizeContent_withNillContent() {
    var content = "";
    var result = Tokenizer.tokenizeContent(content);

    assertNotNull(result);
    assertTrue(result.isEmpty());
  }

  @Test
  void testTokenizeContent_withContent() {
    var content = ReadabilityCalculatorTest.DEFAULT_SENTENCE;
    var result = Tokenizer.tokenizeContent(content);

    assertNotNull(result);
    assertFalse(result.isEmpty());
    assertEquals(1, result.size());
    assertTrue(ReadabilityCalculatorTest.DEFAULT_SENTENCE.equals(result.get(0).text()));
  }

  @Test
  void testTokenizeSentences_withNullList() {
    List<Sentence> sentences = null;
    var result = Tokenizer.tokenizeSentences(sentences);

    assertNotNull(result);
    assertTrue(result.isEmpty());
  }

  @Test
  void testTokenizeSentences_withNillList() {
    List<Sentence> sentences = Collections.emptyList();
    var result = Tokenizer.tokenizeSentences(sentences);

    assertNotNull(result);
    assertTrue(result.isEmpty());
  }

  @Test
  void testTokenizeSentences_withList() {
    var sentence = new Sentence(ReadabilityCalculatorTest.DEFAULT_SENTENCE);
    var sentences = Arrays.asList(sentence);

    var result = Tokenizer.tokenizeSentences(sentences);
    assertNotNull(result);
    assertFalse(result.isEmpty());
    assertEquals(9, result.size());
    assertTrue("The".equals(result.get(0)));
    assertTrue("quick".equals(result.get(1)));
    assertTrue("brown".equals(result.get(2)));
    assertTrue("fox".equals(result.get(3)));
    assertTrue("jumps".equals(result.get(4)));
    assertTrue("over".equals(result.get(5)));
    assertTrue("the".equals(result.get(6)));
    assertTrue("lazy".equals(result.get(7)));
    assertTrue("dog".equals(result.get(8)));
  }

  @Test
  void testTokenizeSyntaxTokens_withNullTokens() {
    List<SyntaxToken> syntaxTokens = null;

    var result = Tokenizer.tokenizeSyntaxTokens(syntaxTokens);
    assertNotNull(result);
    assertTrue(result.isEmpty());
  }

  @Test
  void testTokenizeSyntaxTokens_withNillTokens() {
    List<SyntaxToken> syntaxTokens = Collections.emptyList();

    var result = Tokenizer.tokenizeSyntaxTokens(syntaxTokens);
    assertNotNull(result);
    assertTrue(result.isEmpty());
  }

  @Test
  void testTokenizeSyntaxTokens_withList() {
    var syntaxTokens = List.of( //
        this.withTag("The", PartOfSpeechTagType.DET), //
        this.withTag("quick", PartOfSpeechTagType.ADJ), //
        this.withTag("brown", PartOfSpeechTagType.ADJ), //
        this.withTag("fox", PartOfSpeechTagType.NOUN), //
        this.withTag("jumps", PartOfSpeechTagType.VERB), //
        this.withTag("over", PartOfSpeechTagType.ADP), //
        this.withTag("the", PartOfSpeechTagType.DET), //
        this.withTag("lazy", PartOfSpeechTagType.ADJ), //
        this.withTag("dog", PartOfSpeechTagType.NOUN), //
        this.withTag(".", PartOfSpeechTagType.PUNCT));

    var result = Tokenizer.tokenizeSyntaxTokens(syntaxTokens);
    assertNotNull(result);
    assertFalse(result.isEmpty());
    assertEquals(9, result.size());
    assertTrue("The".equals(result.get(0)));
    assertTrue("quick".equals(result.get(1)));
    assertTrue("brown".equals(result.get(2)));
    assertTrue("fox".equals(result.get(3)));
    assertTrue("jumps".equals(result.get(4)));
    assertTrue("over".equals(result.get(5)));
    assertTrue("the".equals(result.get(6)));
    assertTrue("lazy".equals(result.get(7)));
    assertTrue("dog".equals(result.get(8)));
  }

  SyntaxToken withTag(String text, PartOfSpeechTagType tag) {
    var pos = new PartOfSpeechTag();
    pos.setTag(tag.name());
    var token = new SyntaxToken();
    token.setText(text);
    token.setPartOfSpeech(pos);
    return token;
  }

}
