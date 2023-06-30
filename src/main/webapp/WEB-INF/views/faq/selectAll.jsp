<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>FAQ</title>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.4/jquery.min.js"></script>
<link rel="stylesheet" href="../resources/css/faq/list.css">
<script type="text/javascript">
	$(function(){
		$('#searchWord').val("${param.searchWord}");
		$.ajax({
			url: "json/searchList.do",
			data:{
				searchKey: "${param.searchKey}",
				searchWord: "${param.searchWord}",
				page: ${param.page},
			},
			method: 'GET',
			dataType: 'json',
			success: function(arr){
				console.log('ajax...',arr);
				let tag_vos = `
					<li>
						<div class="faq_header">
							<span>제목</span>
						</div>
					</li>
				`;
				
				$.each(arr, function(index, vo){
					tag_vos +=`
						<li>
							<div class="faq_header" onclick="clickPlain(\${vo.num})">
								<span>\${vo.title}</span>
							</div>
							<div id="clickNum\${vo.num}" class="faq_content" style="display:none;">
								\${vo.content}
							</div>
							<div id="atagNum\${vo.num}" style="display:none; text-align:right">
								<a href="update.do?num=\${vo.num}">수정</a>
								<a href="javascript:void(0);" onclick="deleteOK(\${vo.num})">삭제</a>
							</div>
						</li>
					`;
					
				}); // end for-each
				$('#faq_vos').html(tag_vos);

			}, // end success
			
			error:function(xhr,status,error){
				console.log('xhr.status:', xhr.status);
			} // end error
		}); // end searchList ajax;
		
		$.ajax({
			url: "json/selectAll.do",
			data:{
				searchKey: "${param.searchKey}",
				searchWord: "${param.searchWord}",
			},
			method: 'GET',
			dataType: 'json',
			success: function(cnt){
				console.log('ajax...',cnt);
				if(${param.page}==1){
//		 			$('#pre_page').hide();
					$('#pre_page').click(function(){
						alert('첫번째 페이지입니다.');
						return false;
					});
				}
				if((${param.page}*5) >= cnt){
//		 			$('#next_page').hide();
					$('#next_page').click(function(){
						alert('마지막 페이지입니다.');
						return false;
					});
				}

			}, // end success
			
			error:function(xhr,status,error){
				console.log('xhr.status:', xhr.status);
				
			} // end error
		}); // end selectAll ajax;
		
		
		
	}); // end onload
	
	
	
	function searchList(){
		let sKey = $('#searchKey').val();
		let sWord = $('#searchWord').val();
		console.log('skey:', sKey);
		console.log('sWord:', sWord);
		let url = 'selectAll.do?searchKey=' + sKey + "&searchWord=" + sWord + "&page=1";
		location.replace(url);
	}; // end searchList
	
	function deleteOK(wnum){
		console.log('wnum:', wnum);
		$.ajax({
			url: "json/deleteOK.do",
			data: {
				num: wnum
			},
			method: 'GET',
			dataType: 'json',
			success: function(obj){
				console.log('ajax...', obj.result);
				if(obj.result==1){
					let url='selectAll.do?searchKey=title&page=1';
					location.replace(url);
				}
			},
			
			error: function(xhr, status, error){
				console.log('xhr.status', xhr.status);
			}
			
		}); // end ajax;
	}

	
	function clickPlain(num){
		let name = '#clickNum' + num;
		let tag = '#atagNum' + num;
		console.log(name);
// 		if($(name).css("display") == 'none'){
// 			$(name).css("display", '');
// 			$(tag).css("display", '');
// 		}
// 		else{
// 			$(name).css("display", 'none');
// 			$(tag).css("display", 'none');
// 		}
		$(name).slideToggle();
		$(tag).slideToggle();
	};
	
</script>
</head>
<body>
	<h1>FAQ</h1>

	
	
	<div class="faq_div">
		<ul class="faq_list" id="faq_vos">

		</ul>
	</div>
	
	<div style="width:30%; display:inline-block">
		<select id="searchKey" name="searchKey">
			<option value="title" <c:if test="${param.searchKey == 'title'}"> selected </c:if>>제목</option>
			<option value="content" <c:if test="${param.searchKey =='content'}"> selected </c:if>>내용</option>
		</select>
		<input type="text" name="searchWord" id="searchWord" value="${param.searchWord}">
		<input type="hidden" name="page" value=1>
		<button onclick="searchList()">검색</button>
	</div>
	
	<div style="width:45%; display:inline">
		<a href="insert.do">글작성</a>
	</div>
	
	<div id="pre_next">
		<a href="selectAll.do?searchKey=${param.searchKey}&searchWord=${param.searchWord}&page=${param.page-1}" id="pre_page">이전</a>
		<a href="selectAll.do?searchKey=${param.searchKey}&searchWord=${param.searchWord}&page=${param.page+1}" id="next_page">다음</a>
	</div>
	
	
	

	
	
		
	
</body>
</html>