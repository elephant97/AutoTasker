package com.autotasker.controller.admin;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

//@RequestMapping //TODO admin User일 경우 admin 페이지 버튼이 활성화 되고, 해당 페이지로 매핑되도록 변경예정
@Controller
public class AdminController {
    //TODO user 정보 List가 출력되도록 해당 서비스 구현예정
    //@Autowired
    //private UserManageService userManagerService;

    @RequestMapping(value = "/admin")
    public String AdminMainPage()
    {
        return "admin/admin-main";
    }

}
