<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>결제 확인화면</title>
<%@ include file="/WEB-INF/views/module/link.jsp" %>
<script src="https://code.jquery.com/jquery-3.6.3.js" ></script>
</head>
<body>
<!-- Header area-->
<%@ include file="/WEB-INF/views/module/header.jsp" %> 

<!-- Start page header --> 
<section>
    <div class="count-area"> 
    <div class="container">
        <div class="row">
            <div class="col-md-10 col-md-offset-1 col-sm-12 text-center page-title" style="padding: 60px;">
				<h3>결제 완료<br> 
					<small>결제 상세 정보를 확인하세요.</small>
				</h3>
			</div>
        </div>
    </div>
</div>
<!-- End page header -->



 <!-- start contents-area -->
        <div class="content-area user-profiel">
            <div class="container">   
                <div class="row">
                    <div class="col-sm-10 col-sm-offset-1 profiel-container">
						<div class="profiel-header" >
						    <h3> ${mapVo.proName } <br>
						    </h3>						    
						</div>
						<div class="container">
						    <div class="reservation-info">
						        <div class="group-left">
						            <div class="info-item">
						                <div class="label">예약 번호</div>
						                <div class="value"><span>${dto.rsvNum }</span></div>
						            </div>
						            <div class="info-item">
						                <div class="label">예약 날짜</div>
						                <div class="value"><span>${dto.rsvDate }</span></div>
						            </div>
						            <div class="info-item">
						                <div class="label">이용 시간</div>
						                <c:forEach  var="rsvTime" items="${dto.rsvTime }">
						                	<div class="value">${rsvTime }</div>
						                </c:forEach>
						            </div>
						        </div>
						        <div class="group-right">
						            <div class="info-item">
						                <div class="label">예약 인원</div>
						                <div class="value"><span>${dto.rsvPerson }명</span></div>
						            </div>
						            <div class="info-item">
						                <div class="label">공간 전화번호</div>
						                <div class="value"><span>${mapVo.proPhone }</span></div>
						            </div>
						            <div class="info-item">
						                <div class="label">가격</div>
						                <div class="value"><span>${dto.rsvAmount }</span>원</div>
						            </div>
						        </div>
						    </div>
						</div>
							<span> 
							<button class="btn btn-primary" >확인</button>	
							<!-- <button class="btn btn-primary" >취소하기</button>	 -->						                                                         
                            </span>    
                                       
 	               </div>
            </div>

        </div>
    </div>
<!-- End contents-area -->


<style>
	.profiel-container .profiel-header h3 {
    font-weight: 200;
    text-align: center;
    padding-top: 50px;
}

	 .group-left {
        float: left;
        width: 50%;
        padding-top: 100px;
        padding-left: 220px;
        padding-bottom: 10px;
    }
    
    .group-right {
        float: right;
        width: 50%;
        padding-top:100px;
        padding-bottom: 10px;
    }
    
    .info-item{
    	margin: 30px;
    }
    
    button.btn-primary {
    display: block;
    margin: 100px auto;
    width: 50%;
    height: 55px;
}

    
</style>


          
</section>
<%@ include file="/WEB-INF/views/module/footer.jsp" %>
</body>
</html>