package com.kilmurray.dnd_userservice.controller;

import com.kilmurray.dnd_userservice.dao.UserDao;
import com.kilmurray.dnd_userservice.dto.CharacterDto;
import com.kilmurray.dnd_userservice.dto.UserDto;
import com.kilmurray.dnd_userservice.proxy.CharacterProxy;
import com.kilmurray.dnd_userservice.repository.UserRepository;
import io.jsonwebtoken.Jwts;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.*;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final CharacterProxy characterProxy;

    public UserService(UserRepository userRepository, CharacterProxy characterProxy) {
        this.userRepository = userRepository;
        this.characterProxy = characterProxy;
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

    private UserDao updateUserDetails(Optional<String> email,Optional<Boolean> isDm, Optional<Long> partyId, UserDao updateUser) {
        if (email.isPresent()) {
            updateUser.setEmail(email.get());
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
        Optional<UserDao> checkUser = userRepository.findByEmail(email);
        return checkUser.isPresent();
    }

    public UserDto convertToDto(UserDao userDao) {
        return new UserDto(userDao.getId(), userDao.getUsername(),
                userDao.getEmail(), userDao.getIsDm(),userDao.getPartyId());
    }

    public UserDao convertToDao(UserDto userDto) {
        if (userDto.isDm()) {
            return new UserDao(userDto.getId(), userDto.getUsername(),
                    userDto.getEmail(), userDto.isDm(),
                    userDto.getPartyId(), "DM");
        }
        else {
            return new UserDao(userDto.getId(), userDto.getUsername(),
                    userDto.getEmail(), userDto.isDm(),
                    userDto.getPartyId(), "PLAYER");
        }
    }

    public UserDto getUserByEmail(String email) {
        Optional<UserDao> foundUser = userRepository.findByEmail(email);
        if (!foundUser.isPresent()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,"User cannot be found.");
        }
        return convertToDto(foundUser.get());
    }

    public UserDto updateUser(String userEmail, Optional<String> email, Optional<Boolean> isDm, Optional<Long> partyId) {
        Optional<UserDao> updateUser = userRepository.findByEmail(userEmail);
        UserDao updatedUser = updateUser
                .map(users -> updateUserDetails(email,isDm, partyId, users)) // update the user
                .orElseThrow(() -> new RuntimeException("User not found."));
        return convertToDto(updatedUser);
    }

    public UserDto updateUserPartyId(String userEmail, Long partyId) {
        Optional<UserDao> foundUser = userRepository.findByEmail(userEmail);
        if (foundUser.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,"User not found");
        }
        List<UserDao> partyIdCheck = userRepository.findByPartyId(partyId);
        if (partyIdCheck.size() != 0) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"Party ID already exists");
        }
        foundUser.get().setPartyId(partyId);
        foundUser.get().setIsDm(true);
        userRepository.save(foundUser.get());
        return convertToDto(foundUser.get());
    }

    public List<CharacterDto> getCharacters(String email) {
        Optional<UserDao> foundUser = userRepository.findByEmail(email);
        if (foundUser.isEmpty()) {
            return null;
        }

        List<CharacterDto> characters = characterProxy.getByEmail(foundUser.get().getId());
        return characters;
    }
}
