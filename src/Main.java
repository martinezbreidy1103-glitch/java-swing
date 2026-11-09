import javax.swing.SwingUtilities;

/** Punto de entrada de la aplicación de Registro Académico. */
public class Main {

    /**
     * Inicia la interfaz gráfica en el hilo de eventos de Swing.
     *
     * @param args argumentos de línea de comandos (no se usan)
     */
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new RegistroAcademico().setVisible(true));
    }
}