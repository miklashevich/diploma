package helpers.project;

import org.apache.log4j.Logger;

import static io.restassured.RestAssured.given;

public class ProjectHelper {
    static Logger logger = Logger.getLogger(ProjectHelper.class);

    public ProjectHelper() {

    }

    public String getAllProjects() {
        return given()
                .when()
                .get("/v1/project")
                .asString();
    }
}
