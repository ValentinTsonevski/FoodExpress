package com.example.foodexpress.web;

import com.example.foodexpress.domain.dtos.view.AllUsersViewDto;
import com.example.foodexpress.domain.entity.UserEntity;
import com.example.foodexpress.domain.entity.UserRoleEntity;
import com.example.foodexpress.domain.enums.UserRoleEnum;
import com.example.foodexpress.service.UserService;
import com.example.foodexpress.service.impl.UserRoleServiceImpl;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/admin")
public class AdminController {
    private final UserService userService;
    private final UserRoleServiceImpl roleService;

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
        public String makeAdmin(@RequestParam("username") String username) {
            Optional<UserEntity> userOptional = this.userService.findUserByUsername(username);
            if (userOptional.isEmpty()) {
                return "redirect:/admin/users/all";
            }

            UserEntity user = userOptional.get();
            if (user.getRoles().stream().anyMatch(roleEntity -> roleEntity.getRole() == UserRoleEnum.ADMIN)) {
                return "redirect:/admin/users/all";
            }

            UserRoleEntity adminRoleEntity = this.roleService.findRoleByRoleName(UserRoleEnum.ADMIN);
            user.getRoles().add(adminRoleEntity);
            this.userService.saveUser(user);

            return "redirect:/admin/users/all";
        }

}
