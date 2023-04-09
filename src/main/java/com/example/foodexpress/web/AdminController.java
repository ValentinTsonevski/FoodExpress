package com.example.foodexpress.web;

import com.example.foodexpress.domain.dtos.user.AllUsersViewDto;
import com.example.foodexpress.domain.dtos.user.UserDto;
import com.example.foodexpress.domain.dtos.user.UserRoleModelDto;
import com.example.foodexpress.domain.enums.UserRoleEnum;
import com.example.foodexpress.service.UserService;
import com.example.foodexpress.service.impl.UserRoleServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;
import java.util.Optional;

import static com.example.foodexpress.outputs.SuccessMessages.NEW_ADMIN;

@Controller
@RequestMapping("/admin")
public class AdminController {
    private final UserService userService;
    private final UserRoleServiceImpl roleService;

    @Autowired
    public AdminController(UserService userService, UserRoleServiceImpl roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }


        @GetMapping("/users/all")
        public String getAllUsers(Model model) {
            List<AllUsersViewDto> users = this.userService.getAllUsers();

            model.addAttribute("users", users);
            return "all-users";
        }


        @PostMapping("/users/make-admin")
        public String makeAdmin(@RequestParam("username") String username, RedirectAttributes redirectAttributes) {

            Optional<UserDto> userOptional = this.userService.findUserByUsername(username);
            if (userOptional.isEmpty()) {
                return "redirect:/admin/users/all";
            }

            UserDto user = userOptional.get();
            if (user.getRoles().stream().anyMatch(roles -> roles.getRole().equals(String.valueOf(UserRoleEnum.ADMIN)))) {
                return "redirect:/admin/users/all";
            }

            UserRoleModelDto adminRoleEntity = this.roleService.findRoleByRoleName(UserRoleEnum.ADMIN);
            user.getRoles().add(adminRoleEntity);
            this.userService.saveUser(user);

            redirectAttributes.addFlashAttribute("successMessage", user.getUsername() + NEW_ADMIN);
            return "redirect:/admin/users/all";
        }

}
