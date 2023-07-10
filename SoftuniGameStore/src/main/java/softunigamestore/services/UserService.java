package softunigamestore.services;

public interface UserService {
    String registerUser(String[] args);
    String logInUser(String[] args);
    String logout();

    boolean isUserAdmin();
}
