package helpers.project;

import static io.restassured.RestAssured.given;

public class ProjectHelper {

    public ProjectHelper() {

    }

    public String getAllProjects() {
        return given()
                .when()
                .get("/v1/project")
                .asString();
    }
}
