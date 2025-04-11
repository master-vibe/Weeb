package com.Weeb.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.repository.query.Param;

import com.Weeb.Entity.Users;

@EnableJpaRepositories
public interface UserRepository extends JpaRepository<Users, String> {

    @Query("SELECT u FROM Users u WHERE u.username = :username")
    Users findByUsername(@Param("username") String username);

    @Query("SELECT COUNT(u) > 0 FROM Users u WHERE u.username = :username")
    boolean isUsernameAvailable(@Param("username") String username);

    @Query("SELECT u FROM Users u WHERE u.username = :username AND u.password = :password")
    Users findByUsernamePassword(@Param("username") String username, @Param("password") String password);
}
