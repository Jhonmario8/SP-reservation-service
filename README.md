# SP-reservation-service

Microservicio principal del proyecto **SportHub**, encargado de la administración de canchas deportivas y la gestión de reservas.

Este servicio implementa la lógica de negocio relacionada con la creación, actualización y consulta de canchas, así como el ciclo de vida de las reservas realizadas por los usuarios.

---

# 📖 Tabla de Contenidos

- Descripción
- Arquitectura
- Tecnologías
- Requisitos Previos
- Configuración
- Variables de Entorno
- Instalación
- Ejecución
- Endpoints
- Flujo de Reservas
- Base de Datos
- Estructura del Proyecto
- Manejo de Errores
- Buenas Prácticas
- Autor

---

# 📝 Descripción

El microservicio **SP-reservation-service** es responsable de administrar toda la lógica de negocio relacionada con las reservas del sistema SportHub.

Entre sus principales responsabilidades se encuentran:

- Administración de canchas deportivas.
- Registro de nuevas reservas.
- Actualización del estado de una reserva.
- Cancelación de reservas.
- Consulta paginada de reservas por usuario.
- Consulta paginada de canchas disponibles.
- Filtrado de canchas por tipo.

Este servicio se comunica con los demás microservicios del sistema para validar usuarios autenticados y enviar notificaciones cuando es necesario.

Toda la lógica fue desarrollada siguiendo el patrón de **Arquitectura Hexagonal (Ports & Adapters)**.

---

# 🏗 Arquitectura

El proyecto implementa Arquitectura Hexagonal.

La estructura principal del proyecto está organizada de la siguiente manera:

```text
application
│
├── constants
├── dto
├── handler
├── mapper
└── usecase

domain
│
├── api
├── model
├── spi
└── enums

infrastructure
│
├── configuration
├── exception
├── input
│   └── controller
└── output
    ├── feign
    ├── jpa
    └── security
```

Esta arquitectura permite:

- Separar la lógica del negocio de la infraestructura.
- Facilitar el mantenimiento.
- Favorecer la escalabilidad.
- Mejorar la capacidad de realizar pruebas.

---

# 🚀 Tecnologías

- Java 17
- Spring Boot
- Spring Security
- Spring Data JPA
- OpenFeign
- JWT
- MapStruct
- Lombok
- MySQL
- Gradle

---

# 📋 Requisitos Previos

Antes de ejecutar el proyecto es necesario tener instalado:

- Java JDK 17
- Gradle
- MySQL
- Git

Además deben estar disponibles los microservicios con los que este servicio interactúa.

---

# ⚙ Configuración

Clonar el repositorio:

```bash
git clone https://github.com/Jhonmario8/SP-reservation-service.git

cd SP-reservation-service
```

Crear la base de datos correspondiente en MySQL.

---

# 🔐 Variables de Entorno

El proyecto utiliza variables de entorno para la configuración de la base de datos y autenticación.

Ejemplo:

```properties
MYSQL_USER=root
MYSQL_PASSWORD=123456
PRAGMA_JWT_KEY=MiClaveJWT
AUTH_SERVICE_URL=http://localhost:8080
NOTIFICATION_SERVICE_URL=http://localhost:8082
```

> Las variables pueden variar según el entorno de despliegue.

---

# ▶️ Ejecución

Ejecutar el proyecto mediante Gradle:

```bash
./gradlew bootRun
```

o

```bash
gradlew bootRun
```

También puede ejecutarse directamente desde IntelliJ IDEA ejecutando:

```
SpReservationServiceApplication
```

El servicio estará disponible en:

```
http://localhost:8081
```

---

# 🌐 Endpoints

## Gestión de Canchas

### Crear cancha

```
POST /courts
```

Registra una nueva cancha deportiva.

Respuesta:

```
201 Created
```

---

### Actualizar cancha

```
PUT /courts/{courtId}
```

Actualiza la información de una cancha.

Respuesta:

```
200 OK
```

---

### Deshabilitar cancha

```
PATCH /courts/{courtId}
```

Marca una cancha como inactiva.

Respuesta:

```
204 No Content
```

---

### Consultar canchas

```
GET /courts
```

Permite obtener un listado paginado de canchas.

Parámetros:

| Parámetro | Descripción |
|-----------|-------------|
| courtTypeId | Filtrar por tipo de cancha |
| page | Número de página |
| size | Cantidad de registros |

Respuesta:

```
200 OK
```

---

# Gestión de Reservas

### Crear reserva

```
POST /reservations
```

Registra una nueva reserva para una cancha.

Respuesta:

```
201 Created
```

---

### Actualizar estado

```
PATCH /reservations/{id}
```

Actualiza el estado de una reserva.

Parámetro:

```
status
```

Respuesta:

```
200 OK
```

---

### Cancelar reserva

```
DELETE /reservations/{id}
```

Cancela una reserva existente.

Respuesta:

```
204 No Content
```

---

### Consultar reservas

```
GET /reservations/{userId}
```

Obtiene las reservas de un usuario de forma paginada.

Parámetros:

| Parámetro | Descripción |
|-----------|-------------|
| status | Filtrar por estado |
| page | Página |
| size | Cantidad de registros |

Respuesta:

```
200 OK
```

---

# 📅 Flujo de Reservas

El flujo básico implementado por el sistema es:

1. Registrar una cancha.
2. Consultar las canchas disponibles.
3. Crear una reserva.
4. Actualizar el estado de la reserva cuando corresponda.
5. Cancelar la reserva si es necesario.
6. Consultar el historial de reservas del usuario.

---

# 🔒 Seguridad

Este microservicio utiliza autenticación basada en JWT.

Las solicitudes protegidas deben enviar el token mediante el encabezado:

```
Authorization: Bearer <token>
```

La validación del token permite controlar el acceso a los recursos según el usuario autenticado.

---

# 🗄 Base de Datos

Motor utilizado:

- MySQL

Persistencia implementada mediante:

- Spring Data JPA
- Hibernate

---

# 📂 Estructura del Proyecto

```text
src
│
├── main
│   ├── java
│   │
│   └── com.sp.reservationservice
│       ├── application
│       ├── domain
│       └── infrastructure
│
└── resources
    └── application.yml
```

---

# ❌ Manejo de Errores

El proyecto implementa manejo centralizado de excepciones para responder con códigos HTTP apropiados.

Algunos códigos utilizados:

| Código | Descripción |
|---------|-------------|
| 400 | Bad Request |
| 401 | Unauthorized |
| 403 | Forbidden |
| 404 | Not Found |
| 409 | Conflict |
| 500 | Internal Server Error |

---

# 🧪 Pruebas

Para ejecutar las pruebas:

```bash
./gradlew test
```

---

# ✅ Buenas Prácticas Implementadas

- Arquitectura Hexagonal.
- Uso de DTOs.
- Separación entre dominio e infraestructura.
- Validación de datos.
- Persistencia mediante puertos y adaptadores.
- Comunicación entre microservicios utilizando OpenFeign.
- Mapeo de objetos mediante MapStruct.
- Autenticación con JWT.
- Paginación para consultas.

---

# 👨‍💻 Autor

**Jhon Mario**

GitHub:

https://github.com/Jhonmario8

Repositorio:

https://github.com/Jhonmario8/SP-reservation-service