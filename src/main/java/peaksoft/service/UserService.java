package peaksoft.service;

import peaksoft.entity.User;

import java.util.List;


public interface UserService {
    void saveUser(User user);

    User getUserById(Long id);

    List<User> getAllUsers();

    void updateUser(Long id, User user);

    void deleteUser(Long id);

    User findUserByEmail(String email);
}
