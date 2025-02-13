package co.edu.escuelaing.reflexionlab;

import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

@RestController
public class GreetingController {

  private static final String template = "Probando / greeting con, %s!";

  @GetMapping({ "/greeting" })
  public String greeting(
    @RequestParam(value = "name", defaultValue = "World") String name
  ) {
    return String.format(template, name);
  }

  @GetMapping("/")
  public String redirectToIndex() {
    return (
      "HTTP/1.1 302 Found\r\n" +
      "Location: /index.html\r\n" +
      "Content-Length: 0\r\n" + // No hay cuerpo en la respuesta de redirección
      "\r\n"
    );
  }

  @GetMapping("/index.html")
  public String getIndex() {
    try (
      InputStream inputStream = getClass()
        .getClassLoader()
        .getResourceAsStream("static/index.html")
    ) {
      if (inputStream == null) {
        return (
          "HTTP/1.1 404 Not Found\r\n" +
          "Content-Type: text/plain\r\n" +
          "Content-Length: 30\r\n" +
          "\r\n" +
          "Error: index.html no encontrado"
        );
      }
      Scanner scanner = new Scanner(inputStream, StandardCharsets.UTF_8.name())
        .useDelimiter("\\A");
      String responseBody = scanner.hasNext() ? scanner.next() : "";

      return (
        "HTTP/1.1 200 OK\r\n" +
        "Content-Type: text/html; charset=UTF-8\r\n" +
        "Content-Length: " +
        responseBody.length() +
        "\r\n" +
        "\r\n" +
        responseBody
      );
    } catch (Exception e) {
      e.printStackTrace();
      return (
        "HTTP/1.1 500 Internal Server Error\r\n" +
        "Content-Type: text/plain\r\n" +
        "Content-Length: 30\r\n" +
        "\r\n" +
        "Error al leer index.html"
      );
    }
  }

  @GetMapping("/style.css")
  public String getStyles() {
    try (
      InputStream inputStream = getClass()
        .getClassLoader()
        .getResourceAsStream("static/style.css")
    ) {
      if (inputStream == null) {
        return (
          "HTTP/1.1 404 Not Found\r\n" +
          "Content-Type: text/plain\r\n" +
          "Content-Length: 30\r\n" +
          "\r\n" +
          "Error: style.css no encontrado"
        );
      }
      Scanner scanner = new Scanner(inputStream, StandardCharsets.UTF_8.name())
        .useDelimiter("\\A");
      String responseBody = scanner.hasNext() ? scanner.next() : "";

      return (
        "HTTP/1.1 200 OK\r\n" +
        "Content-Type: text/css; charset=UTF-8\r\n" +
        "Content-Length: " +
        responseBody.length() +
        "\r\n" +
        "\r\n" +
        responseBody
      );
    } catch (Exception e) {
      e.printStackTrace();
      return (
        "HTTP/1.1 500 Internal Server Error\r\n" +
        "Content-Type: text/plain\r\n" +
        "Content-Length: 30\r\n" +
        "\r\n" +
        "Error al leer style.css"
      );
    }
  }

  @GetMapping("/main.js")
  public String getMainJs() {
    try (
      InputStream inputStream = getClass()
        .getClassLoader()
        .getResourceAsStream("static/main.js")
    ) {
      if (inputStream == null) {
        return (
          "HTTP/1.1 404 Not Found\r\n" +
          "Content-Type: text/plain\r\n" +
          "Content-Length: 30\r\n" +
          "\r\n" +
          "Error: main.js no encontrado"
        );
      }
      Scanner scanner = new Scanner(inputStream, StandardCharsets.UTF_8.name())
        .useDelimiter("\\A");
      String responseBody = scanner.hasNext() ? scanner.next() : "";

      return (
        "HTTP/1.1 200 OK\r\n" +
        "Content-Type: application/javascript; charset=UTF-8\r\n" +
        "Content-Length: " +
        responseBody.length() +
        "\r\n" +
        "\r\n" +
        responseBody
      );
    } catch (Exception e) {
      e.printStackTrace();
      return "HTTP/1.1 500 Internal Server Error\r\n\r\n";
    }
  }
}
