package com.autotasker.domain.admin.repositories;

import com.autotasker.domain.admin.model.Users;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserListRepository extends JpaRepository<Users, Long> {
    List<Users> findAll();
}
