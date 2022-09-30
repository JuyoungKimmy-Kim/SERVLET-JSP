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
    <div class="container">
        <div id="map" style="width: 100%; height: 400px"></div>
        <script
          type="text/javascript"
          src="//dapi.kakao.com/v2/maps/sdk.js?appkey=09e7abf5333e8968e225d4399e7f4ebc&libraries=services"
        ></script>
      </div>
    
</div>


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
    let listHtml = `<option style="cursor:pointer" >시, 도 선택</option>`;

    list.forEach( el => {
        console.log(el);


        listHtml +=
            `<option style="cursor:pointer" value=\${el.code}>\${el.name}</option>`;
    });

    document.querySelector("#sido").innerHTML = listHtml;

}

function makeGugunListHtml( list ){
    let listHtml = `<option style="cursor:pointer" >군, 구 선택</option>`;

    list.forEach( el => {
        console.log(el);

    
        listHtml +=
            `<option style="cursor:pointer" value=\${el.code}>\${el.name}</option>`;
    });

    document.querySelector("#gugun").innerHTML = listHtml;

}

function makeDongListHtml( list ){
    let listHtml = `<option style="cursor:pointer" >동 선택</option>`;

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

    makeMap(list)
}

function makeMap(list){


    var infowindow = new kakao.maps.InfoWindow({ zIndex: 1 });
    var mapContainer = document.getElementById('map'), // 지도를 표시할 div
    mapOption = { 
        center: new kakao.maps.LatLng(37.574333, 126.9688), // 지도의 중심좌표
        level: 9 // 지도의 확대 레벨
    };

var map = new kakao.maps.Map(mapContainer, mapOption); // 지도를 생성합니다
 
// 마커를 표시할 위치와 title 객체 배열입니다 

 
var positions= []
list.forEach(data => {
    positions.push({
        title: data.aptName,
        latlng:new kakao.maps.LatLng(parseFloat(data.lat), parseFloat(data.lng)), // 마커를 표시할 위치

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
        image : markerImage // 마커 이미지 
    });

}

}

</script>



<%@ include file="../footer.jsp" %>



</body>
</html>