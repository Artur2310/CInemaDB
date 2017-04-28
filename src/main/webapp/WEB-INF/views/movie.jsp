<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Movie</title>
</head>
<body>

	<a href="${pageContext.request.contextPath}/movie/add-movie">add
		Movie</a>

	<table class="movieTable">
	<tr>
				<td>Title</td>
				<td>IMDB</td>
				<td>Description</td>
				<td>Genre</td>
				
			</tr>
		<c:forEach var="movie_item" items="${movies}">
			<tr>
				<td>${movie_item.title}</td>
				<td>${movie_item.imdb}</td>
				<td>${movie_item.description}</td>
				<td>${movie_item.genre}</td>
				<td>
				<form action="${pageContext.request.contextPath}/movie/delete-movie/${movie_item.id}" method="post">
				<input type="submit" value="delete"  />
				</form>
				</td>
				<td>
				<form action="${pageContext.request.contextPath}/movie/update-movie/${movie_item.id}" method="get">
				<input type="submit" value="change"  />
				</form>
				</td>
			</tr>
		</c:forEach>
	</table>
</body>
</html>