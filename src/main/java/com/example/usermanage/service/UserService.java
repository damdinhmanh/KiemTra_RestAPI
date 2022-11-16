package com.example.usermanage.service;

import com.example.usermanage.dto.*;
import com.example.usermanage.model.User;
import com.example.usermanage.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public List<ResponseUserData> getUsers() {
        return userRepository.findAll();
    }

    public ResponseUserData findUserByName(String name) {
        return userRepository.findByName(name);
    }

    public ResponseUserData findUserById(int id) {
        return userRepository.findById(id);
    }

    public ResponseUserData createUser(CreateUserRequest request) {
        return userRepository.createUser(request.getName(), request.getEmail(), request.getPhone()
                                        , request.getAddress(), request.getPassword());
    }

    public ResponseUserData updateUserInfo(int id, UpdateUserInfoRequest request) {
        return userRepository.updateUserInfor(id, request.getName(), request.getPhone(), request.getAddress());
    }

    public void deleteUser(int id) {
        userRepository.deleteUser(id);
    }

    public void updateUserAvatar(int id, UpdateUserAvatarRequest request) {
        userRepository.updateUserAvatar(id, request.getAvatar());
    }

    public void updatePassword(int id, UpdatePasswordRequest request) {
        boolean isCorrectPassword = userRepository.updatePassword(id, request.getOldPassword(), request.getNewPassword());

        if (isCorrectPassword == false) {
            throw new com.example.loginfunction.exception.PasswordWrongException();
        }
    }

    public String fotgotPassword(int id) {
        return userRepository.fotgotPassword(id);
    }

    public List<UserPage> getUserByPage(int page, int limit) {
        return userRepository.getUserByPage(page, limit);
    }

}
