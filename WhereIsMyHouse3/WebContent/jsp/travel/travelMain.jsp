<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html lang="en">


<head>
<meta charset="UTF-8" />
<meta http-equiv="X-UA-Compatible" content="IE=edge" />
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<title>Document</title>

</head>
<body>


<%@ include file="../header.jsp" %>   

<!-- 중앙 content start -->
<div id="travelInfo" >
<h2 class="text-center mt-5 mb-3">부산 관광지 소개</h2>
<div class="container"  style = "display: flex">
	<div style="height: 70px"></div>
	
	

	<!--  
	<div class="container">
		<div id="map" style="width: 100%; height: 400px"></div>
		<script type="text/javascript"
			src="//dapi.kakao.com/v2/maps/sdk.js?appkey=09e7abf5333e8968e225d4399e7f4ebc&libraries=services"></script>
	</div>
-->

	<table class="table table-hover text-center" style="display:">
		<tr>
			<th>관광지 이름</th>
			<th>가시는 길</th>
			
		</tr>
		<tbody id="aptlist"></tbody>
	</table>
	<div class="container">
        <div id="map" style="width: 100%; height: 800px"></div>
        <script
          type="text/javascript"
          src="//dapi.kakao.com/v2/maps/sdk.js?appkey=09e7abf5333e8968e225d4399e7f4ebc&libraries=services"
        ></script>
      </div>
	
</div>
</div>


<script>

var SIDO_CODE;
var GUGUN_CODE;
var DONG_NAME;
var SEARCH_WORD = '';

var infowindow = new kakao.maps.InfoWindow({ zIndex: 1 });
var mapContainer = document.getElementById('map'), // 지도를 표시할 div  
mapOption = { 
    center: new kakao.maps.LatLng(35.1887, 129.0739), // 지도의 중심좌표
    level: 9 // 지도의 확대 레벨
};

var map = new kakao.maps.Map(mapContainer, mapOption);
window.onload=function () {
	
		// #1. 처음 화면 -> 테이블은 비어있고, 시도 정보만 받아온 상태
	travelList();
	

	
};

//POST
async function travelList(){
	let url = '<%=contextPath%>/travel';
	let fetchOptions = {
		method: 'get',
	}
	
	try{
		let response = await fetch( url, fetchOptions )
		let data = await response.json();
		maketravelListHtml(data);
		makeMap(data)
		
		
		
	}catch( error ){
		console.log(error);
		alertify.error('아파트 조회 페이지 생성 과정에서 문제가 발생하였습니다.')
	}
};

function maketravelListHtml( list ){
	let listHtml = ``;
	
	list.forEach( el => {
		console.log(el);	

		listHtml +=
			`<tr style="cursor:pointer" ><td>\${el.place}</td><td>\${el.TRFC_INFO}</td></tr>`;
	});
	
	document.querySelector("#aptlist").innerHTML = listHtml;

	makeMap(list)
}

function makeMap(list){

	console.log("Gdgd",list)
	
 
var positions= []
list.forEach(data => {
	positions.push({
		title: data.place,
		latlng:new kakao.maps.LatLng(data.lat, data.lng), // 마커를 표시할 위치
		info : data.TRFC_INFO
	})
})





// 마커 이미지의 이미지 주소입니다
var imageSrc = "https://t1.daumcdn.net/localimg/localimages/07/mapapidoc/markerStar.png"; 
    
for (var i = 0; i < positions.length; i ++) {
    
    // 마커 이미지의 이미지 크기 입니다
    var imageSize = new kakao.maps.Size(24, 35); 
    
    // 마커 이미지를 생성합니다    
    var markerImage = new kakao.maps.MarkerImage(imageSrc, imageSize); 
    
    // 마커를 생성합니다
    var marker = new kakao.maps.Marker({
        map: map, // 마커를 표시할 지도
        position: positions[i].latlng, // 마커를 표시할 위치
        title : positions[i].title, // 마커의 타이틀, 마커에 마우스를 올리면 타이틀이 표시됩니다
        image : markerImage, // 마커 이미지 
        clickable: true
        
    });
   
  
    
}
	
}

</script>



<%@ include file="../footer.jsp" %>  



</body>
</html>