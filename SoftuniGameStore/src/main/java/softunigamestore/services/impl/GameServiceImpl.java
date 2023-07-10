package softunigamestore.services.impl;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import softunigamestore.entities.Game;
import softunigamestore.models.NewGameDto;
import softunigamestore.repositories.GameRepository;
import softunigamestore.services.GameService;
import softunigamestore.services.UserService;

import java.math.BigDecimal;

@Service
public class GameServiceImpl implements GameService {
    private ModelMapper mapper;
    private GameRepository repository;
    private UserService userService;

    public GameServiceImpl(GameRepository repository, UserService userService) {
        this.repository = repository;
        this.userService = userService;
        this.mapper= new ModelMapper();
    }

    @Override
    public String addGame(String[] args) {
        if (!userService.isUserAdmin()){
            return "The user is not Administrator";
        }

        String title = args.length > 1 ? args[1] : "";
        BigDecimal price = args.length > 2 ?  new BigDecimal(args[2]) : BigDecimal.ZERO;
        double size = args.length > 3 ?Double.parseDouble( args[3]) : 0.0;
        String trailer = args.length > 4 ? args[4] : "";
        String thubnailURL = args.length > 5 ? args[5] : "";
        String description = args.length > 6 ? args[6] : "";
        String releaseDate = args.length > 7 ? args[7] : "";
        NewGameDto newGame= new NewGameDto(title,price,size,trailer,thubnailURL,description,releaseDate);

        Game game= mapper.map(newGame, Game.class);
        Game savedGame = repository.saveAndFlush(game);
        return newGame.successfullySaved();
    }

    @Override
    public String editGame(String[] args) {
        return null;
    }

    @Override
    public String deleteGame(String[] args) {
        return null;
    }
}
