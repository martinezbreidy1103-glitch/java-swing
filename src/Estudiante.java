/**
 * Clase que guarda los datos de un estudiante y calcula su nota.
 */
public class Estudiante {

    // Datos del estudiante (privados = encapsulamiento)
    private String nombre;
    private String matricula;
    private String carrera;
    private double practicas;
    private double parciales;
    private double asignaciones;
    private double examenFinal;

    /**
     * Constructor: recibe todos los datos del estudiante.
     */
    public Estudiante(String nombre, String matricula, String carrera,
                      double practicas, double parciales,
                      double asignaciones, double examenFinal) {
        this.nombre = nombre;
        this.matricula = matricula;
        this.carrera = carrera;
        this.practicas = practicas;
        this.parciales = parciales;
        this.asignaciones = asignaciones;
        this.examenFinal = examenFinal;
    }

    /** Devuelve el nombre del estudiante. */
    public String getNombre() {
        return nombre;
    }

    /** Devuelve la matrícula del estudiante. */
    public String getMatricula() {
        return matricula;
    }

    /** Devuelve la carrera del estudiante. */
    public String getCarrera() {
        return carrera;
    }

    /**
     * Suma todas las notas para obtener la nota total.
     * @return la nota total (máximo 100)
     */
    public double calcularNotaTotal() {
        return practicas + parciales + asignaciones + examenFinal;
    }

    /**
     * Revisa si el estudiante aprobó.
     * @return true si la nota total es 60 o más
     */
    public boolean aprobo() {
        return calcularNotaTotal() >= 60;
    }
}