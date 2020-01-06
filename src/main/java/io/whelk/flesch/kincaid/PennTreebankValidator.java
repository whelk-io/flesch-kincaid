package io.whelk.flesch.kincaid;

import static io.whelk.flesch.kincaid.PennTreebankValidator.PennTreebankTag.parse;
import java.util.Arrays;
import java.util.List;
import edu.stanford.nlp.simple.Token;
import lombok.experimental.UtilityClass;

@UtilityClass
public class PennTreebankValidator {
  
  private static final List<PennTreebankTag> invalidWordTags = 
      Arrays.asList(
          PennTreebankTag.UNKNOWN, 
          PennTreebankTag.POS, 
          PennTreebankTag.SYM);
  
  public static boolean isWord(Token token) {
    return token != null && !invalidWordTags.contains(parse(token.posTag()));
  }

  enum PennTreebankTag {

    CC, 
    CD, 
    DT, 
    EX, 
    FW, 
    IN, 
    JJ, 
    JJR, 
    JJS, 
    LS, 
    MD, 
    NN, 
    NNS, 
    NNP, 
    NNPS, 
    PDT, 
    POS, 
    PRP, 
    PRP$, 
    RB, 
    RBR, 
    RBS, 
    RP, 
    SYM, 
    TO, 
    UH, 
    VB, 
    VBD, 
    VBG, 
    VBN, 
    VBP, 
    VBZ, 
    WDT, 
    WP, 
    WP$, 
    WRB,
    UNKNOWN;
    
    public static PennTreebankTag parse(String posTag) { 
      return Arrays
              .stream(PennTreebankTag.values())
              .filter(p -> p.name().equals(posTag))
              .findFirst()
              .orElse(PennTreebankTag.UNKNOWN);
    }
  }

}
