package me.eexxlliinn.task2.services.impl;

import lombok.extern.slf4j.Slf4j;
import me.eexxlliinn.task2.dtos.UserRequest;
import me.eexxlliinn.task2.entities.UserEntity;
import me.eexxlliinn.task2.repositories.RoleRepository;
import me.eexxlliinn.task2.repositories.UserRepository;
import me.eexxlliinn.task2.repositories.impl.RoleRepositoryImpl;
import me.eexxlliinn.task2.repositories.impl.UserRepositoryImpl;
import me.eexxlliinn.task2.services.UserService;

@Slf4j
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository = new UserRepositoryImpl();
    private final RoleRepository roleRepository = new RoleRepositoryImpl();

    @Override
    public void register(UserRequest userRequest) {
        if (userRepository.findByUsername(userRequest.getUsername()) != null) {
            throw new IllegalArgumentException("Username already exists");
        }
        UserEntity user = new UserEntity();
        user.setUsername(userRequest.getUsername());
        user.setPassword(userRequest.getPassword());
        user.setEmail(userRequest.getEmail());
        user.setRole(roleRepository.getByName("USER"));
        userRepository.save(user);
    }

    @Override
    public UserEntity login(String username, String password) {
        UserEntity userEntity = userRepository.findByUsername(username);
        if (userEntity == null || !password.equals(userEntity.getPassword())) {
            return null;
        } else {
            return userEntity;
        }
    }
}
