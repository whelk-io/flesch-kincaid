# Flesch-Kincaid
Java OSS library for performing Flesch-Kincaid readability tests.

[![CodeFactor](https://www.codefactor.io/repository/github/whelk-io/flesch-kincaid/badge)](https://www.codefactor.io/repository/github/whelk-io/flesch-kincaid) ![](https://github.com/whelk-io/flesch-kincaid/workflows/deploy/badge.svg) [![Dependabot Status](https://api.dependabot.com/badges/status?host=github&repo=whelk-io/flesch-kincaid)](https://dependabot.com)

## Calculate Reading Ease
In the Flesch reading-ease test, higher scores indicate material that is easier to read; lower numbers mark passages that are more difficult to read. The formula for the Flesch reading-ease score (FRES) test is

![](/doc/images/reading-ease.svg)

Scores can be interpreted as shown in the table below.

| Score | School level | Notes 
| ----- | ------------ | ----- 
| 100.00–90.00 | 5th grade | Very easy to read. Easily understood by an average 11-year-old student.
| 90.0–80.0 | 6th grade | Easy to read. Conversational English for consumers.
| 80.0–70.0 | 7th grade | Fairly easy to read.
| 70.0–60.0 | 8th & 9th grade | Plain English. Easily understood by 13- to 15-year-old students.
| 60.0–50.0 | 10th to 12th grade | Fairly difficult to read.
| 50.0–30.0 | College | Difficult to read.
| 30.0–0.0 | College graduate | Very difficult to read. Best understood by university graduates.

source: https://en.wikipedia.org/wiki/Flesch–Kincaid_readability_tests#Flesch_reading_ease

**Example Usage**

````java
  public static void main(String...args) {

    String content = 
      "A rich man's wife became sick, and when she felt that her end was drawing near, " +
      "she called her only daughter to her bedside and said, \"Dear child, remain pious " +
      "and good, and then our dear God will always protect you, and I will look down on " +
      "you from heaven and be near you.\" With this she closed her eyes and died. " +
      "The girl went out to her mother's grave every day and wept, and she remained pious " +
      "and good. When winter came the snow spread a white cloth over the grave, and when " +
      "the spring sun had removed it again, the man took himself another wife. This wife " +
      "brought two daughters into the house with her. They were beautiful, with fair faces, " +
      "but evil and dark hearts. Times soon grew very bad for the poor stepchild.";

      double result = ReadabilityCalculator.calculateReadingEase(content);

      System.out.println(result);  // 93.55913669064749

  }
````

## Calculate Grade Level
These readability tests are used extensively in the field of education. The "Flesch–Kincaid Grade Level Formula" instead presents a score as a U.S. grade level, making it easier for teachers, parents, librarians, and others to judge the readability level of various books and texts. It can also mean the number of years of education generally required to understand this text, relevant when the formula results in a number greater than 10. The grade level is calculated with the following formula:

![](doc/images/grade-level.svg)

The result is a number that corresponds with a U.S. grade level. The sentence, "The Australian platypus is seemingly a hybrid of a mammal and reptilian creature" is an 11.3 as it has 24 syllables and 13 words. The different weighting factors for words per sentence and syllables per word in each scoring system mean that the two schemes are not directly comparable and cannot be converted. The grade level formula emphasises sentence length over word length. By creating one-word strings with hundreds of random characters, grade levels may be attained that are hundreds of times larger than high school completion in the United States. Due to the formula's construction, the score does not have an upper bound.

The lowest grade level score in theory is −3.40, but there are few real passages in which every sentence consists of a single one-syllable word. Green Eggs and Ham by Dr. Seuss comes close, averaging 5.7 words per sentence and 1.02 syllables per word, with a grade level of −1.3. (Most of the 50 used words are monosyllabic; "anywhere", which occurs eight times, is the only exception.)

source: https://en.wikipedia.org/wiki/Flesch–Kincaid_readability_tests#Flesch–Kincaid_grade_level

**Example Usage**
````java
  public static void main(String...args) {

    String content = 
      "A rich man's wife became sick, and when she felt that her end was drawing near, " +
      "she called her only daughter to her bedside and said, \"Dear child, remain pious " +
      "and good, and then our dear God will always protect you, and I will look down on " +
      "you from heaven and be near you.\" With this she closed her eyes and died. " +
      "The girl went out to her mother's grave every day and wept, and she remained pious " +
      "and good. When winter came the snow spread a white cloth over the grave, and when " +
      "the spring sun had removed it again, the man took himself another wife. This wife " +
      "brought two daughters into the house with her. They were beautiful, with fair faces, " +
      "but evil and dark hearts. Times soon grew very bad for the poor stepchild.";

      double result = ReadabilityCalculator.calculateGradeLevel(content);

      System.out.println(result);  // 5.142774922918807

  }
````

## Maven Integration


**~/.m2/settings.xml**

````xml
<settings>

  <activeProfiles>
    <activeProfile>github</activeProfile>
  </activeProfiles>

  <profiles>
    <profile>
      <id>github</id>
      <repositories>
        <repository>
          <id>github-flesch-kincaid</id>
          <url>https://maven.pkg.github.com/whelk-io/flesch-kincaid</url>
        </repository>
      </repositories>
    </profile>
  </profiles>

  <servers>
    <server>
      <id>github-flesch-kincaid</id>
      <username>GITHUB_USERNAME</username>
      <password>PERSONAL_ACCESS_TOKEN</password>
    </server>
  </servers>

</settings>
````

**pom.xml**

````xml
<dependencies>
	<dependency>
		<groupId>io.whelk.flesch.kincaid</groupId>
		<artifactId>whelk-flesch-kincaid</artifactId>
		<version>0.0.17-release</version>
	</dependency>

</dependencies>

<repositories>
	<repository>
		<id>github</id>
		<url>https://maven.pkg.github.com/whelk-io/flesch-kincaid</url>
	</repository>
</repositories>
````

More information on authenticating with GitHub packages: https://help.github.com/en/github/managing-packages-with-github-packages/configuring-apache-maven-for-use-with-github-packages#authenticating-to-github-packages

