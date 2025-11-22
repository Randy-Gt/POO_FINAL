package empleados;

public class Empleado {
    private int id;
    private String nombre;
    private double salario;
    private String departamento;

    public Empleado(int id, String nombre, double salario, String departamento) {
        this.id = id;
        this.nombre = nombre;
        this.salario = salario;
        this.departamento = departamento;
    }

    public int getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public double getSalario() {
        return salario;
    }

    public String getDepartamento() {
        return departamento;
    }

    @Override
    public String toString() {
        return "ID: " + id + " Nombre: " + nombre + " Salario: " + salario + " Depto: " + departamento;
    }
}