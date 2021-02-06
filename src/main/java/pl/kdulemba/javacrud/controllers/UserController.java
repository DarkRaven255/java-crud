package pl.kdulemba.javacrud.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.server.ResponseStatusException;
import pl.kdulemba.javacrud.entities.User;
import pl.kdulemba.javacrud.repositories.UserRepository;

import javax.validation.Valid;


@Controller
public class UserController {

    private final UserRepository userRepository;

    @Autowired
    public UserController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @GetMapping("/")
    public String getUsers(Model model) {
        model.addAttribute("users", userRepository.findAll());
        return "index";
    }

    @GetMapping("/adduser")
    public String addUserForm(@Valid User user, Model model) {
        model.addAttribute("users", userRepository.findAll());
        return "add-user";
    }

    @PostMapping("/add")
    public String addUser(@Valid User user, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "add-user";
        }

        userRepository.save(user);
        return "redirect:/";
    }

    @GetMapping("/edit/{id}")
    public String editUserForm(@PathVariable("id") long id, Model model) {
        try {
            model.addAttribute("users", userRepository.findAll());
            User user = userRepository.findById(id);

            if (user == null) {
                throw new IllegalArgumentException("User id does not exist: " + id);
            }
            model.addAttribute("user", user);

            return "update-user";
        } catch (Exception exc) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "User Not Found", exc);
        }

    }

    @PostMapping("/update/{id}")
    public String updateUser(@PathVariable("id") long id, @Valid User user, BindingResult result, Model model) {
        if (result.hasErrors()) {
            user.setId(id);
            return "update-user";
        }

        userRepository.save(user);

        return "redirect:/";
    }

    @GetMapping("/delete/{id}")
    public String deleteUser(@PathVariable("id") long id, Model model) {
        try {
            User user = userRepository.findById(id);

            if (user == null) {
                throw new IllegalArgumentException("User id does not exist: " + id);
            }

            userRepository.delete(user);
            return "redirect:/";

        } catch (Exception exc) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "User Not Found", exc);
        }
    }
}
