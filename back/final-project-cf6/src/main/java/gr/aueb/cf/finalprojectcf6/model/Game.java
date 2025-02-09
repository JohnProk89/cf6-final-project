package gr.aueb.cf.finalprojectcf6.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "games")
public class Game {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long gameId;

    @Column(unique = true, nullable = false)
    private String name;

    @Column(unique = true, nullable = false)
    private String description;

//    @ManyToMany(mappedBy = "favouriteGames")
//    @JsonIgnore
//    Set<User> users;
}
