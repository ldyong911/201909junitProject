<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<div id="divTmpl">tmpl 테스트</div>

<script src="<c:url value='/script/jquery-1.11.0.min.js'/>"></script>
<script src="<c:url value='/script/jquery.tmpl.min.js'/>"></script>

<script>
	var tmplArray = [{firstName: "1", lastName: "one"},
		{firstName: "2", lastName: "two"}];
	
	$(document).ready(function(){
		$("#tmplTest").tmpl(tmplArray).appendTo("#divTmpl");
	});
	
</script>

<script id="tmplTest" type="text/x-jquery-tmpl">
	<br> \${firstName} <strong> \${lastName} </strong> <br>
</script>