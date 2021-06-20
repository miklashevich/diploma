package dao;

import enums.AccessType;
import models.project.Project;
import services.DatabaseConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProjectDaoImplementation implements ProjectDao{

    static Connection con = DatabaseConnection.getConnection();

    @Override
    public int add(Project project) throws SQLException {

        String query = "INSERT INTO projects (title, code, description, access, groupAccess)  VALUES (?, ?, ?, ?, ?)";

        PreparedStatement ps = con.prepareStatement(query);
        ps.setString(1, project.getTitle());
        ps.setString(2, project.getCode());
        ps.setString(3, project.getDescription());
        ps.setString(4, project.getAccess().toString().toLowerCase());
        ps.setString(5, project.getGroupAccess());

        return ps.executeUpdate();
    }

    @Override
    public Project getProject(String projectCode) throws SQLException {

        String query = "SELECT * FROM projects WHERE code = ?;";

        PreparedStatement ps = con.prepareStatement(query);

        ps.setString(1, projectCode);
        ResultSet rs = ps.executeQuery();

        boolean check = false;
        Project project = Project.builder().build();

        while (rs.next()) {
            check = true;
            project.setId(rs.getInt("id"));
            project.setTitle(rs.getString("title"));
            project.setCode(rs.getString("code"));
            project.setDescription(rs.getString("description"));
            project.setAccess(AccessType.getEnumByValue(rs.getString("access").toLowerCase()));
            project.setGroupAccess(rs.getString("groupAccess"));
        }

        return check ? project : null;
    }

    @Override
    public int delete(String projectCode) throws SQLException {
        String query =
                "DELETE FROM projects " +
                        "WHERE code = ?";

        PreparedStatement ps = con.prepareStatement(query);
        ps.setString(1, projectCode);

        int result = ps.executeUpdate();

        if(result > 0) {
            return result;
        } else {
            throw new SQLException();
        }
    }

    @Override
    public int update(Project project) throws SQLException {
        return 0;
    }

    @Override
    public List<Project> getProjects() throws SQLException {
        List<Project> projectList = new ArrayList<>();
        return projectList;
    }

    @Override
    public void create() throws SQLException {
    }

    @Override
    public void drop() throws SQLException {
        String query = "DROP TABLE IF EXISTS projects CASCADE";

        PreparedStatement ps = con.prepareStatement(query);
        ps.execute();
    }
}
