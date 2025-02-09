package gr.aueb.cf.finalprojectcf6.repository;

import gr.aueb.cf.finalprojectcf6.model.Game;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GameRepository extends JpaRepository<Game, Long> {
}
