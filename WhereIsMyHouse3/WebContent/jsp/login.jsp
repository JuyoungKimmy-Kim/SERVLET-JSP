<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
	String contextPath = request.getContextPath();
%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8" />
<meta http-equiv="X-UA-Compatible" content="IE=edge" />
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<title>Document</title>

<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC"
	crossorigin="anonymous" />
<script
	src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js"
	integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM"
	crossorigin="anonymous"></script>

<script
	src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.9.2/dist/umd/popper.min.js"
	integrity="sha384-IQsoLXl5PILFhosVNubq5LC7Qb9DXgDA9i+tQ8Zj3iwWAwPtgFTxbJ8NT4GN1R8p"
	crossorigin="anonymous"></script>
<script
	src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.min.js"
	integrity="sha384-cVKIPhGWiC2Al4u+LWgxfKTRIcfu0JTxR+EQDz/bgldoEyl4H0zUF0QKbrJ0EcQF"
	crossorigin="anonymous"></script>

<!-- JavaScript -->
<script
	src="//cdn.jsdelivr.net/npm/alertifyjs@1.13.1/build/alertify.min.js"></script>

<!-- CSS -->
<link rel="stylesheet"
	href="//cdn.jsdelivr.net/npm/alertifyjs@1.13.1/build/css/alertify.min.css" />
<!-- Default theme -->
<link rel="stylesheet"
	href="//cdn.jsdelivr.net/npm/alertifyjs@1.13.1/build/css/themes/default.min.css" />
<!-- Semantic UI theme -->
<link rel="stylesheet"
	href="//cdn.jsdelivr.net/npm/alertifyjs@1.13.1/build/css/themes/semantic.min.css" />
<!-- Bootstrap theme -->
<link rel="stylesheet"
	href="//cdn.jsdelivr.net/npm/alertifyjs@1.13.1/build/css/themes/bootstrap.min.css" />


</head>
<body>
	<!-- 상단 navbar start -->
	<nav class="navbar navbar-expand-lg navbar-dark bg-dark">
		<div class="container-fluid">
			<a class="navbar-brand" href="#">Where is my home ? </a>
			<button class="navbar-toggler" type="button"
				data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent"
				aria-controls="navbarSupportedContent" aria-expanded="false"
				aria-label="Toggle navigation">
				<span class="navbar-toggler-icon"></span>
			</button>
			<div class="collapse navbar-collapse" id="navbarSupportedContent">
				<ul class="navbar-nav me-auto mb-2 mb-lg-0">
					<li class="nav-item"><a class="nav-link active"
						aria-current="page" href="../index.jsp">Home</a></li>
				</ul>
			</div>
		</div>
		<br />
	</nav>
	<!-- 상단 navbar end -->

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
        	window.location.href="<%=contextPath%>/jsp/index.jsp";
        }
      }
          
	</script>
</body>
</html>
