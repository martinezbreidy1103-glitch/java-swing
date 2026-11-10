/**
 * Calcula raíz cuadrada y exponente de un número.
 */
public class Raizcuadrada_Exponente {

    /**
     * Raíz cuadrada de un número.
     * @throws IllegalArgumentException si el número es negativo
     */
    public double raizCuadrada(double numero) {
        if (numero < 0) {
            throw new IllegalArgumentException("Error: no existe raíz cuadrada de un negativo");
        }
        return Math.sqrt(numero);
    }

    /** Eleva la base al exponente indicado (base^exponente). */
    public double exponente(double base, double exponente) {
        return Math.pow(base, exponente);
    }
}