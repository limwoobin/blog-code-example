package org.example.ormbulkinsert.genrator;

import jakarta.persistence.Column;
import jakarta.persistence.Embedded;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class EmbeddedBulkInsertGenerator implements BulkInsertGenerator {

  @Override
  public List<String> getColumns(Field field) {
    if (!field.isAnnotationPresent(Embedded.class)) {
      return Collections.emptyList();
    }

    List<String> columns = new ArrayList<>();
    Class<?> embeddedClass = field.getType();
    for (Field embeddedField : embeddedClass.getDeclaredFields()) {
      embeddedField.setAccessible(true);
      if (embeddedField.isAnnotationPresent(Column.class)) {
        Column embeddedColumn = embeddedField.getAnnotation(Column.class);
        columns.add(embeddedColumn.name());
      }
    }

    return columns;
  }

  @Override
  public List<String> getValues(Field field, Object entity) throws IllegalAccessException {
    if (!field.isAnnotationPresent(Embedded.class)) {
      return Collections.emptyList();
    }

    List<String> values = new ArrayList<>();
    Object embeddedObject = field.get(entity);
    Class<?> embeddedClass = field.getType();
    for (Field embeddedField : embeddedClass.getDeclaredFields()) {
      embeddedField.setAccessible(true);
      if (embeddedField.isAnnotationPresent(Column.class)) {
        Object embeddedValue = embeddedField.get(embeddedObject);
        values.add(formatValue(embeddedValue));
      }
    }

    return values;
  }
}
