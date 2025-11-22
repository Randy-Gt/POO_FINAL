package libros;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class MainLibros {

    public static void main(String[] args) {
        
        ArrayList<Libro> libros = new ArrayList<>();
        libros.add(new Libro(1, "Cien Años de Soledad", 45000, "Ficción"));
        libros.add(new Libro(2, "El Principito", 25000, "Infantil"));
        libros.add(new Libro(3, "1984", 38000, "Ficción"));
        libros.add(new Libro(4, "Harry Potter", 52000, "Fantasía"));
        libros.add(new Libro(5, "Don Quijote", 35000, "Clásico"));

       
        guardarJSON(libros);

       
        ArrayList<Libro> librosLeidos = leerJSON();

       
        System.out.println(" TODOS LOS LIBROS");
        librosLeidos.forEach(System.out::println);

        System.out.println(" LIBRO MÁS COSTOSO");
        Libro masCostoso = librosLeidos.stream()
                .max((l1, l2) -> Double.compare(l1.getPrecio(), l2.getPrecio()))
                .orElse(null);
        if (masCostoso != null) {
            System.out.println(masCostoso);
        }

        System.out.println("PROMEDIO DE PRECIOS");
        double promedio = librosLeidos.stream()
                .mapToDouble(Libro::getPrecio)
                .average()
                .orElse(0.0);
        System.out.println("Promedio: " + promedio);

        System.out.println("LIBROS DE FICCIÓN");
        List<Libro> ficcion = librosLeidos.stream()
                .filter(l -> l.getGenero().equals("Ficción"))
                .collect(Collectors.toList());
        ficcion.forEach(System.out::println);
    }

    private static void guardarJSON(ArrayList<Libro> libros) {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        try (FileWriter writer = new FileWriter("libros.json")) {
            gson.toJson(libros, writer);
            System.out.println("Archivo JSON creado exitosamente\n");
        } catch (IOException e) {
            System.err.println("Error: " + e.getMessage());
        }
    }

    private static ArrayList<Libro> leerJSON() {
        Gson gson = new Gson();
        try (FileReader reader = new FileReader("libros.json")) {
            Type listType = new TypeToken<ArrayList<Libro>>(){}.getType();
            return gson.fromJson(reader, listType);
        } catch (IOException e) {
            System.err.println("Error: " + e.getMessage());
            return new ArrayList<>();
        }
    }
}
