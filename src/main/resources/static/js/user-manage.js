

//Spring security 인증 Tokken
var csrfToken = $( 'meta[name="_csrf"]' ).attr( 'content' );
var csrfHeader = $( 'meta[name="_csrf_header"]' ).attr( 'content' );


document.getElementById("edit-save-button").addEventListener("click",function (){
    let userNo = this.getAttribute('userNo');
    let userEditForm = this.getAttribute('userEditForm');
    if (confirm("저장 하시겠습니까?")) {
        saveAction(userNo,userEditForm);
    }
});

function saveAction(userNo, userEditForm)
{
    var $slim = jQuery.noConflict(true);
    jQuery(function ($) {
        $.ajax({
            type: 'POST',
            url: 'admin/edit-user', // Ajax 요청을 보낼 URL
            data: {userNo: userNo,
                userListRequestFormDto: userEditForm
            }, // 요청에 필요한 데이터
            beforeSend: function(xhr) {
                xhr.setRequestHeader(csrfHeader, csrfToken);
            },
            success: function (response) {
                alert(response);
                // return;
            },
            error: function () {
                alert("처리 중 오류가 발생했습니다.");
                // return;
            }
        });
    });

}
document.querySelectorAll('.approval-button').forEach(function(button) {
    button.addEventListener("click", function(event) {
        let action = this.getAttribute('do-action');
        let userName = this.getAttribute('user-name');
        let userNo = this.getAttribute('user-no');
        var confirmationMessage = (action === 'delete') ? "유저 승인을 거절하시겠습니까?\n확인을 누르면 해당 유저는 삭제됩니다." : "유저를 승인하시겠습니까?";
        if (confirm("\"" + userName + "\"" + confirmationMessage)) {
            approveAction(userNo, action);
        }
    });
});


function approveAction(userkey, doAction) {
    let action = (doAction ==='delete') ? '/admin/delete-user' : '/admin/approve-user';
    var $slim = jQuery.noConflict(true);

    jQuery(function ($) {
        $.ajax({
            type: 'POST',
            url: action, // Ajax 요청을 보낼 URL
            data: {userNo: userkey}, // 요청에 필요한 데이터
            beforeSend: function(xhr) {
                xhr.setRequestHeader(csrfHeader, csrfToken);
            },
            success: function (response) {
                alert(response);
                // return;
            },
            error: function () {
                alert("처리 중 오류가 발생했습니다.");
                // return;
            }
        });
    });
}


// 모달 열기
document.querySelectorAll('tr[id="userListTable"]').forEach(function (element) {
    element.addEventListener('click', function () {
        var userData = $(this).children(); // JSON 형태의 데이터
        // var user = JSON.parse(userData);
        // JavaScript로 데이터 설정td.eq(0).text()
        console.log("하이" + userData.eq(0).val());
        document.getElementById("editNo").value =  userData.eq(0).val();
        document.getElementById("userId").value = userData.eq(1).text();
        document.getElementById("userPwd").value = userData.eq(2).text();
        document.getElementById("userEmail").value = userData.eq(3).text();
        document.getElementById("svnId").value = userData.eq(4).text();

        var departmentSelect = document.getElementById("department");
        var selectedValue =  userData.eq(5).text(); // 선택하려는 option의 value

        for (var i = 0; i < departmentSelect.options.length; i++) {
            if (departmentSelect.options[i].value === selectedValue) {
                departmentSelect.selectedIndex = i;
                break;
            }
        }

        $(document).ready(function () {
            $("#userEditModal").modal("show");
        });
        // 모달 창을 열기
    });
});


// 모달 닫기
document.addEventListener("click", function (e) {
    if (e.target.classList.contains("close")) {
        var modal = e.target.closest(".modal");
        modal.style.display = "none";
    }
});