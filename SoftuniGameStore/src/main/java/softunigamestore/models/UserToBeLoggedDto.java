package softunigamestore.models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class UserToBeLoggedDto {
    private String email;
    private String password;

    public UserToBeLoggedDto(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public String successfullyLogged() {
        return "User " + this.email + " successfully logged";
    }

    public String successfullyLoggedOut() {
        return "User " + this.email + " successfully logged out";
    }
}
