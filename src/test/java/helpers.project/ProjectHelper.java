package helpers.project;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import models.project.Project;
import org.apache.log4j.Logger;

import static io.restassured.RestAssured.given;

public class ProjectHelper {
    static Logger logger = Logger.getLogger(ProjectHelper.class);
    private Gson gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create();

    public ProjectHelper() {

    }

    public String getAllProjects() {
        return given()
                .when()
                .get("/v1/project")
                .asString();
    }

    public Project getProject(String projectCode) {
         String body = given()
                .when()
                .get("/v1/project/" + projectCode)
                .body()
                .asString();

         return gson.fromJson(body, Project.class);
    }
}
