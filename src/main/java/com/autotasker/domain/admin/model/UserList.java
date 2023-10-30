package com.autotasker.domain.admin.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class UserList {
    /**
     * [UserList Table 데이터 조회 클래스]
     * */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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
}
