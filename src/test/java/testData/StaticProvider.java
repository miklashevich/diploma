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
                {"Project1", Project.builder()
                        .title(randomProjectName.randomString(randomStringLength))
                        .code("PB")
                        .description("ProjectBuilder Description")
                        .access(AccessType.ALL)
                        .build()
                }
        };
    }

    @DataProvider(name = "Create Test Case with the help of Builder")
    public Object[][] createTestCase() {
        RandomStringGenerator randomProjectName = new RandomStringGenerator();
        final int randomStringLength = 255;
        return new Object[][]{
                {
                        "TestCase1",

                        Project.builder()
                                .title("Test Project")
                                .build(),

                        TestCase.builder()
                                .title("TestCase123")
                                .severity(SeverityAttribute.CRITICAL)
                                .build()
                }
        };
    }

    @DataProvider(name = "Create project with the name more than 255")
    public Object[][] createProjectLengthNameMorePermitted() {
        RandomStringGenerator randomProjectName = new RandomStringGenerator();
        final int randomStringLength = 256;
        return new Object[][]{
                {"Project1", Project.builder()
                        .title(randomProjectName.randomString(randomStringLength))
                        .code("PB")
                        .description("ProjectBuilder Test Description")
                        .access(AccessType.ALL)
                        .build()
                }
        };
    }

}
