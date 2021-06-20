package helpers.project;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.parsing.Parser;
import io.restassured.response.Response;
import lombok.extern.slf4j.Slf4j;
import models.project.GetProjectResponse;
import models.project.Project;
import org.apache.http.HttpStatus;
//import org.apache.log4j.Logger;

import java.io.File;
import java.util.Map;

import static io.restassured.RestAssured.given;

@Slf4j
public class ProjectHelper {
//    static Logger logger = Logger.getLogger(ProjectHelper.class);
    private Gson gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create();

    public int getAllProjects() {

        return given()
                .when()
                .get("/v1/project")
                .getStatusCode();
    }

    public Response createNewProject(File file) {
        RestAssured.defaultParser = Parser.JSON;
        return given()
                .contentType(ContentType.JSON)
                .body(file)
                .post("/v1/project")
                .then()
                .contentType(ContentType.JSON)
                .extract()
                .response();
    }

    public Response getProject(String projectCode) {
        return given()
                .when()
                .get("/v1/project/" + projectCode)
                .then()
                .extract()
                .response();

    }

    public String deleteProject(String projectCode) {
        return given()
                .when()
                .delete("/v1/project/" + projectCode)
                .then()
                .extract()
                .jsonPath()
                .get("status").toString();
    }

    public String getNonExistingProject(String projectCode) {
        return given()
                .when()
                .get("/v1/project/" + projectCode)
                .then()
                .statusCode(HttpStatus.SC_NOT_FOUND)
                .extract().jsonPath().get("errorMessage");
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
