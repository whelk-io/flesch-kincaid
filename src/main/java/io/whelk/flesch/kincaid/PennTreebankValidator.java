/*
 * Copyright 2021 Whelk Contributors (http://whelk.io)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package io.whelk.flesch.kincaid;

import static io.whelk.flesch.kincaid.PennTreebankValidator.PennTreebankTag.parse;
import java.util.Arrays;
import java.util.List;
import edu.stanford.nlp.simple.Token;
import lombok.experimental.UtilityClass;

/**
 * @author Zack Teater
 * @since 0.1.3
 */
@UtilityClass
public class PennTreebankValidator {

  private static final List<PennTreebankTag> invalidWordTags = List.of( //
      PennTreebankTag.UNKNOWN, //
      PennTreebankTag.POS, //
      PennTreebankTag.SYM);

  /**
   * @param token to evaluate
   * @return true if token does not match invalidate tags, otherwise false
   */
  public static boolean isWord(Token token) {
    return token != null && !invalidWordTags.contains(parse(token.posTag()));
  }

  enum PennTreebankTag {

    CC, //
    CD, //
    DT, //
    EX, //
    FW, //
    IN, //
    JJ, //
    JJR, //
    JJS, //
    LS, //
    MD, //
    NN, //
    NNS, //
    NNP, //
    NNPS, //
    PDT, //
    POS, //
    PRP, //
    PRP$, //
    RB, //
    RBR, //
    RBS, //
    RP, //
    SYM, //
    TO, //
    UH, //
    VB, //
    VBD, //
    VBG, //
    VBN, //
    VBP, //
    VBZ, //
    WDT, //
    WP, //
    WP$, //
    WRB, //
    UNKNOWN;

    public static PennTreebankTag parse(String posTag) {
      return Arrays //
          .stream(PennTreebankTag.values()) //
          .filter(p -> p.name().equals(posTag)) //
          .findFirst() //
          .orElse(PennTreebankTag.UNKNOWN);
    }
  }

}
