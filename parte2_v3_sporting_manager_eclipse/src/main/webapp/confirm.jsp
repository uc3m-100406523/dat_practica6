
<!DOCTYPE html>
<html>
<head><title>Confirmación</title></head>
<body>
<h2>¿Son estos datos correctos?</h2>
<ul>
  <li>Nombre: <%= session.getAttribute("cliente_name") %></li>
  <li>Apellido: <%= session.getAttribute("cliente_surname") %></li>
  <li>Dirección: <%= session.getAttribute("cliente_address") %></li>
  <li>Teléfono: <%= session.getAttribute("cliente_phone") %></li>
  <li>Nombre de usuario: <%= session.getAttribute("cliente_login") %></li>
  <li>Contraseña: <%= session.getAttribute("cliente_pwd") %></li>
</ul>
<form action="guardarCliente" method="post">
  <button type="submit" value="Sí, son correctos">
</form>
<form action="reregister.jsp" method="post">
  <button type="submit" value="No, deseo cambiarlos">
</form>
</body>
</html>
