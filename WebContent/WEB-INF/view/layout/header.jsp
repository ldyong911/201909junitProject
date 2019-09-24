<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<style>
	.menubar li ul {
		display: none;  /* 평상시에는 서브메뉴가 안보이게 하기 */
	}
	
	.menubar li:hover ul {
		display:block;   /* 마우스 커서 올리면 서브메뉴 보이게 하기 */
	}
	
	ul {
		padding-inline-start: 0px; /* ul 맨 앞에 표시되는 특수기호(●, ○) 제거 */
	}
	
	#liBoardList {
		background-color: yellow; /* 배경색 변경 */
	}
	
	#liUserMngList {
		background-color: orange;
	}
	
	#liLogout {
		background-color: red;
	}
	
	li {
		width: 750px;
	}
	
	a {
		text-decoration: none; /* a태그 밑줄 제거 */
	}
</style>

<h2>
	<a href="<c:url value='/board/main.do'/>">
		<img src="<c:url value='/images/wizetech.png'/>"/>
	</a>
</h2>

<%--세션이 null이면 메뉴 안보임 --%>
<c:if test="${sessionScope.userVO != null}">
	<div class="menubar">
		<ul>
			<li id="liBoardList">
				게시판
				<ul>
					<c:forEach items="${sessionScope.comCdList}" var="comCd">
						<li><a href="<c:url value='/board/board_getBoardList.do?board_type_cd=${comCd.cd}'/>">${comCd.cd_nm}</a></li>
					</c:forEach>
				</ul>
			</li>
			<li id="liUserMngList">
				<a href="<c:url value='/system/userMng/userMng_getUserMngList.do'/>">사용자목록</a>
			</li>
			<li id="liLogout">
				<a href="<c:url value='/common/auth/logout.do'/>">로그아웃</a>
			</li>
		</ul>
	</div>
</c:if>