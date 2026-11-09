import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Ventana principal del Registro Académico.
 * Aquí se escriben los datos y se muestra el resultado.
 */
public class RegistroAcademico extends JFrame {

    // Campos de texto
    private JTextField campoNombre;
    private JTextField campoMatricula;
    private JTextField campoCarrera;
    private JTextField campoPracticas;
    private JTextField campoParciales;
    private JTextField campoAsignaciones;
    private JTextField campoExamen;

    // Botón y etiquetas de resultado
    private JButton botonCalcular;
    private JLabel etiquetaNota;
    private JLabel etiquetaResultado;

    /**
     * Constructor: crea la ventana con todos sus componentes.
     */
    public RegistroAcademico() {
        setTitle("Registro Académico");
        setSize(400, 450);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null); // centra la ventana

        // Creamos los campos de texto
        campoNombre = new JTextField();
        campoMatricula = new JTextField();
        campoCarrera = new JTextField();
        campoPracticas = new JTextField();
        campoParciales = new JTextField();
        campoAsignaciones = new JTextField();
        campoExamen = new JTextField();

        // Panel con los campos (2 columnas: etiqueta y campo)
        JPanel panelCampos = new JPanel(new GridLayout(7, 2, 5, 5));
        panelCampos.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        panelCampos.add(new JLabel("Nombre completo:"));
        panelCampos.add(campoNombre);
        panelCampos.add(new JLabel("Matrícula:"));
        panelCampos.add(campoMatricula);
        panelCampos.add(new JLabel("Carrera:"));
        panelCampos.add(campoCarrera);
        panelCampos.add(new JLabel("Prácticas (0-40):"));
        panelCampos.add(campoPracticas);
        panelCampos.add(new JLabel("Parciales (0-20):"));
        panelCampos.add(campoParciales);
        panelCampos.add(new JLabel("Asignaciones (0-20):"));
        panelCampos.add(campoAsignaciones);
        panelCampos.add(new JLabel("Examen Final (0-20):"));
        panelCampos.add(campoExamen);

        // Botón
        botonCalcular = new JButton("Calcular Resultado");

        // Etiquetas para mostrar el resultado
        etiquetaNota = new JLabel("Nota total: ", SwingConstants.CENTER);
        etiquetaResultado = new JLabel("", SwingConstants.CENTER);
        etiquetaResultado.setFont(new Font("Arial", Font.BOLD, 20));

        // Panel de abajo con botón y resultados
        JPanel panelAbajo = new JPanel(new GridLayout(3, 1, 5, 5));
        panelAbajo.add(botonCalcular);
        panelAbajo.add(etiquetaNota);
        panelAbajo.add(etiquetaResultado);

        // Agregamos los paneles a la ventana
        add(panelCampos, BorderLayout.CENTER);
        add(panelAbajo, BorderLayout.SOUTH);

        // Cuando se presiona el botón, se llama al método calcular()
        botonCalcular.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                calcular();
            }
        });
    }

    /**
     * Lee los datos, los valida, calcula la nota y muestra el resultado.
     */
    private void calcular() {
        // 1. Verificar que los campos de texto no estén vacíos
        if (campoNombre.getText().trim().isEmpty()
                || campoMatricula.getText().trim().isEmpty()
                || campoCarrera.getText().trim().isEmpty()) {
            mostrarError("Debe llenar nombre, matrícula y carrera.");
            return;
        }

        try {
            // 2. Convertir los textos a números
            double practicas = Double.parseDouble(campoPracticas.getText().trim());
            double parciales = Double.parseDouble(campoParciales.getText().trim());
            double asignaciones = Double.parseDouble(campoAsignaciones.getText().trim());
            double examen = Double.parseDouble(campoExamen.getText().trim());

            // 3. Verificar los rangos permitidos
            if (practicas < 0 || practicas > 40) {
                mostrarError("Prácticas debe estar entre 0 y 40.");
                return;
            }
            if (parciales < 0 || parciales > 20) {
                mostrarError("Parciales debe estar entre 0 y 20.");
                return;
            }
            if (asignaciones < 0 || asignaciones > 20) {
                mostrarError("Asignaciones debe estar entre 0 y 20.");
                return;
            }
            if (examen < 0 || examen > 20) {
                mostrarError("Examen Final debe estar entre 0 y 20.");
                return;
            }

            // 4. Crear el estudiante y calcular
            Estudiante estudiante = new Estudiante(
                    campoNombre.getText().trim(),
                    campoMatricula.getText().trim(),
                    campoCarrera.getText().trim(),
                    practicas, parciales, asignaciones, examen);

            double total = estudiante.calcularNotaTotal();
            etiquetaNota.setText("Nota total: " + total + " / 100");

            // 5. Mostrar aprobado o reprobado
            if (estudiante.aprobo()) {
                etiquetaResultado.setText("APROBADO");
                etiquetaResultado.setForeground(Color.GREEN.darker());
            } else {
                etiquetaResultado.setText("REPROBADO");
                etiquetaResultado.setForeground(Color.RED);
            }

        } catch (NumberFormatException ex) {
            // Si escribieron letras en un campo numérico
            mostrarError("Las calificaciones deben ser números válidos.");
        }
    }

    /**
     * Muestra una ventana con un mensaje de error.
     * @param mensaje el texto del error
     */
    private void mostrarError(String mensaje) {
        JOptionPane.showMessageDialog(this, mensaje, "Error", JOptionPane.ERROR_MESSAGE);
    }
}