package com.autotasker.domain.admin.model.DTO.Form;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Data
@NoArgsConstructor
@Getter
public class UserListRequestFromDTO{

    @NotEmpty
    private String userPwd;
    @Email
    @NotEmpty
    private String userEmail;

    @NonNull
    private String svnId;
}
