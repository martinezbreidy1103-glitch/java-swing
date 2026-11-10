import javax.swing.*;
import java.awt.*;
import java.util.function.DoubleSupplier;

/**
 * Ventana principal. Tiene dos pestañas: "Modo Calculadora" y "Modo Conversor".
 */
public class Main extends JFrame {

    // Objetos con la lógica (separados de la interfaz)
    private final Calculadora calculadora = new Calculadora();
    private final ConversorTemperatura conversor = new ConversorTemperatura();
    private final Raizcuadrada_Exponente raizExp = new Raizcuadrada_Exponente();

    // Componentes de la pestaña Calculadora
    private final JTextField txtNum1 = new JTextField(8);
    private final JTextField txtNum2 = new JTextField(8);
    private final JLabel lblResultado = new JLabel("Resultado: ");

    // Componentes de la pestaña Conversor
    private final JTextField txtTemp = new JTextField(8);
    private final JLabel lblTemp = new JLabel("Resultado: ");

    /** Construye la ventana. */
    public Main() {
        setTitle("Calculadora Extendida");
        setSize(480, 280);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JTabbedPane pestanas = new JTabbedPane();
        pestanas.addTab("Modo Calculadora", crearPanelCalculadora());
        pestanas.addTab("Modo Conversor", crearPanelConversor());
        add(pestanas);
    }

    /** Crea el panel de la calculadora. */
    private JPanel crearPanelCalculadora() {
        JPanel panel = new JPanel(new GridLayout(4, 1, 5, 5));

        JPanel filaCampos = new JPanel();
        filaCampos.add(new JLabel("Número 1:"));
        filaCampos.add(txtNum1);
        filaCampos.add(new JLabel("Número 2:"));
        filaCampos.add(txtNum2);

        JButton btnSumar = new JButton("+");
        JButton btnRestar = new JButton("-");
        JButton btnMult = new JButton("×");
        JButton btnDiv = new JButton("÷");
        JButton btnRaiz = new JButton("√ (Número 1)");
        JButton btnExp = new JButton("N1 ^ N2");

        JPanel filaBasicas = new JPanel();
        filaBasicas.add(btnSumar);
        filaBasicas.add(btnRestar);
        filaBasicas.add(btnMult);
        filaBasicas.add(btnDiv);

        JPanel filaEspeciales = new JPanel();
        filaEspeciales.add(btnRaiz);
        filaEspeciales.add(btnExp);

        // Cada botón calcula y muestra el resultado
        btnSumar.addActionListener(e -> mostrar(lblResultado,
                () -> calculadora.sumar(leer(txtNum1), leer(txtNum2))));
        btnRestar.addActionListener(e -> mostrar(lblResultado,
                () -> calculadora.restar(leer(txtNum1), leer(txtNum2))));
        btnMult.addActionListener(e -> mostrar(lblResultado,
                () -> calculadora.multiplicar(leer(txtNum1), leer(txtNum2))));
        btnDiv.addActionListener(e -> mostrar(lblResultado,
                () -> calculadora.dividir(leer(txtNum1), leer(txtNum2))));
        btnRaiz.addActionListener(e -> mostrar(lblResultado,
                () -> raizExp.raizCuadrada(leer(txtNum1))));
        btnExp.addActionListener(e -> mostrar(lblResultado,
                () -> raizExp.exponente(leer(txtNum1), leer(txtNum2))));

        panel.add(filaCampos);
        panel.add(filaBasicas);
        panel.add(filaEspeciales);
        panel.add(lblResultado);
        return panel;
    }

    /** Crea el panel del conversor de temperatura. */
    private JPanel crearPanelConversor() {
        JPanel panel = new JPanel(new GridLayout(3, 1, 5, 5));

        JPanel filaCampo = new JPanel();
        filaCampo.add(new JLabel("Temperatura:"));
        filaCampo.add(txtTemp);

        JButton btnFC = new JButton("°F → °C");
        JButton btnCF = new JButton("°C → °F");
        JPanel filaBotones = new JPanel();
        filaBotones.add(btnFC);
        filaBotones.add(btnCF);

        btnFC.addActionListener(e -> mostrar(lblTemp,
                () -> conversor.fahrenheitACelsius(leer(txtTemp))));
        btnCF.addActionListener(e -> mostrar(lblTemp,
                () -> conversor.celsiusAFahrenheit(leer(txtTemp))));

        panel.add(filaCampo);
        panel.add(filaBotones);
        panel.add(lblTemp);
        return panel;
    }

    /**
     * Lee un número de un campo de texto.
     * @throws NumberFormatException si el texto no es un número válido
     */
    private double leer(JTextField campo) {
        return Double.parseDouble(campo.getText().trim());
    }

    /**
     * Ejecuta un cálculo y muestra el resultado o el mensaje de error en la etiqueta.
     */
    private void mostrar(JLabel etiqueta, DoubleSupplier calculo) {
        try {
            double resultado = calculo.getAsDouble();
            etiqueta.setForeground(Color.BLACK);
            etiqueta.setText("Resultado: " + resultado);
        } catch (NumberFormatException ex) {
            etiqueta.setForeground(Color.RED);
            etiqueta.setText("Error: escribe números válidos");
        } catch (ArithmeticException | IllegalArgumentException ex) {
            etiqueta.setForeground(Color.RED);
            etiqueta.setText(ex.getMessage()); // ej. "no se puede dividir entre cero"
        }
    }

    /** Punto de entrada del programa. */
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new Main().setVisible(true));
    }
}