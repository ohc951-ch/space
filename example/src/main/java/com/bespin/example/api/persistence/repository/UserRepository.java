package com.bespin.example.api.persistence.repository;

import com.bespin.example.api.persistence.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Project : group.example
 * Class : com.bespin.example.api.persistence.repository.UserRepository
 * Version : 0.0.1
 * Created by josihyeon on 2019-05-23.
 */
public interface UserRepository extends JpaRepository<User, Long> {
    User findByUserId(String userId);
}
