<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/common/taglib.jsp"%>


<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Forgot Password</title>
<%@include file="/common/head.jsp"%>
</head>
<body>
	<%@include file="/common/header.jsp"%>

	<form action="/ASM-JAVA4/ShareMailWithFriend" method="post"
		class="col-lg-12 col-12 mb-5">
		<center>
			<h2 class="tm-text-primary mb-2 mt-2">Share Video With Friend</h2>
		</center>

		<div class="tm-contact-form mx-auto">
			<div class="form-group">
				<input type="email" name="email" id="emailId"
					class="form-control rounded-0" placeholder="Email" required />
			</div>

			<div class="form-group tm-text-right my-0 mx-auto">
				<button type="submit" class="btn btn-primary" id="ShareId">Send</button>
			</div>
			<div class="form-group mt-4">
				<h5 class="alert alert-info">${messageId}</h5>
			</div>
		</div>


	</form>

	<%@include file="/common/footer.jsp"%>
</body>
</html>