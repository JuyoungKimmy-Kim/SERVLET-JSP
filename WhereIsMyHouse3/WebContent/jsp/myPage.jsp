<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="board.dao.*"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<%@ include file="header.jsp"%>
	<c:if test="${empty userDto}">
		<script>
	alert("로그인 상태에서 볼 수 있는 페이지입니다.");
	location.href = "<%=contextPath%>/jsp/login.jsp";
	</script>
	</c:if>


	<div class="container" style="text-align: center">
		<div class="mb-3 mt-3 d-flex justify-content-center"></div>
		<div class="mb-3">
			<h2>My Page</h2>
		</div>


		<form novalidate>

			<div class="mb-3">
				<label for="userName" class="form-label">User Name</label> <input
					type="text" class="modify" id="userName" placeholder="이름을 입력하세요."
					value="${userDto.name}" disabled />
				<div class="valid-feedback">Valid</div>
				<div class="invalid-feedback">이름을 4이상 입력하세요.</div>
			</div>



			<div class="mb-3">
				<label for="userEmail" class="form-label">Email</label> <input
					type="email" class="modify" id="userEmail"
					value="${userDto.address}" disabled />
				<div class="valid-feedback">Valid</div>
				<div class="invalid-feedback">올바른 이메일 주소를 입력</div>
			</div>


			<div class="mb-3">
				<label for="userEmail" class="form-label">Phone</label> <input
					type="tel" class="modify" id="userPhone" value="${userDto.phone}"
					disabled />
				<div class="valid-feedback">Valid</div>
				<div class="invalid-feedback">올바른 번호 입력</div>
			</div>

			<%		
				CodeDao codeDao = CodeDaoImpl.getInstance();
				HttpSession s = request.getSession();
				System.out.println(((UserDto) s.getAttribute("userDto")).getCode());

				String codeName = codeDao.getCodeName("001", ((UserDto) s.getAttribute("userDto")).getCode());
				System.out.println(codeName);
			%>

			<div class="mb-3">
				<label for="code" class="form-label">유저 등급</label> <input type="tel"
					class="modify" id="userPhone" value="<%=codeName%>" disabled readonly/>
			</div>
	</div>


	</form>
	<div>
		<div class="col-lg-12 d-flex justify-content-center btn-box">
			<button id="modify" type="button" class="btn btn-primary"
				style="display: none">확인</button>
			<button id="btnModify" type="button" class="btn btn-primary" >
				수정</button> 
			<button id="btnDelete" type="button" class="btn btn-danger" style="margin:0 10px">
				탈퇴</button>
		</div>
	</div>



	<script>
      window.onload = function () {
        document.querySelector("#userName").focus();

        // 유효성 검사 처리
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
        if (userName.length >= 4) return true;
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

      document.querySelector("#btnModify").onclick = function () {
    	  document.querySelectorAll(".modify").forEach((data) => {
    		  data.removeAttribute("disabled")
    	  })
    	    	  
    	 // document.querySelector("#modify").classList.toggle("d-none");
    	 // document.querySelector("#btnModify").classList.toggle("d-none");
    	 
    	 document.querySelector ("#modify").style.display='inline-block';
    	 document.querySelector ("#btnModify").style.display='none';

    	  
    	  
    	
      }
      
      document.querySelector("#btnDelete").onclick = function () {
    	  if(confirm("정말로 탈퇴하실건가요?")) {
    		  del();    		  
    	  }	
      }
      
      async function del(){
    	  let urlParams = new URLSearchParams({
        	  userId: "${userDto.id}",
           
          });
          let fetchOptions = {
            method: "POST",
            body: urlParams,
          };

          //backend에서 login 처리
          let response = await fetch("<%=contextPath%>/user?act=del", fetchOptions);
          let data = await response.json(); //json => javascript object <= JSON.parse();

          if (data.result == "success") {
            //login.jsp => boardMain.jsp로 페이지 이동 (새로운 페이지 요청 (html..))
            alert("탈퇴 완료!");
            window.location.href = "<%=contextPath%>/";
          } else if (data.result == "fail") {
            alert("등록에 실패하였습니다.");
          }

      }
      document.querySelector("#modify").onclick = function () {
    	  
    	  let userName = document.querySelector("#userName").value;
          let userEmailValue = document.querySelector("#userEmail").value;
          
          if (document.querySelectorAll("form .is-invalide").length>0) {
            alert("입력이 올바르지 않습니다.");
          } else {
          modify();
          }
        };

        async function modify() {
            
          let userName = document.querySelector("#userName").value;
          let userEmail = document.querySelector("#userEmail").value;
          let userPhone = document.querySelector("#userPhone").value;

          
          let urlParams = new URLSearchParams({
        	  userId: "${userDto.id}",
            userName: userName,
            userEmail: userEmail,
            userPhone: userPhone,
          });
          let fetchOptions = {
            method: "POST",
            body: urlParams,
          };

          //backend에서 login 처리
          let response = await fetch("<%=contextPath%>/user?act=modify", fetchOptions);
          let data = await response.json(); //json => javascript object <= JSON.parse();

          if (data.result == "success") {
              alert("수정 완료!");

            //login.jsp => boardMain.jsp로 페이지 이동 (새로운 페이지 요청 (html..))
            window.location.href = "<%=contextPath%>/";
          } else if (data.result == "fail") {
            alert("등록에 실패하였습니다.");
          }
        }

      
     
	</script>

	<%@ include file="footer.jsp"%>



</body>
</html>






