package demo.Database;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

@RestController
@RequestMapping("/api/jdbc")
public class Jdbc {

    String dbUrl = "jdbc:postgresql://ep-blue-king-ad4eyntd-pooler.c-2.us-east-1.aws.neon.tech:5432/neondb?sslmode=require";
    String username = "neondb_owner";
    String password = "npg_VRIbkYcht10l";

    @GetMapping("")
    public String jdbcTest() {
        StringBuilder result = new StringBuilder();
        try {
            // optional with JDBC 4+
            Class.forName("org.postgresql.Driver");

            Connection connection = DriverManager.getConnection(dbUrl, username, password);
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery("SELECT * FROM users;");
            System.out.println(rs.toString());
            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                String email = rs.getString("email");

                result.append("ID: ").append(id)
                        .append(", Name: ").append(name)
                        .append(", Email: ").append(email)
                        .append("<br>");
            }

            rs.close();
            statement.close();
            connection.close();

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return result.toString();
    }
}
