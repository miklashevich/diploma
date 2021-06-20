package tests.sqlTests;

import baseEntities.BaseTest;
import dao.ProjectDaoImplementation;
import lombok.extern.slf4j.Slf4j;
import models.project.Project;
import org.testng.annotations.Test;
import services.JdbcService;
import testData.StaticProvider;

import java.sql.ResultSet;
import java.sql.SQLException;

@Slf4j
public class SQLTests extends BaseTest {

    @Test
    public void connectionTest1() throws SQLException {
        JdbcService jdbcService = new JdbcService();
        ResultSet resultSet = jdbcService.executeQuery("select * from projects");

        while(resultSet.next()) {
            String id = resultSet.getString("id");
            String title = resultSet.getString("title");
            String code = resultSet.getString("code");
            String access = resultSet.getString("access");
            String groupAccess = resultSet.getString("groupAccess");

            log.info("id: " + id + ", title: " + title + ", code: " + code + ", access: " + access + ", group: " + groupAccess);
        }
        jdbcService.closeConnection();
    }

    @Test
    public void getProjectTest() throws SQLException {
        ProjectDaoImplementation prDao = new ProjectDaoImplementation();

        Project project = prDao.getProject("TPO");
        System.out.println(project.toString());
    }

    @Test(dataProviderClass = StaticProvider.class, dataProvider = "Create project for DB")
    public void addProjectTest(Project project) throws SQLException {
        ProjectDaoImplementation prDao = new ProjectDaoImplementation();
        System.out.println(prDao.add(project));

        prDao.getProject(project.getCode());
        System.out.println(project);

        prDao.delete(project.getCode());
    }
}

