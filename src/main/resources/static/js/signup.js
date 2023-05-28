// var idChk = 0;

function checkId() {
    console.log("checking Id...");
    const id = $('#userId').val(); //id값이 "userId"인 입력란의 값을 저장
    if (!/^[a-z0-9_-]{5,15}$/g.test(id)) {
        alert("아이디는 5~15자의 영문소문자, 숫자, '_', '-' 만 가능합니다");
        return;
    }
    $.ajax({
        url: "idCheck", //Controller에서 요청 받을 주소
        type: "post", //POST 방식으로 전달
        data: id,
        dataType: "json", //서버로 돌려받는 값의 타입 지정
        //서버로 보낼 데이터 설정
        contentType: "application/json; charset=utf-8",
        success: function (data) {
            if (data == true) {
                alert("아이디가 존재합니다. 다른 아이디를 입력해 주세요");
                $('.chk-btn').addClass("has-error");
                $('.chk-btn').removeClass("has-success");
                $("#userId").focus();
            } else {
                alert("사용가능한 아이디입니다");
                $('.chk-btn').addClass("has-success");
                $('.chk-btn').removeClass("has-error");
                $("#password").focus();
                idChk = 1;
            }
        },
        error: function (error) {
            alert("아이디를 다시 입력해 주세요");
        }
    });
};

function pwCheck() {
    console.log("checking pw...");
    const p1 = document.getElementById('password').value;
    const p2 = document.getElementById('password2').value;
    const pattern = /^(?=.*[A-Za-z])(?=.*\d)(?=.*[!@#$%^&*])[A-Za-z\d!@#$%^&*]{8,20}$/;

    document.getElementById('pwValid').innerHTML = ("비밀번호는 영문자, 숫자, 특수문자(!@#$%^&*)를 모두 포함하고 8자 이상 20자 이하여야 합니다.");
    if (pattern.test(p1)) {
        document.getElementById('pwValid').innerHTML = '사용가능한 비밀번호입니다';
        if (p1 != p2) {
            document.getElementById('pwSame').innerHTML = '비밀번호가 일치하지 않습니다.';
            document.getElementById('pwSame').style.color = 'red';
            return false;
        } else {
            document.getElementById('pwSame').innerHTML = '비밀번호가 일치합니다.';
            document.getElementById('pwSame').style.color = 'blue';
            return true;
        }
    }
}


let nickChk = 0;

function checkNickName() {
    const id = $('#nickName').val(); //id값이 "nickName"인 입력란의 값을 저장
    alert(id);
    $.ajax({
        url: "/nickNameCheck", //Controller에서 요청 받을 주소 앞에 '/' 꼭 붙여서 절대경로로 쓰기
        type: "post", //POST 방식으로 전달
        data: id,
        dataType: "json", //서버로 돌려받는 값의 타입 지정
        //서버로 보낼 데이터 설정
        contentType: "application/json; charset=utf-8",
        success: function (data) {
            if (data == true) {
                alert("닉네임이 존재합니다. 다른 닉네임을 입력해 주세요");
                $('.chk-btn').addClass("has-error");
                $('.chk-btn').removeClass("has-success");
                $("#nickName").focus();
            } else {
                alert("사용가능한 닉네임입니다");
                $('.chk-btn').addClass("has-success");
                $('.chk-btn').removeClass("has-error");
                $("#name").focus();
                nickChk = 1;
            }
        },
        error: function (error) {
            alert("닉네임을 다시 입력해 주세요");
        }
    });
}


$('#mail-Check-Btn').click(function () {
    const email = $('#email1').val() + $('#email2').val(); // 이메일 주소값 얻어오기!
    const checkInput = $('.mail-check-input') // 인증번호 입력하는곳

    $.ajax({
        type: 'get',
        url: /*[[${#httpServletRequest.requestURL}]]*/ 'mailCheck?email=' + email, // GET방식이라 Url 뒤에 email을 뭍힐수있다.
        success: function (data) {
            console.log("data : " + data);
            checkInput.attr('disabled', false);
            code = data;
            alert('인증번호가 전송되었습니다.')
        }
    }); // end ajax
}); // end send eamil

// 인증번호 비교
// blur -> focus가 벗어나는 경우 발생
$('.mail-check-input').blur(function () {
    const inputCode = $(this).val();
    const $resultMsg = $('#mail-check-warn');

    if (inputCode === code) {
        $resultMsg.html('인증번호가 일치합니다.');
        $resultMsg.css('color', 'green');
        $('#mail-Check-Btn').attr('disabled', true);
        $('#email1').attr('readonly', true);
        $('#email2').attr('readonly', true);
        $('#email2').attr('onFocus', 'this.initialSelect = this.selectedIndex');
        $('#email2').attr('onChange', 'this.selectedIndex = this.initialSelect');
    } else {
        $resultMsg.html('인증번호가 불일치 합니다. 다시 확인해주세요!');
        $resultMsg.css('color', 'red');
    }
});

function execDaumPostcode() {
    new daum.Postcode({
        oncomplete: function (data) {
            // 팝업에서 검색결과 항목을 클릭했을때 실행할 코드를 작성하는 부분.

            // 각 주소의 노출 규칙에 따라 주소를 조합한다.
            // 내려오는 변수가 값이 없는 경우엔 공백('')값을 가지므로, 이를 참고하여 분기 한다.
            var fullAddr = ''; // 최종 주소 변수
            var extraAddr = ''; // 조합형 주소 변수

            // 사용자가 선택한 주소 타입에 따라 해당 주소 값을 가져온다.
            if (data.userSelectedType === 'R') { // 사용자가 도로명 주소를 선택했을 경우
                fullAddr = data.roadAddress;
            } else { // 사용자가 지번 주소를 선택했을 경우(J)
                fullAddr = data.jibunAddress;
            }

            // 사용자가 선택한 주소가 도로명 타입일때 조합한다.
            if (data.userSelectedType === 'R') {
                //법정동명이 있을 경우 추가한다.
                if (data.bname !== '') {
                    extraAddr += data.bname;
                }
                // 건물명이 있을 경우 추가한다.
                if (data.buildingName !== '') {
                    extraAddr += (extraAddr !== '' ? ', ' + data.buildingName : data.buildingName);
                }
                // 조합형주소의 유무에 따라 양쪽에 괄호를 추가하여 최종 주소를 만든다.
                fullAddr += (extraAddr !== '' ? ' (' + extraAddr + ')' : '');
            }

            // 우편번호와 주소 정보를 해당 필드에 넣는다.
            document.querySelector('input[name=zipcode]').value = data.zonecode; //5자리 새우편번호 사용
            document.querySelector('input[name=address1]').value = fullAddr;

            // 커서를 상세주소 필드로 이동한다.
            document.querySelector('input[name=address2]').focus();
        }
    }).open();
}

document.addEventListener("DOMContentLoaded", function () {

    document.querySelector('.btn-submit').addEventListener('click', function () {
        const emailInput = document.querySelector('.emailInput');
        const email1 = document.querySelector('#email1');
        const email2 = document.querySelector('#email2');

        emailInput.value = email1.value + email2.value;

    });
})