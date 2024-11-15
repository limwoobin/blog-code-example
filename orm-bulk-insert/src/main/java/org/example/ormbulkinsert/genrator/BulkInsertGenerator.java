package org.example.ormbulkinsert.genrator;

import java.lang.reflect.Field;
import java.util.List;

public interface BulkInsertGenerator {

  List<Field> getFields(Field field, Object entity) throws IllegalAccessException;

  List<String> getColumns(Field field);

  List<String> getValues(Field field, Object entity) throws IllegalAccessException;

  default String formatValue(Object value) {
    if (value == null) {
      return "NULL";
    } else if (value instanceof Number) {
      return value.toString();
    } else {
      return "'" + value + "'";
    }
  }
}
