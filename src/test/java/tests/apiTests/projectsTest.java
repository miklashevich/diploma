package tests.apiTests;

import baseEntities.BaseApiTest;
import helpers.project.ProjectHelper;
import models.project.GetProjectResponse;
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

public class projectsTest extends BaseApiTest {

    static Logger logger = Logger.getLogger(projectsTest.class);
    ProjectHelper projectHelper = new ProjectHelper();

    @Test
    public void getAllProjectsTest() {

        int response = projectHelper.getAllProjects();
        logger.info(response);

        Assert.assertEquals(response, HttpStatus.SC_OK);
    }

    @Test
    public void getProjectTest() throws IOException {

        Reader reader = Files.newBufferedReader(Paths.get("src/test/java/testData/expectedProject.json"));

        GetProjectResponse expectedProject = ObjectUtil.getObjectFromJson(reader, GetProjectResponse.class);
        GetProjectResponse actualProject = projectHelper.getProject("TP");

        System.out.println("Expected project code: " + expectedProject);
        System.out.println("Actual project code: " + actualProject);

        Assert.assertEquals(expectedProject, actualProject);
    }

    @Test
    public void createNewProjectTest() throws IOException {

        Reader reader = Files.newBufferedReader(Paths.get("src/test/java/testData/newProjectData.json"));
        File jsonDataInFile = new File("src/test/java/testData/newProjectData.json");

        Project newCreatedProjectModel = ObjectUtil.getObjectFromJson(reader, Project.class);
        GetProjectResponse actualProject = projectHelper.createNewProject(jsonDataInFile);

        System.out.println("actual project: " + actualProject);
        System.out.println("expected project: " + newCreatedProjectModel);

        Assert.assertEquals(newCreatedProjectModel.getCode(), actualProject.getResult().getCode());
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
}
