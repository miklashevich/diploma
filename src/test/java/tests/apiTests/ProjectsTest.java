package tests.apiTests;

import baseEntities.BaseApiTest;
import dao.ProjectDaoImplementation;
import helpers.project.ProjectHelper;
import io.qameta.allure.*;
import lombok.extern.slf4j.Slf4j;
import models.project.Project;
import org.apache.http.HttpStatus;
import org.testng.Assert;
import org.testng.annotations.Test;
import testData.staticProviders.ProjectProvider;
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

    @Feature("Feature1")
    @Story("Story10")
    @Flaky
    @Owner("Dzmitry Rudak")
    @TmsLink("16")
    @Link(name = "Text Link",
            url = "https://thumbs.dreamstime.com/z/funny-cartoon-bug-vector-illustration-cute-beetle-50577038.jpg")
    @Issue("QO-88")
    @Severity(SeverityLevel.MINOR)
    @Test
    public void getAllProjectsTest() {

        int response = projectHelper.getAllProjects();
        log.info(String.valueOf(response));

        Assert.assertEquals(response, HttpStatus.SC_OK);
    }

    @Feature("Feature1")
    @Story("Story10")
    @Flaky
    @Owner("Dzmitry Rudak")
    @TmsLink("16")
    @Issue("QO-88")
    @Severity(SeverityLevel.CRITICAL)
    @Test(dependsOnMethods = "createNewProjectTestFromFile",
            priority = 1,
            dataProviderClass = ProjectProvider.class,
            dataProvider = "Create project for DB")
    public void getProjectTest(Project project) throws IOException, SQLException {

        Reader reader = Files.newBufferedReader(Paths
                .get("src", "test", "java", "testData", "newProjectData.json"));
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

    @Feature("Feature1")
    @Story("Story10")
    @Owner("Dzmitry Rudak")
    @Severity(SeverityLevel.NORMAL)
    @Test
    public void createNewProjectTestFromFile() throws IOException {

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

    @Feature("Feature1")
    @Story("Story10")
    @Owner("Dzmitry Rudak")
    @Severity(SeverityLevel.NORMAL)
    @Test
    public void getNonExistingProjectTest() {

        final String nonExistingProjectId = Integer.toString(Integer.MIN_VALUE);

        String response = projectHelper.getNonExistingProject(nonExistingProjectId);
        Assert.assertEquals(response, "Project is not found.");
    }

    @Feature("Feature2")
    @Story("Story10")
    @Owner("Dzmitry Rudak")
    @Severity(SeverityLevel.NORMAL)
    @Test
    public void deleteNonExistingTestCaseTest() {

        final String projectId = "TP";
        final String nonExistingTestCaseId = Integer.toString(Integer.MIN_VALUE);

        String response = projectHelper.deleteNonExistingTestCase(projectId, nonExistingTestCaseId);
        Assert.assertEquals(response, "Test case not found");
    }

    @Feature("Feature1")
    @Story("Story10")
    @Owner("Dzmitry Rudak")
    @Severity(SeverityLevel.NORMAL)
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
