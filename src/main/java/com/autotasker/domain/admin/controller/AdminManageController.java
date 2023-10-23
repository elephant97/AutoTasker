package com.autotasker.domain.admin.controller;

import com.autotasker.domain.admin.model.Users;
import com.autotasker.domain.admin.repositories.UserListRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

//@RequestMapping //TODO admin User일 경우 admin 페이지 버튼이 활성화 되고, 해당 페이지로 매핑되도록 변경예정
@Controller
public class AdminManageController {
    //TODO user 정보 List가 출력되도록 해당 서비스 구현예정
    @Autowired
    UserListRepository userListRepository;

    @GetMapping(value = "/admin")
    public String AdminGetUserList(Model model)
    {
        List<Users> userList = userListRepository.findAll();
        model.addAttribute("user", userList);
        return "admin/admin-main";
    }

}
