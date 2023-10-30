package com.autotasker.domain.admin.controller;

import com.autotasker.domain.admin.model.UserList;
import com.autotasker.domain.admin.repositories.UserListRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

//@RequestMapping //TODO admin User일 경우 admin 페이지 버튼이 활성화 되고, 해당 페이지로 매핑되도록 변경예정
@RequestMapping("/admin")
@Controller
public class AdminManageController {
    //TODO user 정보 List가 출력되도록 해당 서비스 구현예정
    @Autowired
    UserListRepository userListRepository;

    @GetMapping
    public String AdminGetUserList(@RequestParam(name = "page", defaultValue = "0") int page, Model model)
    {
        int pageSize = 5; // 한 페이지에 표시할 항목 수
        Pageable pageable = PageRequest.of(page, pageSize);
        Page<UserList> usersPage = userListRepository.findAll(pageable);

        model.addAttribute("userList", usersPage);
        return "admin/user-manage";
    }

    @GetMapping(value ="/UserJoin")
    public String AdminRequestUserJoin(Model model)
    {
        return "admin/UserJoin";
    }

}
