package com.autotasker.domain.admin.service;

import ch.qos.logback.core.net.SyslogOutputStream;
import com.autotasker.domain.admin.model.DTO.Form.UserListRequestFormDto;
import com.autotasker.domain.admin.model.DTO.UserListDTO;
import com.autotasker.domain.admin.repositories.UserListRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Objects;
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

    public String editUserInfo(Long userNo, UserListRequestFormDto userEditData) {
        Optional<UserListDTO> user = userListRepository.findById(userNo);
        boolean updateExsit = false;

        if (user.isPresent()) { // 기존정보와 변동이 있을 때에만 update
            UserListDTO userList = user.get();
            if(!Objects.equals(userList.getUserEmail(), userEditData.getUserEmail())){
                userList.setUserEmail(userEditData.getUserEmail());
                System.out.println(userList.getUserEmail()+"->"+userEditData.getUserEmail());
                updateExsit = true;
            }
            if(!Objects.equals(userList.getSvnId(),userEditData.getSvnId())){
                userList.setSvnId(userEditData.getSvnId());
                System.out.println(userList.getSvnId()+"->"+userEditData.getSvnId());
                updateExsit = true;
            }
            if(!Objects.equals(userList.getDepartment(),userEditData.getDepartment())){
                userList.setDepartment(userEditData.getDepartment());
                System.out.println(userList.getDepartment()+"->"+userEditData.getDepartment());
                updateExsit = true;
            }

            if(updateExsit) {
                userListRepository.save(userList);
                return"저장을 성공하였습니다.";
            }else{
                return"기존 정보와 동일합니다.";
            }

        } else {
            return "사용자를 찾을 수 없습니다.";
        }
    }

    public UserListDTO saveUser(UserListDTO user) {
        validateDuplicateUser(user);
        return userListRepository.save(user);
    }

    public void validateDuplicateUser(UserListDTO user) {
        UserListDTO findUser = userListRepository.findByUserId(user.getUserId());
        if (findUser != null) {
            throw new IllegalStateException("이미 가입된 회원입니다.");
        }
    }
}
