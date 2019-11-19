<%@page import="java.util.List"%>
<%@page import="java.util.Map"%>
<%@ page contentType="text/html; charset=euc-kr" %>


<%@ page import="com.model2.mvc.service.domain.*" %>
<%@ page import="com.model2.mvc.common.*" %>

<%
	Map<String,Object> map = (Map<String,Object>)request.getAttribute("map");
	List<Purchase> list = (List<Purchase>)map.get("list");
	Search search = (Search)request.getAttribute("search");
	Page resultPage = (Page)request.getAttribute("resultPage");
	
	/**/System.out.println("** listPurchase.jsp ---- START");
	/**/System.out.println("** "+search);
	

	//String searchCondition = CommonUtil.null2str(search.getSearchCondition());
	//String searchKeyword = CommonUtil.null2str(search.getSearchKeyword());
%>

<html>
<head>
<title>���� �����ȸ</title>

<link rel="stylesheet" href="/css/admin.css" type="text/css">

<script type="text/javascript">
function fncGetPurchaseList(currentPage){	
	document.getElementById("currentPage").value = currentPage;
	document.detailForm.submit();
}
</script>
</head>

<body bgcolor="#ffffff" text="#000000">

<div style="width:98%; margin-left:10px;">

<form name="detailForm" action="/listPurchase.do" method="post">

<table width="100%" height="37" border="0" cellpadding="0"	cellspacing="0">
	<tr>
		<td width="15" height="37">
			<img src="/images/ct_ttl_img01.gif" width="15" height="37">
		</td>
		<td background="/images/ct_ttl_img02.gif" width="100%" style="padding-left:10px;">
			<table width="100%" border="0" cellspacing="0" cellpadding="0">
				<tr>
					<td width="93%" class="ct_ttl01">���� �����ȸ</td>
				</tr>
			</table>
		</td>
		<td width="12" height="37">
			<img src="/images/ct_ttl_img03.gif" width="12" height="37">
		</td>
	</tr>
</table>

<%-- 
<table width="100%" border="0" cellspacing="0" cellpadding="0" style="margin-top:10px;">
	<tr>
		<td align="right">
			<select name="searchCondition" class="ct_input_g" style="width:80px">
				<option value="0" <%= (searchCondition.equals("0") ? "selected" : "")%>>��ǰ��ȣ</option>
				<option value="1" <%= (searchCondition.equals("1") ? "selected" : "")%>>��ǰ��</option>
				<option value="2" <%= (searchCondition.equals("2") ? "selected" : "")%>>��ǰ����</option>
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
						<a href="javascript:fncGetPurchaseList('1');">�˻�</a>
					</td>
					<td width="14" height="23">
						<img src="/images/ct_btnbg03.gif" width="14" height="23">
					</td>
				</tr>
			</table>
		</td>
	</tr>
</table>
--%>

<table width="100%" border="0" cellspacing="0" cellpadding="0" style="margin-top:10px;">
	<tr>
		<td colspan="11" >��ü  <%=resultPage.getTotalCount()%> �Ǽ�, ���� <%=resultPage.getCurrentPage()%>/<%=resultPage.getMaxPage()%> ������</td>
	</tr>
	<tr>
		<td class="ct_list_b" width="100">No</td>
		<td class="ct_line02"></td>
		<td class="ct_list_b" width="150">ȸ��ID</td>
		<td class="ct_line02"></td>
		<td class="ct_list_b" width="150">ȸ����</td>
		<td class="ct_line02"></td>
		<td class="ct_list_b">��ȭ��ȣ</td>
		<td class="ct_line02"></td>
		<td class="ct_list_b">�����Ȳ</td>
		<td class="ct_line02"></td>
		<td class="ct_list_b">��������</td>		
	</tr>
	<tr>
		<td colspan="11" bgcolor="808285" height="1"></td>
	</tr>
	<%
		for(int i=0; i<list.size(); i++) {
			Purchase vo = (Purchase)list.get(i);
	%>
	<tr class="ct_list_pop">
		<td align="center">
			<a href="/getPurchase.do?tranNo=<%=vo.getTranNo()%>"><%=i+1%></a></td>
		<td></td>
		<td align="left">
			<a href="/getUser.do?userId=<%=vo.getBuyer().getUserId()%>"><%= vo.getBuyer().getUserId() %></a>
		</td>
		<td></td>
		<td align="left"><%= vo.getReceiverName() %></td>
		<td></td>
		<td align="left"><%= vo.getReceiverPhone() %></td>
		<td></td>
		<td align="left">
		<%-- ����
		<% if(vo.getTranCode().equals("sale")){ %>
				�Ǹ���
		<% } else if(vo.getTranCode().trim().equals("1")) {%>
				���ſϷ�
		<% } else if(vo.getTranCode().trim().equals("2")) {%>
				�����
		<% } else if(vo.getTranCode().trim().equals("3")) {%>
				��ۿϷ�
		<% } else {%>
				tran_status_code ����
		<% } %>
		���� �Դϴ�. --%>
		
		<%
			if(vo.getTranCode() != null) {
				if(vo.getTranCode().trim().equals("1")) {
			%>
				�ǸſϷ�
				
				
				<% } else if(vo.getTranCode().trim().equals("2")) { %>
				
				�����
				<a href="/updateTranCode.do?tranNo=<%=vo.getTranNo()%>&tranCode=3">���ǵ���</a>
				<% } else if(vo.getTranCode().trim().equals("3")) { %>
				
				��ۿϷ�
				
				<% } %>
			<% } else {%>
				�Ǹ���		
			<% } %>
		</td>	
		<td></td>
		<%-- <td align="left">
			<% if( vo.getTranCode().trim().equals("2") ){ %>
				<a href="/updateTranCode.do?tranNo=<%=vo.getTranNo()%>&tranCode=3">���ǵ���</a>
			<% } %>
		</td>	 --%>		
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
				<% if( resultPage.getCurrentPage() > resultPage.getPageUnit() ){ %>
					<!--  <a href="javascript:fncGetPurchaseList('<%=resultPage.getCurrentPage()-1%>')">�� ����</a> -->
					<a href="javascript:fncGetPurchaseList('<%=((resultPage.getCurrentPage()-1)/resultPage.getPageUnit())*resultPage.getPageUnit()%>')">�� ����</a>
				<% } %>
				
				<% for(int i=resultPage.getBeginUnitPage(); i<=resultPage.getEndUnitPage(); i++){ %>
					<a href="javascript:fncGetPurchaseList('<%=i %>');"><%=i %></a>
				<% } %>
				
				<% if( (resultPage.getCurrentPage()-1)/resultPage.getPageUnit() < (resultPage.getMaxPage()-1)/resultPage.getPageUnit() ){ %>
					<!--  <a href="javascript:fncGetPurchaseList('<%=resultPage.getEndUnitPage()+1%>')">���� ��</a> -->
					<a href="javascript:fncGetPurchaseList('<%=((resultPage.getCurrentPage()-1)/resultPage.getPageUnit()+1)*resultPage.getPageUnit()+1%>')">���� ��</a>
				<% } %>
		
	
		
		<%-- �����丵 �� --%>
		<%-- 
		<%
			for(int i=1;i<=totalPage;i++){
		%>
			<a href="/listPurchase.do?page=<%=i%>"><%=i %></a>
		<%
			}
		%>	
		--%>
		
    	</td>
	</tr>
</table>
<!--  ������ Navigator �� -->
</form>
</div>

</body>
</html>

<% /**/System.out.println("** listPurchase.jsp ---- END "); %>