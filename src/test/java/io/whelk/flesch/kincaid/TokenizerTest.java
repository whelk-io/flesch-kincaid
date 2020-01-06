package io.whelk.flesch.kincaid;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import org.junit.Test;
import com.amazonaws.services.comprehend.model.PartOfSpeechTag;
import com.amazonaws.services.comprehend.model.PartOfSpeechTagType;
import com.amazonaws.services.comprehend.model.SyntaxToken;
import edu.stanford.nlp.simple.Sentence;
import lombok.var;

public class TokenizerTest {

  @Test
  public void testTokenizeContent_withNullContent() {
    String content = null;
    var result = Tokenizer.tokenizeContent(content);

    assertNotNull(result);
    assertTrue(result.isEmpty());
  }

  @Test
  public void testTokenizeContent_withNillContent() {
    var content = "";
    var result = Tokenizer.tokenizeContent(content);

    assertNotNull(result);
    assertTrue(result.isEmpty());
  }

  @Test
  public void testTokenizeContent_withContent() {
    var content = ReadabilityCalculatorTest.DEFAULT_SENTENCE;
    var result = Tokenizer.tokenizeContent(content);

    assertNotNull(result);
    assertFalse(result.isEmpty());
    assertThat(result.size(), is(1));
    assertThat(result.get(0).text(), is(ReadabilityCalculatorTest.DEFAULT_SENTENCE));
  }

  @Test
  public void testTokenizeSentences_withNullList() {
    List<Sentence> sentences = null;
    var result = Tokenizer.tokenizeSentences(sentences);

    assertNotNull(result);
    assertTrue(result.isEmpty());
  }

  @Test
  public void testTokenizeSentences_withNillList() {
    List<Sentence> sentences = Collections.emptyList();
    var result = Tokenizer.tokenizeSentences(sentences);

    assertNotNull(result);
    assertTrue(result.isEmpty());
  }

  @Test
  public void testTokenizeSentences_withList() {
    var sentence = new Sentence(ReadabilityCalculatorTest.DEFAULT_SENTENCE);
    var sentences = Arrays.asList(sentence);

    var result = Tokenizer.tokenizeSentences(sentences);
    assertNotNull(result);
    assertFalse(result.isEmpty());
    assertThat(result.size(), is(9));
    assertThat(result.get(0), is("The"));
    assertThat(result.get(1), is("quick"));
    assertThat(result.get(2), is("brown"));
    assertThat(result.get(3), is("fox"));
    assertThat(result.get(4), is("jumps"));
    assertThat(result.get(5), is("over"));
    assertThat(result.get(6), is("the"));
    assertThat(result.get(7), is("lazy"));
    assertThat(result.get(8), is("dog"));
  }

  @Test
  public void testTokenizeSyntaxTokens_withNullTokens() {
    List<SyntaxToken> syntaxTokens = null;

    var result = Tokenizer.tokenizeSyntaxTokens(syntaxTokens);
    assertNotNull(result);
    assertTrue(result.isEmpty());
  }
  
  @Test
  public void testTokenizeSyntaxTokens_withNillTokens() { 
    List<SyntaxToken> syntaxTokens = Collections.emptyList();

    var result = Tokenizer.tokenizeSyntaxTokens(syntaxTokens);
    assertNotNull(result);
    assertTrue(result.isEmpty());
  }
  
  @Test
  public void testTokenizeSyntaxTokens_withList() {
    var syntaxTokens = 
        Arrays.asList(
            this.withTag("The", PartOfSpeechTagType.DET),
            this.withTag("quick", PartOfSpeechTagType.ADJ),
            this.withTag("brown", PartOfSpeechTagType.ADJ),
            this.withTag("fox", PartOfSpeechTagType.NOUN),
            this.withTag("jumps", PartOfSpeechTagType.VERB),
            this.withTag("over", PartOfSpeechTagType.ADP),
            this.withTag("the", PartOfSpeechTagType.DET),
            this.withTag("lazy", PartOfSpeechTagType.ADJ),
            this.withTag("dog", PartOfSpeechTagType.NOUN),
            this.withTag(".", PartOfSpeechTagType.PUNCT));
    
    var result = Tokenizer.tokenizeSyntaxTokens(syntaxTokens);
    assertNotNull(result);
    assertFalse(result.isEmpty());
    assertThat(result.size(), is(9));
    assertThat(result.get(0), is("The"));
    assertThat(result.get(1), is("quick"));
    assertThat(result.get(2), is("brown"));
    assertThat(result.get(3), is("fox"));
    assertThat(result.get(4), is("jumps"));
    assertThat(result.get(5), is("over"));
    assertThat(result.get(6), is("the"));
    assertThat(result.get(7), is("lazy"));
    assertThat(result.get(8), is("dog"));
  }
  
  private SyntaxToken withTag(String text, PartOfSpeechTagType tag) { 
    var pos = new PartOfSpeechTag();
    pos.setTag(tag.name());
    var token = new SyntaxToken();
    token.setText(text);
    token.setPartOfSpeech(pos);
    return token;
  }

}
