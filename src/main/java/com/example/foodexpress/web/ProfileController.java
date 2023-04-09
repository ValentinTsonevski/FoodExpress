package com.example.foodexpress.web;


import com.example.foodexpress.domain.dtos.user.UserDto;
import com.example.foodexpress.domain.dtos.user.UserProfileView;
import com.example.foodexpress.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.security.Principal;

import static com.example.foodexpress.outputs.SuccessMessages.UPDATE_PROFILE;

@Controller
@RequestMapping("/users")
public class ProfileController {

    private final UserService userService;

    @Autowired
    public ProfileController( UserService userService) {

        this.userService = userService;
    }

    @GetMapping("/profile")
    public String profile(Principal principal, Model model) {
        UserDto user = userService.getUserByEmail(principal.getName());
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
    public String updateProfilePage(@ModelAttribute("user") UserDto user, Principal principal, RedirectAttributes redirectAttributes) {
        String email = principal.getName();
        UserDto currentUser = this.userService.getUserByEmail(email);

        currentUser.setUsername(user.getUsername());
        currentUser.setEmail(user.getEmail());

        this.userService.saveUser(currentUser);
        redirectAttributes.addFlashAttribute("successMessage",
                user.getUsername() + UPDATE_PROFILE);
        return "redirect:/users/profile";

    }




}
