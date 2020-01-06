package io.whelk.flesch.kincaid;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({
    ComprehendValidatorTest.class, 
    PennTreebankValidatorTest.class,
    ReadabilityCalculatorTest.class,
    TokenizerTest.class})
public class AllTests {

}
