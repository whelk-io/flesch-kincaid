package io.whelk.flesch.kincaid;

import java.util.Arrays;

public enum POSTag {

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
  
  public static POSTag parse(String posTag) { 
    return Arrays
            .stream(POSTag.values())
            .filter(p -> p.name().equals(posTag))
            .findFirst()
            .orElse(UNKNOWN);
  }

}
