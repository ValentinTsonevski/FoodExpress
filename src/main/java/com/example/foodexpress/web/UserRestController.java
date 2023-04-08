package com.example.foodexpress.web;

import com.example.foodexpress.domain.dtos.user.UsersRestDto;
import com.example.foodexpress.service.UserService;
import javassist.tools.rmi.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/api/users")
public class UserRestController {

    private final UserService userService;

    @Autowired
    public UserRestController(UserService userService) {
        this.userService = userService;
    }


    @GetMapping
    public ResponseEntity<List<UsersRestDto>> getUsers(){
        return ResponseEntity.ok(this.userService.getAllUsersRest());
    }


    @GetMapping("/delete/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Void> deleteGuest(@PathVariable("id") Long id) throws ObjectNotFoundException {

        this.userService.deleteUserREST(id);

        return ResponseEntity.ok(null);
    }


}
