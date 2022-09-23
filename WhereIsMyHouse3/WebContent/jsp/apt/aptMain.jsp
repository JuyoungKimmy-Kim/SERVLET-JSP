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




<link rel="stylesheet"
	href="https://fonts.googleapis.com/css?family=Trirong" />

<style>
.navbar-brand, .nav-link active, .row {
	font-family: "Trirong", serif;
}

#carouselExampleCaptions {
	height: 30%;
}

.row, footer {
	font-family: 'Noto Sans KR', sans-serif;
}

footer {
	color: black;
	clear: both;
	margin: 10px auto;
	padding: 10px;
	max-width: 1200px;
	height: 70px;
}

footer>ul>li {
	float: left;
	display: inline-block;
	margin-right: 20px;
	height: 50px;
	line-height: 50px;
}
</style>
</head>

<body>
<!-- 상단 navbar start -->
<nav class="navbar navbar-expand-lg navbar-dark bg-dark">
	<div class="container-fluid">
		<a class="navbar-brand" href="#">Where is my home ? </a>
		<button class="navbar-toggler" type="button" data-bs-toggle="collapse"
			data-bs-target="#navbarSupportedContent"
			aria-controls="navbarSupportedContent" aria-expanded="false"
			aria-label="Toggle navigation">
			<span class="navbar-toggler-icon"></span>
		</button>
		<div class="collapse navbar-collapse" id="navbarSupportedContent">
			<ul class="navbar-nav me-auto mb-2 mb-lg-0">
				<li class="nav-item"><a class="nav-link active"
					aria-current="page" href="../index.jsp">Home</a></li>
			</ul>
			<form class="d-flex">
				<input class="form-control me-2" type="search" id="inputSearchWord" placeholder="아파트 이름으로 검색하기"
					aria-label="Search" />
				<button class="btn btn-outline-success" id="btnSearchWord" type="button">
					Search</button>
			</form>
		</div>
	</div>
	<br />
</nav>
<!-- 상단 navbar end -->


<!-- 중앙 content start -->
<div id="houseInfo"></div>
<div class="container">
	<div style="height: 70px"></div>
	<h2 class="text-center mt-5 mb-3">아파트 매매 정보</h2>
	<div class="row col-md-12 justify-content-center mb-2">
		<div class="form-group col-md-2">
			<select class="form-select bg-secondary text-light" id="sido">
				<option value="">시도선택</option>
			</select>
		</div>
		<div class="form-group col-md-2">
			<select class="form-select bg-secondary text-light" id="gugun">
				<option value="">구군선택</option>

			</select>
		</div>
		<div class="form-group col-md-2">
			<select class="form-select bg-secondary text-light" id="dong">
				<option value="">동선택</option>
			</select>
		</div>

		<div class="form-group col-md-2">
			<button type="button" id="list-btn" class="btn btn-outline-primary">
				검색</button>
		</div>
	</div>

	<!--  
	<div class="container">
		<div id="map" style="width: 100%; height: 400px"></div>
		<script type="text/javascript"
			src="//dapi.kakao.com/v2/maps/sdk.js?appkey=09e7abf5333e8968e225d4399e7f4ebc&libraries=services"></script>
	</div>
-->

	<table class="table table-hover text-center" style="display:">
		<tr>
			<th>동</th>
			<th>아파트 이름</th>
			<th>건축년도</th>
			<th>지번</th>
		</tr>
		<tbody id="aptlist"></tbody>
	</table>
</div>

<!--  kakao map start -->
<!--  
<div class="container">
	<div id="map" style="width: 100%; height: 400px"></div>
	<script type="text/javascript"
		src="//dapi.kakao.com/v2/maps/sdk.js?appkey=09e7abf5333e8968e225d4399e7f4ebc&libraries=services"></script>
</div>-->
<!--  kakao map end -->


<script>

var SIDO_CODE;
var GUGUN_CODE;
var DONG_NAME;
var SEARCH_WORD = '';

window.onload=function () {
	
	// #1. 처음 화면 -> 테이블은 비어있고, 시도 정보만 받아온 상태
	sidoList();
	
	// #2. 시도 정보를 선택하면 -> 구군 리스트 받아오기
	document.querySelector ("#sido").onchange=function () {
		
		SIDO_CODE=document.querySelector("#sido").value;
		console.log(SIDO_CODE);
		
		gugunList();
	}
	
	// #3. 구군 정보 선택하면 -> 동 리스트 받아오기
	document.querySelector ("#gugun").onchange=function () {
		
		GUGUN_CODE=document.querySelector("#gugun").value;
		console.log(GUGUN_CODE);
		
		dongList();
	}
	
	// #4. 동 정보 선택하면 -> 동 저장
	document.querySelector ("#dong").onchange=function () {
		DONG_NAME=document.querySelector("#dong").value;
		console.log(DONG_NAME);
		
	}
	
	// #5. list btn (검색버튼) 누르면 -> 동 이름으로 아파트 정보를 가져옴
	document.querySelector ("#list-btn").onclick=function () {
		houseInfoList();
	}
	
	// #6. 아파트 이름으로 검색하기
	document.querySelector ("#btnSearchWord").onclick=function () {
		SEARCH_WORD=document.querySelector("#inputSearchWord").value;
		console.log(SEARCH_WORD);
		
		houseInfoList();
	}
	
};

//POST
async function sidoList(){
	let url = '<%=contextPath%>/apt/aptSidoList';
	let fetchOptions = {
		method: 'POST',
	}
	
	try{
		let response = await fetch( url, fetchOptions )
		let data = await response.json();
		
		console.log( data );
		
		// #sido에 data 추가
		makeSidoListHtml(data);
		
	}catch( error ){
		console.log(error);
		alertify.error('아파트 조회 페이지 생성 과정에서 문제가 발생하였습니다.')
	}
};

//GET
async function gugunList(){
	let url = '<%=contextPath%>/apt/aptGugunList';
	let urlParams='?sidoCode='+SIDO_CODE;
	let fetchOptions = {
		method: 'GET',
	}
	
	try{
		let response = await fetch( url+urlParams, fetchOptions )
		let data = await response.json();
		
		console.log( data );
		
		// #gugun에 data 추가
		makeGugunListHtml(data);
		
	}catch( error ){
		console.log(error);
		alertify.error('아파트 조회 페이지 생성 과정에서 문제가 발생하였습니다.')
	}
};

//GET
async function dongList(){
	let url = '<%=contextPath%>/apt/aptDongList';
	let urlParams='?gugunCode='+GUGUN_CODE;
	let fetchOptions = {
		method: 'GET',
	}
	
	try{
		let response = await fetch( url+urlParams, fetchOptions )
		let data = await response.json();
		
		console.log( data );
		
		// #gugun에 data 추가
		makeDongListHtml(data);
		
	}catch( error ){
		console.log(error);
		alertify.error('아파트 조회 페이지 생성 과정에서 문제가 발생하였습니다.')
	}
};

//GET
async function houseInfoList(){
	
	let url;
	let urlParams;
	
	if (SEARCH_WORD=='') {
		url = '<%=contextPath%>/apt/aptListByDong';
		urlParams='?dongName='+DONG_NAME;
	} else {
		url='<%=contextPath%>/apt/aptListByName';
		urlParams='?aptName='+SEARCH_WORD;
	}
	
	let fetchOptions = {
		method: 'GET',
	}
	
	try{
		let response = await fetch( url+urlParams, fetchOptions )
		let data = await response.json();
		
		console.log( data );
		
		makeHouseListHtml(data);
		
	}catch( error ){
		console.log(error);
		alertify.error('아파트 조회 페이지 생성 과정에서 문제가 발생하였습니다.')
	}
};

function makeSidoListHtml( list ){
	let listHtml = ``;
	
	list.forEach( el => {
		console.log(el);

		
		listHtml +=
			`<option style="cursor:pointer" value=\${el.code}>\${el.name}</option>`;
	});
	
	document.querySelector("#sido").innerHTML = listHtml;

}

function makeGugunListHtml( list ){
	let listHtml = ``;
	
	list.forEach( el => {
		console.log(el);	

	
		listHtml +=
			`<option style="cursor:pointer" value=\${el.code}>\${el.name}</option>`;
	});
	
	document.querySelector("#gugun").innerHTML = listHtml;

}

function makeDongListHtml( list ){
	let listHtml = ``;
	
	list.forEach( el => {
		console.log(el);	

		listHtml +=
			`<option style="cursor:pointer" value=\${el.name}>\${el.name}</option>`;
	});
	
	document.querySelector("#dong").innerHTML = listHtml;

}


function makeHouseListHtml( list ){
	let listHtml = ``;
	
	list.forEach( el => {
		console.log(el);	

		listHtml +=
			`<tr style="cursor:pointer" value=\${el.no}><td>\${el.dong}</td><td>\${el.aptName}</td><td>\${el.buildYear}</td><td>\${el.jibun}</td></tr>`;
	});
	
	document.querySelector("#aptlist").innerHTML = listHtml;

}


</script>



<!-- footer start -->

<br />
<br />
<br />
<br />
<br />
<br />
<footer
	class="navbar navbar-expand-lg navbar-light bg-light container-fluid justify-content-end fixed-bottom bottom-0">
	<div class="row">
		<ul class="navbar-nav">
			<li><a href="#" class="nav-link text-secondary">회사소개</a></li>
			<li><a href="#" class="nav-link text-secondary">개인정보처리방침</a></li>
			<li><a href="#" class="nav-link text-secondary">이용약관</a></li>
			<li><a href="#" class="nav-link text-secondary">오시는길</a></li>
			<li><label class="nav-link text-secondary">&copy; JYSH
					Corp.</label></li>
		</ul>
	</div>
</footer>
<!-- footer end -->






</body>
</html>