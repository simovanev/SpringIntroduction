package softunigamestore.services.impl;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import softunigamestore.entities.User;
import softunigamestore.models.RegisterUserDto;
import softunigamestore.models.UserToBeLoggedDto;
import softunigamestore.repositories.userRepository;
import softunigamestore.services.UserService;

import java.util.Optional;

import static softunigamestore.constants.ErrorMessages.EMAIL_ALREADY_EXIST;
import static softunigamestore.constants.ErrorMessages.NO_LOGGED_USER;

@Service
public class UserServiceImpl implements UserService {
    private User loggedInUser;
    private userRepository userRepository;
    private ModelMapper modelMapper;

    @Autowired
    public UserServiceImpl(userRepository repository) {

        this.userRepository = repository;
        this.modelMapper = new ModelMapper();
    }

    @Override
    public String registerUser(String[] args) {

        String email = args.length > 1 ? args[1] : "";
        String password = args.length > 2 ? args[2] : "";
        String confirmPassword = args.length > 3 ? args[3] : "";
        String fullName = args.length > 4 ? args[4] : "";
        RegisterUserDto registerUserDto = null;
        try {
            registerUserDto = new RegisterUserDto(email, password, confirmPassword, fullName);
        } catch (IllegalAccessException e) {
            System.out.println(e.getMessage());
        }
        if (this.userRepository.findByEmail(registerUserDto.getEmail()).isPresent()) {
            return EMAIL_ALREADY_EXIST;
        }
        ;
        User user = modelMapper.map(registerUserDto, User.class);
        if (userRepository.count() == 0) {
            user.setAdmin(true);
        }

        userRepository.saveAndFlush(user);
        return registerUserDto.successfullyRegisteredUser();
    }

    @Override
    public String logInUser(String[] args) {
        if (this.loggedInUser != null) return "User is already logged.";

        String email = args.length > 1 ? args[1] : "";
        String password = args.length > 2 ? args[2] : "";

        Optional<User> userToBeLogged = this.userRepository.findByEmail(email);
        if (userToBeLogged.isEmpty()) {
            return "No such user";
        }
        UserToBeLoggedDto userToBeLoggedDto = new UserToBeLoggedDto(email, password);
        if (!userToBeLoggedDto.getPassword().equals(userToBeLogged.get().getPassword())) {
            return "Incorrect password";
        }
        this.loggedInUser = userToBeLogged.get();

        return userToBeLoggedDto.successfullyLogged();

    }

    @Override
    public String logout() {
        if (loggedInUser==null){
            return NO_LOGGED_USER;
        }
        UserToBeLoggedDto logoutUserDto = modelMapper.map(loggedInUser, UserToBeLoggedDto.class);
        this.loggedInUser = null;
        return logoutUserDto.successfullyLoggedOut();
    }

    @Override
    public boolean isUserAdmin() {

        return loggedInUser!=null && loggedInUser.isAdmin();
    }
}
