<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<script type="text/javascript" src="https://service.iamport.kr/js/iamport.payment-1.1.1.js">
var IMP = window.IMP;
IMP.init('imp32851262');

/* var IMP_param = IMP.object_param();
IMP_param.pg = ${pg_provider}
IMP_param.pay_method = ${pay_method}
IMP_param.escrow = ${true}
IMP_param.merchan_uid = ${'imp32851262' + new Date().getTime()}
IMP_param.name = ${'쏘렌토'}
IMP_param.amount = ${10}
IMP_param.buyer_name = ${'백상휘'}
IMP_param.buyer_email = ${'jagjayo@gmail.com'}
IMP_param.buyer_tel = ${'010-3103-6671'}
IMP_param.buyer_addr = ${'서울특별시 강남구 삼성동'}
IMP_param.buyer_postcode = ${'123-456'}
IMP_param.vbank_due = ${'20151231'} */



IMP.request_pay({
    pg : 'inicis', // version 1.1.0부터 지원.
        /*
            'kakao':카카오페이,
            'inicis':이니시스, 'html5_inicis':이니시스(웹표준결제),
            'nice':나이스,
            'jtnet':jtnet,
            'uplus':LG유플러스
        */
    pay_method : 'card', // 'card' : 신용카드 | 'trans' : 실시간계좌이체 | 'vbank' : 가상계좌 | 'phone' : 휴대폰소액결제
    merchant_uid : 'merchant_' + new Date().getTime(),
    name : '주문명:결제테스트',
    amount : 14000,
    buyer_email : 'iamport@siot.do',
    buyer_name : '구매자이름',
    buyer_tel : '010-1234-5678',
    buyer_addr : '서울특별시 강남구 삼성동',
    buyer_postcode : '123-456'
}, function(rsp) {
    if ( rsp.success ) {
        var msg = '결제가 완료되었습니다.';
        msg += '고유ID : ' + rsp.imp_uid;
        msg += '상점 거래ID : ' + rsp.merchant_uid;
        msg += '결제 금액 : ' + rsp.paid_amount;
        msg += '카드 승인번호 : ' + rsp.apply_num;
    } else {
        var msg = '결제에 실패하였습니다.';
        msg += '에러내용 : ' + rsp.error_msg;
    }
});

</script>
<head>
<title>Insert title here</title>
</head>
<body>
<form>
<table border="1" cellpadding="0" cellspacing="1">
	<tr>
		<td><label for="pg_provider" class="col-md-4 col-xs-4">지원 PG사</label></td>
		<td>
			<select name="pg_provider" id="pg-provider" class="col-md-8 col-xs-8">
				<option value="kakao" selected>카카오페이</option>
				<option value="html5_inicis">KG이니시스(웹표준결제)</option>
				<option value="inicis">KG이니시스(기존모듈)</option>
				<option value="uplus">LG유플러스</option>
				<option value="nice">나이스정보통신</option>
				<option value="jtnet">JTNet</option>
				<option value="danal">다날-휴대폰소액결제전용</option>
				<option value="paypal">페이팔-ExpressCheckout</option>
            </select>
		</td>
	</tr>
	<tr>
		<td><label for="pay_method">결제수단</label></td>
		<td><select name="pay_method" id="pay_method" class="col-md-8 col-xs-8">
		<option value="card">신용카드</option></select>
		</td>
	</tr>
	<tr>
		<td><label for="merchant_uid" class="col-md-4 col-xs-4">주문번호</label></td>
		<td><input type="text" name="merchant_uid" id="merchant_uid" value="" class="col-md-8 col-xs-8"/></td>
	</tr>
	<tr>
		<td><label for="name" class="col-md-4 col-xs-4">결제명</label></td>
		<td><input type="text" name="name" id="name" value="결제테스트" class="col-md-8 col-xs-8"/></td>
	</tr>
	<tr>
		<td><label for="amount" class="col-md-4 col-xs-4">금액</label></td>
		<td><input type="tel" name="amount" id="amount" value="1004" class="col-md-8 col-xs-8"/></td>
	</tr>
	<tr>
		<td><label for="buyer_email" class="col-md-4 col-xs-4">이메일주소</label></td>
		<td><input type="text" name="buyer_email" id="buyer_email" value="iamport@siot.do" class="col-md-8 col-xs-8"/></td>
	</tr>
	<tr>
		<td><label for="buyer_name" class="col-md-4 col-xs-4">성함</label></td>
		<td><input type="text" name="buyer_name" id="buyer_name" value="구매자" class="col-md-8 col-xs-8"/></td>
	</tr>
	<tr>
		<td><label for="buyer_tel" class="col-md-4 col-xs-4">전화번호</label></td>
		<td><input type="tel" name="buyer_tel" id="buyer_tel" value="010-1234-5678" class="col-md-8 col-xs-8"/></td>
	</tr>
	<tr>
		<td><label for="buyer_addr" class="col-md-4 col-xs-4">주소</label></td>
		<td><input type="text" name="buyer_addr" id="buyer_addr" value="서울특별시 강남구 삼성동" class="col-md-8 col-xs-8"/></td>
	</tr>
	<tr>
		<td><label for="buyer_postcode" class="col-md-4 col-xs-4">우편번호</label></td>
		<td><input type="text" name="buyer_postcode" id="buyer_postcode" value="123-456" class="col-md-8 col-xs-8"/></td>
	</tr>
	<tr>
		<td><label for="vbank_due" class="col-md-4 col-xs-4">가상계좌 입금일자<br>(YYYYMMDD)</label></td>
		<td><input type="text" name="vbank_due" id="vbank_due" value="" class="col-md-8 col-xs-8"/></td>
	</tr>
	<tr>
		<td colspan="2" align="center"><input type="button" value="결제하기" onclick="IMP.request_pay()"></td>
	</tr>
</table>
</form><br>

</body>
</html>