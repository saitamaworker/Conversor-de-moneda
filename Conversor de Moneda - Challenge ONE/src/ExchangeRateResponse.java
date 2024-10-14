import java.util.Map;

// Clase que representa la respuesta de la API de tasas de cambio
public class ExchangeRateResponse {
    private String base_code;  // Campo base_code del JSON
    private Map<String, Double> conversion_rates;  // Campo conversion_rates del JSON

    // Getters y Setters
    public String getBaseCode() {
        return base_code;
    }

    public void setBaseCode(String base_code) {
        this.base_code = base_code;
    }

    public Map<String, Double> getConversionRates() {
        return conversion_rates;
    }

    public void setConversionRates(Map<String, Double> conversion_rates) {
        this.conversion_rates = conversion_rates;
    }
}
