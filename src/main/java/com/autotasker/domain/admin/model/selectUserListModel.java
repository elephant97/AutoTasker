package com.autotasker.domain.admin.model;

import lombok.Data;

@Data
public class selectUserListModel {
    /**
     * [UserList Table 데이터 조회 클래스]
     * */

    private String userName;
    private String userEmail;
    private String svnId;
    private String department;
    private String approvalStaus;

    public selectUserListModel(String UserName, String UserEmail, String SvnId, String Department, String ApprovalStatus)
    {
        this.approvalStaus=ApprovalStatus;
        this.svnId=SvnId;
        this.userEmail=UserEmail;
        this.department=Department;
        this.userName=UserName;
    }

}
