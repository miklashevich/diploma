package helpers.project;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import io.restassured.http.ContentType;
import models.project.GetProjectResponse;
import org.apache.http.HttpStatus;
import org.apache.log4j.Logger;

import java.io.File;

import static io.restassured.RestAssured.given;

public class ProjectHelper {
    static Logger logger = Logger.getLogger(ProjectHelper.class);
    private Gson gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create();

    public ProjectHelper() {

    }

    public int getAllProjects() {

        return given()
                .when()
                .get("/v1/project")
                .getStatusCode();
    }

    public GetProjectResponse createNewProject(File file) {
        String newProjectCreated = given()
                .contentType(ContentType.JSON)
                .body(file)
                .post("/v1/project")
                .body()
                .asString();
        return gson.fromJson(newProjectCreated, GetProjectResponse.class);
    }

    public GetProjectResponse getProject(String projectCode) {
        String body = given()
                .when()
                .get("/v1/project/" + projectCode)
                .body()
                .asString();

        return gson.fromJson(body, GetProjectResponse.class);
    }

    public String getNonExistingProject(String projectCode) {
        return given()
                .when()
                .get("/v1/project/" + projectCode)
                .then()
                .statusCode(HttpStatus.SC_NOT_FOUND)
                .extract().jsonPath().get("errorMessage");
    }

    public int deleteOneTestCase(String projectCode, String id) {
        return given()
                .when()
                .delete("/v1/case/" + projectCode + "/" + id)
                .getStatusCode();

    }

    public String deleteNonExistingTestCase(String projectCode, String id) {
        return given()
                .when()
                .delete("/v1/case/" + projectCode + "/" + id)
                .then()
                .statusCode(HttpStatus.SC_NOT_FOUND)
                .extract().jsonPath().get("errorMessage");
    }
}
