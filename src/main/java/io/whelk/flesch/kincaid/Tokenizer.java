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

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import com.amazonaws.services.comprehend.model.SyntaxToken;
import edu.stanford.nlp.simple.Document;
import edu.stanford.nlp.simple.Sentence;
import edu.stanford.nlp.simple.Token;
import lombok.experimental.UtilityClass;

/**
 * @author Zack Teater
 * @since 0.1.3
 */
@UtilityClass
public class Tokenizer {

  /**
   * Convert raw text into n-1 sentences.
   * 
   * @param content to convert to sentences
   * @return sentences converted to tokens
   */
  public static List<Sentence> tokenizeContent(String content) {
    return content != null ? new Document(content).sentences() : Collections.emptyList();
  }

  /**
   * Convert sentences to tokens and filter out non-word tokens.
   * 
   * @param sentences to convert to tokens
   * @return sentences converted to tokens
   */
  public static List<String> tokenizeSentences(List<Sentence> sentences) {
    return sentences != null //
        ? sentences.stream() //
            .map(Sentence::tokens) //
            .flatMap(List<Token>::stream) //
            .filter(PennTreebankValidator::isWord) //
            .map(Token::originalText) //
            .collect(Collectors.toList())
        : Collections.emptyList();
  }

  /**
   * Convert syntaxTokens to tokens and filter out non-word tokens.
   * 
   * @param syntaxTokens to filter and convert
   * @return tokens validated and converted to text
   */
  public static List<String> tokenizeSyntaxTokens(List<SyntaxToken> syntaxTokens) {
    return syntaxTokens != null ? //
        syntaxTokens //
            .stream() //
            .filter(ComprehendValidator::isWord) //
            .map(SyntaxToken::getText) //
            .collect(Collectors.toList()) //
        : Collections.emptyList();
  }
}
