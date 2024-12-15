package me.eexxlliinn.task2.services;

import me.eexxlliinn.task2.dtos.UserRequest;
import me.eexxlliinn.task2.entities.UserEntity;

public interface UserService {
    void register(UserRequest userRequest);
    UserEntity login(String username, String password);
}
