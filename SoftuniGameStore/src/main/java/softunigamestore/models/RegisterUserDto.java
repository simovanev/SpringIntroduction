package softunigamestore.models;

import lombok.Getter;
import lombok.Setter;

import java.util.regex.Pattern;

import static softunigamestore.constants.ErrorMessages.*;
import static softunigamestore.constants.Patterns.EMAIL_PATTERN;
import static softunigamestore.constants.Patterns.PASSWORD_PATTERN;
@Getter
@Setter
public class RegisterUserDto {

    private String email;
    private String password;
    private String confirmPassword;
    private String fullName;

    public RegisterUserDto(String email, String password, String confirmPassword, String fullName) throws IllegalAccessException {
        this.email = email;
        this.password = password;
        this.confirmPassword = confirmPassword;
        this.fullName = fullName;
        validate();
    }

    private void validate() throws IllegalAccessException {
        if (!Pattern.matches(EMAIL_PATTERN, this.email)) {
            throw new IllegalAccessException(INVALID_EMAIL);
        }
        if (!Pattern.matches(PASSWORD_PATTERN, this.password)) {
            throw new IllegalAccessException(INVALID_PASSWORD);
        }
        if (!this.password.equals(this.confirmPassword)) {
            throw new IllegalAccessException(PASSWORDS_MISS_MATCH);
        }


    }
    public String successfullyRegisteredUser() {
        return fullName + " was registered.";
    }
}
