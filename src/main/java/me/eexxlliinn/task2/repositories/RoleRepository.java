package me.eexxlliinn.task2.repositories;

import me.eexxlliinn.task2.entities.RoleEntity;

public interface RoleRepository {
    RoleEntity getByName(String roleName);
    void save(RoleEntity role);
}
