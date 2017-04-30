<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Download Image Movie</title>
</head>
<body>
<form method="POST" action="${pageContext.request.contextPath}/movie/downloadImage/${movie_id}" enctype="multipart/form-data">
		File to upload: <input type="file" name="file"><br /> 
		<input type="submit" value="Upload"> 
		Press here to upload the file!
	</form>
	
	<a href="${pageContext.request.contextPath}">Back to the home page!</a>
	<a href="${pageContext.request.contextPath}/movie">Back to the movie page!</a>
</body>
</html>