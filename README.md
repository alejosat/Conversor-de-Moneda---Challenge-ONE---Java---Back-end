# Conversor de Moneda

Este proyecto es una aplicación simple de conversión de monedas desarrollada en Java. Utiliza varias tecnologías y librerías para obtener tasas de cambio en tiempo real desde la API de Exchange Rate, manejar la estructura de JSON con Gson, y mostrar los resultados en una interfaz web usando Thymeleaf y un servidor HTTP nativo.

## Tecnologías utilizadas

- **Java 17**: Lenguaje principal del proyecto.
- **Maven**: Sistema de gestión de dependencias y compilación.
- **Gson**: Librería para el manejo de JSON.
- **Exchange Rate API**: API utilizada para obtener las tasas de cambio en tiempo real.
- **Thymeleaf**: Motor de plantillas utilizado para la renderización de vistas HTML.
- **HttpServer**: Servidor HTTP ligero para manejar solicitudes GET y POST.
- **Bootstrap 5**: Framework CSS para el diseño responsivo y atractivo de la interfaz.

## Requisitos previos

Asegúrate de tener instalados los siguientes componentes antes de ejecutar la aplicación:

- **Java 17 o superior**
- **Maven**
- **Conexión a internet** (para consumir la API de Exchange Rate)

## Instalación

1. Clona este repositorio en tu máquina local:

```bash
  git clone https://github.com/tu-usuario/conversor-de-moneda.git
```

3. Navega al directorio del proyecto:

```bash
  cd conversor-de-moneda
```

3. Compila y construye el proyecto utilizando Maven:

```bash
  mvn clean install
```

4. Ejecución

Para iniciar la aplicación:

Ejecuta el siguiente comando:

```bash
  mvn exec:java -Dexec.mainClass="com.ConversorDeMoneda.Main"
```
ó en el Editor IntelliJ IDEA ejecuta el archivo `Main.java` para correr la aplicación el Local a través de la URL:

Abre tu navegador y visita `http://localhost:8000/` para acceder a la aplicación.

## Funcionalidades
. Formulario de conversión: El usuario puede seleccionar la moneda de origen y destino, así como ingresar el monto que desea convertir.
. Resultado de la conversión: La tasa de conversión se obtiene de la API de Exchange Rate en tiempo real y se muestra el resultado en la misma página.
. Diseño responsive: Utiliza Bootstrap 5 para garantizar que el formulario y los resultados se vean bien en diferentes dispositivos.

## Estructura del Proyecto
. /src/main/java: Contiene todo el código fuente de la aplicación.
    . controller: Maneja el flujo del programa y la lógica de la conversión.
    . model: Representa el modelo de datos, incluyendo las tasas de cambio.
    . server: Implementación del servidor HTTP que maneja las solicitudes web.
    . service: Servicio que consume la API de Exchange Rate y realiza la conversión.
. /src/main/resources: Archivos de recursos, como plantillas Thymeleaf y archivos de configuración.

## Dependencias
Este proyecto utiliza las siguientes dependencias de Maven:

```xml
  <dependencies>
      <dependency>
          <groupId>com.google.code.gson</groupId>
          <artifactId>gson</artifactId>
          <version>2.8.9</version>
      </dependency>
      <dependency>
          <groupId>org.thymeleaf</groupId>
          <artifactId>thymeleaf</artifactId>
          <version>3.0.12.RELEASE</version>
      </dependency>
  </dependencies>
```

## API
Se utiliza Exchange Rate API para obtener las tasas de cambio. Puedes encontrar más detalles en su [documentación oficial](https://www.exchangerate-api.com/).
