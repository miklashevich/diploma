package tests.apiTests;

import baseEntities.BaseApiTest;
import helpers.project.ProjectHelper;
import models.project.Project;
import org.apache.http.HttpStatus;
import org.apache.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.Test;
import utils.ObjectUtil;

import java.io.File;
import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Map;

public class projectsTest extends BaseApiTest {

    static Logger logger = Logger.getLogger(projectsTest.class);
    ProjectHelper projectHelper = new ProjectHelper();

    @Test
    public void getAllProjectsTest() {

        int response = projectHelper.getAllProjects();
        logger.info(response);

        Assert.assertEquals(response, HttpStatus.SC_OK);
    }

    @Test(dependsOnMethods = "createNewProjectTest", priority = 1)
    public void getProjectTest() throws IOException {

        Reader reader = Files.newBufferedReader(Paths.get("src", "test", "java", "testData", "newProjectData.json"));
        Project expectedProject = ObjectUtil.getObjectFromJson(reader, Project.class);

        Map<String, String> actualProject =
                projectHelper
                        .getProject(expectedProject.getCode())
                        .jsonPath()
                        .getMap("result");

        Assert.assertEquals(expectedProject.getCode(), actualProject.get("code"));
    }

    @Test
    public void createNewProjectTest() throws IOException {

        Reader reader = Files.newBufferedReader(Paths.get("src", "test", "java", "testData", "newProjectData.json"));
        File jsonDataInFile = new File(String.valueOf(Paths.get("src", "test", "java", "testData", "newProjectData.json")));

        Project actualProject = ObjectUtil.getObjectFromJson(reader, Project.class);
        Map<String, String> newCreatedProjectModelCode =
                projectHelper
                        .createNewProject(jsonDataInFile)
                        .jsonPath()
                        .getMap("result");

        Assert.assertEquals(newCreatedProjectModelCode.get("code"), actualProject.getCode());
    }

    @Test
    public void getNonExistingProjectTest() {

        final String nonExistingProjectId = Integer.toString(Integer.MIN_VALUE);

        String response = projectHelper.getNonExistingProject(nonExistingProjectId);
        Assert.assertEquals(response, "Project is not found.");
    }

    @Test
    public void deleteNonExistingTestCaseTest() {

        final String projectId = "TP";
        final String nonExistingTestCaseId = Integer.toString(Integer.MIN_VALUE);

        String response = projectHelper.deleteNonExistingTestCase(projectId, nonExistingTestCaseId);
        Assert.assertEquals(response, "Test case not found");
    }

    @Test(priority = 2)
    public void deleteProjectTest() throws IOException {

        Reader reader = Files.newBufferedReader(Paths.get("src", "test", "java", "testData", "newProjectData.json"));
        Project expectedProjectModel = ObjectUtil.getObjectFromJson(reader, Project.class);

        Map<String, String> expectedProjectMap =
                projectHelper
                        .getProject(expectedProjectModel.getCode())
                        .jsonPath()
                        .getMap("result");

        String deleteProjectResponse = projectHelper.deleteProject(expectedProjectMap.get("code"));

        Assert.assertEquals(deleteProjectResponse, "true");
    }
}
