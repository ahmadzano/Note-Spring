package us.satis.notes.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import us.satis.notes.model.HttpResponse;
import us.satis.notes.model.User;
import us.satis.notes.repository.UserRepository;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/all")
    public @ResponseBody
    Iterable<User> getAllUsers() {

        return userRepository.findAll();
    }

    @PostMapping("/find")
    public @ResponseBody List<User> getUserByUsernameAndPassword(
            @RequestParam(required = true) String username,
            @RequestParam(required = true) String password) {

        return userRepository.findByUsernameAndPassword(username, password);
    }

    @GetMapping("/{id}")
    public @ResponseBody User getUserById(@RequestParam int id) {

        return userRepository.findOne(id);
    }

    @PostMapping("/")
    public @ResponseBody
    ModelMap addUser(@RequestParam User user) {
        ModelMap viewModel = new ModelMap();

        if (user.getUsername() == null || user.getPassword() == null) {
            viewModel.addAttribute("status", HttpResponse.STATUS.error);
            viewModel.addAttribute("error_message", "Both of username and password must be set.");
        } else {

            try {
                userRepository.save(user);
                viewModel.addAttribute("status", HttpResponse.STATUS.ok);
                viewModel.addAttribute("item", user);
            } catch (Exception error) {
                viewModel.addAttribute("status", HttpResponse.STATUS.error);
                viewModel.addAttribute("item", error.getMessage());
            }
        }

        return viewModel;
    }
}
