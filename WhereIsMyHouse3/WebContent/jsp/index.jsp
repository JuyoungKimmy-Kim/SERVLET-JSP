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
			<button class="navbar-toggler" type="button"
				data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent"
				aria-controls="navbarSupportedContent" aria-expanded="false"
				aria-label="Toggle navigation">
				<span class="navbar-toggler-icon"></span>
			</button>
			<div class="collapse navbar-collapse" id="navbarSupportedContent">
				<ul class="navbar-nav me-auto mb-2 mb-lg-0">
					<li class="nav-item"><a class="nav-link active"
						aria-current="page" href="index.jsp">Home</a></li>
				</ul>

				<ul class="navbar-nav me-auto mb-2 mb-lg-0">
					<li class="nav-item"><a class="nav-link active"
						aria-current="page" href="apt/aptMain.jsp">아파트 조회</a></li>
				</ul>


				<ul class="navbar-nav me-auto mb-2 mb-lg-0">
					<li class="nav-item"><a class="nav-link active"
						aria-current="page" href="login.jsp" id="login" style="display:">로그인</a></li>
				</ul>

				<ul class="navbar-nav me-auto mb-2 mb-lg-0">
					<li class="nav-item"><a class="nav-link active"
						aria-current="page" href="login.jsp" id="logout"
						style="display: none">로그아웃</a></li>
				</ul>
			</div>

			<form class="d-flex">
				<input class="form-control me-2" type="search" id="inputSearchWord"
					placeholder="아파트 이름으로 검색하기" aria-label="Search" />
				<button class="btn btn-outline-success" id="btnSearchWord"
					type="button">Search</button>
			</form>
		</div>
		</div>
		<br />
	</nav>
	<!-- 상단 navbar end -->


	<!-- 중앙 Content IMG 시작-->
	<div id="carouselExampleCaptions" class="carousel slide"
		data-bs-ride="carousel">
		<div class="carousel-indicators">
			<button type="button" data-bs-target="#carouselExampleCaptions"
				data-bs-slide-to="0" class="active" aria-current="true"
				aria-label="Slide 1"></button>
			<button type="button" data-bs-target="#carouselExampleCaptions"
				data-bs-slide-to="1" aria-label="Slide 2"></button>
			<button type="button" data-bs-target="#carouselExampleCaptions"
				data-bs-slide-to="2" aria-label="Slide 3"></button>
		</div>
		<div class="carousel-inner">
			<div class="carousel-item active">
				<img src="../img/home1.png" class="d-block w-100" alt="...">


				<div class="carousel-caption d-none d-md-block">
					<h5>강원도 평창</h5>
					<p>GangWon-do, pyeongchang-gun</p>
				</div>
			</div>
			<div class="carousel-item">
				<img src="../img/home2.png" class="d-block w-100" alt="...">
				<div class="carousel-caption d-none d-md-block">
					<h5>서울특별시 강남구</h5>
					<p>Seoul, Gangnam-gu</p>
				</div>
			</div>
			<div class="carousel-item">
				<img src="../img/home3.png" class="d-block w-100" alt="...">
				<div class="carousel-caption d-none d-md-block">
					<h5>부산광역시 해운대구</h5>
					<p>Busan, Haeundae-gu</p>
				</div>
			</div>
		</div>
		<button class="carousel-control-prev" type="button"
			data-bs-target="#carouselExampleCaptions" data-bs-slide="prev">
			<span class="carousel-control-prev-icon" aria-hidden="true"></span> <span
				class="visually-hidden">Previous</span>
		</button>
		<button class="carousel-control-next" type="button"
			data-bs-target="#carouselExampleCaptions" data-bs-slide="next">
			<span class="carousel-control-next-icon" aria-hidden="true"></span> <span
				class="visually-hidden">Next</span>
		</button>
	</div>

	<!--  중앙 Content IMG 종료-->


	<!-- 아래 왼쪽 공지사항 시작-->
	<br>
	<br>
	<br>
	<div class="row">
		<div class="col-3">
			<table class="table table-striped">
				<thead>
					<tr>
						<th scope="col">공지/뉴스</th>
					</tr>
				</thead>
				<tbody>
					<tr>
						<th scope="row">[공지] 위치기반 서비스 이용약관 <span
							class="badge bg-secondary">New</span></th>

					</tr>
					<tr>
						<th scope="row">[뉴스] 수도권 15억 넘는 아파트, 주택 담보대출 허용 검토 <span
							class="badge bg-secondary">New</span></th>
					</tr>
					<tr>
						<th scope="row">[공지] 개인정보처리방침 개정</th>
					</tr>
					<tr>
						<th scope="row">[공지] 시스템 업데이트 및 서버작업 안내</th>
					</tr>
					<tr>
						<th scope="row">[공지] 실거래 데이터 09월 07일자 기준 업데이트 안내</th>
					</tr>
					<tr>
						<th scope="row">[공지] 구해줘 홈즈 앱 출시!</th>
					</tr>
				</tbody>
			</table>
			<!-- 아래 왼쪽 공지사항 종료-->

			<!-- 아래 오른쪽 이미지3개 시작-->

		</div>
		<div class="col-9">
			<div class="row">
				<div class="col-3">
					<div class="card" style="width: 15rem;">
						<img src="../img/sub1.png" class="card-img-top" alt="...">
						<div class="card-body">
							<h5 class="card-title">
								<strong>부동산도 이제 온라인</strong>
							</h5>
							<p class="card-text">실시간 실거래가를 통한 주택 정보</p>
							<a href="#" class="btn btn-primary stretched-link">바로 가기</a>
						</div>
					</div>
				</div>
				<div class="col-3">
					<div class="card" style="width: 15rem;">
						<img src="../img/sub2.png" class="card-img-top" alt="...">
						<div class="card-body">
							<h5 class="card-title">
								<strong>구해줘 홈즈 앱 출시!</strong>
							</h5>
							<p class="card-text">모바일에서도 부동산 매매 정보를 한눈에 확인할 수 있습니다!</p>
							<a href="#" class="btn btn-primary stretched-link">다운로드</a>
						</div>
					</div>
				</div>
				<div class="col-3">
					<div class="card" style="width: 15rem;">
						<img src="../img/sub3.png" class="card-img-top" alt="...">
						<div class="card-body">
							<h5 class="card-title">
								<strong></strong>
							</h5>
							<p class="card-text">부동산 중개보수 요율표 살펴보기</p>
							<a href="#" class="btn btn-primary stretched-link">살펴보기</a>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
	<!-- 아래 오른쪽 이미지3개 종료-->
</body>