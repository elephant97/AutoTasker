package com.autotasker.domain.admin.service;

import com.autotasker.domain.admin.model.DTO.UserListDTO;
import com.autotasker.domain.admin.repositories.UserListRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserManageService {
    private final UserListRepository userListRepository;
    @Autowired
    public UserManageService(UserListRepository userListRepository) {
        this.userListRepository = userListRepository;
    }


    public String approveUser(Long userNo) {
        Optional<UserListDTO> user = userListRepository.findById(userNo);

        if (user.isPresent()) {
            UserListDTO userList = user.get();
            userList.setStatus("Y"); // 승인되었을 때 'status' 값을 "Y"로 설정
            userListRepository.save(userList); // 데이터베이스 업데이트
            return "사용자 승인을 성공했습니다.";
        } else {
            return "사용자를 찾을 수 없습니다.";
        }
    }

    public String deleteUser(Long userNo) {
        Optional<UserListDTO> user = userListRepository.findById(userNo);

        if (user.isPresent()) {
            UserListDTO userList = user.get();
            userListRepository.delete(userList);// 사용자 삭제
            return"사용자 승인거절(삭제)이 성공했습니다.";
        } else {
            return "사용자를 찾을 수 없습니다.";
        }
    }
}
