package io.whelk.flesch.kincaid;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import edu.stanford.nlp.simple.Document;

class ReadabilityCalculatorTest {

  static final String DEFAULT_SENTENCE = "The quick brown fox jumps over the lazy dog.";
  static final String CINDERLLA_EXCERPT = "A rich man's wife became sick, and when she felt that her end was drawing near, "
      + "she called her only daughter to her bedside and said, \"Dear child, remain pious "
      + "and good, and then our dear God will always protect you, and I will look down on "
      + "you from heaven and be near you.\" With this she closed her eyes and died. "
      + "The girl went out to her mother's grave every day and wept, and she remained pious "
      + "and good. When winter came the snow spread a white cloth over the grave, and when "
      + "the spring sun had removed it again, the man took himself another wife. This wife "
      + "brought two daughters into the house with her. They were beautiful, with fair faces, "
      + "but evil and dark hearts. Times soon grew very bad for the poor stepchild.";

  @Test
  void testCalculateReadingEase_withNullContent() {
    String content = null;
    var result = ReadabilityCalculator.calculateReadingEase(content);

    assertEquals(Double.NaN, result);
  }

  @Test
  void testCalculateReadingEase_withNillContent() {
    var content = "";
    var result = ReadabilityCalculator.calculateReadingEase(content);

    assertEquals(Double.NaN, result);
  }

  @Test
  void testCalculateReadingEase_withContent() {
    var content = DEFAULT_SENTENCE;
    var result = ReadabilityCalculator.calculateReadingEase(content);

    assertEquals(103.70000000000002, result);
  }

  @Test
  void testCalculateReadingEase_withCinderalla() {
    var content = CINDERLLA_EXCERPT;
    var result = ReadabilityCalculator.calculateReadingEase(content);

    assertEquals(80.13934306569344, result);
  }

  @Test
  void testCalculateReadingEase_withSentences() {
    var sentences = new Document(CINDERLLA_EXCERPT).sentences();
    var words = Tokenizer.tokenizeSentences(sentences);
    var result = ReadabilityCalculator.calculateReadingEase(sentences, words);

    assertEquals(80.13934306569344, result);
  }

  @Test
  void testCalculateGradeLevel_withNullContent() {
    String content = null;
    var result = ReadabilityCalculator.calculateGradeLevel(content);

    assertEquals(Double.NaN, result);
  }

  @Test
  void testCalculateGradeLevel_withNillContent() {
    var content = "";
    var result = ReadabilityCalculator.calculateGradeLevel(content);

    assertEquals(Double.NaN, result);
  }

  @Test
  void testCalculateGradeLevel_withContent() {
    var content = DEFAULT_SENTENCE;
    var result = ReadabilityCalculator.calculateGradeLevel(content);

    assertEquals(1.0311111111111124, result);
  }

  @Test
  void testCalculateGradeLevel_withCinderalla() {
    var content = CINDERLLA_EXCERPT;
    var result = ReadabilityCalculator.calculateGradeLevel(content);

    assertEquals(6.943587069864442, result);
  }

  @Test
  void testCalculateGradeLevel_withSentences() {
    var sentences = new Document(CINDERLLA_EXCERPT).sentences();
    var words = Tokenizer.tokenizeSentences(sentences);
    var result = ReadabilityCalculator.calculateGradeLevel(sentences, words);

    assertEquals(6.943587069864442, result);
  }

  @Test
  void testCountSyllables_withNullWord() {
    String word = null;
    var result = ReadabilityCalculator.countSyllables(word);

    assertEquals(0.0, result);
  }

  @Test
  void testCountSyllables_withNillWord() {
    var word = "";
    var result = ReadabilityCalculator.countSyllables(word);

    assertEquals(0.0, result);
  }

  @Test
  void testCountSyllables_withEmptyWord() {
    var word = " ";
    var result = ReadabilityCalculator.countSyllables(word);

    assertEquals(0.0, result);
  }

  @Test
  void testCountSyllables_withQuickWord() {
    var word = "quick";
    var result = ReadabilityCalculator.countSyllables(word);

    assertEquals(1.0, result);
  }

  @Test
  void testCountSyllables_withSilentE() {
    var word = "dine";
    var result = ReadabilityCalculator.countSyllables(word);

    assertEquals(1.0, result);
  }

  @Test
  void testCountSyllables_withSilentES() {
    var word = "bikes";
    var result = ReadabilityCalculator.countSyllables(word);

    assertEquals(1.0, result);
  }

  @Test
  void testCountSyllables_withDipthong() {
    var word = "table";
    var result = ReadabilityCalculator.countSyllables(word);

    assertEquals(2.0, result);
  }

}
