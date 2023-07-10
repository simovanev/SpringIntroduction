package softunigamestore.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@Entity
@Table
@Getter
@Setter
@AllArgsConstructor@NoArgsConstructor

public class Order  extends BaseEntity{
    @ManyToOne
    private User user;
    @ManyToMany(targetEntity = Game.class,fetch = FetchType.EAGER)
    private Set<Game> gameSet;

}
