<%@ page language="java" contentType="text/html; charset=GB18030"
	pageEncoding="GB18030" import="edu.neu.cs5200.HW5.orm.dao.*, edu.neu.cs5200.HW5.orm.models.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=GB18030">
<title>Insert title here</title>
<!-- <script src="js/jquery-1.11.2.js"></script> -->
<script src="//code.jquery.com/jquery-1.11.2.min.js"></script>
</head>
<body>
	<h1>Hello</h1>
	<script>
	
		$(function() {
			alert("Hello from JQuery");
			var site = {
				id : 321,
				name : "Site 5",
				latitude : 12.34,
				longitude : 23.45
			};
			
			//createSite(site);
			//removeSite(321);
			//updateSite(3,site);
		});

		function createSite(site) {
			$.ajax({
				url : "/cs5200.HW5/api/site",
				data : JSON.stringify(site),
				/* convert site into astring */
				type : "post",
				contentType : "application/json",
				dataType : "json",
				success : function(response) {
					consple.log("ok");
				}
			});
		}
		
		function removeSite(id)
		{
			$.ajax({
				url : "/cs5200.HW5/api/site/"+id,
				type : "delete",
				contentType : "application/json",
				dataType : "json",
				success : function(response) {
					consple.log("ok");
				}
			});
		}
		
		function updateSite(id, site){
			$.ajax(
			{
				url: "/cs5200.HW5/api/site/"+id,
				data: JSON.stringify(director),
				type: "put",
				dataType: "json",
				contentType: "application/json",
				success: function(response) {
					console.log(response);
				}
			});
		}
		
	</script>
</body>
</html>