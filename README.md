# Gym Control API

Sistema de gestión para gimnasio desarrollado con Spring Boot y MySQL.
El proyecto permite administrar socios, membresías, entrenadores, clases, reservas, pagos, rutinas y ejercicios mediante una API RESTful.

# Descripción del Proyecto

Gym Control es una aplicación backend orientada a la administración de un gimnasio.
El sistema fue desarrollado utilizando arquitectura por capas y persistencia de datos con Spring Data JPA e Hibernate.
El objetivo principal es centralizar la gestión de:

- Socios
- Membresías
- Entrenadores
- Clases
- Reservas
- Pagos
- Rutinas
- Ejercicios
---
# Tecnologías Utilizadas

- Java 21
- Spring Boot
- Spring Data JPA
- Hibernate
- MySQL
- Maven
- Lombok
- Postman
- GitHub

---

# Arquitectura del Proyecto

El sistema utiliza una arquitectura monolítica modular basada en capas.

```text
Controller
↓
Service
↓
Repository
↓
Base de Datos
```

Cada módulo contiene:

```text
- controller
- dto
- model
- repository
- service
```

---

# Persistencia de Datos

El proyecto utiliza Spring Data JPA junto con Hibernate para mapear entidades Java hacia tablas en MySQL.

Se implementan relaciones mediante anotaciones como:

- `@Entity`
- `@Table`
- `@Id`
- `@GeneratedValue`
- `@OneToMany`
- `@ManyToOne`
- `@JoinColumn`

---

# Uso de DTO

El sistema utiliza DTOs (Data Transfer Object) para controlar la información enviada al cliente y evitar exponer directamente las entidades de la base de datos.

Esto ayuda a:

- mejorar seguridad
- controlar respuestas JSON
- evitar referencias circulares
- separar lógica de persistencia y presentación

---

# Validaciones Implementadas

Se utilizan validaciones mediante Jakarta Validation para asegurar integridad de datos antes de guardar registros.

Validaciones utilizadas:

- `@NotBlank`
- `@NotNull`
- `@Email`
- `@Size`
- `@Pattern`

---

# Funcionalidades Principales

## Socios

- Crear socios
- Listar socios
- Buscar socio por ID
- Actualizar socio
- Eliminar socio

## Membresías

- Crear membresías
- Asociar membresías a socios
- Cancelar membresías
- Listar membresías

## Entrenadores

- Registrar entrenadores
- Actualizar entrenadores
- Eliminar entrenadores

## Clases

- Crear clases
- Asociar clases a entrenadores
- Gestionar cupos

## Reservas

- Reservar clases
- Cancelar reservas
- Validar disponibilidad

## Pagos

- Registrar pagos
- Asociar método de pago
- Control de estados

## Rutinas

- Crear rutinas
- Asociar rutinas a socios
- Asociar rutinas a entrenadores

## Ejercicios

- Registrar ejercicios
- Asociar ejercicios a rutinas

---

# Relaciones Principales

- Un socio puede tener muchas membresías
- Un socio puede realizar muchas reservas
- Un entrenador puede impartir muchas clases
- Una rutina puede contener muchos ejercicios
- Un ejercicio puede pertenecer a muchas rutinas

---

# Base de Datos

Motor utilizado:

- MySQL

# Endpoints Principales

## Socios

```http
GET    /api/v1/socios
GET    /api/v1/socios/{id}
POST   /api/v1/socios
PUT    /api/v1/socios/{id}
DELETE /api/v1/socios/{id}
```

---

## Membresías

```http
GET    /api/v1/membresias
GET    /api/v1/membresias/{id}
POST   /api/v1/membresias
PUT    /api/v1/membresias/{id}
DELETE /api/v1/membresias/{id}
```

---

## Entrenadores

```http
GET    /api/v1/entrenadores
POST   /api/v1/entrenadores
PUT    /api/v1/entrenadores/{id}
DELETE /api/v1/entrenadores/{id}
```

---

## Clases

```http
GET    /api/v1/clases
POST   /api/v1/clases
PUT    /api/v1/clases/{id}
DELETE /api/v1/clases/{id}
```

---

# Ejemplo JSON - Crear Socio

```json
{
  "rut": "12345678-9",
  "nombre": "Simon",
  "apellido": "Perez",
  "correo": "simon@gmail.com",
  "telefono": "912345678",
  "direccion": "Maipu",
  "fechaNacimiento": "2000-05-10",
  "estado": true
}
```

---

# Ejemplo JSON - Crear Membresía

```json
{
  "socio": {
    "id": 1
  },
  "tipo": "Premium",
  "fechaInicio": "2026-05-07",
  "fechaFin": "2026-06-07",
  "estado": "ACTIVA",
  "observaciones": "Membresía mensual premium"
}
```
# Reglas de Negocio Implementadas

- Un socio puede tener múltiples membresías
- No se puede registrar una membresía sin socio
- Las reservas dependen de disponibilidad de cupos
- Los pagos deben tener un método de pago válido
- Las rutinas deben estar asociadas a un socio
---
# Integrantes

- Leonardo Fuentes 
- Matias Salvador

---
