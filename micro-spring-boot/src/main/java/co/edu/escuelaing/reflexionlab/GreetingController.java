package co.edu.escuelaing.reflexionlab;

import java.util.ArrayList;
import java.util.List;

@RestController
public class GreetingController {

  private static final String template = "Hello, %s!";

  @GetMapping({ "/greeting" })
  public String greeting(
    @RequestParam(value = "name", defaultValue = "World") String name
  ) {
    return String.format(template, name);
  }

  @GetMapping("/books")
  public String getBooks() {
    List<String> books = new ArrayList<>();
    books.add("El Quijote");
    books.add("Cien años de soledad");
    books.add("1984");
    books.add("El Principito");

    StringBuilder html = new StringBuilder();
    html.append("<html><head><title>Libros</title></head><body>");
    html.append("<h1>Lista de Libros</h1><ul>");
    for (String book : books) {
      html.append("<li>").append(book).append("</li>");
    }
    html.append("</ul></body></html>");

    return html.toString();
  }
}
