<%@ page contentType="text/html; charset=euc-kr" %>

<%@ page import="com.model2.mvc.service.domain.*" %>

<%
	Product vo =(Product)request.getAttribute("product");
	//request.setAttribute("productVO", vo);		//다음 페이지 리퀘스트에 상품객체 심어보내기 불가능(JS fnc에서 포워드가 안됨)
	//session = request.getSession();
	User user = (User)session.getAttribute("user");
%>

<%
	/**/System.out.println("** addPurchaseView.jsp ---- START");
	/**/System.out.println("** "+vo);
	/**/System.out.println("** "+user);
	/**/System.out.println("** addPurchaseView.jsp ---- END ");
%>

<html>
<head>
<title>상품 구매</title>

<link rel="stylesheet" href="/css/admin.css" type="text/css">

<script type="text/javascript" src="/javascript/calendar.js"></script>
<script type="text/javascript">
<!--
function fncAddPurchase() {
	// Form 유효성 검증
	var paymentOption=document.detailForm.paymentOption.value;
	var receiverName=document.detailForm.receiverName.value;
	var receiverPhone=document.detailForm.receiverPhone.value;
	var dlvyAddr=document.detailForm.dlvyAddr.value;
	var dlvyRequest=document.detailForm.dlvyRequest.value;
	var dlvyDate=document.detailForm.dlvyDate.value;
	
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
		
	document.detailForm.action='/addPurchase.do';
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

<input type="hidden" name="prodNo" value="<%=vo.getProdNo() %>">

<table width="100%" height="37" border="0" cellpadding="0"	cellspacing="0">
	<tr>
		<td width="15" height="37">
			<img src="/images/ct_ttl_img01.gif" width="15" height="37">
		</td>
		<td background="/images/ct_ttl_img02.gif" width="100%" style="padding-left:10px;">
		<table width="100%" border="0" cellspacing="0" cellpadding="0">
			<tr>
				<td width="93%" class="ct_ttl01">상품 구매</td>
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
			상품번호 <img src="/images/ct_icon_red.gif" width="3" height="3" align="absmiddle"/>
		</td>
		<td bgcolor="D6D6D6" width="1"></td>
		<td class="ct_write01">
			<table width="100%" border="0" cellspacing="0" cellpadding="0">
				<tr>
					<td width="105"><%=vo.getProdNo() %></td>
				</tr>
			</table>
		</td>
	</tr>
	<tr>
		<td height="1" colspan="3" bgcolor="D6D6D6"></td>
	</tr>
	
	<tr>
		<td width="104" class="ct_write">
			상품명 <img src="/images/ct_icon_red.gif" width="3" height="3" align="absmiddle">
		</td>
		<td bgcolor="D6D6D6" width="1"></td>
		<td class="ct_write01"><%=vo.getProdName() %></td>
	</tr>
	<tr>
		<td height="1" colspan="3" bgcolor="D6D6D6"></td>
	</tr>
	
	<%-- <tr>
		<td width="104" class="ct_write">상품이미지</td>
		<td bgcolor="D6D6D6" width="1"></td>
		<td class="ct_write01">
			<img src="/images/uploadFiles/<%=vo.getFileName()%>"/>
		</td>
	</tr>
	<tr>
		<td height="1" colspan="3" bgcolor="D6D6D6"></td>
	</tr> --%>
	
	<tr>
		<td width="104" class="ct_write">
		상품상세정보 <img src="/images/ct_icon_red.gif" width="3" height="3" align="absmiddle">
		</td>
		<td bgcolor="D6D6D6" width="1"></td>
		<td class="ct_write01"><%=vo.getProdDetail() %></td>
	</tr>

	<tr>
		<td height="1" colspan="3" bgcolor="D6D6D6"></td>
	</tr>
	<tr>
		<td width="104" class="ct_write">제조일자 </td>
		<td bgcolor="D6D6D6" width="1"></td>
		<td class="ct_write01">
			<table border="0" cellspacing="0" cellpadding="0">
				<tr>
					<td height="26"><%=vo.getManuDate() %></td>
				</tr>
			</table>
		</td>
	</tr>
	
	<tr>
		<td height="1" colspan="3" bgcolor="D6D6D6"></td>
	</tr>
	<tr>
		<td width="104" class="ct_write">가격</td>
		<td bgcolor="D6D6D6" width="1"></td>
		<td class="ct_write01"><%=vo.getPrice() %></td>
	</tr>
	
	<tr>
		<td height="1" colspan="3" bgcolor="D6D6D6"></td>
	</tr>
	<tr>
		<td width="104" class="ct_write">등록일자</td>
		<td bgcolor="D6D6D6" width="1"></td>
		<td class="ct_write01"><%=vo.getRegDate() %></td>
	</tr>

	<tr>
		<td height="1" colspan="3" bgcolor="D6D6D6"></td>
	</tr>
	
	
	<tr>
		<td width="104" class="ct_write">
			구매자아이디 <img src="/images/ct_icon_red.gif" width="3" height="3" align="absmiddle"></td>
		<td bgcolor="D6D6D6" width="1"></td>
		<td class="ct_write01"><%=user.getUserId() %></td>
	</tr>

	<tr>
		<td height="1" colspan="3" bgcolor="D6D6D6"></td>
	</tr>
	
	
	<tr>
		<td width="104" class="ct_write">구매방법</td>
		<td bgcolor="D6D6D6" width="1"></td>
		<td class="ct_write01">
			<select 	name="paymentOption" class="ct_input_g" style="width:100px" >
				<option value="0" >신용카드</option>
				<option value="1" >온라인입금</option>
			</select>
		</td>
	</tr>
	<tr>
		<td height="1" colspan="3" bgcolor="D6D6D6"></td>
	</tr>
	
	<tr>
		<td width="104" class="ct_write">
			구매자이름
		</td>
		<td bgcolor="D6D6D6" width="1"></td>
		<td class="ct_write01">
			<input 	type="text" name="receiverName" value="<%=user.getUserName() %>" class="ct_input_g" 
							style="width:100px; height:19px"  maxLength="20" >
		</td>
	</tr>
	
	<tr>
		<td height="1" colspan="3" bgcolor="D6D6D6"></td>
	</tr>
	
	
	<tr>
		<td width="104" class="ct_write">
			구매자연락처
		</td>
		<td bgcolor="D6D6D6" width="1"></td>
		<td class="ct_write01">
			<input 	type="text" name="receiverPhone" value="<%=user.getPhone() %>" class="ct_input_g" 
							style="width:100px; height:19px"  maxLength="14" >
		</td>
	</tr>
	<tr>
		<td height="1" colspan="3" bgcolor="D6D6D6"></td>
	</tr>
	
	<tr>
		<td width="104" class="ct_write">
			구매자주소
		</td>
		<td bgcolor="D6D6D6" width="1"></td>
		<td class="ct_write01">
			<input 	type="text" name="dlvyAddr" value="<%=user.getAddr() %>" class="ct_input_g" 
							style="width:100px; height:19px"  maxLength="100" >
		</td>
	</tr>
	<tr>
		<td height="1" colspan="3" bgcolor="D6D6D6"></td>
	</tr>
	
	<tr>
		<td width="104" class="ct_write">
			구매요청사항
		</td>
		<td bgcolor="D6D6D6" width="1"></td>
		<td class="ct_write01">
			<input 	type="text" name="dlvyRequest" class="ct_input_g" 
							style="width:200px; height:19px"  maxLength="100" >
		</td>
	</tr>
	<tr>
		<td height="1" colspan="3" bgcolor="D6D6D6"></td>
	</tr>
	
	<tr>
		<td width="104" class="ct_write">
			배송희망일자
		</td>
		<td bgcolor="D6D6D6" width="1"></td>
		<td class="ct_write01">
			<input 	type="text" name="dlvyDate" class="ct_input_g" 
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
					
						<!--  <a href="/addPurchase.do?prodNo=<%=vo.getProdNo()%>">구매</a>-->
						<a href="javascript:fncAddPurchase();">구매완료</a>
						
					</td>
					<td width="14" height="23">
						<img src="/images/ct_btnbg03.gif" width="14" height="23"/>
					</td>
					
					
					<td width="30"></td>					
					<td width="17" height="23">
						<img src="/images/ct_btnbg01.gif" width="17" height="23"/>
					</td>
					<td background="/images/ct_btnbg02.gif" class="ct_btn01" style="padding-top:3px;">
						<a href="javascript:history.go(-1);">이전</a>
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