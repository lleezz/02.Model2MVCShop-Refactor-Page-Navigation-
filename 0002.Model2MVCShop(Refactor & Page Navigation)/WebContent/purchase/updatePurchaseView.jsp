<%@ page contentType="text/html; charset=euc-kr" %>
<%@ page import="com.model2.mvc.service.domain.*" %>

<%
	Purchase vo = (Purchase)request.getAttribute("vo");
%>

<%
	/**/System.out.println("** updatePurchase.jsp ---- START");
	/**/System.out.println("** "+vo);
	/**/System.out.println("** updatePurchase.jsp ---- END ");
%>

<html>
<head>
<title>구매정보수정</title>

<link rel="stylesheet" href="/css/admin.css" type="text/css">

<script type="text/javascript">
<!--
function fncUpdatePurchase(){
	
	var paymentOption=document.detailForm.paymentOption.value;
	var receiverName=document.detailForm.receiverName.value;
	var receiverPhone=document.detailForm.receiverPhone.value;
	var dlvyAddr=document.detailForm.dlvyAddr.value;
	//var dlvyRequest=document.detailForm.dlvyRequest.value;
	//var dlvyDate=document.detailForm.dlvyDate.value;
	
	if(paymentOption == null || paymentOption.length <1){
		alert("구매방법은 반드시 선택하셔야 합니다.");
		return;
	}
	if(receiverName == null || receiverName.length <1){
		alert("구매자이름은  반드시 입력하셔야 합니다.");
		return;
	}
	/* if(receiverPhone == null || receiverPhone.length <1){
		alert("구매자 연락처는  반드시 입력하셔야 합니다.");
		return;
	} */
	/* if(dlvyAddr == null || dlvyAddr.length <1){
		alert("구매자 주소는  반드시 입력하셔야 합니다.");
		return;
	} */
	
	document.detailForm.action='/updatePurchase.do';
	document.detailForm.submit();
}

function resetData() {
	document.detailForm.reset();
}
-->
</script>

</head>

<body bgcolor="#ffffff" text="#000000">

<form name="detailForm"  method="post" >

<input type="hidden" name="tranNo" value="<%=vo.getTranNo() %>">

<table width="100%" height="37" border="0" cellpadding="0"	cellspacing="0">
	<tr>
		<td width="15" height="37">
			<img src="/images/ct_ttl_img01.gif" width="15" height="37">
		</td>
		<td background="/images/ct_ttl_img02.gif" width="100%" style="padding-left:10px;">
		<table width="100%" border="0" cellspacing="0" cellpadding="0">
			<tr>
				<td width="93%" class="ct_ttl01">구매정보수정</td>
				<td width="20%" align="right">&nbsp;</td>
			</tr>
		</table>
		</td>
		<td width="12" height="37"><img src="/images/ct_ttl_img03.gif" width="12" height="37"></td>
	</tr>
</table>

<table width="100%" border="0" cellspacing="0" cellpadding="0" style="margin-top:13px;">
	<tr>
		<td height="1" colspan="3" bgcolor="D6D6D6"></td>
	</tr>
	
	<tr>
		<td width="104" class="ct_write">
			구매자아이디 <img src="/images/ct_icon_red.gif" width="3" height="3" align="absmiddle">
		</td>
		<td bgcolor="D6D6D6" width="1"></td>
		<td class="ct_write01"><%=vo.getBuyer().getUserId() %></td>
	</tr>
	<tr>
		<td height="1" colspan="3" bgcolor="D6D6D6"></td>
	</tr>
	
	<tr>
		<td width="104" class="ct_write">구매방법</td>
		<td bgcolor="D6D6D6" width="1"></td>
		<td class="ct_write01">
			<select 	name="paymentOption" class="ct_input_g" style="width:100px" >
				<% if(vo.getPaymentOption().trim().equals("0")){ %>
					<option value="0" selected >신용카드</option>
					<option value="1" >온라인입금</option>
				<% }else if(vo.getPaymentOption().trim().equals("1")){ %>
					<option value="0" >신용카드</option>
					<option value="1" selected >온라인입금</option>
				<% } %>
			</select>
		</td>
	</tr>
	<tr>
		<td height="1" colspan="3" bgcolor="D6D6D6"></td>
	</tr>
	
	<tr>
		<td width="104" class="ct_write">구매자이름</td>
		<td bgcolor="D6D6D6" width="1"></td>
		<td class="ct_write01">
			<input 	type="text" name="receiverName" value="<%=vo.getReceiverName() %>" class="ct_input_g" 
							style="width:100px; height:19px"  maxLength="20"  />
		</td>
	</tr>
	<tr>
		<td height="1" colspan="3" bgcolor="D6D6D6"></td>
	</tr>
	
	<tr>
		<td width="104" class="ct_write">구매자연락처</td>
		<td bgcolor="D6D6D6" width="1"></td>
		<td class="ct_write01">
			<input 	type="text" name="receiverPhone" value="<%=vo.getReceiverPhone() %>" class="ct_input_g" 
							style="width:100px; height:19px"  maxLength="14"  />
		</td>
	</tr>
	<tr>
		<td height="1" colspan="3" bgcolor="D6D6D6"></td>
	</tr>
	
	<tr>
		<td width="104" class="ct_write">구매자주소</td>
		<td bgcolor="D6D6D6" width="1"></td>
		<td class="ct_write01">
			<input 	type="text" name="dlvyAddr" value="<%=vo.getDivyAddr() %>" class="ct_input_g" 
							style="width:100px; height:19px"  maxLength="100"  />
		</td>
	</tr>
	<tr>
		<td height="1" colspan="3" bgcolor="D6D6D6"></td>
	</tr>
	
	<tr>
		<td width="104" class="ct_write">구매요청사항</td>
		<td bgcolor="D6D6D6" width="1"></td>
		<td class="ct_write01">
			<input 	type="text" name="dlvyRequest" 
					<% if(vo.getDivyRequest()!=null){ %>
						value="<%=vo.getDivyRequest() %>"
					<% } %>
					 class="ct_input_g" 
							style="width:200px; height:19px"  maxLength="100"  />
		</td>
	</tr>
	<tr>
		<td height="1" colspan="3" bgcolor="D6D6D6"></td>
	</tr>
	
	<tr>
		<td width="104" class="ct_write">배송희망일자</td>
		<td bgcolor="D6D6D6" width="1"></td>
		<td class="ct_write01">

			<input 	type="text" name="dlvyDate" 
					<% if(vo.getDivyDate()!=null){ %>
						value="<%=vo.getDivyDate()%>" 
					<% } %>
						class="ct_input_g" 
							style="width:100px; height:19px"  maxLength="20"  /> 
			<a href="javascript:show_calendar('document.detailForm.dlvyDate', document.detailForm.dlvyDate.value);">
				<img src="/images/ct_icon_date.gif" width="18" height="18" align="absmiddle"/>
			</a>

		</td>
	</tr>
	<tr>
		<td height="1" colspan="3" bgcolor="D6D6D6"></td>
	</tr>

	
</table>

<table width="100%" border="0" cellspacing="0" cellpadding="0"	style="margin-top:10px;">
	<tr>
		<td width="53%"></td>
		<td align="right">
			<table border="0" cellspacing="0" cellpadding="0">
				<tr>

					<td width="17" height="23">
						<img src="/images/ct_btnbg01.gif" width="17" height="23">
					</td>
					<td background="/images/ct_btnbg02.gif" class="ct_btn01" style="padding-top:3px;">
						<a href="javascript:fncUpdatePurchase();">수정</a>
					</td>
					<td width="14" height="23">
						<img src="/images/ct_btnbg03.gif" width="14" height="23"/>
					</td>
					
					
					<td width="30"></td>					
					<td width="17" height="23">
						<img src="/images/ct_btnbg01.gif" width="17" height="23"/>
					</td>
					<td background="/images/ct_btnbg02.gif" class="ct_btn01" style="padding-top:3px;">
						<a href="javascript:resetData();">취소</a>
					</td>
					<td width="14" height="23">
						<img src="/images/ct_btnbg03.gif" width="14" height="23"/>
					</td>
					
				</tr>
			</table>
		</td>
	</tr>
</table>

</form>

</body>
</html>