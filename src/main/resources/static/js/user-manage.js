
function confirmApproval(userData) {
    var userName = userData.getAttribute('data-user-name');
    var userNo = userData.getAttribute('data-user-no');
    var form = userData.closest("form");


    if (confirm("\""+userName+"\""+" 유저를 승인하시겠습니까?")) {
    // 승인 작업을 수행할 URL로 이동하거나 AJAX 요청을 보낼 수 있습니다.
    // 여기서는 예시로 승인 버튼을 누를 경우 승인 작업을 수행하지 않고 알림창만 표시합니다.
        alertRetrunValue(form);
    } else {
    // 사용자가 확인 대화 상자에서 "취소"를 선택한 경우 아무 작업도 수행하지 않습니다.
        window.location.href = "/admin";
    }


}

function alertRetrunValue(form) {
    var formData = new FormData(form);
    fetch(form.action, {
        method: 'POST',
        body: formData
    })
        .then(response => response.text())
        .then(data => {
            alert(data); // 서버에서 반환한 결과 메시지를 alert 창에 표시
            if (data === "사용자 승인을 성공했습니다." || data === "사용자 승인거절(삭제)이 성공했습니다.") {
                // 서버에서 반환한 메시지에 따라 리다이렉션
                window.location.href = "/admin"; // 원하는 URL로 리다이렉션
            }
        })
        .catch(error => {
            console.error('Error:', error);
        });
}
function confirmReject(userData) {
    var userName = userData.getAttribute('data-user-name');
    var form = userData.closest("form");

    if (confirm("\""+userName+"\""+" 유저 승인을 거절하시겠습니까?\n확인을 누르면 해당 유저는 삭제됩니다.")) {
    // 거절 작업을 수행할 URL로 이동하거나 AJAX 요청을 보낼 수 있습니다.
    // 여기서는 예시로 거절 버튼을 누를 경우 거절 작업을 수행하지 않고 알림창만 표시합니다.
        alertRetrunValue(form);
    } else {
    // 사용자가 확인 대화 상자에서 "취소"를 선택한 경우 아무 작업도 수행하지 않습니다.
        window.location.href = "/admin";
    }
}


