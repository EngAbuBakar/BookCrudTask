package com.task.crudapplication.Repository;

import com.task.crudapplication.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

public interface UserRepository extends JpaRepository <User, Long> {
    public Optional<User> findById(Long id );
    public Optional<User> findByName(String name);
}
