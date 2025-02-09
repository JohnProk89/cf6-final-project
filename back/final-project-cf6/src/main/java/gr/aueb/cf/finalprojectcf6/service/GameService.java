package gr.aueb.cf.finalprojectcf6.service;

import gr.aueb.cf.finalprojectcf6.dto.GameDTO;
import gr.aueb.cf.finalprojectcf6.model.Game;
import gr.aueb.cf.finalprojectcf6.repository.GameRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class GameService {

    private final GameRepository gameRepository;

    public GameDTO addGame(GameDTO gameDTO) {
        Game game = mapGameDTOToGame(gameDTO);
        gameRepository.save(game);
        return gameDTO;
    }

    public GameDTO findById(Long id) {
        Optional<Game> gameOptional = gameRepository.findById(id);
        return mapGameToGameDTO(gameOptional.get());
    }

    public Set<Game> findAll() {
        Set<Game> games = new HashSet<>();
        games.addAll(gameRepository.findAll());
        return games;
    }

    public Game update(Game game){
        return gameRepository.save(game);
    }

    public void deleteById(Long id) {
        gameRepository.deleteById(id);
    }

    public Game mapGameDTOToGame(GameDTO gameDTO) {
        Game game = new Game();
        game.setName(gameDTO.getName());
        game.setDescription(gameDTO.getDescription());
        return game;
    }

    public GameDTO mapGameToGameDTO(Game game) {
        GameDTO gameDTO = new GameDTO();
        gameDTO.setName(game.getName());
        gameDTO.setDescription(game.getDescription());
        return gameDTO;
    }
}
