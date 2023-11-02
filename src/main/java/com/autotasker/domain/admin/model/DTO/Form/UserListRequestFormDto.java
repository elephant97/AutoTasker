package com.autotasker.domain.admin.model.DTO.Form;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import lombok.*;

@Data
@NoArgsConstructor
@Getter
public class UserListRequestFormDto {

    //userName, userId, userNo는 변경할 수 없는 값
    private String userPwd;

    @Email(message = "이메일 형식으로 입력해주세요")
    @NotBlank(message = "이메일은 필수 입력 값 입니다.")
    private String userEmail;

    @NotBlank(message = "SVN ID는 필수 입력 값 입니다.")
    private String svnId;

    @NotBlank(message = "부서를 체크해주세요")
    private String department;



    @Builder
    public UserListRequestFormDto(String userEmail, String userPwd, String svnId, String department) {
//        this.userName = userName;
//        this.userId = userId;
        this.userPwd = userPwd;
        this.userEmail = userEmail;
        this.svnId = svnId;
        this.department = department;
    }
}
