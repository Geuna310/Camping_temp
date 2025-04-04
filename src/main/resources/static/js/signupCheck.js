function checkDuplicate(field, inputSelector) {
    let value = $(inputSelector).val().trim();

    if (value === "") {
        alert(getEmptyFieldMessage(field));
        return;
    }

    $.ajax({
        url: `/user/check-duplicate`,
        type: "GET",
        data: { field: field, value: value },
        success: function(isDuplicate) {
            alert(getFieldMessage(field, isDuplicate));
        },
        error: function() {
            alert("중복 확인 중 오류가 발생했습니다.");
        }
    });
}

// 필드별 맞춤 메시지 반환
function getFieldMessage(field, isDuplicate) {
    if (isDuplicate) {
        switch (field) {
            case "userId": return "이미 사용 중인 아이디입니다. 다른 아이디를 입력해주세요.";
            case "nickname": return "이 닉네임은 이미 사용 중입니다. 새로운 닉네임을 선택해주세요.";
            case "email": return "이 이메일은 이미 등록되어 있습니다. 다른 이메일을 사용해주세요.";
            case "phoneNumber": return "이 전화번호는 이미 사용 중입니다. 정확한 번호인지 확인해주세요.";
            default: return "이미 사용 중인 값입니다.";
        }
    } else {
        switch (field) {
            case "userId": return "사용 가능한 아이디입니다.";
            case "nickname": return "사용 가능한 닉네임입니다.";
            case "email": return "사용 가능한 이메일입니다.";
            case "phoneNumber": return "사용 가능한 전화번호입니다.";
            default: return "사용 가능한 값입니다.";
        }
    }
}

// 입력값이 비어 있을 경우 메시지 반환
function getEmptyFieldMessage(field) {
    switch (field) {
        case "userId": return "아이디를 입력해주세요.";
        case "nickname": return "닉네임을 입력해주세요.";
        case "email": return "이메일을 입력해주세요.";
        case "phoneNumber": return "전화번호를 입력해주세요.";
        default: return "값을 입력해주세요.";
    }
}
