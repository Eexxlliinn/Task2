package me.eexxlliinn.task2.repositories;

import me.eexxlliinn.task2.entities.UserEntity;

public interface UserRepository {
    UserEntity findByUsername(String username);
    void save(UserEntity user);
}
