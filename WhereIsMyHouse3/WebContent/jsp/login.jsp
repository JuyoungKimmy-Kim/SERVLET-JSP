<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>


<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8" />
<meta http-equiv="X-UA-Compatible" content="IE=edge" />
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<title>Document</title>


<body>
	
<%@ include file="header.jsp" %> 
	</br>
	</br>
	</br>
	<div class="container">
		<div class="mb-3">
			<label for="userId" class="form-label">ID</label> <input type="email"
				class="form-control" id="userId" placeholder="아이디를 입력하세요."
				value="ssafy" />
		</div>
		<div class="mb-3">
			<label for="userPassword" class="form-label">Password</label> <input
				type="password" class="form-control" id="userPassword"
				placeholder="비밀번호를 입력하세요." value="1234" />
		</div>

		<div>
		
		
			<button id="btnLogin" type="button" class="btn btn-outline-primary">
				로그인</button>
			<a href="/WhereIsMyHouse3/jsp/register.jsp"
				class="btn btn-outline-success">회원가입</a>
				
				
		</div>
	</div>
	<script>
      window.onload = function () {
        document.querySelector("#btnLogin").onclick = function () {
          let userIdValue = document.querySelector("#userId").value;
          let userPasswordValue = document.querySelector("#userPassword").value;
          console.log(userIdValue, userPasswordValue);

          if (validate()) {
            login();
          } else {
            //유효성 검사 실패에 대한 UI 처리
            alertify.error("아이디 또는 패스워드를 확인하세요.");
          }
        };
      };

      function validate() {
        // return true/false
        let isUserIdValid = false;
        let isUserPasswordValid = false;

        let userIdValue = document.querySelector("#userId").value;
        let userPasswordValue = document.querySelector("#userPassword").value;

        if (userIdValue.length > 0) {
          isUserIdValid = true;
        }
        if (userPasswordValue.length > 0) {
          isUserPasswordValid = true;
        }

        if (isUserIdValid && isUserPasswordValid) {
          return true;
        }
        return false;
      }

      async function login() {

        let userId = document.querySelector("#userId").value;
        let userPassword = document.querySelector("#userPassword").value;

        console.log(userId, userPassword);
        
        //왼쪽 : request.getParameter, 오른쪽 : 실제 위에 있는 value
        let urlParams = new URLSearchParams({
        	act:"login",
          userId: userId,
          userPassword: userPassword,
        });
        let fetchOptions = {
          method: "POST",
          body: urlParams,
        }
        
        //데이터를 받고 싶으면 fetch
        let response = await fetch("<%=contextPath%>/login", fetchOptions);
        let data = await response.json(); 

        if (data.result == "success") { 
        	//화면을 전환하고 싶으면 window.location
        	
        	sessionStorage.setItem("userName", data.userName);
        	window.location.href="<%=contextPath%>/jsp/index.jsp";
        }
      }
          
	</script>
</body>
</html>
