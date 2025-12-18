# BaZaaR Marketplace Backend API
![Hexagonal Architecture](https://img.shields.io/badge/Architecture-Hexagonal-blueviolet)
![Java 21](https://img.shields.io/badge/Java-21-orange?logo=openjdk&logoColor=white)
![Spring Boot 3.4.5](https://img.shields.io/badge/Spring_Boot-3.4.5-brightgreen?logo=springboot&logoColor=white)
![Flyway](https://img.shields.io/badge/Flyway-Database_Migrations-CC0200?logo=flyway&logoColor=white)
![Swagger](https://img.shields.io/badge/Swagger-API_Docs-85EA2D?logo=swagger&logoColor=black)
![JWT Security](https://img.shields.io/badge/Security-JWT-orange?logo=jsonwebtokens&logoColor=white)
![MySQL 8.0](https://img.shields.io/badge/MySQL-8.0-blue?logo=mysql&logoColor=white)
![Stripe](https://img.shields.io/badge/Stripe-Integration-626CD9?logo=stripe&logoColor=white)
![Maven](https://img.shields.io/badge/Maven-Build-CA4245?logo=apachemaven&logoColor=white)
![MIT License](https://img.shields.io/badge/License-MIT-yellow?logo=opensourceinitiative&logoColor=white)

## Índice

* [Visión General](#visión-general)
* [Stack Tecnológico](#stack-tecnológico)
* [Arquitectura (Hexagonal)](#arquitectura-hexagonal)
* [Flujo de Información](#flujo-de-información)
* [Seguridad](#seguridad)
* [Base de Datos](#base-de-datos)
* [Configuración por Entornos](#configuración-por-entornos)
* [Testing](#testing)
* [Cómo Ejecutar](#cómo-ejecutar)
* [Documentación OpenAPI / Swagger](#documentación-openapi--swagger)
* [Estado del Proyecto](#estado-del-proyecto)

---

## Visión General

BaZaaR Backend es una API REST diseñada para soportar un marketplace actuando como núcleo de negocio y punto central de integración para clientes externos (actualmente, un cliente Android nativo).

<p align="center">
<img src="/screenshots/CS.png" alt="Vista general Cliente-Servidor" width="700"/>
</p>

El sistema está orientado a entornos reales de producción, priorizando:

* Separación estricta de responsabilidades
* Desacoplamiento entre dominio y tecnologías
* Mantenibilidad y testabilidad
* Seguridad en el acceso y procesamiento de datos

La API gestiona autenticación de usuarios, catálogo de productos, carrito de compra, pedidos y pagos, ofreciendo una base sólida y extensible para un ecosistema de comercio electrónico.

---

## Stack Tecnológico

| Categoría         | Tecnología                      | Descripción                                                           |
| ----------------- | ------------------------------- | --------------------------------------------------------------------- |
| **Lenguaje**      | **Java 21**                     | Versión LTS moderna utilizada para el desarrollo del backend.         |
| **Framework**     | **Spring Boot 3.4.5**           | Framework principal para aplicaciones backend listas para producción. |
| **Base de Datos** | **MySQL / MariaDB**             | Base de datos relacional utilizada para persistencia.                 |
| **Persistencia**  | **Spring Data JPA / Hibernate** | Implementación del acceso a datos mediante ORM.                       |
| **Seguridad**     | **Spring Security + JWT**       | Autenticación y autorización basada en tokens.                        |
| **Mapeo**         | **MapStruct**                   | Generación de mappers eficientes entre capas.                         |
| **Pagos**         | **Stripe**                      | Integración de pagos segura del lado servidor.                        |
| **Testing**       | **JUnit 5, Mockito**            | Pruebas unitarias y de integración.                                   |
| **Utilidades**    | **Lombok**                      | Reducción de código repetitivo (boilerplate).                         |

---

## Arquitectura (Hexagonal)

El backend está diseñado siguiendo Arquitectura Hexagonal (Puertos y Adaptadores), con el objetivo de aislar completamente la lógica de negocio de los detalles de infraestructura.

Esta aproximación permite:

* Cambiar tecnologías externas sin afectar al dominio
* Facilitar pruebas unitarias sin dependencias técnicas
* Mantener un bajo acoplamiento y alta cohesión

### Capas del Sistema

#### 1. Dominio

* Núcleo del sistema
* Entidades y lógica de negocio
* Totalmente independiente de frameworks

#### 2. Aplicación

* Implementación de Casos de Uso
* Orquestación de la lógica de negocio
* Definición de Puertos Primarios (entrada) y Puertos Secundarios (salida)

#### 3. Infraestructura

* Adaptadores concretos de tecnología
* Controladores REST (adaptadores primarios)
* Repositorios JPA y clientes externos (adaptadores secundarios)

<p align="center">
<img src="/screenshots/ArqHex.png" alt="Arquitectura Hexagonal" width="700"/>
</p>

---

## Flujo de Información

Flujo típico ante una petición HTTP:

1. El Controlador REST recibe la solicitud
2. Traduce el request a DTOs / modelos de dominio
3. Invoca un Puerto Primario
4. El Caso de Uso interactúa con Puertos Secundarios
5. Los Adaptadores implementan la lógica de persistencia o integración
6. El resultado regresa al controlador, que construye la respuesta HTTP

Este flujo garantiza que el dominio nunca dependa de frameworks ni tecnologías externas.

---

## Seguridad

La seguridad está implementada mediante Spring Security con autenticación basada en JSON Web Tokens (JWT).

Características principales:

* Autenticación stateless
* Control de acceso basado en roles (RBAC)
* Separación clara entre endpoints públicos y protegidos
* Validación del token en cada request mediante filtros de seguridad

El diseño permite evolucionar fácilmente hacia mecanismos más avanzados (refresh tokens, revocación de sesiones, OAuth2).

---

## Base de Datos

El modelo relacional se diseñó a partir del dominio de negocio, priorizando:

* Integridad referencial
* Eliminación de redundancias
* Normalización (BCNF cuando aplica)

La persistencia se realiza mediante JPA/Hibernate, manteniendo las entidades de dominio desacopladas de los detalles de almacenamiento.

<p align="center">
<img src="/screenshots/DB.png" alt="Diagrama ER" width="700"/>
</p>

### Migraciones

El esquema de base de datos está preparado para evolucionar mediante migraciones versionadas con Flyway, siendo la única fuente de verdad del esquema y los datos estructurales y asegurando:

* Reproducibilidad del entorno
* Control de cambios
* Seguridad en despliegues a producción

Flyway se introdujo sobre un esquema existente utilizando baseline en entorno de desarrollo.

---

## Configuración por Entornos

La aplicación utiliza **Spring Profiles** para separar configuraciones según el entorno:

* `dev`: desarrollo local
* `test`: ejecución de pruebas automatizadas
* `prod`: entorno de producción

Las credenciales y valores sensibles se proporcionan mediante **variables de entorno**, evitando configuraciones hardcodeadas en el repositorio y asegurando un despliegue seguro en producción.

---

## Testing

La calidad del sistema se garantiza mediante:

* **Pruebas Unitarias**: validación de reglas de negocio en dominio y aplicación sin depender de Spring ni del contexto de aplicación
* **Pruebas de Integración**: verificación de flujos críticos del sistema desde controladores hasta persistencia

Las pruebas están diseñadas para ejecutarse de forma aislada y reproducible.

---

## Cómo Ejecutar

### Requisitos Previos

* Java 21
* Maven 3.x (o Maven Wrapper)
* MySQL o MariaDB
* Cuenta de Stripe (modo test). Los pagos se procesan mediante Stripe desde el backend, garantizando que las claves privadas nunca estén expuestas al cliente

### Variables de Entorno Necesarias

Antes de ejecutar la aplicación, es necesario definir las siguientes variables de entorno.
En un entorno de desarrollo, la aplicación proporciona valores por defecto, pero se recomienda definirlas explícitamente.

#### Linux / macOS

```bash
export DB_URL=jdbc:mysql://localhost:3306/db_backend_bazaar
export DB_USER=bazaar
export DB_PASSWORD=password
export STRIPE_API_KEY=sk_test_TU_CLAVE
export JWT_SECRET=tu-clave-secreta-super-segura-minimo-32-caracteres
```

#### Windows (PowerShell)

```powershell
$env:DB_URL="jdbc:mysql://localhost:3306/db_backend_bazaar"
$env:DB_USER="bazaar"
$env:DB_PASSWORD="password"
$env:STRIPE_API_KEY="sk_test_TU_CLAVE"
$env:JWT_SECRET="tu-clave-secreta-super-segura-minimo-32-caracteres"
```

> ⚠️ Nota: estas variables solo estarán disponibles durante la sesión de la terminal.
> Para configurarlas de forma permanente en Windows, pueden definirse desde "Variables de entorno del sistema".

En un entorno de producción, estas variables deben definirse obligatoriamente mediante el sistema de configuración del entorno y nunca versionarse en el repositorio.

### Ejecución Local

1. Clonar el repositorio:

```bash
git clone https://github.com/Jherna77/marketplace-backend-spring-hexagonal.git
cd marketplace-backend-spring-hexagonal
```

2. Crear la base de datos:

```sql
CREATE DATABASE db_backend_bazaar;
CREATE USER 'bazaar'@'%' IDENTIFIED BY 'password';
GRANT ALL PRIVILEGES ON db_backend_bazaar.* TO 'bazaar'@'%';
FLUSH PRIVILEGES;
```

3. Compilar y ejecutar:

```bash
./mvnw clean install
./mvnw spring-boot:run
```

La API quedará disponible en `http://localhost:8080`.

---

## Documentación OpenAPI / Swagger

La API cuenta con documentación generada con OpenAPI/Swagger accesible desde `http://localhost:8080/swagger-ui.html`, y que facilita su exploración y prueba, además de servir como documentación oficial para integraciones externas.

Swagger actúa como contrato vivo de la API, alineado con los casos de uso del dominio y las restricciones de seguridad JWT.

<p align="center">
<img src="/screenshots/swagger-main.png" alt="Swagger UI principal" width="700"/>
</p>

---

## Estado del Proyecto

Este backend está diseñado para ser un proyecto de portafolio con varias características implementadas que pretenden mostrar buenas prácticas de desarrollo y preparación para producción:

**Próximas mejoras planificadas:**

* Manejo profesional de errores (RFC 7807)
* Notificación de eventos de dominio (creación de un pedido, pago realizado correctamente, etc)
* Observabilidad: health checks, métricas y logging estructurado con correlation ID
* Dockerización y Docker Compose
