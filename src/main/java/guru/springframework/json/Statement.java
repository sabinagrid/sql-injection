package guru.springframework.json;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Statement {
    public static void executeStatement(String userInput) {
        String query = "SELECT * FROM users WHERE name = '" + userInput + "'";

        try (Connection connection = DatabaseConnection.getConnection();
             java.sql.Statement statement = connection.createStatement()) {

            ResultSet resultSet = statement.executeQuery(query);

            while (resultSet.next()) {
                System.out.println("User ID: " + resultSet.getInt("id"));
                System.out.println("User Name: " + resultSet.getString("name"));
                System.out.println("Email: " + resultSet.getString("email"));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
