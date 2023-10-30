package com.autotasker.domain.admin.controller;

import com.autotasker.domain.admin.model.DTO.UserListDTO;
import com.autotasker.domain.admin.repositories.UserListRepository;
import com.autotasker.domain.admin.service.UserManageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

//@RequestMapping //TODO admin User일 경우 admin 페이지 버튼이 활성화 되고, 해당 페이지로 매핑되도록 변경예정
@RequestMapping("/admin")
@Controller
public class AdminManageController {
    //TODO user 정보 List가 출력되도록 해당 서비스 구현예정
    @Autowired
    UserListRepository userListRepository;
    @Autowired
    private UserManageService userManageService;

    @GetMapping
    public String AdminGetUserList(@RequestParam(name = "page", defaultValue = "0") int page, Model model)
    {
        int pageSize = 5; // 한 페이지에 표시할 항목 수
        Pageable pageable = PageRequest.of(page, pageSize);
        Page<UserListDTO> usersPage = userListRepository.findAll(pageable);

        model.addAttribute("userList", usersPage);
        return "admin/user-manage";
    }

    @PostMapping("/approve-user")
    public ResponseEntity<String> approveUser(@RequestParam("userNo") Long userNo)
    {
        ResponseEntity<String> response = userManageService.approveUser(userNo);
        return response;
    }

    @PostMapping("/delete-user")
    public ResponseEntity<String> deleteUser(@RequestParam("userNo") Long userNo)
    {
        ResponseEntity<String> response = userManageService.deleteUser(userNo);
        return response;
    }

    @GetMapping(value ="/UserJoin")
    public String AdminRequestUserJoin(Model model)
    {
        return "admin/UserJoin";
    }

}
