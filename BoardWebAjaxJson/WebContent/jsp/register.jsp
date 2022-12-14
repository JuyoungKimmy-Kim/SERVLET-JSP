<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
String contextPath=request.getContextPath();
%>

<!DOCTYPE html>

<html lang="en">
  <head>
    <meta charset="UTF-8" />
    <meta http-equiv="X-UA-Compatible" content="IE=edge" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <link
      href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css"
      rel="stylesheet"
      integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC"
      crossorigin="anonymous"
    />
    <script
      src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.9.2/dist/umd/popper.min.js"
      integrity="sha384-IQsoLXl5PILFhosVNubq5LC7Qb9DXgDA9i+tQ8Zj3iwWAwPtgFTxbJ8NT4GN1R8p"
      crossorigin="anonymous"
    ></script>
    <script
      src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.min.js"
      integrity="sha384-cVKIPhGWiC2Al4u+LWgxfKTRIcfu0JTxR+EQDz/bgldoEyl4H0zUF0QKbrJ0EcQF"
      crossorigin="anonymous"
    ></script>

	<!-- JavaScript -->
	<script src="//cdn.jsdelivr.net/npm/alertifyjs@1.13.1/build/alertify.min.js"></script>
	
	<!-- CSS -->
	<link rel="stylesheet" href="//cdn.jsdelivr.net/npm/alertifyjs@1.13.1/build/css/alertify.min.css"/>
	<!-- Default theme -->
	<link rel="stylesheet" href="//cdn.jsdelivr.net/npm/alertifyjs@1.13.1/build/css/themes/default.min.css"/>
	<!-- Semantic UI theme -->
	<link rel="stylesheet" href="//cdn.jsdelivr.net/npm/alertifyjs@1.13.1/build/css/themes/semantic.min.css"/>
	<!-- Bootstrap theme -->
	<link rel="stylesheet" href="//cdn.jsdelivr.net/npm/alertifyjs@1.13.1/build/css/themes/bootstrap.min.css"/>
	

    <title>Register</title>
  </head>
  <body>
    <div class="container">
      <div class="mb-3 mt-3 d-flex justify-content-center">
        <h1>Welcome to Juyoung's world</h1>
      </div>
      <div class="mb-3">
        <h2>Register</h2>
      </div>

      <form novalidate>
        <div class="mb-3">
          <label for="userName" class="form-label">User Name</label>
          <input
            type="text"
            class="form-control"
            id="userName"
            placeholder="????????? ???????????????."
          />
          <div class="valid-feedback">Valid</div>
          <div class="invalid-feedback">????????? 4?????? ???????????????.</div>
        </div>
        <div class="mb-3">
          <label for="userPassword" class="form-label">Password</label>
          <input
            type="password"
            class="form-control"
            id="userPassword"
            placeholder="??????????????? ???????????????."
          />
          <div class="valid-feedback">Valid</div>
          <div class="invalid-feedback">
            1??? ?????? ????????????, ??????, ????????? ???????????? ????????? 8??????
          </div>
        </div>
        <div class="mb-3">
          <label for="userPassword2" class="form-label">Password Confirm</label>
          <input
            type="password"
            class="form-control"
            id="userPassword2"
            placeholder="??????????????? ?????? ???????????????."
          />
          <div class="valid-feedback">Valid</div>
          <div class="invalid-feedback">??????????????? ?????? ???????????????.</div>
        </div>
        <div class="mb-3">
          <label for="userEmail" class="form-label">Email</label>
          <input
            type="email"
            class="form-control"
            id="userEmail"
            placeholder="???????????? ???????????????."
          />
          <div class="valid-feedback">Valid</div>
          <div class="invalid-feedback">????????? ????????? ????????? ??????</div>
        </div>
      </form>

      <div>
        <button id="btnRegister" type="button" class="btn btn-primary">
          ????????????
        </button>
      </div>
    </div>

    <script>
      window.onload = function () {
        document.querySelector("#userName").focus();

        // ????????? ?????? ??????
        document.querySelector("#userName").onblur = function () {
          if (validateUserName(this.value)) {
            // ??????
            this.classList.remove("is-invalid");
            this.classList.add("is-valid");
          } else {
            // ?????? X
            this.classList.remove("is-valid");
            this.classList.add("is-invalid");
          }
        };

        document.querySelector("#userPassword").onblur = function () {
          if (validatePassword(this.value)) {
            // ??????
            this.classList.remove("is-invalid");
            this.classList.add("is-valid");
          } else {
            // ?????? X
            this.classList.remove("is-valid");
            this.classList.add("is-invalid");
          }
        };

        document.querySelector("#userPassword2").onblur = function () {
          if (validatePassword2(this.value)) {
            // ??????
            this.classList.remove("is-invalid");
            this.classList.add("is-valid");
          } else {
            // ?????? X
            this.classList.remove("is-valid");
            this.classList.add("is-invalid");
          }
        };

        document.querySelector("#userEmail").onblur = function () {
          if (validateUserEmail(this.value)) {
            // ??????
            this.classList.remove("is-invalid");
            this.classList.add("is-valid");
          } else {
            // ?????? X
            this.classList.remove("is-valid");
            this.classList.add("is-invalid");
          }
        };
      };

      // ????????? 4???????????? valid
      function validateUserName(userName) {
        if (userName.length >= 4) return true;
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
        // ^ ????????????, $ ??? ??????
        // {2, 3} 2??? ~ 3???
        // * 0??? ??????, + 1??? ??????
        // [-_.] - ?????? _ ?????? .
        // ? ????????? 1???
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
          alert("????????? ???????????? ????????????.");
        } else {
        register();
        }
      };

      async function register() {
        let userName = document.querySelector("#userName").value;
        let userEmail = document.querySelector("#userEmail").value;
        let userPassword = document.querySelector("#userPassword").value;

        let urlParams = new URLSearchParams({
          userName: userName,
          userEmail: userEmail,
          userPassword: userPassword,
        });
        let fetchOptions = {
          method: "POST",
          body: urlParams,
        };

        //backend?????? login ??????
        let response = await fetch("<%=contextPath%>/register", fetchOptions);
        let data = await response.json(); //json => javascript object <= JSON.parse();

        if (data.result == "success") { 
          
        	alertify.alert("Welcome!", "??????????????? ???????????????. ????????? ???????????? ???????????????.", function () {
                window.location.href = "<%=contextPath%>/jsp/login.jsp";
        	})
        } else if (data.result == "fail") {

          alert("?????? ??????!");
        }
      }
    </script>
  </body>
</html>
