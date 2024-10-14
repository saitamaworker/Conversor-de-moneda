import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Scanner;

public class CurrencyConverter {
    private static final String API_URL = "https://v6.exchangerate-api.com/v6/a719c482689f84266f96d33b/latest/USD";

    public static void main(String[] args) throws IOException, InterruptedException {
        // Se Crea el escáner
        Scanner lectura = new Scanner(System.in);
        // Se Crea cliente HTTP
        HttpClient client = HttpClient.newHttpClient();
        // Se Crea bucle
        boolean continuar = true;
        while (continuar) {
            System.out.println("Ingresa la cantidad en USD: ");
            double montoUSD = lectura.nextDouble();  // Leer el monto en USD

            // Crear solicitud
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(API_URL))
                    .build();

            // Enviar la solicitud y obtener la respuesta
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

            // Parsear la respuesta JSON utilizando JsonParser
            String jsonResponse = response.body();
            JsonObject jsonObject = JsonParser.parseString(jsonResponse).getAsJsonObject();
            JsonObject conversionRates = jsonObject.getAsJsonObject("conversion_rates");

            if (conversionRates == null) {
                System.out.println("Error: El campo 'conversion_rates' no está presente en la respuesta o es null.");
                return;
            }

            // Preguntar al usuario qué moneda desea convertir
            System.out.println("Selecciona la moneda a la que deseas convertir:");
            System.out.println("1. ARS (Pesos Argentinos)");
            System.out.println("2. BOB (Bolivianos)");
            System.out.println("3. BRL (Reales Brasileños)");

            int opcion = lectura.nextInt();

            switch (opcion) {
                case 1: // Convertir a ARS
                    if (conversionRates.has("ARS")) {
                        double usdToArs = conversionRates.get("ARS").getAsDouble();
                        double montoARS = montoUSD * usdToArs;
                        System.out.println(montoUSD + " USD son " + montoARS + " ARS");
                    } else {
                        System.out.println("Error: No se pudo encontrar la tasa de cambio para ARS.");
                    }
                    break;
                case 2: // Convertir a BOB
                    if (conversionRates.has("BOB")) {
                        double usdToBob = conversionRates.get("BOB").getAsDouble();
                        double montoBOB = montoUSD * usdToBob;
                        System.out.println(montoUSD + " USD son " + montoBOB + " BOB");
                    } else {
                        System.out.println("Error: No se pudo encontrar la tasa de cambio para BOB.");
                    }
                    break;
                case 3: // Convertir a BRL
                    if (conversionRates.has("BRL")) {
                        double usdToBrl = conversionRates.get("BRL").getAsDouble();
                        double montoBRL = montoUSD * usdToBrl;
                        System.out.println(montoUSD + " USD son " + montoBRL + " BRL");
                    } else {
                        System.out.println("Error: No se pudo encontrar la tasa de cambio para BRL.");
                    }
                    break;
                default:
                    System.out.println("Opción no válida. Inténtalo de nuevo.");
                    break;
            }

            // Preguntar si el usuario desea realizar otra conversión
            System.out.println("¿Deseas realizar otra conversión? (s/n): ");
            String respuesta = lectura.next();

            if (!respuesta.equalsIgnoreCase("s")) {
                continuar = false;
                System.out.println("Gracias por usar el conversor de monedas. ¡Hasta luego!");
            }
        }
    }
}
