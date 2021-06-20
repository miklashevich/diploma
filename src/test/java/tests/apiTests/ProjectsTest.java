package tests.apiTests;

import baseEntities.BaseApiTest;
import dao.ProjectDaoImplementation;
import helpers.project.ProjectHelper;
import lombok.extern.slf4j.Slf4j;
import models.project.Project;
import org.apache.http.HttpStatus;
//import org.apache.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.Test;
import testData.StaticProvider;
import utils.ObjectUtil;

import java.io.File;
import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.SQLException;
import java.util.Map;

@Slf4j
public class ProjectsTest extends BaseApiTest {

    ProjectHelper projectHelper = new ProjectHelper();

    @Test
    public void getAllProjectsTest() {

        int response = projectHelper.getAllProjects();
        log.info(String.valueOf(response));

        Assert.assertEquals(response, HttpStatus.SC_OK);
    }

    @Test(dependsOnMethods = "createNewProjectTest",
            priority = 1,
            dataProviderClass = StaticProvider.class,
            dataProvider = "Create project for DB")
    public void getProjectTest(Project project) throws IOException, SQLException {

        Reader reader = Files.newBufferedReader(Paths.get("src", "test", "java", "testData", "newProjectData.json"));
        Project expectedProject = ObjectUtil.getObjectFromJson(reader, Project.class);
        ProjectDaoImplementation projectDao = new ProjectDaoImplementation();

        Map<String, String> actualProject =
                projectHelper
                        .getProject(expectedProject.getCode())
                        .jsonPath()
                        .getMap("result");

        projectDao.add(project);
        String projectFromDBCode = projectDao.getProject(project.getCode()).getCode();

        Assert.assertEquals(projectFromDBCode, actualProject.get("code"));

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
