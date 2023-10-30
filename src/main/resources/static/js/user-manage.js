
function confirmApproval(userData) {
    var userName = userData.getAttribute('data-user-name');
    var userNo = userData.getAttribute('data-user-no');

    if (confirm("\""+userName+"\""+" 유저를 승인하시겠습니까?")) {
    // 승인 작업을 수행할 URL로 이동하거나 AJAX 요청을 보낼 수 있습니다.
    // 여기서는 예시로 승인 버튼을 누를 경우 승인 작업을 수행하지 않고 알림창만 표시합니다.
        approveUserData(userNo);
        return "redirect:/";
    } else {
    // 사용자가 확인 대화 상자에서 "취소"를 선택한 경우 아무 작업도 수행하지 않습니다.
        return "redirect:/";
    }
}

function confirmReject(userData) {
    var userName = userData.getAttribute('data-user-name');
    var userNo = userData.getAttribute('data-user-no');
    console.log("여기1");
    if (confirm("\""+userName+"\""+" 유저 승인을 거절하시겠습니까?\n확인을 누르면 해당 유저는 삭제됩니다.")) {
    // 거절 작업을 수행할 URL로 이동하거나 AJAX 요청을 보낼 수 있습니다.
    // 여기서는 예시로 거절 버튼을 누를 경우 거절 작업을 수행하지 않고 알림창만 표시합니다.
        console.log("여기2");
        deleteUserData(userNo);
    } else {
    // 사용자가 확인 대화 상자에서 "취소"를 선택한 경우 아무 작업도 수행하지 않습니다.
        return "redirect:/";
    }
}

function deleteUserData(userNo) {
    // AJAX를 사용하여 서버로 사용자 삭제 요청을 보냅니다
    // jQuery AJAX 예시
    $.ajax({
        url: '/admin/delete-user',
        type: 'POST',
        data: { userId: userNo },
        success: function (data) {
            console.log("여기3");
            // 삭제가 성공한 경우에 대한 처리
        },
        error: function () {
            // 오류 처리
        }
    });
}

function approveUserData(userNo) {
    // AJAX를 사용하여 서버로 사용자 삭제 요청을 보냅니다
    // jQuery AJAX 예시
    $.ajax({
        url: '/admin/approve-user',
        type: 'POST',
        data: { userId: userNo },
        success: function (data) {
            printf("여기야1");
            // 삭제가 성공한 경우에 대한 처리
            if (confirm(data)) {
                // 확인 버튼을 클릭한 경우, 페이지를 다시로드 (리디렉션)
                printf("여기야2");
                // window.location.href = '/admin'; // 원하는 URL로 변경
            }
        },
        error: function () {
            // 오류 처리
        }
    });
}