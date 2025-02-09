package gr.aueb.cf.finalprojectcf6.controller;

import gr.aueb.cf.finalprojectcf6.model.User;
import gr.aueb.cf.finalprojectcf6.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping("/me")
    public ResponseEntity<User> authenticatedUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User currentUser = (User) authentication.getPrincipal();
        return ResponseEntity.ok(currentUser);
    }

    @GetMapping
    public String allUsers(Model model) {
        model.addAttribute("users", userService.showUsers());
        return "viewUsers";
    }

    @GetMapping("/angular")
    public ResponseEntity<List<User>> allUsers() {
        List <User> users = userService.showUsers();
        return ResponseEntity.ok(users);
    }

    @PutMapping("/update")
    public ResponseEntity<User> updateUser(@RequestBody User user) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User authenticatedUser = (User) authentication.getPrincipal();
        if (user.getId().equals(authenticatedUser.getId())) {
            return ResponseEntity.ok(userService.updateUser(user));
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable Long id) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User authenticatedUser = (User) authentication.getPrincipal();
        if (id.equals(authenticatedUser.getId())) {
            return ResponseEntity.ok(userService.deleteUser(id) + " self deleted");
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
    }

//    @PostMapping("/register")
//    public ResponseEntity<UserDTO> registerUser(@RequestBody UserDTO userDTO) {
//        System.out.println("Registering user: " + userDTO);
//        UserDTO savedUser = userService.registerUser(userDTO);
//        return ResponseEntity.ok(savedUser);
//    }

//    @GetMapping("/get/{id}")
//    public ResponseEntity<User> getUser(@PathVariable Long id) {
//        User presentedUser = userService.findById(id);
//        return ResponseEntity.ok(presentedUser);
//    }
//
//    @GetMapping("/get/all")
//    public ResponseEntity<List<User>> getAllUsers() {
//        List<User> users = userService.showUsers();
//        return ResponseEntity.ok(users);
//    }

//    @PostMapping("/login")
//    public ResponseEntity<String> loginUser(@RequestBody UserDTO userDTO) {
//        System.out.println("Logging user: " + userDTO);
//        UserDTO loggedUser = userService.loginUser(userDTO);
//        if (loggedUser == null) {
//            return ResponseEntity.notFound().build();
//        }
//        return ResponseEntity.ok("greetings: " + loggedUser.getUsername());
//    }

//    @PostMapping("/update")
//    public ResponseEntity<String> updateUser(@RequestBody User user) {
//        System.out.println("Updating user: " + user);
//        userService.updateUser(user);
//        return ResponseEntity.ok("User updated");
//    }
//
//    @DeleteMapping("/delete/{id}")
//    public ResponseEntity<String> deleteUser(@PathVariable Long id) {
//        System.out.println("Deleting user: " + id);
//        String username = userService.deleteUser(id);
//        return ResponseEntity.ok("User deleted: " + username);
//    }
//
//    @PostMapping("/favorgame")
//    public ResponseEntity<String> addFavouriteGame(@RequestBody GameFavorDTO gameFavorDTO) {
//        System.out.println("Adding favourite game: " + gameFavorDTO + " to a user, but which one?");
//        userService.favorGame(gameFavorDTO);
//        return ResponseEntity.ok("ok");
//    }
}
