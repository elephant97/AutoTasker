package com.autotasker.domain.admin.controller;

import com.autotasker.domain.admin.model.DTO.Form.UserJoinFormDto;
import com.autotasker.domain.admin.model.DTO.Form.UserListRequestFormDto;
import com.autotasker.domain.admin.model.DTO.UserListDTO;
import com.autotasker.domain.admin.repositories.UserListRepository;
import com.autotasker.domain.admin.service.UserManageService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
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

    private final PasswordEncoder passwordEncoder;

    public AdminManageController(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    @RequestMapping
    public String AdminGetUserList(@RequestParam(name = "page", defaultValue = "0") int page, Model model,Model map)
    {
        int pageSize = 5; // 한 페이지에 표시할 항목 수
        //status가 N인 항목 먼저 출력 되도록 함
        Pageable pageable = PageRequest.of(page, pageSize, Sort.by(Sort.Direction.ASC, "status"));
        Page<UserListDTO> usersPage = userListRepository.findAll(pageable);
        model.addAttribute("userList", usersPage);
        map.addAttribute("userListRequestFormDto", new UserListRequestFormDto());

        return "admin/user-manage";
    }

    @PostMapping("/approve-user")
    public String approveUser(@RequestParam("userNo") Long userNo ,Model model)
    {
        String result = userManageService.approveUser(userNo);
        model.addAttribute("resultMessage", result);

        return "redirect:/admin";
    }

    @PostMapping("/delete-user")
    public String deleteUser(@RequestParam("userNo") Long userNo, Model model)
    {
        model.addAttribute("resultMessage", userManageService.deleteUser(userNo));
        return "redirect:/admin";
    }

    @PostMapping("/edit-user")
    public String EditUserData(@RequestParam("userNo") Long userNo, @Valid @ModelAttribute("userListRequestFormDto") UserListRequestFormDto userListRequestFormDto, BindingResult bindingResult, Model model)
    {
        System.out.println(userListRequestFormDto);
        System.out.println(userNo);
        if(bindingResult.hasErrors()){
            return "admin/user-manage";
        }
        try {
            String resultMessage =  userManageService.editUserInfo(userNo,userListRequestFormDto);
            System.out.println(resultMessage);
            model.addAttribute("resultMessage", resultMessage);
        }catch (IllegalStateException e){
            model.addAttribute("errorMessage", e.getMessage());
            return "admin/user-manage";
        }
        return "admin/user-manage";
    }

    @GetMapping(value ="/user-join")
    public String AdminRequestUserJoin(Model model)
    {
        model.addAttribute("UserJoinFormDto", new UserJoinFormDto());
        return "admin/user-join";
    }
    @PostMapping(value ="/user-join")
    public String AdminRequestUserJoin(UserJoinFormDto userJoinFormDto)
    {
        UserListDTO user = UserListDTO.createUser(userJoinFormDto, passwordEncoder);
        userManageService.saveUser(user);
        return "redirect:/";
    }
}
