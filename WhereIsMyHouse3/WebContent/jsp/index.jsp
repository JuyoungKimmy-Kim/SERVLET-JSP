<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html lang="en">


<head>
<meta charset="UTF-8" />
<meta http-equiv="X-UA-Compatible" content="IE=edge" />
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<title>Document</title>

<style>
.row {
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

<%@ include file="header.jsp" %>   

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
				<img src="<%= contextPath %>/img/home1.png" class="d-block w-100" alt="...">


				<div class="carousel-caption d-none d-md-block">
					<h5>강원도 평창</h5>
					<p>GangWon-do, pyeongchang-gun</p>
				</div>
			</div>
			<div class="carousel-item">
				<img src="<%= contextPath %>/img/home2.png" class="d-block w-100" alt="...">
				<div class="carousel-caption d-none d-md-block">
					<h5>서울특별시 강남구</h5>
					<p>Seoul, Gangnam-gu</p>
				</div>
			</div>
			<div class="carousel-item">
				<img src="<%= contextPath %>/img/home3.png" class="d-block w-100" alt="...">
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
						<img src="<%= contextPath %>/img/sub1.png" class="card-img-top" alt="...">
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
						<img src="<%= contextPath %>/img/sub2.png" class="card-img-top" alt="...">
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
						<img src="<%= contextPath %>/img/sub3.png" class="card-img-top" alt="...">
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

</body>