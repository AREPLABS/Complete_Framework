package co.edu.escuelaing.reflexionlab;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.PARAMETER) // Agrega esta línea
public @interface RequestParam {
  String value();

  String defaultValue() default ""; // Asegúrate de que tenga un valor por defecto
}
