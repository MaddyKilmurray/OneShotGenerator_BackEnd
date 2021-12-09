package com.kilmurray.dnd_userservice.controller;

import com.kilmurray.dnd_userservice.dto.EmailDto;
import com.kilmurray.dnd_userservice.dto.UserDto;
import com.kilmurray.dnd_userservice.dto.UsernameDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin(value = "*")
@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<UserDto> getAllUsers() {
        return userService.getAll();
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public UserDto getUserById(@PathVariable(name = "id") Long id) {
        return userService.getUserById(id);
    }

    @GetMapping("/{email}")
    @ResponseStatus(HttpStatus.OK)
    public UserDto getUserByUsername(@PathVariable(name="email") String email) {
        return userService.getUserByEmail(email);
    }

    @PostMapping("/register")
    @ResponseStatus(HttpStatus.CREATED)
    public UserDto createUser(@RequestBody UserDto userDto) {
        return userService.create(userDto);
    }

    @PatchMapping("/update/{email}")
    @ResponseStatus(HttpStatus.OK)
    public UserDto updateUser(@PathVariable(name = "email") String userEmail, @RequestParam Optional<String> email,
                              @RequestParam Optional<Boolean> isDm, @RequestParam Optional<Long> partyId) {
        return userService.updateUser(userEmail,email,isDm,partyId);
    }

    @PostMapping("/validate/username")
    @ResponseStatus(HttpStatus.OK)
    public boolean isValidUsername(@RequestBody UsernameDto usernameDto) {
        return userService.validateUsernameExists(usernameDto.getUsername());
    }

    @PostMapping("/validate/email")
    @ResponseStatus(HttpStatus.OK)
    public boolean isValidEmail(@RequestBody EmailDto emailDto) {
        return userService.validateEmailExists(emailDto.getEmail());
    }

}
