package apiTests;

import baseEntities.BaseApiTest;
import helpers.project.ProjectHelper;
import org.apache.log4j.Logger;
import org.testng.annotations.Test;

public class projectsTest extends BaseApiTest {

    static Logger logger = Logger.getLogger(projectsTest.class);
    ProjectHelper projectHelper = new ProjectHelper();


    @Test
    public void getAllProjectsTest() {
        String response = projectHelper.getAllProjects();

        logger.info(response);
    }
}
