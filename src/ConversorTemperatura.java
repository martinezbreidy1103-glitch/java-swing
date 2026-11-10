/**
 * Convierte temperaturas entre Fahrenheit y Celsius.
 */
public class ConversorTemperatura {

    /** °C = (°F - 32) × 5/9 */
    public double fahrenheitACelsius(double fahrenheit) {
        return (fahrenheit - 32) * 5 / 9;
    }

    /** °F = (°C × 9/5) + 32 */
    public double celsiusAFahrenheit(double celsius) {
        return (celsius * 9 / 5) + 32;
    }
}