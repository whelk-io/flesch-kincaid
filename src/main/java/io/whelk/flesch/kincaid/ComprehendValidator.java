package io.whelk.flesch.kincaid;

import static com.amazonaws.services.comprehend.model.PartOfSpeechTagType.ADJ;
import static com.amazonaws.services.comprehend.model.PartOfSpeechTagType.ADP;
import static com.amazonaws.services.comprehend.model.PartOfSpeechTagType.ADV;
import static com.amazonaws.services.comprehend.model.PartOfSpeechTagType.AUX;
import static com.amazonaws.services.comprehend.model.PartOfSpeechTagType.CCONJ;
import static com.amazonaws.services.comprehend.model.PartOfSpeechTagType.CONJ;
import static com.amazonaws.services.comprehend.model.PartOfSpeechTagType.DET;
import static com.amazonaws.services.comprehend.model.PartOfSpeechTagType.INTJ;
import static com.amazonaws.services.comprehend.model.PartOfSpeechTagType.NOUN;
import static com.amazonaws.services.comprehend.model.PartOfSpeechTagType.NUM;
import static com.amazonaws.services.comprehend.model.PartOfSpeechTagType.PART;
import static com.amazonaws.services.comprehend.model.PartOfSpeechTagType.PRON;
import static com.amazonaws.services.comprehend.model.PartOfSpeechTagType.PROPN;
import static com.amazonaws.services.comprehend.model.PartOfSpeechTagType.SCONJ;
import static com.amazonaws.services.comprehend.model.PartOfSpeechTagType.VERB;
import java.util.Arrays;
import java.util.List;
import com.amazonaws.services.comprehend.model.SyntaxToken;
import lombok.experimental.UtilityClass;

@UtilityClass
public class ComprehendValidator {
  
  private static final List<String> validTagTypes =
      Arrays.asList(
          ADJ.name(),
          ADP.name(),
          ADV.name(),
          AUX.name(),
          CONJ.name(),
          CCONJ.name(),
          DET.name(),
          INTJ.name(),
          NOUN.name(),
          NUM.name(),
          PART.name(),
          PRON.name(),
          PROPN.name(),
          SCONJ.name(),
          VERB.name());
  
  /**
   * Determine if <code>syntaxToken</code> is valid word. Unknown tags, punctuation, or symbols are
   * not considered valid words.
   * 
   * 
   * @param syntaxToken
   * @return
   */
  public static boolean isWord(SyntaxToken syntaxToken) { 
    return syntaxToken != null &&
           syntaxToken.getPartOfSpeech() != null &&
           syntaxToken.getPartOfSpeech().getTag() != null &&
           validTagTypes.contains(syntaxToken.getPartOfSpeech().getTag());
  }

}
