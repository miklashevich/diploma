package testData.staticProviders;

import enums.AccessType;
import models.project.Project;
import org.testng.annotations.DataProvider;
import testData.RandomStringGenerator;

public class ProjectProvider {

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

    @DataProvider(name = "Create project for DB")
    public Object[][] createProjectForDB() {
        return new Object[][]{
                {Project.builder()
                        .title("DRProject")
                        .code("DRP")
                        .description("Project description test")
                        .access(AccessType.GROUP)
                        .groupAccess("404")
                        .build()
                }
        };
    }

    @DataProvider(name = "Create project with the name more than 255")
    public Object[][] createProjectLengthNameMorePermitted() {
        RandomStringGenerator randomProjectName = new RandomStringGenerator();
        final int stringLength = 256;
        return new Object[][]{
                {Project.builder()
                        .title(randomProjectName.randomString(stringLength))
                        .code("PB")
                        .description("ProjectBuilder Test Description")
                        .access(AccessType.ALL)
                        .build()
                }
        };
    }
}
