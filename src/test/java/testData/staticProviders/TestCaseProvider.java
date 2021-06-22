package testData.staticProviders;

import enums.testCaseAttributes.SeverityAttribute;
import models.project.Project;
import models.testcase.TestCase;
import org.testng.annotations.DataProvider;

public class TestCaseProvider {

    @DataProvider(name = "Create a Test Case")
    public Object[][] createTestCase() {
        return new Object[][]{
                {
                        Project.builder()
                                .title("Test Project")
                                .build(),

                        TestCase.builder()
                                .title("TestCase For Test")
                                .severity(SeverityAttribute.CRITICAL)
                                .build()
                }
        };
    }

    @DataProvider(name = "Delete a Test Case")
    public Object[][] deleteTestCase() {
        return new Object[][]{
                {
                        Project.builder()
                                .title("Test Project")
                                .build(),

                        TestCase.builder()
                                .title("TestCase For Test")
                                .severity(SeverityAttribute.CRITICAL)
                                .build()
                }
        };
    }
}
