import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        UserDAO userDAO = new UserDAO();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\nCRUD Application Menu:");
            System.out.println("1. Create User");
            System.out.println("2. Read All Users");
            System.out.println("3. Update User");
            System.out.println("4. Delete User");
            System.out.println("5. Get User By ID");
            System.out.println("6. Exit");

            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1: // Create
                    System.out.println("Enter name:");
                    String name = scanner.nextLine();
                    System.out.println("Enter email:");
                    String email = scanner.nextLine();
                    userDAO.addUser(new UserModel(name, email));
                    System.out.println("User created successfully.");
                    break;

                case 2: // Read
                    List<UserModel> userModels = userDAO.getAllUsers();
                    System.out.println("List of Users:");
                    for (UserModel userModel : userModels) {
                        System.out.println(userModel);
                    }
                    break;

                case 3: // Update
                    System.out.println("Enter user ID to update:");
                    int updateId = scanner.nextInt();
                    scanner.nextLine(); // Consume newline
                    UserModel userModelToUpdate = userDAO.getUserById(updateId);
                    if (userModelToUpdate != null) {
                        System.out.println("Enter new name:");
                        String newName = scanner.nextLine();
                        System.out.println("Enter new email:");
                        String newEmail = scanner.nextLine();
                        userModelToUpdate.setName(newName);
                        userModelToUpdate.setEmail(newEmail);
                        userDAO.updateUser(userModelToUpdate);
                        System.out.println("User updated successfully.");
                    } else {
                        System.out.println("User not found.");
                    }
                    break;

                case 4: // Delete
                    System.out.println("Enter user ID to delete:");
                    int deleteId = scanner.nextInt();
                    userDAO.deleteUser(deleteId);
                    System.out.println("User deleted successfully.");
                    break;

                case 5: // Get by ID
                    System.out.println("Enter user ID:");
                    int userId = scanner.nextInt();
                    UserModel userModel = userDAO.getUserById(userId);
                    if (userModel != null) {
                        System.out.println(userModel);
                    } else {
                        System.out.println("User not found.");
                    }
                    break;

                case 6: // Exit
                    System.out.println("Exiting...");
                    scanner.close();
                    System.exit(0);

                default:
                    System.out.println("Invalid choice. Try again.");
            }
        }
    }
}
