package libros;

public class Libro {
    private int id;
    private String titulo;
    private double precio;
    private String genero;

    public Libro(int id, String titulo, double precio, String genero) {
        this.id = id;
        this.titulo = titulo;
        this.precio = precio;
        this.genero = genero;
    }

    public int getId() {
        return id;
    }

    public String getTitulo() {
        return titulo;
    }

    public double getPrecio() {
        return precio;
    }

    public String getGenero() {
        return genero;
    }

    @Override
    public String toString() {
        return "ID: " + id + "  Título: " + titulo + "  Precio: " + precio + "  Género: " + genero;
    }
}