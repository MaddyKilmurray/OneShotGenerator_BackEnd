package com.kilmurray.dnd_userservice.controller;

import com.kilmurray.dnd_userservice.dto.EmailDto;
import com.kilmurray.dnd_userservice.dto.UserDto;
import com.kilmurray.dnd_userservice.dto.UsernameDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.web.bind.annotation.*;
import org.springframework.security.core.Authentication;

import java.util.List;
import java.util.Optional;

@CrossOrigin(value = "*")
@RestController
@RequestMapping("/api/users")
public class UserController {

    final
    UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

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

    @GetMapping("/authenticated")
    @ResponseStatus(HttpStatus.OK)
    public UserDto getUserByUsername(@RequestHeader (name="Authorization") String token) {
        return userService.getUserByUsername(token);
    }

    @PostMapping("/register")
    @ResponseStatus(HttpStatus.CREATED)
    public UserDto createUser(@RequestBody UserDto userDto) {
        return userService.create(userDto);
    }

    @PatchMapping("/authenticated/update")
    @ResponseStatus(HttpStatus.OK)
    public UserDto updateUser(@RequestHeader (name="Authorization") String token, @RequestParam Optional<String> password, @RequestParam Optional<String> email,
                              @RequestParam Optional<Boolean> isDm, @RequestParam Optional<Long> partyId) {
        return userService.updateUser(token,password,isDm,partyId);
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
