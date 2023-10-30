package com.autotasker.domain.admin.repositories;

import com.autotasker.domain.admin.model.DTO.UserListDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;


//page로 구현하기 위해 CrudRepository사용, Pageable사용
public interface UserListRepository extends CrudRepository<UserListDTO, Long> {
    Page<UserListDTO> findAll(Pageable pageable);
    Optional<UserListDTO> findById(Long userId);

}
