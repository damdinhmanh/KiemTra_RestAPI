package com.example.usermanage.repository;

import com.example.usermanage.dto.ResponseUserData;
import com.example.usermanage.dto.UserPage;
import com.example.usermanage.model.User;
import com.github.javafaker.Faker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class UserRepository {
    private List<User> users;
    private final Faker faker;

    @Autowired
    private ApplicationContext context;

    private UserRepository(Faker faker) {
        this.faker = faker;
        initUsers();
    }

    private void initUsers() {
        users = new ArrayList<>();
        for (int i = 1; i < 20; i++) {
            User user = new User(
                    i,
                    faker.name().fullName(),
                    faker.internet().emailAddress(),
                    faker.phoneNumber().phoneNumber(),
                    faker.address().city(),
                    faker.avatar().image(),
                    "abc13579");
            users.add(user);
        }
    }

    public List<ResponseUserData> findAll() {
        List<ResponseUserData> userResList = (List<ResponseUserData>) context.getBean("responseUserList", Object.class);
        userResList.clear();

        for (User aUser: users) {
            ResponseUserData userRes = new ResponseUserData();
            userRes.setId(aUser.getId());
            userRes.setName(aUser.getName());
            userRes.setEmail(aUser.getEmail());
            userRes.setPhone(aUser.getPhone());
            userRes.setAddress(aUser.getAddress());
            userRes.setAvatar(aUser.getAvatar());

            userResList.add(userRes);
        }
        
        
        return userResList;
    }

    public ResponseUserData findByName(String name) {
        Optional<User> user = users.
                                stream()
                                .filter(useri -> useri.getName().equals(name))
                                .findFirst();
        if(user.isPresent()) {
            User aUser = user.get();
            ResponseUserData userRes = context.getBean("responseUser", ResponseUserData.class);
            userRes.setId(aUser.getId());
            userRes.setName(aUser.getName());
            userRes.setEmail(aUser.getEmail());
            userRes.setPhone(aUser.getPhone());
            userRes.setAddress(aUser.getAddress());
            userRes.setAvatar(aUser.getAvatar());
            return userRes;
        }
        return null;
    }

    public ResponseUserData findById(int id) {
        Optional<User> user = users.
                stream()
                .filter(useri -> useri.getId() == id)
                .findFirst();

        if(user.isPresent()) {
            User aUser = user.get();
                ResponseUserData userRes = context.getBean("responseUser", ResponseUserData.class);
                userRes.setId(aUser.getId());
                userRes.setName(aUser.getName());
                userRes.setEmail(aUser.getEmail());
                userRes.setPhone(aUser.getPhone());
                userRes.setAddress(aUser.getAddress());
                userRes.setAvatar(aUser.getAvatar());
                return userRes;
        }
        return null;
    }

    public ResponseUserData createUser(String userName, String email, String phone, String address, String password) {
        User aUser = new User(users.get(users.size() -1).getId() + 1,
                            userName,
                            email,
                            phone,
                            address,
                            null,
                            password);
        users.add(aUser);
        ResponseUserData userRes = context.getBean("responseUser", ResponseUserData.class);
        userRes.setId(aUser.getId());
        userRes.setName(aUser.getName());
        userRes.setEmail(aUser.getEmail());
        userRes.setPhone(aUser.getPhone());
        userRes.setAddress(aUser.getAddress());
        userRes.setAvatar(aUser.getAvatar());
        return userRes;
    }

    public ResponseUserData updateUserInfor(int id, String name, String phone, String address) {
        Optional<User> user = users.
                stream()
                .filter(useri -> useri.getId() == id)
                .findFirst();

        if(user.isPresent()) {
            User aUser = user.get();

            aUser.setName(name);
            aUser.setPhone(phone);
            aUser.setAddress(address);

            ResponseUserData userRes = context.getBean("responseUser", ResponseUserData.class);
            userRes.setId(aUser.getId());
            userRes.setName(aUser.getName());
            userRes.setEmail(aUser.getEmail());
            userRes.setPhone(aUser.getPhone());
            userRes.setAddress(aUser.getAddress());
            userRes.setAvatar(aUser.getAvatar());
            return userRes;
        }
        return null;
    }

    public void deleteUser(int id) {
        Optional<User> user = users.
                stream()
                .filter(useri -> useri.getId() == id)
                .findFirst();

        if(user.isPresent()) {
            users.remove(user.get());
        }
    }

    public void updateUserAvatar(int id, String avatar) {
        Optional<User> user = users.
                stream()
                .filter(useri -> useri.getId() == id)
                .findFirst();

        if(user.isPresent()) {
            User aUser = user.get();
            aUser.setAvatar(avatar);
        }
    }

    public boolean updatePassword(int id, String oldPassword, String newPassword) {
        Optional<User> user = users.
                stream()
                .filter(useri -> useri.getId() == id)
                .findFirst();

        if(user.isPresent()) {
            User aUser = user.get();

            if (aUser.getPassword().equals(oldPassword)) {
                if (newPassword.equals(aUser.getPassword())) {
                    return false;
                } else {
                    aUser.setPassword(newPassword);
                    return true;
                }
            } else {
                return false;
            }
        }
        return false;
    }

    public String fotgotPassword(int id) {
        Optional<User> user = users.
                stream()
                .filter(useri -> useri.getId() == id)
                .findFirst();

        if(user.isPresent()) {
            User aUser = user.get();
            String defaultPassword = "abc12345";
            aUser.setPassword(defaultPassword);
            return defaultPassword;
        }
        return "";
    }

    public List<UserPage> getUserByPage(int page, int limit) {
        int totalPages = users.size()/limit;
        if (users.size()%limit > 0) {
            totalPages = totalPages + 1;
        }

        List<UserPage> retList = new ArrayList<UserPage>();


        for (int i = 0; i < users.size(); i++) {
            int currentPage = i / limit;
            currentPage = currentPage + 1;
            if (currentPage == page) {
                UserPage userPage = new UserPage();
                userPage.setCurrentPage(currentPage);
                userPage.setTotalPages(totalPages);
                userPage.setSize(limit);
                ResponseUserData userDtatob = new ResponseUserData();
                userDtatob.setName(users.get(i).getName());
                userDtatob.setId(users.get(i).getId());
                userDtatob.setPhone(users.get(i).getPhone());
                userDtatob.setAddress(users.get(i).getAddress());
                userDtatob.setAvatar(users.get(i).getAvatar());

                userPage.setResponseUserData(userDtatob);

                retList.add(userPage);
            }
        }
        return  retList;
    }
}
