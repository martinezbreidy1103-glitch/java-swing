/**
 * Realiza las operaciones básicas.
 */
public class Calculadora {

    // Suma dos números.
    public double sumar(double a, double b) {
        return a + b;
    }

    /** Resta b de a. */
    public double restar(double a, double b) {
        return a - b;
    }

    /** Multiplica dos números. */
    public double multiplicar(double a, double b) {
        return a * b;
    }

    /**
     * Divide a entre b.
     * @throws ArithmeticException si b es cero
     */
    public double dividir(double a, double b) {
        // Validación pedida en la tarea: evitar división entre cero
        if (b == 0) {
            throw new ArithmeticException("Error: no se puede dividir entre cero");
        }
        return a / b;
    }
}
