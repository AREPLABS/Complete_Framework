package co.edu.escuelaing.reflexionlab;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Method;
import java.net.Socket;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

public class RequestHandler implements Runnable {

  private final Socket clientSocket;
  private final Object[] controllerInstances;

  public RequestHandler(Socket clientSocket, Object[] controllerInstances) {
    this.clientSocket = clientSocket;
    this.controllerInstances = controllerInstances;
  }

  @Override
  public void run() {
    try (
      BufferedReader in = new BufferedReader(
        new InputStreamReader(clientSocket.getInputStream())
      );
      OutputStream out = clientSocket.getOutputStream()
    ) {
      String requestLine = in.readLine();
      if (requestLine != null && requestLine.startsWith("GET")) {
        String uri = requestLine.split(" ")[1];
        String response = handleGetRequest(uri);
        sendResponse(out, response);
      }
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  private String handleGetRequest(String uri) {
    try {
      String query = uri.contains("?") ? uri.split("\\?")[1] : "";
      Map<String, String> queryParams = parseQueryParams(query); // Extraer parámetros

      for (Object controllerInstance : controllerInstances) {
        Method[] methods = controllerInstance.getClass().getDeclaredMethods();

        for (Method method : methods) {
          if (method.isAnnotationPresent(GetMapping.class)) {
            GetMapping mapping = method.getAnnotation(GetMapping.class);

            for (String path : mapping.value()) {
              if (uri.startsWith(path)) {
                Object[] args = new Object[method.getParameterCount()];

                for (int i = 0; i < method.getParameterCount(); i++) {
                  if (
                    method
                      .getParameters()[i].isAnnotationPresent(
                        RequestParam.class
                      )
                  ) {
                    RequestParam paramAnnotation = method
                      .getParameters()[i].getAnnotation(RequestParam.class);
                    String paramName = paramAnnotation.value();
                    String defaultValue = paramAnnotation.defaultValue();

                    // Obtener el valor del parámetro desde la URL o usar el valor por defecto
                    args[i] = queryParams.getOrDefault(paramName, defaultValue);
                  }
                }

                return (String) method.invoke(controllerInstance, args);
              }
            }
          }
        }
      }
      return "404 Not Found";
    } catch (Exception e) {
      e.printStackTrace();
      return "500 Internal Server Error";
    }
  }

  // Método para extraer los parámetros de la URL y almacenarlos en un mapa
  private Map<String, String> parseQueryParams(String query)
    throws UnsupportedEncodingException {
    Map<String, String> queryParams = new HashMap<>();
    if (!query.isEmpty()) {
      String[] pairs = query.split("&");
      for (String pair : pairs) {
        String[] keyValue = pair.split("=");
        String key = URLDecoder.decode(keyValue[0], "UTF-8");
        String value = keyValue.length > 1
          ? URLDecoder.decode(keyValue[1], "UTF-8")
          : "";
        queryParams.put(key, value);
      }
    }
    return queryParams;
  }

  private void sendResponse(OutputStream out, String response)
    throws IOException {
    String statusLine = response.startsWith("404")
      ? "HTTP/1.1 404 Not Found"
      : response.startsWith("500")
        ? "HTTP/1.1 500 Internal Server Error"
        : "HTTP/1.1 200 OK";

    out.write(
      (
        statusLine +
        "\r\n" +
        "Content-Type: text/plain\r\n" +
        "Content-Length: " +
        response.length() +
        "\r\n" +
        "\r\n" +
        response
      ).getBytes(StandardCharsets.UTF_8)
    );
    out.flush();
  }
}
