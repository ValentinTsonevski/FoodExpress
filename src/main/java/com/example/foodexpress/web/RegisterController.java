package com.example.foodexpress.web;

import com.example.foodexpress.domain.dtos.banding.UserRegisterFormDto;
import com.example.foodexpress.repository.UserRepository;
import com.example.foodexpress.service.UserService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import static com.example.foodexpress.outputs.SuccessMessages.OFFER_REMOVE;
import static com.example.foodexpress.outputs.SuccessMessages.REGISTER;

@Controller
@RequestMapping("/users")
public class RegisterController {

    public static final String BINDING_RESULT_PATH = "org.springframework.validation.BindingResult.";
    private final UserService userService;
    private final UserRepository userRepository;

    public RegisterController(UserService userService, UserRepository userRepository) {
        this.userService = userService;
        this.userRepository = userRepository;
    }

    @GetMapping("/register")
    public String getRegister(){
        return "register";
    }

    @PostMapping("/register")

        public String postRegister(@Valid @ModelAttribute(name = "userRegisterForm") UserRegisterFormDto userRegisterForm,
                BindingResult bindingResult,
                RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors() || !userRegisterForm.getPassword().equals(userRegisterForm.getConfirmPassword())
         || userRepository.findByEmail(userRegisterForm.getEmail()).isPresent()) {
            redirectAttributes.addFlashAttribute("userRegisterForm", userRegisterForm)
                    .addFlashAttribute(BINDING_RESULT_PATH + "userRegisterForm", bindingResult);

            return "redirect:register";
        }

        this.userService.registerUser(userRegisterForm);

        redirectAttributes.addFlashAttribute("successMessage",REGISTER);
        return "redirect:login";
    }

    @ModelAttribute(name = "userRegisterForm")
    public UserRegisterFormDto userRegisterForm(){
        return new UserRegisterFormDto();
    }



}
