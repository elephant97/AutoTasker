package com.autotasker.domain.admin.model.DTO.Form;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.*;
import org.springframework.data.annotation.Id;


@Getter
@Data
public class UserJoinFormDto {
    private String userId;
    private String userPwd;
    private String userName;
    @Email(message = "이메일 형식으로 입력해주세요")
    @NotBlank(message = "이메일은 필수 입력 값 입니다.")
    private String userEmail;
    @NotBlank(message = "SVN ID는 필수 입력 값 입니다.")
    private String svnId;
    @NotBlank(message = "부서를 체크해주세요")
    private String department;

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


}
