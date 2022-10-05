<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>partner_qna_input</title>
<link rel='stylesheet' type='text/css' href='./css/partner/partner_request.css'>
<link href="https://fonts.googleapis.com/css2?family=Noto+Sans+KR:wght@300&display=swap" rel="stylesheet">
<script defer src="./js/partner/partner_qna_input.js"></script>
<script src="https://code.jquery.com/jquery-3.6.0.min.js" integrity="sha256-/xUj+3OJU5yExlq6GSYGSHk7tPXikynS7ogEvDej/m4=" crossorigin="anonymous"></script>
</head>
<body>
<div id='partner_qna_input'>
   <form name='frm_partner_qna_input' class='frm_partner_qna_input' method='post'>
      <h2>문의사항 답변</h2>
      <label>작성일</label>
      <input type='date' name='nal' size='26' value="${vo.nal }" readonly/><br/>
      
      <label>캠핑장코드</label>
      <input type='text' name='itemCode' size='26' value="${vo.itemCode }"/><br/>
      
      <label>작성자</label>
      <input type='text' name='mName' size='26' value="${vo.mName }"/><br/>
      
      <label>문의 제목 : </label>
      <input type='text' name='qna_title' size='26' value='${vo.qtitle }'/><br>
      <div id="question">
      <label>문의 내용 : </label>
      <label></label>
      <textarea name='qnw' class='qna_doc' readonly>${que }</textarea><br/>
      </div>
      <label class='ans_title'>답변 제목 : </label>
      <input type='text' name='title' size='26' value="${vo.title }"/><br/>
      <label>답변 내용 : </label><br/>
      <label></label>
      <textarea name='doc' class='ans_doc'>${ans }</textarea><br/>
      <div class='btns_input'>
         <button type='button' class='btn_save'  onclick="qna_input.save(this.form)">저장</button>
         <button type='button' class='btn_cancel' onclick="qna_input.return(this.form)">취소</button>
         <button type='button' class='btn_delete' onclick="qna_input.delete(this.form)">삭제</button>
         
      </div>
      
      
      <input type='hidden' name='nowPage' value='${vo.nowPage }'/>
      <input type='hidden' name='findStr' value='${vo.findStr }'/>
      <input type='hidden' name='sno' value="${vo.sno }"/>
      <input type='hidden' name='grp' value="${vo.grp }"/>
      <input type='hidden' name='state' value='${vo.state}'/>
      <input type='hidden' name='userCode' value="${vo.userCode}"/>
      <input type='hidden' name='pwd' value='${vo.pwd }'/>
      
   </form>

</div>
</body>
</html>