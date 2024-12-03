package guru.springframework.json;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class Main {

    public static void main(String[] args) {
        try (Connection connection = DatabaseConnection.getConnection()) {
            testStatement(connection, "' OR '1'='1");

            testPreparedStatement(connection, "' OR '1'='1");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void testStatement(Connection connection, String userInput) {
        try {
            String query = "SELECT * FROM users WHERE name = '" + userInput + "'";
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(query);

            System.out.println("Results using Statement (Vulnerable):");
            while (rs.next()) {
                System.out.println("User: " + rs.getString("name"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void testPreparedStatement(Connection connection, String userInput) {
        try {
            String query = "SELECT * FROM users WHERE name = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, userInput);
            ResultSet rs = preparedStatement.executeQuery();

            System.out.println("Results using PreparedStatement (Secure):");
            while (rs.next()) {
                System.out.println("User: " + rs.getString("name"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
