Práctica 6: Serlets y JSPs
===
Diseño de Aplicaciones Telemáticas

## Autores

- Juan Manuel Espinosa Moral
- José Manuel García Núñez

## Descripción de la práctica

<!--
TODO: Añadir descripción
-->

## Despliegue

### Acceso

La clave de administrador está fijada a _manager_.

### Base de datos

El sistema requiere de una base de datos SQL en la que se guarda la información.
El modelo de datos requerido puede crearse con las líneas indicadas en el fichero [`files/TABLE_CREATION.txt`](/files/TABLE_CREATION.txt).

La base de datos debe configurarse con los siguientes parámetros:
con las siguientes características:
- Nombre: `sporting_manager`
- URL: `jdbc:mysql://localhost/sporting_manager`
- Usuario: `root`
- Clave de acceso: `datuc3m`
Si encuentra problemas para configurar las credenciales, consulte las siguientes páginas de la documentación de *MySQL*:
- https://dev.mysql.com/doc/refman/8.4/en/resetting-permissions.html
- https://dev.mysql.com/doc/refman/8.4/en/alter-user.html
- https://dev.mysql.com/doc/refman/8.4/en/set-password.html