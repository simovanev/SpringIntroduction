package softunigamestore.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import softunigamestore.entities.User;

import java.util.Optional;

@Repository
public interface userRepository extends JpaRepository<User,Long> {

    Optional<User> findByEmail(String email);
}
