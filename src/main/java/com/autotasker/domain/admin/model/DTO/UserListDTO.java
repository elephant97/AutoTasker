package com.autotasker.domain.admin.model.DTO;

import com.autotasker.domain.admin.model.DTO.Form.UserJoinFormDto;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.NoArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;

@Entity
@NoArgsConstructor
@Table(name="user_list")
public class UserListDTO {
    /**
     * [UserList Table 데이터 조회 클래스]
     * */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_no")
    private Long userNo;
    private String userId;
    private String userPwd;
    private String userName;
    private String userEmail;
    private String svnId;
    private String department;
    private String status; //approvalStaus

    public Long getUserNo() {
        return userNo;
    }

    public String getUserId() {
        return userId;
    }

    public String getUserPwd() {
        return userPwd;
    }

    public String getUserName() {
        return userName;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public String getSvnId() {
        return svnId;
    }

    public String getDepartment() {
        return department;
    }

    public String getStatus() {
        return status;
    }


    public void setUserNo(Long userNo) {
        this.userNo = userNo;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public void setUserPwd(String userPwd) {
        this.userPwd = userPwd;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public void setSvnId(String svnId) {
        this.svnId = svnId;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Builder
    public UserListDTO(Long userNo, String userId, String userPwd, String userName, String userEmail, String svnId, String department, String status) {
        this.userNo = userNo;
        this.userId = userId;
        this.userPwd = userPwd;
        this.userName = userName;
        this.userEmail = userEmail;
        this.svnId = svnId;
        this.department = department;
        this.status = status;
    }

    public static UserListDTO createUser(UserJoinFormDto userJoinFormDto, PasswordEncoder passwordEncoder) {
        UserListDTO user = new UserListDTO();
        user.setUserId(userJoinFormDto.getUserId());
        String password = passwordEncoder.encode(userJoinFormDto.getUserPwd());
        user.setUserPwd(password);
        user.setUserName(userJoinFormDto.getUserName());
        user.setUserEmail(userJoinFormDto.getUserEmail());
        user.setSvnId(userJoinFormDto.getSvnId());
        user.setDepartment(userJoinFormDto.getDepartment());

        return user;
    }
}
