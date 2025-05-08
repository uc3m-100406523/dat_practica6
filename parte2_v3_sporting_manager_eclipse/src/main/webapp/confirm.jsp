
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
</ul>
<form action="GuardarCliente" method="post">
  <button type="submit" id="boton_afirmativo" name="is_confirmed" value="True">Afirmativo</button>
</form>
<form action="GuardarCliente" method="post">
  <button type="submit" id="boton_negativo" name="is_confirmed" value="False">Negativo</button>
</form>
</body>
</html>