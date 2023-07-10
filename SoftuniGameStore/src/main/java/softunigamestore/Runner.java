package softunigamestore;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import softunigamestore.services.GameService;
import softunigamestore.services.UserService;

import java.util.Scanner;

import static softunigamestore.constants.Commands.*;

@Component
public class Runner implements CommandLineRunner {
    private static final Scanner SCANNER = new Scanner(System.in);
    private final UserService userService;
    private final GameService gameService;

    @Autowired
    public Runner(UserService userService, GameService gameService) {
        this.userService = userService;
        this.gameService = gameService;
    }

    @Override
    public void run(String... args) throws Exception {
        String input;
        while (!(input = SCANNER.nextLine()).equals(CLOSE)) {
            String[] arguments = input.split("\\|");
            String command = arguments[0];
            String output = switch (command) {
                case REGISTER_USER -> userService.registerUser(arguments);
                case LOG_IN_USER -> userService.logInUser(arguments);
                case LOGOUT -> userService.logout();
                case ADD_GAME -> gameService.addGame(arguments);
                case EDIT_GAME -> gameService.editGame(arguments);
                case DELETE_GAME -> gameService.deleteGame(arguments);
                default -> NO_COMMAND_FOUND;
            };
            System.out.println(output);

        }


    }
}
