package guru.springframework.json;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

public class PreparedStatement {
    public static void executePreparedStatement(String userInput) {
        String query = "SELECT * FROM users WHERE name = ?";

        try (Connection connection = DatabaseConnection.getConnection();
             java.sql.PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setString(1, userInput);
            ResultSet resultSet = preparedStatement.executeQuery();

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
