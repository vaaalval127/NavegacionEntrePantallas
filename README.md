# Práctica: Navegación y Estado con Jetpack Compose

Este proyecto es una práctica de la asignatura **POO II – Desarrollo de aplicaciones móviles en Android**. El objetivo es implementar un sistema de navegación continuo entre pantallas y gestionar el **estado global** de la aplicación para crear una experiencia interactiva y dinámica.

## 📱 Características de la Aplicación

La aplicación consta de un flujo interactivo de 3 pantallas, utilizando componentes modernos de Material Design 3 y compartiendo datos en tiempo real entre ellas:

1. **Pantalla de Inicio (`PantallaInicio`):** * Muestra un mensaje de bienvenida dinámico que se actualiza si el usuario registra su nombre en el perfil.
   * Incluye un carrusel interactivo (`LazyRow`) para explorar opciones.
   * Cuenta con un Botón Flotante (`FloatingActionButton`) para acceder rápidamente a los detalles y accesos directos en la barra superior.

2. **Pantalla de Detalle (`PantallaDetalle`):** * Muestra información sobre los requisitos cumplidos en la práctica mediante el uso de Tarjetas (`Card`) e iconos descriptivos.
   * Navegación hacia atrás integrada en el `TopAppBar`.

3. **Pantalla de Perfil (`PantallaPerfil`):** * Permite al usuario crear y editar su información personal a través de un formulario interactivo (`OutlinedTextField`).
   * Captura el Nombre, Curso y Matrícula.
   * Al guardar, los datos persisten temporalmente en el flujo de navegación (State Hoisting) y se reflejan inmediatamente en la pantalla de inicio.

## 🛠️ Tecnologías y Componentes Utilizados

* **Lenguaje:** Kotlin
* **UI Toolkit:** Jetpack Compose
* **Diseño:** Material Design 3
* **Navegación:** `androidx.navigation:navigation-compose`
* **Gestión de Estado:** `remember`, `mutableStateOf`, y State Hoisting (Elevación de estado).
* **Componentes Compose:** `NavHost`, `Scaffold`, `TopAppBar`, `LazyRow`, `OutlinedTextField`, `FloatingActionButton`, entre otros.

## 🚀 Instrucciones de Ejecución

Para probar este proyecto en tu entorno local, sigue estos pasos:

1. Clona este repositorio público en tu computadora:
   ```bash
   git clone [URL_DE_TU_REPOSITORIO_AQUI]
