# Alkemy - Disney Challenge Java
Proyecto Disney Challenge desarrollado con SpringBoot y arquitectura multicapa.

## Puntos a tener en cuenta

En la carpeta **SQL** se encuentra el archivo **RegenerateDatabaseAndInsertData.sql** para regenerar la base de datos con información de ejemplo.

Como facilidad a la hora de testear la API, se deja un archivo backup de Postman para su importación. El mismo se encuentra dentro de **/Postman/apis.json**

En el archivo **application.properties** hay que configurar las siguientes propiedades:

* Para el funcionamiento de SendGrid.
    * sendgrid.api-key	 
    * sendgrid.from-mail
* Para que el sistema se pueda conectar con la base de datos.
    * spring.datasource.username
    * spring.datasource.password

## Acciones que se pueden realizar

Listado, creación, edición y eliminación de:

   * Películas/Series
   * Personajes 
   * Géneros

También permite la generación de usuarios para posteriormente obtener un token y operar la API.

## Documentación de la API

URL local: 
* <http://localhost:8080/swagger-ui/index.html>

URL Cloud: 
* <https://app.swaggerhub.com/apis-docs/AGATHASTAN/AlkemyDisneyAPIChallenge/1.0.0>


## Tecnologías utilizadas para la realización del proyecto
* Jira
* JUnit
* Maven
* Java 11
* Mockito
* Postman
* SendGrid
* Swagger 3
* Spring Data
* SwaggerHub
* MySQL 8.0.27
* SpringBoot 2.7.4 
* JSON Web Token
* Eclipse IDE. 4.21.0
* Spring Security 5.7.3








## Patrones de diseño utilizados

* Repository
* Value Object
* Data Access Object
* Data Transfer Object
* Dependency injection
