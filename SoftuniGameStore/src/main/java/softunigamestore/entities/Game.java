package softunigamestore.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Game  extends BaseEntity{
    @Column(nullable = false)
        private String title;
    @Column
    private String trailer;
    @Column
    private String imageThumbnail;
    @Column
    private double size;
    @Column
    private BigDecimal price;
    @Column
    private String description;
    @Column
    private LocalDate releaseDate;
}
