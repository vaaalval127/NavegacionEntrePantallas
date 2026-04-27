package ni.edu.uam.navegacionentrepantallas

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController

// Clase para guardar los datos del usuario temporalmente
data class Usuario(
    var nombre: String = "",
    var curso: String = "",
    var matricula: String = ""
)

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MaterialTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    AppNavigation()
                }
            }
        }
    }
}

// ==========================================
// 1. GESTOR DE NAVEGACIÓN Y ESTADO GLOBAL
// ==========================================
@Composable
fun AppNavigation() {
    val navController = rememberNavController()

    // Estado global para el perfil del usuario
    var perfilUsuario by remember { mutableStateOf(Usuario()) }

    NavHost(navController = navController, startDestination = "inicio") {
        composable("inicio") { PantallaInicio(navController, perfilUsuario) }
        composable("detalle") { PantallaDetalle(navController) }
        composable("perfil") {
            PantallaPerfil(navController, perfilUsuario) { usuarioActualizado ->
                perfilUsuario = usuarioActualizado
            }
        }
    }
}

// ==========================================
// 2. PANTALLA 1: INICIO (MEJORADA)
// ==========================================
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PantallaInicio(navController: NavController, usuario: Usuario) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("App POO II", fontWeight = FontWeight.Bold) },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer,
                    titleContentColor = MaterialTheme.colorScheme.onPrimaryContainer
                ),
                actions = {
                    IconButton(onClick = { navController.navigate("perfil") }) {
                        Icon(Icons.Filled.Person, contentDescription = "Perfil")
                    }
                }
            )
        },
        floatingActionButton = {
            FloatingActionButton(onClick = { navController.navigate("detalle") }) {
                Icon(Icons.Filled.Info, contentDescription = "Información")
            }
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .verticalScroll(rememberScrollState()), // Permite hacer scroll si el contenido es muy largo
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // Sección de Bienvenida Dinámica
            Surface(
                modifier = Modifier.fillMaxWidth(),
                color = MaterialTheme.colorScheme.primary.copy(alpha = 0.1f)
            ) {
                Column(
                    modifier = Modifier.padding(24.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Icon(Icons.Filled.Home, contentDescription = "Home", modifier = Modifier.size(64.dp), tint = MaterialTheme.colorScheme.primary)
                    Spacer(modifier = Modifier.height(8.dp))

                    val mensajeBienvenida = if (usuario.nombre.isNotEmpty()) "¡Hola, ${usuario.nombre}!" else "¡Bienvenido a la App!"
                    Text(mensajeBienvenida, fontSize = 26.sp, fontWeight = FontWeight.Bold)

                    if (usuario.curso.isNotEmpty()) {
                        Text("Curso: ${usuario.curso}", color = MaterialTheme.colorScheme.secondary)
                    } else {
                        Text("No olvides configurar tu perfil", color = MaterialTheme.colorScheme.onSurfaceVariant)
                    }
                }
            }

            Spacer(modifier = Modifier.height(24.dp))

            // Componente Interactivo: Carrusel de Tarjetas
            Text("Explora la aplicación", fontSize = 20.sp, fontWeight = FontWeight.Bold, modifier = Modifier.padding(horizontal = 16.dp).align(Alignment.Start))
            Spacer(modifier = Modifier.height(16.dp))

            LazyRow(
                contentPadding = PaddingValues(horizontal = 16.dp),
                horizontalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                items(3) { index ->
                    Card(
                        onClick = { navController.navigate("detalle") },
                        modifier = Modifier.size(width = 200.dp, height = 120.dp),
                        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceVariant)
                    ) {
                        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                            Text("Opción ${index + 1}\n(Toca para ver)", textAlign = TextAlign.Center)
                        }
                    }
                }
            }

            Spacer(modifier = Modifier.height(32.dp))

            // Botón principal
            Button(
                onClick = { navController.navigate("perfil") },
                modifier = Modifier.padding(horizontal = 24.dp).fillMaxWidth()
            ) {
                Icon(Icons.Filled.Edit, contentDescription = null)
                Spacer(modifier = Modifier.width(8.dp))
                Text("Crear / Editar Perfil")
            }
        }
    }
}

// ==========================================
// 3. PANTALLA 2: DETALLE O INFORMACIÓN
// ==========================================
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PantallaDetalle(navController: NavController) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Detalles") },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(Icons.Filled.ArrowBack, contentDescription = "Regresar")
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.secondaryContainer
                )
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(24.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Card(modifier = Modifier.fillMaxWidth()) {
                Column(modifier = Modifier.padding(16.dp)) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Icon(Icons.Filled.CheckCircle, contentDescription = null, tint = MaterialTheme.colorScheme.secondary)
                        Spacer(modifier = Modifier.width(8.dp))
                        Text("Requisitos de la práctica", fontSize = 18.sp, fontWeight = FontWeight.Bold)
                    }
                    Spacer(modifier = Modifier.height(16.dp))
                    Text("✓ Navegación entre mínimo 3 pantallas.\n✓ Implementación con Jetpack Compose.\n✓ Componentes interactivos.\n✓ Títulos identificando cada pantalla.")
                }
            }
        }
    }
}

// ==========================================
// 4. PANTALLA 3: CREACIÓN DE PERFIL
// ==========================================
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PantallaPerfil(
    navController: NavController,
    usuarioActual: Usuario,
    onGuardarPerfil: (Usuario) -> Unit
) {
    // Variables de estado local para los campos de texto
    var nombreTexto by remember { mutableStateOf(usuarioActual.nombre) }
    var cursoTexto by remember { mutableStateOf(usuarioActual.curso) }
    var matriculaTexto by remember { mutableStateOf(usuarioActual.matricula) }

    // Estado para mostrar un mensaje de confirmación
    var mostrarMensaje by remember { mutableStateOf(false) }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Configurar Perfil") },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(Icons.Filled.ArrowBack, contentDescription = "Regresar")
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.tertiaryContainer
                )
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(24.dp)
                .verticalScroll(rememberScrollState()),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Icon(Icons.Filled.AccountCircle, contentDescription = "Avatar", modifier = Modifier.size(100.dp), tint = MaterialTheme.colorScheme.tertiary)
            Spacer(modifier = Modifier.height(24.dp))

            // Formularios de entrada de texto
            OutlinedTextField(
                value = nombreTexto,
                onValueChange = { nombreTexto = it },
                label = { Text("Nombre completo") },
                leadingIcon = { Icon(Icons.Filled.Person, contentDescription = null) },
                modifier = Modifier.fillMaxWidth(),
                singleLine = true
            )

            Spacer(modifier = Modifier.height(16.dp))

            OutlinedTextField(
                value = cursoTexto,
                onValueChange = { cursoTexto = it },
                label = { Text("Curso / Materia") },
                leadingIcon = { Icon(Icons.Filled.Create, contentDescription = null) },
                modifier = Modifier.fillMaxWidth(),
                singleLine = true
            )

            Spacer(modifier = Modifier.height(16.dp))

            OutlinedTextField(
                value = matriculaTexto,
                onValueChange = { matriculaTexto = it },
                label = { Text("Matrícula / ID") },
                leadingIcon = { Icon(Icons.Filled.List, contentDescription = null) },
                modifier = Modifier.fillMaxWidth(),
                singleLine = true
            )

            Spacer(modifier = Modifier.height(32.dp))

            Button(
                onClick = {
                    // Guardamos el estado global
                    onGuardarPerfil(Usuario(nombreTexto, cursoTexto, matriculaTexto))
                    mostrarMensaje = true
                },
                modifier = Modifier.fillMaxWidth(),
                colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.tertiary)
            ) {
                Icon(Icons.Filled.Done, contentDescription = null)
                Spacer(modifier = Modifier.width(8.dp))
                Text("Guardar Perfil")
            }

            if (mostrarMensaje) {
                Spacer(modifier = Modifier.height(16.dp))
                Text("¡Perfil guardado con éxito! Vuelve al inicio para ver los cambios.", color = MaterialTheme.colorScheme.primary, textAlign = TextAlign.Center)
            }
        }
    }
}