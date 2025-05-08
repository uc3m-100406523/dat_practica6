<!DOCTYPE html>
<html>
<head><title>Registro de Usuario</title></head>
<body>
<h2>Formulario de Registro</h2>
<form action="register" method="post">
  <label for="cliente_name">Nombre:</label>
  <input type="text" id="cliente_name" name="cliente_name" value="<%= StringEscapeUtils.escapeHtml4((String) session.getAttribute("cliente_name")) %>"><br>

  <label for="cliente_surname">Apellido:</label>
  <input type="text" id="cliente_surname" name="cliente_surname" value="<%= StringEscapeUtils.escapeHtml4((String) session.getAttribute("cliente_surname")) %>"><br>

  <label for="cliente_address">Dirección:</label>
  <input type="text" id="cliente_address" name="cliente_address" value="<%= StringEscapeUtils.escapeHtml4((String) session.getAttribute("cliente_address")) %>"><br>

  <label for="cliente_phone">Teléfono:</label>
  <input type="text" id="cliente_phone" name="cliente_phone" value="<%= StringEscapeUtils.escapeHtml4((String) session.getAttribute("cliente_phone")) %>"><br>

  <label for="cliente_login">Nombre de usuario:</label>
  <input type="text" id="cliente_login" name="cliente_login" value="<%= StringEscapeUtils.escapeHtml4((String) session.getAttribute("cliente_login")) %>"><br>

  <label for="cliente_pwd">Contraseña:</label>
  <input type="password" id="cliente_pwd" name="cliente_pwd" value="<%= StringEscapeUtils.escapeHtml4((String) session.getAttribute("cliente_pwd")) %>"><br>

  <input type="submit" value="Enviar">
</form>
</body>
</html>