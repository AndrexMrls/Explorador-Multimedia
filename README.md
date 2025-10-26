# 🎶 Catálogo Musical Hash - DJ ANDREX

Este proyecto es una aplicación de consola en **Java** que permite gestionar un catálogo musical utilizando **estructuras Hash (HashMap)** para búsquedas rápidas y eficientes.

---

## 🚀 Funcionalidades principales

- ➕ Ingresar nuevas canciones  
- 🔍 Buscar canciones por **Título, Artista, Género o Región**  
- 🗑️ Eliminar canciones del catálogo  
- 📋 Mostrar todos los títulos disponibles  
- ⚡ Acceso directo mediante índices hash (búsqueda O(1))  

---

## 🧠 Lógica del programa

La lógica del programa está organizada en dos clases principales:

### ✅ **Cancion.java**

Clase que representa una canción individual. Contiene:

- **Atributos:**  
  - `titulo`  
  - `artista`  
  - `genero`  
  - `region`  
  - `anio`  

- **Métodos:**  
  - `toString()`: muestra los detalles de la canción en formato decorativo.  

---

### ✅ **CatalogoMusicalHash.java**

Clase principal que maneja el **catálogo de canciones** y toda la lógica de interacción.

Utiliza **múltiples índices hash** para optimizar las búsquedas:

- `catalogoPorTitulo` → acceso directo por título  
- `indicePorArtista` → lista de canciones por artista  
- `indicePorGenero` → lista de canciones por género  
- `indicePorRegion` → lista de canciones por región  

Incluye además un menú interactivo que permite:  
- Agregar nuevas canciones  
- Buscar por distintos criterios  
- Eliminar canciones  
- Mostrar todo el catálogo  

---

## 🧺 Estructura de datos

Se utiliza **HashMap**, parte de la colección **Map** del Java Collections Framework.

| Índice | Tipo de clave | Valor |
|--------|----------------|-------|
| `catalogoPorTitulo` | `String` (Título) | `Cancion` |
| `indicePorArtista` | `String` (Artista) | `List<Cancion>` |
| `indicePorGenero` | `String` (Género) | `List<Cancion>` |
| `indicePorRegion` | `String` (Región) | `List<Cancion>` |

### 🔹 Ventajas del uso de HashMap:
- Acceso **rápido (tiempo constante O(1))**  
- Inserción y eliminación eficientes  
- Ideal para aplicaciones con muchas búsquedas  
- Evita duplicados de forma automática  

---

## 📦 Colección utilizada

La colección utilizada es **`java.util.HashMap`**, con apoyo de **`ArrayList`** para agrupar canciones bajo el mismo criterio.

---

## 🧩 Organización del menú

El menú de la aplicación se muestra en consola y ofrece las siguientes opciones:

1. Ingresar nueva canción  
2. Buscar canción por Título, Artista, Género o Región  
3. Mostrar todos los títulos  
4. Eliminar una canción del catálogo  
5. Salir  

Cada opción invoca un método con su propia lógica.  
Por ejemplo, la búsqueda por título usa acceso **directo por Hash** sin recorrer listas.

---

## 💾 Carga inicial

El programa **carga automáticamente 20 canciones** al iniciar, clasificadas por género, artista, y región.  
Estas se indexan en los distintos HashMaps para permitir búsquedas instantáneas.

---

## 🖥️ Requisitos para ejecutar

- ☕ **JDK 8 o superior**  
- 💻 Cualquier IDE (Eclipse, IntelliJ, NetBeans) o línea de comandos.  

### ▶️ Ejecución desde consola:

```bash
javac CatalogoMusicalHash.java
java cancion.CatalogoMusicalHash

👨‍💻 Autores

Andres Morales
