# Conversor de Monedas

Este proyecto es un conversor de monedas simple en Java que permite convertir entre diferentes monedas utilizando datos de tasas de cambio actualizados desde una API externa. El programa proporciona un menú interactivo que permite al usuario elegir las monedas de origen y destino, así como la cantidad que desea convertir.

## Características

- Conversión de monedas en tiempo real utilizando datos de la API de ExchangeRate-API.
- Soporte para las siguientes monedas:
  - Peso Argentino (ARS)
  - Boliviano (BOB)
  - Real Brasileño (BRL)
  - Peso Chileno (CLP)
  - Peso Colombiano (COP)
  - Dólar Estadounidense (USD)
- Manejo de entradas no válidas y errores de conexión a la API.

## Requisitos

- **Java 11** o superior
- **Gson Library** para trabajar con JSON
- Conexión a internet para obtener las tasas de cambio.

## Configuración

1. **Clona el repositorio** o copia el código en tu entorno de desarrollo.
2. **Configura el proyecto** con la librería `Gson`. Puedes agregarla como una dependencia de tu proyecto o incluir el archivo JAR correspondiente.
3. **Modifica la clave de API**:
   - Cambia `API_KEY` con tu propia clave de API de ExchangeRate-API. Puedes registrarte y obtener una clave [aquí](https://www.exchangerate-api.com).

## Ejecución del Programa

1. Una vez configurado el proyecto, abre una terminal o consola en el directorio del proyecto.
2. Ejecuta el programa con el siguiente comando:
   ```bash
   javac ConversorMoneda.java
   java ConversorMoneda
3. El programa te mostrará un menú donde podrás seleccionar la moneda de origen, la moneda de destino y la cantidad a convertir.
4. El resultado de la conversión se mostrará en la consola.

## Ejemplo de uso

```bash
Conversor de Monedas
1. Convertir Monedas
2. Salir
Elige una opción: 1

Monedas disponibles:
1. Peso Argentino (ARS)
2. Boliviano (BOB)
3. Real Brasileño (BRL)
4. Peso Chileno (CLP)
5. Peso Colombiano (COP)
6. Dólar Estadounidense (USD)

Elige la moneda de origen: 1
Elige la moneda de destino: 1
Introduce la cantidad a convertir: 100

Resultado: 100.0 monedas de la moneda origen equivalen a 100.0 monedas de la moneda destino.
