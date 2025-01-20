package test;

import org.junit.platform.suite.api.IncludeTags;
import org.junit.platform.suite.api.SelectClasses;
import org.junit.platform.suite.api.Suite;

@Suite
@SelectClasses({
        VehicleDAOTest.class,
        OrderTest.class,
        OrderServiceTest.class,
        CombinedJMockitTests.class
})

public class ProjectTestSuite {
}
