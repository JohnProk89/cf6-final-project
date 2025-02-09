package gr.aueb.cf.finalprojectcf6.controller;

import gr.aueb.cf.finalprojectcf6.dto.UserDTO;
import gr.aueb.cf.finalprojectcf6.model.User;
import gr.aueb.cf.finalprojectcf6.responses.LoginResponse;
import gr.aueb.cf.finalprojectcf6.service.AuthenticationService;
import gr.aueb.cf.finalprojectcf6.service.JwtService;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

@RequestMapping("/auth")
@Controller
@RequiredArgsConstructor
public class AuthenticationController {

    private final JwtService jwtService;
    private final AuthenticationService authenticationService;

    @GetMapping("/signup")
    public String signupView(Model model) {
        model.addAttribute("user", new User());
        return "signup";
    }

    @PostMapping("/signup")
    public RedirectView signup(@ModelAttribute("user") UserDTO userdto, RedirectAttributes redirectAttributes) {
        final RedirectView redirectView = new RedirectView("/auth/signup", true);
        User signedUpUser = authenticationService.signup(userdto);
        redirectAttributes.addFlashAttribute("signedUpUser", signedUpUser);
        redirectAttributes.addFlashAttribute("signUpUserSuccess", true);
        return redirectView;
    }

    @PostMapping("/angular/signup")
    public ResponseEntity<User> register(@RequestBody UserDTO UserDto) {
        User registeredUser = authenticationService.signup(UserDto);

        return ResponseEntity.ok(registeredUser);
    }

    @GetMapping("/login")
    public ModelAndView loginView(ModelAndView modelAndView) {
        modelAndView.addObject("user", new User());
        modelAndView.setViewName("login");
        return modelAndView;
    }

    @PostMapping("/login")
    public RedirectView login(@ModelAttribute("user") UserDTO userdto,
                              RedirectAttributes redirectAttributes, HttpServletResponse response) {
        final RedirectView redirectView = new RedirectView("/auth/login", true);
        User loggedInUser = authenticationService.authenticate(userdto);
        redirectAttributes.addFlashAttribute("loggedInUser", loggedInUser);
        redirectAttributes.addFlashAttribute("loginUserSuccess", true);
        String jwtToken = jwtService.generateToken(loggedInUser);
        Cookie cookie = new Cookie("JWT","Bearer" + jwtToken);
        cookie.setPath("/");
        response.addCookie(cookie);
        return redirectView;
    }

    @PostMapping("/angular/login")
    public ResponseEntity<LoginResponse> authenticate(@RequestBody UserDTO UserDto, HttpServletResponse response) {
        User authenticatedUser = authenticationService.authenticate(UserDto);
        String jwtToken = jwtService.generateToken(authenticatedUser);
        Cookie cookie = new Cookie("JWT","Bearer" + jwtToken);
        cookie.setPath("/");
        response.addCookie(cookie);
        LoginResponse loginResponse = new LoginResponse();
        loginResponse.setToken(jwtToken);
        loginResponse.setExpiresIn(jwtService.getExpirationTime());
        return ResponseEntity.ok(loginResponse);
    }
}