package gr.aueb.cf.finalprojectcf6.controller;

import gr.aueb.cf.finalprojectcf6.dto.GameDTO;
import gr.aueb.cf.finalprojectcf6.model.Game;
import gr.aueb.cf.finalprojectcf6.repository.GameRepository;
import gr.aueb.cf.finalprojectcf6.service.GameService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@RequestMapping("/game")
@RequiredArgsConstructor
public class GameController {

    private final GameRepository gameRepository;
    private final GameService gameService;

    @PostMapping("/register")
    public ResponseEntity<GameDTO> addGame(@RequestBody GameDTO gameDTO) {
        System.out.println("Saving  game: " + gameDTO);
        GameDTO game = gameService.addGame(gameDTO);
        return ResponseEntity.ok(game);
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<GameDTO> getGame(@PathVariable Long id) {
        GameDTO gameDTO = gameService.findById(id);
        return ResponseEntity.ok(gameDTO);
    }

    @GetMapping("/get/all")
    public ResponseEntity<Set<Game>> getAllGames() {
        Set<Game> games = gameService.findAll();
        return ResponseEntity.ok(games);
    }

    @PostMapping("/update")
    public ResponseEntity<Game> updateGame(@RequestBody Game game) {
        game = gameService.update(game);
        return ResponseEntity.ok(game);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteGame(@PathVariable Long id) {
        gameService.deleteById(id);
        return ResponseEntity.ok("ok");
    }
}
