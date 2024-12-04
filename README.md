# Ejercicio-2

## Descripción
Este ejercicio es una aplicación móvil que permite visualizar tareas, gestionar actividades y ver tareas en un calendario, con soporte para múltiples idiomas (español e inglés). Implementa funcionalidades como registro de usuario, inicio de sesión, gestión de actividades, y un calendario interactivo.

### **Cambiar idioma**
Una de las funcionalidades destacadas es el **cambio dinámico de idioma** entre inglés y español mediante un interruptor (`Switch`). Los cambios se aplican en tiempo real y afectan toda la interfaz de la aplicación.

## Tabla de Contenidos
- [Características](#características)
- [Clases Principales](#clases-principales)
  - [PantallaPrincipalActivity](#pantallaprincipalactivity)
  - [LoginActivity](#loginactivity)
  - [RegisterActivity](#registeractivity)
  - [AddActivity](#addactivity)
  - [CalendarActivity](#calendaractivity)
  - [CurrentTasksActivity](#currenttasksactivity)
  - [Adaptador: CalendarAdapter](#adaptador-calendaradapter)
  - [Modelos](#modelos)
  - [Repositorios](#repositorios)
- [Uso](#uso)

## Características
- Gestión de actividades (añadir, visualizar).
- Calendario interactivo para organizar tareas.
- Autenticación y autorización de usuarios mediante Firebase.
- Funcionalidad para cambiar idioma entre inglés y español con un `Switch`.
- Almacenamiento local de actividades con Room Database.
- Soporte para roles (usuarios y administradores).

## Clases Principales

### PantallaPrincipalActivity
Esta es la actividad principal de la aplicación. Contiene la barra de navegación y el `Switch` para cambiar de idioma.

#### Cambio de idioma
El idioma se cambia dinámicamente mediante el método `setLocale(String lang)`:
- Actualiza la configuración del idioma.
- Guarda la preferencia en `SharedPreferences`.
- Recarga la actividad con el idioma seleccionado.

### LoginActivity
- Permite a los usuarios iniciar sesión.
- Verifica credenciales desde Firebase Authentication y Firestore.
- Redirige a `PantallaPrincipalActivity` tras un inicio de sesión exitoso.

### RegisterActivity
- Gestiona el registro de usuarios.
- Guarda los datos en Firebase Authentication y Firestore.
- Incluye un `Spinner` para seleccionar el rol del usuario.

### AddActivity
- Permite a los usuarios añadir nuevas actividades.
- Realiza validaciones de formato de fecha y hora.
- Guarda las actividades en la base de datos local (Room Database).

### CalendarActivity
- Muestra un calendario mensual.
- Destaca los días con actividades.
- Al hacer clic en un día, muestra detalles de la actividad.

### CurrentTasksActivity
- Muestra las actividades del día actual.
- Recupera los datos desde la base de datos local.

### Adaptador: CalendarAdapter
- Utilizado en `CalendarActivity` para mostrar actividades.
- Asocia las actividades a sus fechas correspondientes.

---

## Models

### Actividad
Modelo para representar una actividad con los siguientes campos:
- **id**: Identificador único.
- **fecha**: Fecha en formato `YYYY-MM-DD`.
- **hora**: Hora en formato `HH:MM`.
- **asignatura**: Materia de la actividad.

### Usuario
Modelo para los usuarios:
- **nombre**: Nombre del usuario.
- **correo**: Email del usuario.
- **rol**: Rol del usuario (Usuario/Administrador).

### Credenciales
- Almacena credenciales de los usuarios.

### Ubicación
- Modelo para la ubicación del usuario.

---

## Repositorios

### FirebaseHandler
- Gestor de operaciones CRUD en Firebase Firestore.
- Métodos para usuarios, credenciales y ubicaciones.

### RoomDatabaseHelper
- Gestor de operaciones CRUD en Room Database.
- Métodos para guardar y recuperar actividades.

---

## Uso

- **Cambiar idioma**: Usa el `Switch` en la pantalla principal para alternar entre inglés y español.
- **Gestionar actividades**:
  - Añade actividades desde la pantalla "Añadir Actividad" (admin).
  - Visualízalas en "Tareas Actuales" o en el calendario.
- **Iniciar sesión o registrarse**:
  - Usa el formulario de inicio de sesión o registro.
  - Los administradores tendrán acceso a funciones adicionales (añadir actividades).


