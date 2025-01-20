package test;

import org.junit.platform.suite.api.ExcludeTags;
import org.junit.platform.suite.api.IncludeTags;
import org.junit.platform.suite.api.SelectClasses;
import org.junit.platform.suite.api.Suite;

@Suite
public class ProjectTestSuite {

    @Suite
    @SelectClasses({
            CombinedJMockitTests.class,
            VehicleDAOTest.class
    })
    public static class FirstLevelTests {
    }

    @Suite
    @SelectClasses({
            OrderServiceTest.class,
            OrderTest.class,
            CombinedJMockitTests.class,
            VehicleDAOTest.class
    })
    @IncludeTags("Order")
    public static class SecondLevelTests {
    }

    @Suite
    @SelectClasses({
            OrderTest.class,
            CombinedJMockitTests.class,
            VehicleDAOTest.class,
            OrderServiceTest.class
    })
    @ExcludeTags("Skipped")
    public static class ThirdLevelTests {
    }
}
