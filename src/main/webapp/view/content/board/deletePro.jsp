<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>

<c:if test="${check==1 }"> 
	<meta http-equiv="Refresh"   content="0;url=<%=request.getContextPath() %>/room/roomcontent?num=<%=request.getParameter("num") %>" >
</c:if>

<c:if test="${check==0 }">
     <script language="JavaScript">      
       alert("비밀번호가 맞지 않습니다");
       history.go(-1);
     </script>
     
</c:if>
</body>
</html>