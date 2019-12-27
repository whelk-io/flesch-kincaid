# Flesch-Kincaid
OSS library for calculating reading level of text using Flesch-Kincaid readability tests.

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

**Example Usage**

````java
  public static void main(String...args) {

    String content = 
      "A rich man's wife became sick, and when she felt that her end was drawing near, " +
      "she called her only daughter to her bedside and said, \"Dear child, remain pious " +
      "and good, and then our dear God will always protect you, and I will look down on " +
      " you from heaven and be near you.\" With this she closed her eyes and died. " +
      "The girl went out to her mother's grave every day and wept, and she remained pious " +
      "and good. When winter came the snow spread a white cloth over the grave, and when " +
      "the spring sun had removed it again, the man took himself another wife. This wife " +
      "brought two daughters into the house with her. They were beautiful, with fair faces, " +
      "but evil and dark hearts. Times soon grew very bad for the poor stepchild.";

      double result = ReadabilityCalculator.calculateReadingEase(content);

      System.out.println(result);  // 77.73467625899282

  }
  
````

## Maven Integration

````xml
<dependency>
	<groupId>io.whelk.flesch.kincaid</groupId>
	<artifactId>whelk-flesch-kincaid</artifactId>
	<version>0.0.1-RELEASE</version>
</dependency>
````
