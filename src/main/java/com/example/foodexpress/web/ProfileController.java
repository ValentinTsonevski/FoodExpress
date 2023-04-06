package com.example.foodexpress.web;


import com.example.foodexpress.domain.dtos.view.UserProfileView;
import com.example.foodexpress.domain.entity.UserEntity;
import com.example.foodexpress.repository.UserRepository;
import com.example.foodexpress.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;

@Controller
@RequestMapping("/users")
public class ProfileController {

    private final UserRepository userRepository;
    private final UserService userService;

    public ProfileController(UserRepository userRepository, UserService userService) {
        this.userRepository = userRepository;
        this.userService = userService;
    }

    @GetMapping("/profile")
    public String profile(Principal principal, Model model) {
        UserEntity user = userService.getUserByEmail(principal.getName());
        UserProfileView profileView = new UserProfileView()
                .setFirstName(user.getFirstName())
                .setLastName(user.getLastName())
                .setUsername(user.getUsername())
                .setEmail(user.getEmail())
                .setAddress(user.getAddress());


        model.addAttribute("user", profileView);
        return "profile";
    }

    @PostMapping("/profile")
    public String updateProfilePage(@ModelAttribute("user") UserEntity user, Principal principal) {
        String email = principal.getName();
        UserEntity currentUser = this.userService.getUserByEmail(email);

        currentUser.setUsername(user.getUsername());
        currentUser.setEmail(user.getEmail());

        this.userRepository.save(currentUser);

        return "redirect:/users/profile";

    }


}
