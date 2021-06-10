package apiTests;

import baseEntities.BaseApiTest;
import helpers.project.ProjectHelper;
import models.project.GetProjectResponse;
import org.apache.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.Test;
import utils.ObjectUtil;

import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;

public class projectsTest extends BaseApiTest {

    static Logger logger = Logger.getLogger(projectsTest.class);
    ProjectHelper projectHelper = new ProjectHelper();


    @Test
    public void getAllProjectsTest() {
        String response = projectHelper.getAllProjects();

        logger.info(response);
    }

    @Test
    public void getProjectTest() throws IOException {
        Reader reader = Files.newBufferedReader(Paths.get("src/test/resources/testData/expectedProject.json"));

        GetProjectResponse expectedProject = ObjectUtil.getObjectFromJson(reader, GetProjectResponse.class);
        GetProjectResponse actualProject = projectHelper.getProject("TP");
        System.out.println("Expected project code: " + expectedProject);
        System.out.println("Actual project code: " + actualProject);
        Assert.assertEquals(expectedProject, actualProject);

    }
}
