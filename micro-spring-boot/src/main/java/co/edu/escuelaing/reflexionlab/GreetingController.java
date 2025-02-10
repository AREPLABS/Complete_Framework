package co.edu.escuelaing.reflexionlab;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

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
    try {
      byte[] encoded = Files.readAllBytes(
        Paths.get("src/main/resources/static/index.html")
      );
      return new String(encoded);
    } catch (IOException e) {
      e.printStackTrace();
      return "Error al leer el archivo index.html";
    }
  }
}
