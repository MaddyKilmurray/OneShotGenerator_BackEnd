package com.kilmurray.dnd_userservice.controller;

import com.kilmurray.dnd_userservice.dao.UserDao;
import com.kilmurray.dnd_userservice.dto.UserDto;
import com.kilmurray.dnd_userservice.repository.UserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<UserDto> getAll() {
        List<UserDao> userDaos = userRepository.findAll();
        List<UserDto> userDtos = new ArrayList<>();
        for (UserDao userDao : userDaos) {
            userDtos.add(convertToDto(userDao));
        }
        return userDtos;
    }

    public UserDto getUserById(Long id) {
        Optional<UserDao> foundUser = userRepository.findById(id);
        if (!foundUser.isPresent()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,"User cannot be found");
        }
        return convertToDto(foundUser.get());
    }

    public UserDto create(UserDto userDto) {
        UserDao newUser = convertToDao(userDto);
        userRepository.save(newUser);
        Optional<UserDao> createdUser = userRepository.findByUsername(newUser.getUsername());
        return createdUser.map(this::convertToDto).orElse(null);
    }

    public UserDto updateUser(Long userId, Optional<String> password, Optional<Boolean> isDm, Optional<Long> partyId) {
        Optional<UserDao> updateUser = userRepository.findById(userId);
        UserDao updatedUser = updateUser
                .map(users -> updateUserDetails(password, isDm, partyId, users)) // update the user
                .orElseThrow(() -> new RuntimeException("User not found."));
        return convertToDto(updatedUser);
    }

    private UserDao updateUserDetails(Optional<String> password, Optional<Boolean> isDm, Optional<Long> partyId, UserDao updateUser) {
        if (password.isPresent()) {
//            String newPassword = passwordEncoder.encode(password.get());
            updateUser.setPassword(password.get());
            userRepository.save(updateUser);
        }
        if (isDm.isPresent()) {
            updateUser.setIsDm(isDm.get());
            userRepository.save(updateUser);
        }
        if (partyId.isPresent()) {
            updateUser.setPartyId(partyId.get());
            userRepository.save(updateUser);
        }
        return updateUser;
    }

    public boolean validateUsernameExists(String username) {
        Optional<UserDao> checkUser = userRepository.findByUsername(username);
        return checkUser.isPresent();
    }

    public boolean validateEmailExists(String email) {
        Optional<UserDao> checkUser = userRepository.findByEmailAddress(email);
        return checkUser.isPresent();
    }

    public UserDto convertToDto(UserDao userDao) {
        return new UserDto(userDao.getId(), userDao.getUsername(), userDao.getPassword(),
                userDao.getEmail(), userDao.getIsDm(),userDao.getPartyId());
    }

    public UserDao convertToDao(UserDto userDto) {
        return new UserDao(userDto.getId(), userDto.getUsername(),
                userDto.getPassword(), userDto.getEmail(), userDto.getIsDm(),
                userDto.getPartyId());
    }

}
