<%@page import="com.model2.mvc.common.Search"%>
<%@page import="com.model2.mvc.common.Page"%>
<%@page import="com.model2.mvc.common.util.CommonUtil"%>
<%@page import="com.model2.mvc.service.domain.User"%>
<%@page import="com.model2.mvc.service.domain.Purchase"%>

<%@ page contentType="text/html; charset=euc-kr" %>

<%@ page import="java.util.List"  %>
<%@ page import="java.util.Map"  %>

<%
	Map<String,Object> map = (Map<String,Object>)request.getAttribute("map");
	List<Purchase> list = (List<Purchase>)map.get("list");

	Page resultPage = (Page)request.getAttribute("resultPage");
	Search search =(Search)request.getAttribute("search");
	String menu = request.getParameter("menu");
	
	
	
	// null ===> ""(nullString)으로 변경
	String searchCondition = CommonUtil.null2str(search.getSearchCondition());
	String searchKeyword = CommonUtil.null2str(search.getSearchKeyword());
	

%>

<html>
<head>
<title>상품 목록조회</title>

<link rel="stylesheet" href="/css/admin.css" type="text/css">

<script type="text/javascript">
function fncGetUserList(currentPage){			
	document.getElementById("currentPage").value = currentPage;
	document.detailForm.submit();		
}
</script>
</head>

<body bgcolor="#ffffff" text="#000000">

<div style="width:98%; margin-left:10px;">

<form name="detailForm" action="/listSale.do" method="post">

<table width="100%" height="37" border="0" cellpadding="0"	cellspacing="0">
	<tr>
		<td width="15" height="37">
			<img src="/images/ct_ttl_img01.gif" width="15" height="37">
		</td>
		<td background="/images/ct_ttl_img02.gif" width="100%" style="padding-left:10px;">
			<table width="100%" border="0" cellspacing="0" cellpadding="0">
				<tr>
					판매 상품 목록
				</tr>
			</table>
		</td>
		<td width="12" height="37">
			<img src="/images/ct_ttl_img03.gif" width="12" height="37">
		</td>
	</tr>
</table>

<table width="100%" border="0" cellspacing="0" cellpadding="0" style="margin-top:10px;">
	<tr>
		<td align="right">
			<select name="searchCondition" class="ct_input_g" style="width:80px">
				<option value="0" <%= (searchCondition.equals("0") ? "selected" : "")%>>상품번호</option>
				<option value="1" <%= (searchCondition.equals("1") ? "selected" : "")%>>상품명</option>
				<option value="2" <%= (searchCondition.equals("2") ? "selected" : "")%>>상품가격</option>
			</select>
			<input 	type="text" name="searchKeyword"  value="<%= searchKeyword %>" 
							class="ct_input_g" style="width:200px; height:19px" >
		</td>

		<td align="right" width="70">
			<table border="0" cellspacing="0" cellpadding="0">
				<tr>
					<td width="17" height="23">
						<img src="/images/ct_btnbg01.gif" width="17" height="23">
					</td>
					<td background="/images/ct_btnbg02.gif" class="ct_btn01" style="padding-top:3px;">
						<a href="javascript:fncGetProductList('1');">검색</a>
					</td>
					<td width="14" height="23">
						<img src="/images/ct_btnbg03.gif" width="14" height="23">
					</td>
				</tr>
			</table>
		</td>
	</tr>
</table>

<table width="100%" border="0" cellspacing="0" cellpadding="0" style="margin-top:10px;">
	<tr>
		<td colspan="11" >전체  <%=resultPage.getTotalCount()%> 건수, 현재 <%=resultPage.getCurrentPage()%>/<%=resultPage.getMaxPage()%> 페이지</td>
	</tr>
	<tr>
		<td class="ct_list_b" width="100">No</td>
		<td class="ct_line02"></td>
		<td class="ct_list_b" width="150">상품명</td>
		<td class="ct_line02"></td>
		<td class="ct_list_b" width="150">가격</td>
		<td class="ct_line02"></td>
		<td class="ct_list_b">등록일</td>
		<td class="ct_line02"></td>
		<td class="ct_list_b">현재상태</td>		
	</tr>
	<tr>
		<td colspan="11" bgcolor="808285" height="1"></td>
	</tr>
	<%
		for(int i=0; i<list.size(); i++) {
		Purchase vo = (Purchase )list.get(i);
	%>
	<tr class="ct_list_pop">
		<td align="center"><%= i+1 %></td>
		<td></td>
		<td align="left">
			<a href="/getProduct.do?prodNo=<%=vo.getPurchaseProd().getProdNo()%>&menu=search"><%= vo.getPurchaseProd().getProdName() %></a>
		</td>
		<td></td>
		<td align="left"><%= vo.getPurchaseProd().getPrice() %></td>
		<td></td>
		<td align="left"><%=vo.getPurchaseProd().getRegDate() %></td>
		<td></td>
		<td align="left">
			<%-- <% if(vo.getProTranCode().equals("sale")){ %>
				판매중
			<% } else { %>
				<% if( userRole!=null && userRole.equals("admin") ){ %>
				
					<% if(vo.getProTranCode().trim().equals("1")) {%>
						구매완료 
						<% if(menu.equals("manage")){ %>
							<a href="/updateTranCodeByProd.do?prodNo=<%=vo.getProdNo()%>&tranCode=2">배송하기</a>
						<% } %>
					<% } else if(vo.getProTranCode().trim().equals("2")) {%>
						배송중
					<% } else if(vo.getProTranCode().trim().equals("3")) {%>
						배송완료
					<% } %>
				<% } else { %>
					
					재고 없음
				<% } %>
			<% } %> --%>
			
			<%
			if(vo.getTranCode() != null) {
				if(vo.getTranCode().trim().equals("1")) {
			%>
				판매완료
				<a href="/updateTranCode.do?tranNo=<%=vo.getTranNo()%>&tranCode=2">배송하기</a>
				
				<% } else if(vo.getTranCode().trim().equals("2")) { %>
				
				배송중
			
				<% } else if(vo.getTranCode().trim().equals("3")) { %>
				
				배송완료
				
				<% } %>
			<% } else {%>
				판매중		
			<% } %>
						
			
			<%-- 
			<% if(vo.getProTranCode().equals("sale")){ %>
				판매중
			<% } else if(vo.getProTranCode().trim().equals("1")) {%>
				구매완료 
				<% if(menu.equals("manage")){ %>
					<a href="/updateTranCodeByProd.do?prodNo=<%=vo.getProdNo()%>&tranCode=2">배송하기</a>
				<% } %>
			<% } else if(vo.getProTranCode().trim().equals("2")) {%>
				배송중
			<% } else if(vo.getProTranCode().trim().equals("3")) {%>
				배송완료
			<% } else {%>
				tran_status_code 오류
			<% } %>
			--%>
		</td>		
	</tr>
	<tr>
		<td colspan="11" bgcolor="D6D7D6" height="1"></td>
	</tr>
	<% } %>
</table>

<table width="100%" border="0" cellspacing="0" cellpadding="0" style="margin-top:10px;">
	<tr>
		<td align="center">		
				
			<input type="hidden" id="currentPage" name="currentPage" value=""/>
			<% if( resultPage.getCurrentPage() <= resultPage.getPageUnit() ){ %>
					◀ 이전
			<% }else{ %>
					<a href="javascript:fncGetUserList('<%=resultPage.getCurrentPage()-1%>')">◀ 이전</a>
			<% } %>

			<%	for(int i=resultPage.getBeginUnitPage();i<= resultPage.getEndUnitPage() ;i++){	%>
					<a href="javascript:fncGetUserList('<%=i %>');"><%=i %></a>
			<% 	}  %>
	
			<% if( resultPage.getEndUnitPage() >= resultPage.getMaxPage() ){ %>
					이후 ▶
			<% }else{ %>
					<a href="javascript:fncGetUserList('<%=resultPage.getEndUnitPage()+1%>')">이후 ▶</a>
			<% } %>	
		
		
		<%-- 02리팩토링 내 방식 --%>
		<%-- 
		<%	int ct = currentPage/totalPageUnit;
			if (currentPage%totalPageUnit==0){
				ct -=1;
			}
			int tt = totalPage/totalPageUnit;
			if( totalPage%totalPageUnit ==0){
				tt -=1;
			}
		%>
		<% if( ct !=0){ %>
			<a href="/listProduct.do?menu=<%=menu%>&page=<%=ct*totalPageUnit%>
								<% if(searchMain!=null){ %>
									<%=searchMain%>
								<% } %>">이전 &nbsp;&nbsp; </a>
		<% } %>
		<% if( ct < tt ){ %>
				<% for(int i=(ct)*totalPageUnit+1; i<=(ct+1)*totalPageUnit; i++ ){ %>
					<a href="/listProduct.do?menu=<%=menu%>&page=<%=i%>
								<% if(searchMain!=null){ %>
									<%=searchMain%>
								<% } %>"><%=i %></a>
					
				<% } %>
				
				<a href="/listProduct.do?menu=<%=menu%>&page=<%=(ct+1)*totalPageUnit+1%>
								<% if(searchMain!=null){ %>
									<%=searchMain%>
								<% } %>">&nbsp;&nbsp; 이후</a>
				
		<% }else{ %>
				<% for(int i=(ct)*totalPageUnit+1; i<=totalPage; i++ ){ %>
					<a href="/listProduct.do?menu=<%=menu%>&page=<%=i%>
								<% if(searchMain!=null){ %>
									<%=searchMain%>
								<% } %>"><%=i %></a>
					
				<% } %>
		<% } %>	
		--%>
		
		
		<%-- 01 (리팩토링 전) --%>
		<%-- 
		<%
			for(int i=1;i<=totalPage;i++){
		%>
			<a href="/listProduct.do?menu=<%=menu%>&page=<%=i%>
								<% if(searchMain!=null){ %>
									<%=searchMain%>
								<% } %>"><%=i %></a>
		<%
			}
		%>	
		--%>
		
    	</td>
	</tr>
</table>
<!--  페이지 Navigator 끝 -->
</form>
</div>

</body>
</html>

<% /**/System.out.println("** listProduct.jsp ---- END "); %>