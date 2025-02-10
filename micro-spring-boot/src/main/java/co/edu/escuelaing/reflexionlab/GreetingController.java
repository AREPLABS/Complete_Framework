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
  public String getFirst() {
    System.out.println(
      getClass().getClassLoader().getResource("static/index.html")
    );
    try (
      InputStream inputStream = getClass()
        .getClassLoader()
        .getResourceAsStream("static/index.html")
    ) {
      if (inputStream == null) {
        return "Error: index.html no encontrado";
      }
      Scanner scanner = new Scanner(inputStream, StandardCharsets.UTF_8.name())
        .useDelimiter("\\A");
      return scanner.hasNext() ? scanner.next() : "";
    } catch (Exception e) {
      e.printStackTrace();
      return "Error al leer index.html";
    }
  }
}
