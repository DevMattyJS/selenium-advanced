package suites;

import categories.ReleaseTest;
import categories.SmokeTest;
import org.junit.experimental.categories.Categories;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import tests.BlurTest;
import tests.WaitForItTest;

// We can create test suite as seen bellow
// We can run all tests in included classes or just filter them by category (with use of Categories.class)
@RunWith(Categories.class)
@Categories.IncludeCategory(SmokeTest.class)
@Categories.ExcludeCategory(ReleaseTest.class)
@Suite.SuiteClasses({
        BlurTest.class,
        WaitForItTest.class
})

public class SmokeTestSuite {
}
