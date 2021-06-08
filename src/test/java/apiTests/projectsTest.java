package apiTests;

import helpers.project.ProjectHelper;
//import org.apache.log4j.Logger;
import org.testng.annotations.Test;

public class projectsTest {

//    static Logger logger

    @Test
    public void getAllProjectsTest() {
        ProjectHelper projectHelper = new ProjectHelper();
        String response = projectHelper.getAllProjects();
    }
}
