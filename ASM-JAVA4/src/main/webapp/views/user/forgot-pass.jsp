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

	<div class="col-lg-12 col-12 mb-5">
		<center>
			<h2 class="tm-text-primary mb-2 mt-2">Forgot Password</h2>
		</center>
		<div class="tm-contact-form mx-auto">
			<div class="form-group">
				<input type="email" name="email" id="email"
					class="form-control rounded-0" placeholder="Email" required />
			</div>

			<div class="form-group tm-text-right my-0 mx-auto">
				<button type="submit" class="btn btn-primary" id="sendBtn">Send</button>
			</div>
			<div class="form-group mt-4">
				<h5 class="alert alert-info" id="messageReturn"></h5>
			</div>
		</div>
	</div>

	<%@include file="/common/footer.jsp"%>
	<script type="text/javascript">
		$('#sendBtn').click(function() {
			$('#messageReturn').text('');
			var email = $('#email').val();
			var formData = {'email' : email
			};
			$.ajax({
				url : 'forgotPass',
				type : 'POST',
				data : formData
			}).then(function(data) {
				$('#messageReturn').text('Password has been reset, please check your email');
				setTimeout(function() {window.location.href = 'http://localhost:8080/ASM-JAVA4/login'
				}, 5 * 1000);
			}).fail(function(error) {
				$('#messageReturn').text('Your information is not correct, try again');
			});
		});			
	</script>
</body>
</html>