<%@ page contentType="text/html; charset=euc-kr" %>

<%
	/**/System.out.println("** addProductView.jsp ---- START");
	/**/System.out.println("** addProductView.jsp ---- END ");
%>


<html>
<head>
<title>상품등록</title>

<link rel="stylesheet" href="/css/admin.css" type="text/css">

<script type="text/javascript" src="/javascript/calendar.js"></script>

<script type="text/javascript">
<!--
function fncAddProduct() {
	
	var prodName=document.detailForm.prodName.value;
	var prodDetail=document.detailForm.prodDetail.value;
	var manuDate=document.detailForm.manuDate.value;
	var price=document.detailForm.price.value;
	
	if(prodName == null || prodName.length <1){
		alert("상품명은 반드시 입력하셔야 합니다.");
		return;
	}
	if(prodDetail == null || prodDetail.length <1){
		alert("상품상세정보는  반드시 입력하셔야 합니다.");
		return;
	}
	if(manuDate == null || manuDate.length <1){
		alert("제조일자는  반드시 입력하셔야 합니다.");
		return;
	}
	if(price == null || price.length <1){
		alert("가격은  반드시 입력하셔야 합니다.");
		return;
	}
	
	document.detailForm.action='/addProduct.do';
	document.detailForm.submit();
}

function fncSearchImageFile() {
	popWin = window.open("/product/searchImageFile.jsp","popWin", "left=300,top=200,width=300,height=200,marginwidth=0,marginheight=0,scrollbars=no,scrolling=no,menubar=no,resizable=no");
}

function resetData() {
	document.detailForm.reset();
}
-->
</script>
</head>

<body bgcolor="#ffffff" text="#000000">

<form name="detailForm"  method="post" >

<table width="100%" height="37" border="0" cellpadding="0"	cellspacing="0">
	<tr>
		<td width="15" height="37">
			<img src="/images/ct_ttl_img01.gif" width="15" height="37"/>
		</td>
		<td background="/images/ct_ttl_img02.gif" width="100%" style="padding-left:10px;">
			<table width="100%" border="0" cellspacing="0" cellpadding="0">
				<tr>
					<td width="93%" class="ct_ttl01">상품등록</td>
					<td width="20%" align="right">&nbsp;</td>
				</tr>
			</table>
		</td>
		<td width="12" height="37">
			<img src="/images/ct_ttl_img03.gif" width="12" height="37"/>
		</td>
	</tr> 
</table>

<table width="100%" border="0" cellspacing="0" cellpadding="0" style="margin-top:13px;">
	<tr>
		<td height="1" colspan="3" bgcolor="D6D6D6"></td>
	</tr>
	
	<tr>
		<td width="104" class="ct_write">
			상품명 <img src="/images/ct_icon_red.gif" width="3" height="3" align="absmiddle"/>
		</td>
		<td bgcolor="D6D6D6" width="1"></td>
		<td class="ct_write01">
			<input 	type="text" name="prodName" class="ct_input_g" 
							style="width:100px; height:19px"  maxLength="100" minLength="2"  />
		</td>
	</tr>
	
	<tr>
		<td height="1" colspan="3" bgcolor="D6D6D6"></td>
	</tr>
	
	<tr>
		<td width="104" class="ct_write">
			상품상세정보 <img src="/images/ct_icon_red.gif" width="3" height="3" align="absmiddle"/>
		</td>
		<td bgcolor="D6D6D6" width="1"></td>
		<td class="ct_write01">
			<input 	type="text" name="prodDetail" class="ct_input_g" 
							style="width:600px; height:50px"  maxLength="200"   />
		</td>
	</tr>
	
	<tr>
		<td height="1" colspan="3" bgcolor="D6D6D6"></td>
	</tr>
	
	<tr>
		<td width="104" class="ct_write">
			제조일자 <img src="/images/ct_icon_red.gif" width="3" height="3" align="absmiddle"/>
		</td>
		<td bgcolor="D6D6D6" width="1"></td>
		<td class="ct_write01">
			<input 	type="text" name="manuDate" class="ct_input_g" 
							style="width:100px; height:19px"  maxLength="10" minLength="6"  /> 
			<a href="javascript:show_calendar('document.detailForm.manuDate', document.detailForm.manuDate.value);">
				<img src="/images/ct_icon_date.gif" width="18" height="18" align="absmiddle"/>
			</a>
		</td> 

		

	</tr>
	
	<tr>
		<td height="1" colspan="3" bgcolor="D6D6D6"></td>
	</tr>
	
	<tr>
		<td width="104" class="ct_write">
			가격<img src="/images/ct_icon_red.gif" width="3" height="3" align="absmiddle"/>
		</td>
		<td bgcolor="D6D6D6" width="1"></td>
		<td class="ct_write01">
			<input 	type="text" name="price" class="ct_input_g" 
							style="width:100px; height:19px"  maxLength="50" >원
		</td>
	</tr>
	
	<tr>
		<td height="1" colspan="3" bgcolor="D6D6D6"></td>
	</tr>
	
	<tr>
		<td width="104" class="ct_write">
			상품이미지</td>
		<td bgcolor="D6D6D6" width="1"></td>
		<td class="ct_write01">
		
			<table width="100%" border="0" cellspacing="0" cellpadding="0">
				<tr>
					<td width="105">
						<input 	type="file" name="fileName" class="ct_input_g" 
							style="width:400px; height:19px"  maxLength="100" >
					</td>
					<td>
						<table border="0" cellspacing="0" cellpadding="0">
							<tr>
								<!-- <td width="4" height="21">
									<img src="/images/ct_btng01.gif" width="4" height="21"/>
								</td>
								<td 	align="center" background="/images/ct_btng02.gif" class="ct_btn" 
										style="padding-top:3px;">
									<a href="javascript:fncSearchImageFile();" id="searchFile">찾아보기..</a>
								</td>
								<td width="4" height="21">
									<img src="/images/ct_btng03.gif" width="4" height="21">
								</td> -->
							</tr>
						</table>
					</td>
				</tr>
			</table>
	
		</td>
		
	</tr>

	<tr>
		<td height="1" colspan="3" bgcolor="D6D6D6"></td>
	</tr>
	
</table>

<table width="100%" border="0" cellspacing="0" cellpadding="0" style="margin-top:10px;">
	<tr>
		<td width="53%"></td>
		<td align="right">
			<table border="0" cellspacing="0" cellpadding="0">
				<tr>
					<td width="17" height="23">
						<img src="/images/ct_btnbg01.gif" width="17" height="23"/>
					</td>
					<td background="/images/ct_btnbg02.gif" class="ct_btn01" style="padding-top:3px;">
						<a href="javascript:fncAddProduct();">등록</a>
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
						<img src="/images/ct_btnbg03.gif" width="14" height="23">
					</td>
				</tr>
			</table>
		</td>
	</tr>
</table>

</form>

<script type="text/javascript">
document.getElementById("searchFile").focus();
</script>



</body>
</html>
