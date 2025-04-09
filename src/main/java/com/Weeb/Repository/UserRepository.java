package com.Weeb.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.repository.query.Param;

import com.Weeb.Entity.Users;

@EnableJpaRepositories
public interface UserRepository extends JpaRepository<Users, String> {

    @Query(value = "SELECT u FROM users u WHERE u.username = :username",nativeQuery = true)
    Users findByUsername(@Param("username") String username);

}
