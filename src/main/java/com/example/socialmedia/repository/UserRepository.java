package com.example.socialmedia.repository;

import com.example.socialmedia.model.User;
import com.example.socialmedia.model.UserId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, UserId> {
    @Query("SELECT U FROM User U WHERE U.id.username = :username")
    User findByUsername(@Param("username") String username);
    @Query("SELECT U FROM User U WHERE U.id.username = :username AND U.password = :password")
    User getByUsernameAndPassword(@Param("username") String username, @Param("password") String password);
}