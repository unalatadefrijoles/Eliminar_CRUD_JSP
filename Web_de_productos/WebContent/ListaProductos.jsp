<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
    
    
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
<style>
.cabecera{
	font-size:1.2;
	font-weight: bold;
	color: #ffffff;
	background-color: #08088A;
}

.filas{
	text-align: center;
	background-color: #5882FA;
	
}

table{
	float:left;
}

#contenedorBoton{
	margin-left:1000px;

}


</style>



</head>
<body>

	<table>
		<tr>
			<td class="cabecera">Código Articulo</td>
			<td class="cabecera">Seccion</td>
			<td class="cabecera">Nombre Articulo</td>
			<td class="cabecera">Fecha</td>
			<td class="cabecera">Precio</td>
			<td class="cabecera">Importado</td>
			<td class="cabecera">Pais de Origen</td>
			<td class="cabecera" align="center">Actualizar</td>
		</tr>
		
		<c:forEach var="tempProd" items="${LISTAPRODUCTOS}">
			
		<!-- Link para cada producto con su campo clave -->
		<c:url var="linkTemp" value="ControladorProductos">
			<c:param name="instruccion" value="cargar"></c:param>
			<c:param name="CArticulo" value="${tempProd.cArt}"></c:param>
		</c:url>
		
		<!-- Link para eliminar con su clave -->
		<c:url var="linkTempEliminar" value="ControladorProductos">
			<c:param name="instruccion" value="eliminar"></c:param>
			<c:param name="CArticulo" value="${tempProd.cArt}"></c:param>
		</c:url>
			
		
		<tr>
			<td class="filas">${tempProd.cArt}</td>
			<td class="filas">${tempProd.seccion}</td>
			<td class="filas">${tempProd.nArt}</td>
			<td class="filas">${tempProd.fecha}</td>
			<td class="filas">${tempProd.precio}</td>
			<td class="filas">${tempProd.importado}</td>
			<td class="filas">${tempProd.pOrig}</td>
			<td class="filas"><a href="${linkTemp}">Editar</a> &nbsp;&nbsp;<a href="${linkTempEliminar}">Eliminar</a></td>
		</tr>
		
		</c:forEach>
	
	
		
	</table>
	<div id="contenedorBoton">
		
		<input type="button" value="Insertar Registro" onclick="window.location.href='inserta_producto.jsp'"/>
		
	</div>
	

</body>
</html>