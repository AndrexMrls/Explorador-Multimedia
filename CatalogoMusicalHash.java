
package cancion;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

// Clase Cancion
class Cancion {
    String titulo;
    String artista;
    String genero;
    String region;
    int anio;

    public Cancion(String titulo, String artista, String genero, String region, int anio) {
        this.titulo = titulo;
        this.artista = artista;
        this.genero = genero;
        this.region = region;
        this.anio = anio;
    }

    @Override
    public String toString() {
        return """
               
               --- Detalles de la Cancion ---
               🎵 Título: """ + titulo + "\n" +
               "🎤 Artista: " + artista + "\n" +
               "🧬 Género: " + genero + "\n" +
               "🌍 Región: " + region + "\n" +
               "🗓 Año: " + anio + "\n" +
               "----------------------------\n";
    }
}

public class CatalogoMusicalHash {

    private final Map<String, Cancion> catalogoPorTitulo;
    private final Map<String, List<Cancion>> indicePorArtista;
    private final Map<String, List<Cancion>> indicePorGenero;
    private final Map<String, List<Cancion>> indicePorRegion;
    private final Scanner scanner;

    public CatalogoMusicalHash() {
        this.catalogoPorTitulo = new HashMap<>();
        this.indicePorArtista = new HashMap<>();
        this.indicePorGenero = new HashMap<>();
        this.indicePorRegion = new HashMap<>();
        this.scanner = new Scanner(System.in);
        cargarCancionesIniciales(); 
    }

    // --------------------------- Carga Inicial --------------------------------------
    private void cargarCancionesIniciales() {
        Cancion[] canciones = {
            new Cancion("De Música Ligera", "Soda Stereo", "Rock", "Latinoamerica", 1990),
            new Cancion("Persiana Americana", "Soda Stereo", "Rock", "Latinoamerica", 1986),
            new Cancion("Lamento Boliviano", "Enanitos Verdes", "Rock", "Latinoamerica", 1994),
            new Cancion("Cuando Seas Grande", "Miguel Mateos", "Pop Rock", "Argentina", 1986),
            new Cancion("Sweet Child O' Mine", "Guns N' Roses", "Hard Rock", "Estados Unidos", 1987),
            new Cancion("Despacito", "Luis Fonsi", "Pop Latino", "Puerto Rico", 2017),
            new Cancion("Gasolina", "Daddy Yankee", "Reggaeton", "Puerto Rico", 2004),
            new Cancion("La Camisa Negra", "Juanes", "Pop Latino", "Colombia", 2004),
            new Cancion("Vivir Mi Vida", "Marc Anthony", "Salsa", "Puerto Rico", 2013),
            new Cancion("Danza Kuduro", "Don Omar", "Reggaeton", "Puerto Rico", 2010),
            new Cancion("El Triste", "José José", "Balada", "Mexico", 1970),
            new Cancion("Burbujas de Amor", "Juan Luis Guerra", "Bachata", "Caribe", 1990),
            new Cancion("Ojalá Que Llueva Café", "Juan Luis Guerra", "Merengue", "Caribe", 1989),
            new Cancion("Sabor a Mí", "Luis Miguel", "Bolero", "Mexico", 1997),
            new Cancion("Procuro Olvidarte", "Hernaldo Zuniga", "Balada", "Nicaragua", 1980),
            new Cancion("Levels", "Avicii", "House", "Europa", 2011),
            new Cancion("Titanium", "David Guetta", "Dance Pop", "Europa", 2011),
            new Cancion("September", "Earth, Wind & Fire", "Funk", "Estados Unidos", 1978),
            new Cancion("Billie Jean", "Michael Jackson", "Pop", "Estados Unidos", 1982),
            new Cancion("One", "U2", "Rock", "Europa", 1991)
        };

        for (Cancion c : canciones) {
            indexarCancion(c);
        }
        System.out.println("🎶 Catálogo inicial cargado con " + canciones.length + " canciones.");
    }

    // --------------------------- Indexación --------------------------------------
    private void indexarCancion(Cancion cancion) {
        catalogoPorTitulo.put(cancion.titulo, cancion);
        indicePorArtista.computeIfAbsent(cancion.artista, k -> new ArrayList<>()).add(cancion);
        indicePorGenero.computeIfAbsent(cancion.genero, k -> new ArrayList<>()).add(cancion);
        indicePorRegion.computeIfAbsent(cancion.region, k -> new ArrayList<>()).add(cancion);
    }

    // --------------------------- Nueva función: Eliminar Canción --------------------------
    public void eliminarCancion() {
        if (catalogoPorTitulo.isEmpty()) {
            System.out.println("\n🚫 El catalogo está vacío.");
            return;
        }

        System.out.print("\nIngrese el TiTULO EXACTO de la canción que desea eliminar: ");
        String tituloEliminar = scanner.nextLine().trim();

        Cancion cancion = catalogoPorTitulo.remove(tituloEliminar);

        if (cancion != null) {
            // También eliminar de los índices secundarios
            indicePorArtista.get(cancion.artista).remove(cancion);
            indicePorGenero.get(cancion.genero).remove(cancion);
            indicePorRegion.get(cancion.region).remove(cancion);

            System.out.println("🗑️ Canción '" + tituloEliminar + "' eliminada correctamente del catálogo.");
        } else {
            System.out.println("❌ No se encontró la canción '" + tituloEliminar + "'.");
        }
    }

    // --------------------------- Ingreso de Canción --------------------------
    public void ingresarCancion() {
        System.out.println("\n--- Ingresar Nueva Canción ---");
        System.out.print("Título: ");
        String titulo = scanner.nextLine().trim();

        if (catalogoPorTitulo.containsKey(titulo)) {
            System.out.println("⚠️ Error: La canción '" + titulo + "' ya existe en el catálogo.");
            return;
        }

        System.out.print("Artista: ");
        String artista = scanner.nextLine().trim();
        System.out.print("Genero: ");
        String genero = scanner.nextLine().trim();
        System.out.print("Region: ");
        String region = scanner.nextLine().trim();

        int anio = 0;
        boolean anioValido = false;
        while (!anioValido) {
            System.out.print("Año: ");
            try {
                anio = Integer.parseInt(scanner.nextLine().trim());
                anioValido = true;
            } catch (NumberFormatException e) {
                System.out.println("⚠ Entrada no válida. Ingrese un número entero para el año.");
            }
        }

        Cancion nuevaCancion = new Cancion(titulo, artista, genero, region, anio);
        indexarCancion(nuevaCancion);
        System.out.println("✅ Canción '" + titulo + "' agregada al catálogo.");
    }

    // --------------------------- Búsquedas --------------------------
    public void buscarPorTitulo() {
        if (catalogoPorTitulo.isEmpty()) {
            System.out.println("\n🚫 El catálogo está vacío.");
            return;
        }

        System.out.print("\nIngrese el título exacto: ");
        String tituloBuscado = scanner.nextLine().trim();

        Cancion resultado = catalogoPorTitulo.get(tituloBuscado);

        if (resultado != null) {
            System.out.println("\n🎉 ¡Búsqueda exitosa! (Acceso O(1))");
            System.out.println(resultado);
        } else {
            System.out.println("\n❌ No se encontró la canción '" + tituloBuscado + "'.");
        }
    }

    public void buscarPorCriterio(String tipoBusqueda, Map<String, List<Cancion>> indice) {
        System.out.print("\nIngrese el " + tipoBusqueda + " exacto: ");
        String criterio = scanner.nextLine().trim();

        List<Cancion> resultados = indice.get(criterio);

        if (resultados != null && !resultados.isEmpty()) {
            System.out.println("\n🎶 Canciones encontradas:");
            for (Cancion c : resultados) {
                System.out.println(c);
            }
        } else {
            System.out.println("\n❌ No se encontraron canciones con ese " + tipoBusqueda + ".");
        }
    }

    public void mostrarMenuBusqueda() {
        String opcion;
        do {
            System.out.println("\n--- Menú de Búsqueda ---");
            System.out.println("1. Buscar por Titulo");
            System.out.println("2. Buscar por Artista");
            System.out.println("3. Buscar por Genero");
            System.out.println("4. Buscar por Region");
            System.out.println("5. Volver al menú principal");
            System.out.print("Seleccione una opción: ");
            opcion = scanner.nextLine().trim();

            switch (opcion) {
                case "1" -> buscarPorTitulo();
                case "2" -> buscarPorCriterio("Artista", indicePorArtista);
                case "3" -> buscarPorCriterio("Genero", indicePorGenero);
                case "4" -> buscarPorCriterio("Region", indicePorRegion);
                case "5" -> System.out.println("Regresando al menú principal...");
                default -> System.out.println("Opción no válida.");
            }
        } while (!opcion.equals("5"));
    }

    // --------------------------- Mostrar Títulos --------------------------
    private void mostrarTitulos() {
        if (catalogoPorTitulo.isEmpty()) {
            System.out.println("\n🚫 El catálogo está vacío.");
            return;
        }
        System.out.println("\n--- Títulos en el Catálogo ---");
        catalogoPorTitulo.keySet().forEach(System.out::println);
        System.out.println("----------------------------");
    }

    // --------------------------- Menú Principal --------------------------
    public void ejecutar() {
        String opcion;
        do {
            System.out.println("\n==================================================");
            System.out.println("   ¡.....MUSICA DJ ANDREX QUE SIGA LA RUMBA.....!");
            System.out.println("==================================================");
            System.out.println("1. Ingresar nueva cancion");
            System.out.println("2. Buscar cancion");
            System.out.println("3. Mostrar todos los titulos");
            System.out.println("4. Eliminar cancion"); // 🔥 Nueva opción
            System.out.println("5. Salir");
            System.out.print("Seleccione una opcion: ");
            opcion = scanner.nextLine().trim();

            switch (opcion) {
                case "1" -> ingresarCancion();
                case "2" -> mostrarMenuBusqueda();
                case "3" -> mostrarTitulos();
                case "4" -> eliminarCancion(); // 🔥 Aquí se llama
                case "5" -> System.out.println("Cerrando el catálogo musical DJ Andrex ¡Hasta luego!");
                default -> System.out.println("Opción no válida. Intente nuevamente.");
            }
        } while (!opcion.equals("5"));

        scanner.close();
    }

    // --------------------------- Main --------------------------
    public static void main(String[] args) {
        CatalogoMusicalHash app = new CatalogoMusicalHash();
        app.ejecutar();
    }
}
