0. Disponer de Docker y docker-compose en el equipo
1. Para correr la aplicación correr en consola "docker-compose up -d" en la carpeta raiz.
2. Se ejecuta contenedor para DDBB Mysql y otro para el App Java Spring Boot.
3. Crear una DDBB "dbbank" y migrar datos de casos de uso en el script BaseDatos.sql, el app-container ya debería permitir ejecutar los test's y el app en general.

____________________________________________________________________________________________

CLIENTE
POST 
http://localhost:8080/clientes
{
    "nombre":"Juan Osorio",
    "direccion":"13 junio y Equinoccial",
    "telefono":"098874587",
    "clienteId":4,
    "contrasena":"1245",
    "estado":1
}
________________________________________________________________
CUENTA
POST
*clienteId (no confundir con id de modelo Cliente), valor único al igual que numeroCuenta.
http://localhost:8080/cuentas
{
  "numeroCuenta": "585545",
  "tipoCuenta": "Corriente",
  "saldoInicial": 1000.00,
  "estado": 1,
  "cliente": {
    "id": 2
}
}
________________________________________________________________
MOVIMIENTO
POST 
http://localhost:8080/movimientos
*tipoMovimiento = "Retiro" || "Depósito"
{
  "tipoMovimiento": "Retiro",
  "valor": 540.00,
  "numeroCuenta": "496825"
}
_________________________________________________________________
REPORTE 
GET
http://localhost:8080/reportes?fechaInicio=2022-01-01&fechaFin=2023-09-01&clienteId=3

_________________________________________________________________

DB & Application Properties

spring.jpa.hibernate.ddl-auto=update

spring.datasource.url=jdbc:mysql://localhost:3306/dbbank?allowPublicKeyRetrieval=true
spring.datasource.username=amila_one
spring.datasource.password=Amila_pw

spring.sql.init.mode=always
spring.datasource.initialization-mode=always
____________________________________________________________________

DOCKER

#
# Build stage
#
FROM maven:3.8.3-openjdk-17 AS build
COPY src /home/app/src
COPY pom.xml /home/app
RUN mvn -f /home/app/pom.xml clean package
EXPOSE 8080
ENTRYPOINT ["java","-jar","/home/app/target/spring_rest_docker.jar"]


