import java.util.Scanner;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.URI;
import java.net.http.HttpResponse;
import java.io.IOException;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public class ConversorMoneda {

    private static final String API_KEY = "cf5bfb16a933e1300817f6f9";
    private static final String URL_API = "https://v6.exchangerate-api.com/v6/" + API_KEY + "/latest/USD";

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            mostrarMenu();

            if (!scanner.hasNextInt()) {
                System.out.println("Entrada no válida. Por favor, ingrese un número.");
                scanner.next();
                continue;
            }

            int opcion = scanner.nextInt();
            if (opcion == 1) {
                realizarConversion();
            } else if (opcion == 2) {
                System.out.println("¡Gracias por usar el Conversor de Monedas!");
                break;
            } else {
                System.out.println("Opción no válida. Intenta de nuevo.");
            }
        }
    }

    private static void mostrarMenu() {
        System.out.println("\nConversor de Monedas");
        System.out.println("1. Convertir Monedas");
        System.out.println("2. Salir");
        System.out.print("Elige una opción: ");
    }

    private static void realizarConversion() {
        Scanner scanner = new Scanner(System.in);

        JsonObject tasasDeCambio = obtenerTasasDeCambio();

        if (tasasDeCambio != null) {
            System.out.println("\nMonedas disponibles:");
            System.out.println("1. Peso Argentino (ARS)");
            System.out.println("2. Boliviano (BOB)");
            System.out.println("3. Real Brasileño (BRL)");
            System.out.println("4. Peso Chileno (CLP)");
            System.out.println("5. Peso Colombiano (COP)");
            System.out.println("6. Dólar Estadounidense (USD)");

            System.out.print("Elige la moneda de origen: ");
            if (!scanner.hasNextInt()) {
                System.out.println("Entrada no válida. Debe ser un número.");
                scanner.next();
                return;
            }
            int monedaOrigen = scanner.nextInt();

            System.out.print("Elige la moneda de destino: ");
            if (!scanner.hasNextInt()) {
                System.out.println("Entrada no válida. Debe ser un número.");
                scanner.next();
                return;
            }
            int monedaDestino = scanner.nextInt();

            double tasaDeCambio = obtenerTasaDeCambio(tasasDeCambio, monedaOrigen, monedaDestino);

            if (tasaDeCambio != -1) {
                System.out.print("Introduce la cantidad a convertir: ");
                if (!scanner.hasNextDouble()) {
                    System.out.println("Entrada no válida. Debe ser un número.");
                    scanner.next();
                    return;
                }
                double cantidad = scanner.nextDouble();
                double resultado = cantidad * tasaDeCambio;
                System.out.println("\nResultado: " + cantidad + " monedas de la moneda origen equivalen a " + resultado + " monedas de la moneda destino.");
            } else {
                System.out.println("Moneda no soportada.");
            }
        } else {
            System.out.println("No se pudo obtener las tasas de cambio.");
        }
    }

    private static JsonObject obtenerTasasDeCambio() {
        try {
            HttpClient cliente = HttpClient.newHttpClient();
            HttpRequest solicitud = HttpRequest.newBuilder()
                    .uri(URI.create(URL_API))
                    .build();
            HttpResponse<String> respuesta = cliente.send(solicitud, HttpResponse.BodyHandlers.ofString());

            if (respuesta.statusCode() == 200) {
                JsonObject json = JsonParser.parseString(respuesta.body()).getAsJsonObject();
                return json.getAsJsonObject("conversion_rates");
            } else {
                System.out.println("Error al obtener las tasas de cambio. Código de estado: " + respuesta.statusCode());
                return null;
            }
        } catch (IOException | InterruptedException e) {
            System.out.println("Error al realizar la solicitud: " + e.getMessage());
            return null;
        }
    }

    private static double obtenerTasaDeCambio(JsonObject tasasDeCambio, int monedaOrigen, int monedaDestino) {
        String[] monedas = {"ARS", "BOB", "BRL", "CLP", "COP", "USD"};

        if (monedaOrigen < 1 || monedaOrigen > 6 || monedaDestino < 1 || monedaDestino > 6) {
            return -1;
        }

        String codigoMonedaOrigen = monedas[monedaOrigen - 1];
        String codigoMonedaDestino = monedas[monedaDestino - 1];

        if (tasasDeCambio.has(codigoMonedaDestino)) {
            double tasaOrigen = tasasDeCambio.get(codigoMonedaOrigen).getAsDouble();
            double tasaDestino = tasasDeCambio.get(codigoMonedaDestino).getAsDouble();
            return tasaDestino / tasaOrigen;
        } else {
            return -1;
        }
    }
}