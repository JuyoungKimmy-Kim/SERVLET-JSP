<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>

<body>



<%@ include file="header.jsp" %> 

   <form novalidate>
        <div class="mb-3">
          <label for="userPassword" class="form-label">Password</label>
          <input
            type="password"
            class="form-control"
            id="userPassword"
            placeholder="새 비밀번호를 입력하세요."
          />
          <div class="valid-feedback">Valid</div>
          <div class="invalid-feedback">
            1개 이상 특수문자, 영문, 숫자를 포함하고 길이는 8이상
          </div>
        </div>
        <div class="mb-3">
          <label for="userPassword2" class="form-label">Password Confirm</label>
          <input
            type="password"
            class="form-control"
            id="userPassword2"
            placeholder="비밀번호를 다시 입력하세요."
          />
          <div class="valid-feedback">Valid</div>
          <div class="invalid-feedback">패스워드를 다시 입력하세요.</div>
        </div>
        
      </form>

      <div>
        <button id="btnUpdate" type="button" class="btn btn-primary">
          비밀번호 변경
        </button>
      </div>
   <script>
      window.onload = function () {
        document.querySelector("#userPassword").focus();


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



      document.querySelector("#btnUpdate").onclick = function () {
    	  
        let userPasswordValue = document.querySelector("#userPassword").value;

        if (document.querySelectorAll("form .is-invalide").length>0) {
          alert("입력이 올바르지 않습니다.");
        } else {
        update();
        }
      };

      async function update() {
        let userPassword = document.querySelector("#userPassword").value;

        let urlParams = new URLSearchParams({
        	  userId: "${userDto.id}",
              
          userPassword: userPassword,
        });
        let fetchOptions = {
          method: "POST",
          body: urlParams,
        };

        //backend에서 login 처리
        let response = await fetch("<%=contextPath%>/user?act=modifyPw", fetchOptions);
        let data = await response.json(); //json => javascript object <= JSON.parse();

        if (data.result == "success") {
          //login.jsp => boardMain.jsp로 페이지 이동 (새로운 페이지 요청 (html..))
          alert("비밀번호 변경 완료! 다시 로그인 하세요!")
          window.location.href = "<%=contextPath%>/jsp/login.jsp";
        } else if (data.result == "fail") {
          alert("등록에 실패하였습니다.");
        }
      }}
      
    </script>
   
<%@ include file="footer.jsp" %>    


</body>
</html>



