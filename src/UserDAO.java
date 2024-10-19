import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDAO {
    // Update the PostgreSQL connection URL, username, and password
    private final String jdbcURL = "jdbc:postgresql://localhost:5432/crud_db";
    private final String jdbcUsername = "";  // Replace with your PostgreSQL username
    private final String jdbcPassword = "";  // Replace with your PostgreSQL password

    // Load PostgreSQL JDBC Driver and Get Connection
    private Connection getConnection() throws SQLException {
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return DriverManager.getConnection(jdbcURL, jdbcUsername, jdbcPassword);
    }

    // CREATE: Add a new user to the database
    public void addUser(UserModel userModel) {
        String sql = "INSERT INTO users (name, email) VALUES (?, ?)";
        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, userModel.getName());
            stmt.setString(2, userModel.getEmail());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // READ: Get all users from the database
    public List<UserModel> getAllUsers() {
        List<UserModel> userModels = new ArrayList<>();
        String sql = "SELECT * FROM users";
        try (Connection conn = getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                String email = rs.getString("email");
                userModels.add(new UserModel(id, name, email));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return userModels;
    }

    // UPDATE: Update an existing user in the database
    public void updateUser(UserModel userModel) {
        String sql = "UPDATE users SET name = ?, email = ? WHERE id = ?";
        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, userModel.getName());
            stmt.setString(2, userModel.getEmail());
            stmt.setInt(3, userModel.getId());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // DELETE: Remove a user from the database
    public void deleteUser(int userId) {
        String sql = "DELETE FROM users WHERE id = ?";
        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, userId);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // READ: Get a user by ID
    public UserModel getUserById(int userId) {
        UserModel userModel = null;
        String sql = "SELECT * FROM users WHERE id = ?";
        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, userId);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                String name = rs.getString("name");
                String email = rs.getString("email");
                userModel = new UserModel(userId, name, email);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return userModel;
    }
}
