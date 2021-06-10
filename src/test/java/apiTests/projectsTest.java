package apiTests;

import baseEntities.BaseApiTest;
import helpers.project.ProjectHelper;
import models.project.GetProjectResponse;
import org.apache.http.HttpStatus;
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

    @Test
    public void getNonExistProjectTest()  {
        String response = projectHelper.getNonExistProject("rtt");
        Assert.assertEquals(response, "Project is not found.");

    }

    @Test
    public void deleteTestCaseTest() {

        int response = projectHelper.deleteOneTestCase("TDT", "10");
        Assert.assertEquals(response, HttpStatus.SC_OK);

    }
    @Test
    public void deleteNonExistTestCaseTest() {

        String response = projectHelper.deleteNonExistTestCase("TDT", "5");
        Assert.assertEquals(response, "Test case not found");

    }
}
