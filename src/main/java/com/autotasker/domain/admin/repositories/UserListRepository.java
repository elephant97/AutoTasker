package com.autotasker.domain.admin.repositories;

import com.autotasker.domain.admin.model.UserList;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;


//page로 구현하기 위해 CrudRepository사용, Pageable사용
public interface UserListRepository extends CrudRepository<UserList, Long> {
    Page<UserList> findAll(Pageable pageable);
    Optional<UserList> findById(Long userId);

}
