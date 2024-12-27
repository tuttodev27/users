caracteristicas.
- Registro de usuarios con validaciones de correo y contraseña.
- Generación de tokens JWT para autenticación.
- Persistencia en base de datos en memoria (H2).
- Arquitectura orientada al dominio (Domain-Driven Design, DDD).
- Validación de datos de entrada con Spring Validation.
- Stateless API para una mejor escalabilidad.

---

## Tecnologías Utilizadas

- **Java 21**
- **Spring Boot 3.x**
- **H2 Database**
- **JWT (JSON Web Token)**
- **Lombok**
- **Spring Data JPA**

---

## Requisitos Previos

Asegúrate de tener instalados:

1. **Java 21** o superior
2. **Maven** (para construir y gestionar dependencias)

---

## Configuración

### 1. Clonar el Repositorio

git clone https://github.com/tu-usuario/user-microservice-api.git
cd user-microservice-api

# 2. Configuración de la base de datos
spring.datasource.url=jdbc:h2:mem:testdb
spring.datasource.driverClassName=org.h2.Driver
spring.datasource.username=sa
spring.datasource.password=password
spring.h2.console.enabled=true

3. Configuración de JWT
jwt.secret=secretkey
jwt.expiration=3600000 # 1 hora en milisegundos

4 Construir la aplicacion

mvn clean install

5 Ejecutar la Aplicacion

mvn spring-boot:run
