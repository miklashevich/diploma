package apiTests;

import baseEntities.BaseApiTest;
import helpers.project.ProjectHelper;
import models.Project;
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
        String response = projectHelper.getAllProjects();

        logger.info(response);
    }

    @Test
    public void getProjectTest() throws IOException {
        Reader reader = Files.newBufferedReader(Paths.get("src/test/resources/testData/expectedProject.json"));

        Project expectedProject = ObjectUtil.getObjectFromJson(reader, Project.class);
        Project actualProject = projectHelper.getProject("TP");
        System.out.println("Actual project" + actualProject);
        Assert.assertTrue(expectedProject.equals(actualProject));

    }
}
