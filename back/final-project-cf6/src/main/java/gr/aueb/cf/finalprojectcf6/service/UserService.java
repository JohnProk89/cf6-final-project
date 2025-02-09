package gr.aueb.cf.finalprojectcf6.service;

import gr.aueb.cf.finalprojectcf6.dto.GameFavorDTO;
import gr.aueb.cf.finalprojectcf6.dto.UserDTO;
import gr.aueb.cf.finalprojectcf6.model.Game;
import gr.aueb.cf.finalprojectcf6.model.User;
import gr.aueb.cf.finalprojectcf6.repository.GameRepository;
import gr.aueb.cf.finalprojectcf6.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final GameRepository gameRepository;

    public UserDTO registerUser(UserDTO userDTO) {
            User user = mapUserDTOToUser(userDTO);
            userRepository.save(user);
            return userDTO;
    }

    public User findById(Long userId) {
        Optional<User> optionalUser = userRepository.findById(userId);
        if (optionalUser.isEmpty()) {
            return new User();
        }
        return optionalUser.get();
    }

    public List<User> showUsers() {
        List<User> users = new ArrayList<>();
        users.addAll(userRepository.findAll());
        System.out.println("size" + users.size());
        return users;
    }

    public UserDTO loginUser(UserDTO userDTO) {
        User user = mapUserDTOToUser(userDTO);
        Optional<User> loggedUser = userRepository.findByUsernameAndPassword(user.getUsername(), user.getPassword());
        if (loggedUser.isPresent()) {
            return mapUserToUserDTO(loggedUser.get());
        }
        return null;
    }

    public User updateUser(User user) {
        return userRepository.save(user);
    }

    public String deleteUser(Long id) {
        Optional<User> optionalUser = userRepository.findById(id);
        if (optionalUser.isEmpty()) {
            return "user not found";
        }
        userRepository.delete(optionalUser.get());
        return optionalUser.get().getUsername();
    }

    @Transactional
    public void favorGame(GameFavorDTO gameFavorDTO) {
        Optional<User> userOptional = userRepository.findById(gameFavorDTO.getUserId());
        if (userOptional.isEmpty()) {
            return;
        }
        User user = userOptional.get();
        Optional<Game> gameOptional = gameRepository.findById(gameFavorDTO.getGameId());
        if (gameOptional.isEmpty()) {
            return;
        }
        Game game = gameOptional.get();
//        user.getFavouriteGames().add(game);
    }

    public User mapUserDTOToUser(UserDTO userDTO) {
        User user = new User();
        user.setUsername(userDTO.getUsername());
        user.setPassword(userDTO.getPassword());
        return user;
    }

    public UserDTO mapUserToUserDTO(User user) {
        UserDTO userDTO = new UserDTO();
        userDTO.setUsername(user.getUsername());
        userDTO.setPassword(user.getPassword());
        return userDTO;
    }
}
