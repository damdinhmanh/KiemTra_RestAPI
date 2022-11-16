package com.example.usermanage.controller;

import com.example.usermanage.dto.*;
import com.example.usermanage.model.User;
import com.example.usermanage.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/")
public class UserController {
    @Autowired
    private UserService userService;

    @Autowired
    private ApplicationContext context;

    //1. Lấy danh sách users (có phân trang - pagination)
    //GET http://localhost:8080/api/v1/users (mặc định page = 1, limit = 10)
    // Tương đương GET http://localhost:8080/api/v1/users?page=1&limit=10
    @GetMapping("users")
    public List<UserPage> searchByName(@RequestParam("page") Optional<Integer> page,
                                       @RequestParam("limit") Optional<Integer> limit) {
        if (!page.isPresent() && !limit.isPresent()) {
            return userService.getUserByPage(1, 10);
        } else {
            return userService.getUserByPage(page.get(), limit.get());
        }
    }

    //Get All User List
  //  @GetMapping("users")
    public List<ResponseUserData> getAllUsers() {
        return userService.getUsers();
    }

    //2. Tìm kiếm user theo tên
    // -> GET http://localhost:8080/api/v1/search?name={nameValue}
    @GetMapping("search")
    public ResponseUserData searchByName(@RequestParam String name) {
        return userService.findUserByName(name);
    }
    //3. Lấy chi tiết user theo id
    //GET http://localhost:8080/api/v1/users/{id}
    @GetMapping("users/{id}")
    public ResponseUserData searchById(@PathVariable int id) {
        return userService.findUserById(id);
    }

    //4. Tạo mới user
    //POST http://localhost:8080/api/v1/users
    @PostMapping("users")
    public ResponseUserData createUser(@RequestBody CreateUserRequest request) {
        return userService.createUser(request);
    }

    //5. Cập nhật thông tin user
    //PUT http://localhost:8080/api/v1/users/{id}
    @PutMapping("users/{id}")
    public ResponseUserData updateUser(@PathVariable int id, @RequestBody UpdateUserInfoRequest request) {
        return userService.updateUserInfo(id, request);
    }

    //6. Xóa user
    //DELETE http://localhost:8080/api/v1/users/{id}
    @DeleteMapping("users/{id}")
    public void deleteUser(@PathVariable int id) {
        userService.deleteUser(id);
    }

    //7. Thay đổi ảnh avatar
    //PUT http://localhost:8080/api/v1/users/{id}/update-avatar
    @PutMapping("users/{id}/update-avatar")
    public void updateAvatar(@PathVariable int id, @RequestBody UpdateUserAvatarRequest request) {
        userService.updateUserAvatar(id, request);
    }

    //8. Thay đổi mật khẩu
    //PUT http://localhost:8080/api/v1/users/{id}/update-password
    @PutMapping("users/{id}/update-password")
    public void updatePassword(@PathVariable int id, @RequestBody UpdatePasswordRequest request) {
        userService.updatePassword(id, request);
    }

    //9. Quên mật khẩu
    //Get http://localhost:8080/api/v1/users/{id}/fotgot-password
    @GetMapping("users/{id}/forgot-password")
    public String forgotPassword(@PathVariable int id) {
        return userService.fotgotPassword(id);
    }
}
