package softunigamestore.models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
@Getter
@Setter
@NoArgsConstructor
public class NewGameDto {

    private String title;
    private BigDecimal price;
    private double size;
    private String trailer;
    private String thubnailURL;
    private String description;
    private String releaseDate;

    public NewGameDto(String title, BigDecimal price, double size, String trailer, String thubnailURL, String description, String releaseDate) {
        this.title = title;
        this.price = price;
        this.size = size;
        this.trailer = trailer;
        this.thubnailURL = thubnailURL;
        this.description = description;
        this.releaseDate = releaseDate;
    }

    public String successfullySaved() {
        return "The game "+this.title+" saved successfully";
    }
}
