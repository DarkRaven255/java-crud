package pl.kdulemba.javacrud.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.kdulemba.javacrud.entities.User;
import pl.kdulemba.javacrud.repositories.UserRepository;

@RestController
public class UserController {

    private final UserRepository userRepository;

    @Autowired
    public UserController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @GetMapping("/hello")
    public String hw() {
        return "Hello World!";
    }

    @GetMapping("/getAll")
    public String getUserList(Model model) {
        model.addAttribute("users", userRepository.findAll());
        return "index";
    }

    @PostMapping("/addUser")
    public String addUser(User user) {
        userRepository.save(user);
        return "index";
    }

}
