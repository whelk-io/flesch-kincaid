package io.whelk.flesch.kincaid;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import org.junit.Test;
import edu.stanford.nlp.simple.Document;

public class ReadabilityCalculatorTest {

  public static final String DEFAULT_SENTENCE = "The quick brown fox jumps over the lazy dog.";
  public static final String CINDERLLA_EXCERPT =
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
    var content = "";
    var result = ReadabilityCalculator.calculateReadingEase(content);

    assertThat(result, is(Double.NaN));
  }

  @Test
  public void testCalculateReadingEase_withContent() {
    var content = DEFAULT_SENTENCE;
    var result = ReadabilityCalculator.calculateReadingEase(content);

    assertThat(result, is(103.70000000000002));
  }

  @Test
  public void testCalculateReadingEase_withCinderalla() {
    var content = CINDERLLA_EXCERPT;
    var result = ReadabilityCalculator.calculateReadingEase(content);

    assertThat(result, is(80.13934306569344));
  }
  
  @Test
  public void testCalculateReadingEase_withSentences() { 
    var sentences = new Document(CINDERLLA_EXCERPT).sentences();
    var words = Tokenizer.tokenizeSentences(sentences);
    var result = ReadabilityCalculator.calculateReadingEase(sentences, words);
    
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
    var content = "";
    var result = ReadabilityCalculator.calculateGradeLevel(content);

    assertThat(result, is(Double.NaN));
  }

  @Test
  public void testCalculateGradeLevel_withContent() {
    var content = DEFAULT_SENTENCE;
    var result = ReadabilityCalculator.calculateGradeLevel(content);

    assertThat(result, is(1.0311111111111124));
  }

  @Test
  public void testCalculateGradeLevel_withCinderalla() {
    var content = CINDERLLA_EXCERPT;
    var result = ReadabilityCalculator.calculateGradeLevel(content);

    assertThat(result, is(6.943587069864442));
  }
  
  @Test
  public void testCalculateGradeLevel_withSentences() { 
    var sentences = new Document(CINDERLLA_EXCERPT).sentences();
    var words = Tokenizer.tokenizeSentences(sentences);
    var result = ReadabilityCalculator.calculateGradeLevel(sentences, words);
    
    assertThat(result, is(6.943587069864442));
  }
    
  @Test
  public void testCountSyllables_withNullWord() {
    String word = null;
    var result = ReadabilityCalculator.countSyllables(word);

    assertThat(result, is(0.0));
  }

  @Test
  public void testCountSyllables_withNillWord() {
    var word = "";
    var result = ReadabilityCalculator.countSyllables(word);

    assertThat(result, is(0.0));
  }

  @Test
  public void testCountSyllables_withEmptyWord() {
    var word = " ";
    var result = ReadabilityCalculator.countSyllables(word);

    assertThat(result, is(0.0));
  }

  @Test
  public void testCountSyllables_withQuickWord() {
    var word = "quick";
    var result = ReadabilityCalculator.countSyllables(word);

    assertThat(result, is(1.0));
  }

  @Test
  public void testCountSyllables_withSilentE() {
    var word = "dine";
    var result = ReadabilityCalculator.countSyllables(word);

    assertThat(result, is(1.0));
  }

  @Test
  public void testCountSyllables_withSilentES() {
    var word = "bikes";
    var result = ReadabilityCalculator.countSyllables(word);

    assertThat(result, is(1.0));
  }

  @Test
  public void testCountSyllables_withDipthong() {
    var word = "table";
    var result = ReadabilityCalculator.countSyllables(word);

    assertThat(result, is(2.0));
  }

}
