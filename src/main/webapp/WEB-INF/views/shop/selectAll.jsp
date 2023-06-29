<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>selectAll</title>
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.4/jquery.min.js"></script>
</head>
<body>
	<h1>매장목록</h1>
	
	<div style="padding:5px">
		<form action="selectAll.do">
			<select name="searchKey" id="searchKey">
				<option value="name">name</option>
				<option value="cate">cate</option>
			</select>
			<input type="text" name="searchWord" id="searchWord" value="커피">
			<input type="submit" value="검색">
		</form>
	</div>

	<table id="shopList">
	<thead>
		<tr>
			<th>num</th>
			<th>name</th>
			<th>cate</th>
			<th>tel</th>
		</tr>
	</thead>
	<tbody>
		<c:forEach var="vo" items="${vos}">
			<tr>
				<td><a href="selectOne.do?num=${vo.num}">${vo.num}</a></td>
				<td>${vo.name}</td>
				<td>${vo.cate}</td>
				<td>${vo.tel}</td>
			</tr>
		</c:forEach>
	</tbody>
	<tfoot></tfoot>
	</table>
	
	<div>
		<a href="selectAll.do?searchKey=${param.searchKey}&searchWord=${param.searchWord}&pageNum=${param.pageNum-1}" id="pre_page">이전</a>
		<a href="selectAll.do?searchKey=${param.searchKey}&searchWord=${param.searchWord}&pageNum=${param.pageNum+1}" id="next_page">다음</a>
	</div>
	<script type="text/javascript">
		console.log(${cnt});
		if(${param.pageNum}==1){
 			$('#pre_page').hide();
		}
		if(${cnt}!=10){
			$('#next_page').hide();
		}
</script>
	
</body>
</html>