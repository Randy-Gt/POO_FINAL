package empleados;

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

public class MainEmpleados {

    public static void main(String[] args) {
       
        ArrayList<Empleado> empleados = new ArrayList<>();
        empleados.add(new Empleado(1, "Randy pri pra", 3500000, "Ventas"));
        empleados.add(new Empleado(2, "will", 4200000, "IT"));
        empleados.add(new Empleado(3, "nela", 2800000, "Ventas"));
        empleados.add(new Empleado(4, "anthony", 5000000, "IT"));
        empleados.add(new Empleado(5, "EL TENA", 3000000, "Recursos Humanos"));

       
        guardarJSON(empleados);

        
        ArrayList<Empleado> empleadosLeidos = leerJSON();

        
        System.out.println("TODOS LOS EMPLEADOS");
        empleadosLeidos.forEach(System.out::println);

        System.out.println("EMPLEADO CON MAYOR SALARIO ");
        Empleado mayorSalario = empleadosLeidos.stream()
                .max((e1, e2) -> Double.compare(e1.getSalario(), e2.getSalario()))
                .orElse(null);
        if (mayorSalario != null) {
            System.out.println(mayorSalario);
        }

        System.out.println("PROMEDIO DE SALARIOS");
        double promedio = empleadosLeidos.stream()
                .mapToDouble(Empleado::getSalario)
                .average()
                .orElse(0.0);
        System.out.println("Promedio: $" + promedio);

        System.out.println("EMPLEADOS DE IT");
        List<Empleado> it = empleadosLeidos.stream()
                .filter(e -> e.getDepartamento().equals("IT"))
                .collect(Collectors.toList());
        it.forEach(System.out::println);
    }

    private static void guardarJSON(ArrayList<Empleado> empleados) {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        try (FileWriter writer = new FileWriter("empleados.json")) {
            gson.toJson(empleados, writer);
            System.out.println("Archivo JSON creado exitosamente\n");
        } catch (IOException e) {
            System.err.println("Error: " + e.getMessage());
        }
    }

    private static ArrayList<Empleado> leerJSON() {
        Gson gson = new Gson();
        try (FileReader reader = new FileReader("empleados.json")) {
            Type listType = new TypeToken<ArrayList<Empleado>>(){}.getType();
            return gson.fromJson(reader, listType);
        } catch (IOException e) {
            System.err.println("Error: " + e.getMessage());
            return new ArrayList<>();
        }
    }
}