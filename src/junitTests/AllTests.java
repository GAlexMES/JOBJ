package junitTests;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({ FacesTest.class, ParserTest.class, completeFileTest.class })
public class AllTests {

}
