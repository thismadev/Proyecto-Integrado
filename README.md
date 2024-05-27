# Proyecto Integrado
Este es mi Trabajo Fin de Grado de la Formación Profesional en Desarrollo de Aplicaciones Web con especialización en Blockchain.

## Descripción del Proyecto
La aplicación es una plataforma web destinada a la comunidad de lectores, donde pueden compartir reseñas de libros, prestarlos, y participar en diversas actividades relacionadas con la lectura. Los usuarios tienen la capacidad de crear perfiles y disfrutar de funcionalidades tanto desde el rol de usuario como de administrador.

## Objetivo del Proyecto
El objetivo de este proyecto es desarrollar una plataforma web interactiva para la comunidad lectora utilizando tecnologías web modernas para asegurar la autenticidad de las transacciones.

## Características Principales
- **Autenticación de Usuarios:** Registro e inicio de sesión con roles diferenciados (usuario y administrador).
- **Gestión de Libros:** Añadir, editar, eliminar y listar libros.
- **Reseñas de Libros:** Permitir a los usuarios escribir, editar y leer reseñas de libros.
- **Préstamo de Libros:** Facilitar el préstamo de libros entre usuarios con seguridad y transparencia.
- **Panel de Administración:** Herramientas para la gestión completa de usuarios, libros y reseñas.

## Tecnologías Utilizadas
### Frontend
- **Framework:** Angular

### Backend
- **Framework:** Spring Boot
- **Autenticación:** JSON Web Tokens (JWT)


# Guía de Inicio de la Aplicación con Docker

## Preparación del Entorno Docker
### Instalación de Docker
- Asegúrate de que Docker esté instalado y en funcionamiento. Puedes descargarlo desde [Docker](https://www.docker.com/products/docker-desktop).

### Paso 1: Configuración de Docker Compose
- Ubícate en el directorio que contiene el archivo `docker-compose.yml`.
- Ejecuta el siguiente comando para iniciar los servicios:
  ```bash
  docker-compose up -d

### Paso 2: Configuración de la Base de Datos
### Verificación de la Base de Datos
La base de datos `book_social_network` debería crearse automáticamente con Docker Compose. Si necesitas hacer ajustes o verificaciones, conéctate al contenedor de PostgreSQL usando:
`docker exec -it postgres-sql-bsn psql -U username -d book_social_network`

### Paso 3: Configuración de la Aplicación Spring Boot
### Revisión del Archivo de Configuración
Asegúrate de que el archivo `application.yml` de tu aplicación Spring Boot esté correctamente configurado para conectar con PostgreSQL y MailDev. Verifica que las credenciales y los puertos sean correctos según lo configurado en Docker Compose.

### Paso 4: Ejecución de la Aplicación Spring Boot
### Compilación y Ejecución
Si utilizas Maven, compila y ejecuta tu aplicación con los siguientes comandos:

```bash
mvn clean install
mvn spring-boot:run



