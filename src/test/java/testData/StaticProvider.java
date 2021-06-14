package testData;

import enums.AccessType;
import models.project.Project;
import org.testng.annotations.DataProvider;

public class StaticProvider {

    @DataProvider(name = "Create project with the help of Builder")
    public Object[][] createProject() {
        RandomString randomString = new RandomString();
        return new Object[][]{
                {"Project1", Project.builder()
                        .title(randomString.randomString(255))
                        .code("PB")
                        .description("ProjectBuilder Description")
                        .access(AccessType.ALL)
                        .build()
                }
        };
    }
}
