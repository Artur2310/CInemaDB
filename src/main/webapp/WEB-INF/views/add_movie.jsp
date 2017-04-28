<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Add Movie</title>
</head>
<body>

	<form:form commandName="movie" method="POST" action="add-movie">
		<fieldset class="boxBody">

			<form:label path="title">Title:</form:label>
			<form:input path="title" />
			<form:errors path="title" cssClass="error"/>

			<form:label path="imdb">IMDB:</form:label>
			<form:input path="imdb" />
			<form:errors path="imdb" cssClass="error"/>

			<form:label path="description">Description:</form:label>
			<form:input path="description" />

			<form:label path="genre">Genre:</form:label>
			<form:input path="genre" />


		</fieldset>

		<footer> <input type="submit" class="btnLogin" value="Login"
			tabindex="4"> </footer>

	</form:form>
	
	<form method="POST" action="${pageContext.request.contextPath}/downloadImage" enctype="multipart/form-data">
		File to upload: <input type="file" name="file"><br /> 
		<input type="submit" value="Upload"> 
		Press here to upload the file!
	</form>
	
	
	<a href="${pageContext.request.contextPath}">Back to the home page!</a>
	<a href="${pageContext.request.contextPath}/movie">Back to the movie page!</a>
</body>
</html>