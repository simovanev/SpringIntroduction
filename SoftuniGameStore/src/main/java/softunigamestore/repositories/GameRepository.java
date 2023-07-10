package softunigamestore.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import softunigamestore.entities.Game;
@Repository
public interface GameRepository extends JpaRepository<Game,Long>{
}
