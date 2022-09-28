<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.util.List"%>
<%@ page import="board.dto.*"%>
<%@ page import="board.dao.*"%>

<!DOCTYPE html>

<html lang="en">
<head>

<title>Register</title>
</head>
<body>


	<%@ include file="header.jsp"%>
	<div class="container">
		<div class="mb-3 mt-3 d-flex justify-content-center"></div>
		<div class="mb-3">
			<h2>Register</h2>
		</div>

		<form novalidate>

			<div class="mb-3">
				<label for="userId" class="form-label">User Name</label> <input
					type="text" class="form-control" id="userId"
					placeholder="아이디를 입력하세요." />
				<div class="valid-feedback">Valid</div>
				<div class="invalid-feedback">아이디를 4이상 입력하세요.</div>
			</div>


			<div class="mb-3">
				<label for="userPassword" class="form-label">Password</label> <input
					type="password" class="form-control" id="userPassword"
					placeholder="비밀번호를 입력하세요." />

				<div class="valid-feedback">Valid</div>
				<div class="invalid-feedback">1개 이상 특수문자, 영문, 숫자를 포함하고 길이는 8이상
				</div>
			</div>
			<div class="mb-3">
				<label for="userPassword2" class="form-label">Password
					Confirm</label> <input type="password" class="form-control"
					id="userPassword2" placeholder="비밀번호를 다시 입력하세요." />
				<div class="valid-feedback">Valid</div>
				<div class="invalid-feedback">패스워드를 다시 입력하세요.</div>
			</div>

			<div class="mb-3">
				<label for="userName" class="form-label">User Name</label> <input
					type="text" class="form-control" id="userName"
					placeholder="이름을 입력하세요." />
				<div class="valid-feedback">Valid</div>
				<div class="invalid-feedback">이름을 3글자 이상 입력하세요.</div>
			</div>


			<div class="mb-3">
				<label for="userEmail" class="form-label">Email</label> <input
					type="email" class="form-control" id="userEmail"
					placeholder="이메일을 입력하세요." />
				<div class="valid-feedback">Valid</div>
				<div class="invalid-feedback">올바른 이메일 주소를 입력</div>
			</div>

			<div class="mb-3">
				<label for="userPhone" class="form-label">PhoneNumber</label> <input
					type="tel" class="form-control" id="userPhone"
					placeholder="휴대폰 번호을 입력하세요." />
				<div class="valid-feedback">Valid</div>
				<div class="invalid-feedback">올바른 휴대폰 번호를 입력</div>

			</div>

			<div class="mb-3">
			<label for="code" class="form-label">회원 등급 선택</label>
			<div class="form-check">
			  
				<%
					CodeDao codeDao = CodeDaoImpl.getInstance();
					List<CodeDto> codeList = codeDao.getCodeList("001");
					if (codeList != null) {
						for (CodeDto code : codeList) {
				%>

				<input class="form-check-input" type=radio value=<%=code.getCode()%>
					id="code" name='code'> <label class="form-check-label"
					for="code"><%=code.getCodeName()%></label>
			</div>
			<%
				}
				}
			%>
			
			<div>
				<button id="btnRegister" type="button" class="btn btn-primary">
					회원가입</button>
			</div>
			</div>
		</form>
	</div>

	<script>
      window.onload = function () {
        document.querySelector("#userId").focus();

        // 유효성 검사 처리
        
        document.querySelector("#userId").onblur = function () {
          if (validateUserName(this.value)) {
            // 유효
            this.classList.remove("is-invalid");
            this.classList.add("is-valid");
          } else {
            // 유효 X
            this.classList.remove("is-valid");
            this.classList.add("is-invalid");
          }
        };
        
        
        document.querySelector("#userName").onblur = function () {
          if (validateUserName(this.value)) {
            // 유효
            this.classList.remove("is-invalid");
            this.classList.add("is-valid");
          } else {
            // 유효 X
            this.classList.remove("is-valid");
            this.classList.add("is-invalid");
          }
        };

        document.querySelector("#userPassword").onblur = function () {
          if (validatePassword(this.value)) {
            // 유효
            this.classList.remove("is-invalid");
            this.classList.add("is-valid");
          } else {
            // 유효 X
            this.classList.remove("is-valid");
            this.classList.add("is-invalid");
          }
        };

        document.querySelector("#userPassword2").onblur = function () {
          if (validatePassword2(this.value)) {
            // 유효
            this.classList.remove("is-invalid");
            this.classList.add("is-valid");
          } else {
            // 유효 X
            this.classList.remove("is-valid");
            this.classList.add("is-invalid");
          }
        };

        document.querySelector("#userEmail").onblur = function () {
          if (validateUserEmail(this.value)) {
            // 유효
            this.classList.remove("is-invalid");
            this.classList.add("is-valid");
          } else {
            // 유효 X
            this.classList.remove("is-valid");
            this.classList.add("is-invalid");
          }
        };
      };

      
      // 길이가 4이상이면 valid
      function validateUserName(userName) {
        if (userName.length >= 3) return true;
        else return false;
      }

      function validatePassword(userPassword) {
        var patternEngAtListOne = new RegExp(/[a-zA-Z]+/); // + for at least one
        var patternSpeAtListOne = new RegExp(/[~!@#$%^&()+|<>?:{}]+/); // + for at least one
        var patternNumAtListOne = new RegExp(/[0-9]+/); // + for at least one

        if (
          patternEngAtListOne.test(userPassword) &&
          patternSpeAtListOne.test(userPassword) &&
          patternNumAtListOne.test(userPassword) &&
          userPassword.length >= 8
        ) {
          return true;
        } else return false;
      }

      function validatePassword2(userPassword2) {
        if (userPassword2.length == 0) return false;
        if (userPassword2 == document.querySelector("#userPassword").value)
          return true;
        else return false;
      }

      function validateUserEmail(userEmail) {
        // ^ 시작일치, $ 끝 일치
        // {2, 3} 2개 ~ 3개
        // * 0회 이상, + 1회 이상
        // [-_.] - 또는 _ 또는 .
        // ? 없거나 1회
        let regexp =
          /^[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*.[a-zA-Z]{2,3}$/i;
        if (regexp.test(userEmail)) return true;
        else return false;
      }

      document.querySelector("#btnRegister").onclick = function () {
        let userName = document.querySelector("#userName").value;
        let userEmailValue = document.querySelector("#userEmail").value;
        let userPasswordValue = document.querySelector("#userPassword").value;

        if (document.querySelectorAll("form .is-invalid").length>0) {
          alert("입력이 올바르지 않습니다.");
        } else {
        register();
        }
      };

      async function register() {
    	let userId = document.querySelector("#userId").value;
    	let userName = document.querySelector("#userName").value;
        let userEmail = document.querySelector("#userEmail").value;
        let userPassword = document.querySelector("#userPassword").value;
        let userPhone = document.querySelector("#userPhone").value;
        //let userCode = document.querySelector ("#code option:checked");
        let code=document.querySelector("input[name='code']:checked").value;
        console.log(code);
        
        let urlParams = new URLSearchParams({
        	act:"register",
        	userId: userId,
          userName: userName,
          userEmail: userEmail,
          userPassword: userPassword,
          userPhone: userPhone,
          code: code,
        });
        let fetchOptions = {
          method: "POST",
          body: urlParams,
        };

        //backend에서 login 처리
        let response = await fetch("<%=contextPath%>/register", fetchOptions);
        let data = await response.json(); //json => javascript object <= JSON.parse();

        if (data.result == "success") { 
          
        	alertify.alert("Welcome!", "회원가입을 축하합니다. 로그인 페이지로 이동합니다.", function () {
                window.location.href = "<%=contextPath%>/jsp/login.jsp";
								})
			} else if (data.result == "fail") {

				alert("서버 오류!");
			}
		}

		//GET
	</script>
</body>
</html>
