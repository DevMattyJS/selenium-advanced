package suites;

import categories.ReleaseTest;
import org.junit.experimental.categories.Categories;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import tests.JavascriptExecutorTest;
import tests.WaitForMinionsTest;

@RunWith(Categories.class)
@Categories.IncludeCategory(ReleaseTest.class)
@Suite.SuiteClasses({
        JavascriptExecutorTest.class,
        WaitForMinionsTest.class
})
public class ReleaseTestSuite {
}
