package testData;

import enums.AccessType;
import enums.testCaseAttributes.SeverityAttribute;
import models.project.Project;
import models.testcase.TestCase;
import org.testng.annotations.DataProvider;

public class StaticProvider {

    @DataProvider(name = "Create project with the help of Builder")
    public Object[][] createProject() {
        RandomStringGenerator randomProjectName = new RandomStringGenerator();
        final int randomStringLength = 255;
        return new Object[][]{
                {Project.builder()
                        .title(randomProjectName.randomString(randomStringLength))
                        .code("PB")
                        .description("ProjectBuilder Description")
                        .access(AccessType.ALL)
                        .build()
                }
        };
    }

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

    @DataProvider(name = "Create project with the name more than 255")
    public Object[][] createProjectLengthNameMorePermitted() {
        RandomStringGenerator randomProjectName = new RandomStringGenerator();
        final int stringLength = 256;
        return new Object[][]{
                {"Project1", Project.builder()
                        .title(randomProjectName.randomString(stringLength))
                        .code("PB")
                        .description("ProjectBuilder Test Description")
                        .access(AccessType.ALL)
                        .build()
                }
        };
    }

    @DataProvider(name = "use incorrect credential")
    public Object[][] createIncorrectCredential() {
        return new Object[][]{
                {"email@email.re", "11111111"}
        };
    }
}
