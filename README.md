# 🏨 Sistema de Gestión de Hotel - Arquitectura MVC

Sistema completo de gestión hotelera desarrollado con **Spring Boot** y arquitectura **MVC (Modelo-Vista-Controlador)**.

## 📋 Problemática

Las gestiones hoteleras manuales tienen dificultades para:
- Controlar reservas y disponibilidad de habitaciones
- Gestionar información de huéspedes de manera eficiente
- Calcular automáticamente costos y estadísticas
- Evitar conflictos en reservas de habitaciones

## 🎯 Objetivo

Automatizar la gestión de un hotel mediante un sistema web que permita:
- Administrar habitaciones (CRUD completo)
- Registrar y gestionar huéspedes
- Crear y controlar reservas con validación de disponibilidad
- Visualizar estadísticas en tiempo real

## 🏗️ Arquitectura del Proyecto

### Estructura de Carpetas

```
gestion-hotel/
├── src/
│   └── main/
│       ├── java/com/hotel/gestionhotel/
│       │   ├── model/              # Modelos (Entidades JPA)
│       │   │   ├── Habitacion.java
│       │   │   ├── Huesped.java
│       │   │   └── Reserva.java
│       │   ├── repository/         # Repositorios (Acceso a datos)
│       │   │   ├── HabitacionRepository.java
│       │   │   ├── HuespedRepository.java
│       │   │   └── ReservaRepository.java
│       │   ├── service/            # Servicios (Lógica de negocio)
│       │   │   ├── HabitacionService.java
│       │   │   ├── HuespedService.java
│       │   │   └── ReservaService.java
│       │   ├── controller/         # Controladores (Lógica de control)
│       │   │   ├── HomeController.java
│       │   │   ├── HabitacionController.java
│       │   │   ├── HuespedController.java
│       │   │   └── ReservaController.java
│       │   ├── config/             # Configuración
│       │   │   └── DataInitializer.java
│       │   └── GestionHotelApplication.java
│       └── resources/
│           ├── static/css/         # Archivos CSS
│           │   └── estilos.css
│           ├── templates/          # Vistas (Thymeleaf)
│           │   ├── index.html
│           │   ├── habitaciones/
│           │   │   ├── lista.html
│           │   │   └── formulario.html
│           │   ├── huespedes/
│           │   │   ├── lista.html
│           │   │   └── formulario.html
│           │   └── reservas/
│           │       ├── lista.html
│           │       └── formulario.html
│           └── application.properties
└── pom.xml
```

### 🔧 Componentes MVC

#### **Modelo (Model)**
- `Habitacion`: Representa las habitaciones del hotel
- `Huesped`: Información de los clientes
- `Reserva`: Gestión de reservas con relaciones ManyToOne

#### **Vista (View)**
- Templates Thymeleaf con diseño responsivo
- Dashboard con estadísticas en tiempo real
- Formularios con validación

#### **Controlador (Controller)**
- `HomeController`: Dashboard principal
- `HabitacionController`: CRUD de habitaciones
- `HuespedController`: CRUD de huéspedes
- `ReservaController`: Gestión de reservas con validación de disponibilidad

## 🚀 Requisitos

- Java 17 o superior
- Maven 3.6+
- Navegador web moderno

## ⚙️ Instalación y Ejecución

### 1. Clonar o descargar el proyecto

```bash
cd taller1
```

### 2. Compilar el proyecto

```bash
mvn clean install
```

### 3. Ejecutar la aplicación

```bash
mvn spring-boot:run
```

O ejecutar el JAR generado:

```bash
java -jar target/gestion-hotel-1.0.0.jar
```

### 4. Acceder a la aplicación

- **Aplicación web**: http://localhost:8080
- **Consola H2** (base de datos): http://localhost:8080/h2-console
  - JDBC URL: `jdbc:h2:mem:hoteldb`
  - Usuario: `sa`
  - Contraseña: (dejar vacío)

## 📊 Funcionalidades

### Dashboard Principal
- Total de habitaciones y disponibles
- Número de huéspedes registrados
- Total de reservas
- Ingresos acumulados

### Gestión de Habitaciones
- ✅ Listar todas las habitaciones
- ➕ Crear nueva habitación
- ✏️ Editar habitación existente
- 🗑️ Eliminar habitación
- 🔄 Cambiar disponibilidad

### Gestión de Huéspedes
- ✅ Listar huéspedes
- ➕ Registrar nuevo huésped
- ✏️ Editar información
- 🗑️ Eliminar huésped
- 🔍 Buscar por nombre

### Gestión de Reservas
- ✅ Listar reservas con estados
- ➕ Crear nueva reserva
- ✏️ Editar reserva
- ✓ Confirmar reserva
- ✗ Cancelar reserva
- ✔ Completar reserva
- 🔍 Verificación automática de disponibilidad
- 💰 Cálculo automático de montos

## 🎨 Características Técnicas

- **Base de datos**: H2 (en memoria) - Configurable a MySQL
- **ORM**: Spring Data JPA / Hibernate
- **Motor de plantillas**: Thymeleaf
- **Estilos**: CSS personalizado con gradientes y diseño moderno
- **Validaciones**: Frontend y Backend
- **Datos de prueba**: Inicialización automática con datos de ejemplo

## 📝 Datos de Ejemplo

Al iniciar la aplicación, se cargan automáticamente:
- 5 habitaciones (Simple, Doble, Suite, Presidencial)
- 3 huéspedes registrados
- 2 reservas de ejemplo

## 🔄 Migrar a MySQL

Para usar MySQL en lugar de H2:

1. Descomentar la dependencia de MySQL en `pom.xml`
2. Descomentar la configuración de MySQL en `application.properties`
3. Crear la base de datos:
   ```sql
   CREATE DATABASE hotel_db;
   ```
4. Actualizar credenciales en `application.properties`

## 📱 Capturas de Pantalla

El sistema incluye:
- **Dashboard** con tarjetas de estadísticas
- **Listados** con tablas responsivas
- **Formularios** con validación
- **Estados visuales** con badges de color

## 🛠️ Tecnologías Utilizadas

- Java 17
- Spring Boot 3.2.0
- Spring Data JPA
- Thymeleaf
- H2 Database
- Maven
- HTML5 / CSS3

## 📄 Conclusiones

Este sistema demuestra la implementación completa de la arquitectura **MVC** aplicada a un caso real de gestión hotelera, incluyendo:

✅ Separación clara de responsabilidades (Modelo, Vista, Controlador)  
✅ CRUD completo para todas las entidades  
✅ Relaciones entre entidades (ManyToOne)  
✅ Lógica de negocio (validación de disponibilidad, cálculo de montos)  
✅ Interfaz web intuitiva y moderna  
✅ Datos de prueba para testing inmediato  

## 👨‍💻 Autor

Sistema desarrollado como proyecto educativo de arquitectura MVC en Java.

## 📧 Soporte

Para preguntas o reportar problemas, consulta la documentación de Spring Boot: https://spring.io/projects/spring-boot
