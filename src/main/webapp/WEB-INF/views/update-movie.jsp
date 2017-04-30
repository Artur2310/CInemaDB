<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Update movie</title>
</head>
<body>
	<form:form commandName="movie" method="POST"
		action="${pageContext.request.contextPath}/movie/update-movie">
		<fieldset class="boxBody">

			
			<form:input path="id" type="hidden" />

			<form:label path="title">Title:</form:label>
			<form:input path="title" />
			<form:errors path="title"/>

			<form:label path="imdb">IMDB:</form:label>
			<form:input path="imdb" />
			<form:errors path="imdb" cssClass="error"/>

			<form:label path="description">Description:</form:label>
			<form:input path="description" />

			<form:label path="genre">Genre:</form:label>
			<form:input path="genre" />


		</fieldset>

		<footer> <input type="submit" class="btnUpdate"
			value="Update" tabindex="4"> </footer>

	</form:form>

<a href="${pageContext.request.contextPath}/movie/downloadImage/${movie.id}">Update image</a>
		
	<a href="${pageContext.request.contextPath}">Back to the home page!</a>
	<a href="${pageContext.request.contextPath}/movie">Back to the
		movie page!</a>
</body>
</html>