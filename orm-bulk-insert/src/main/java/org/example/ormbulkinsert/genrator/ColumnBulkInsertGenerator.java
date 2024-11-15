package org.example.ormbulkinsert.genrator;

import jakarta.persistence.Column;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ColumnBulkInsertGenerator implements BulkInsertGenerator {

  @Override
  public List<Field> getFields(Field field, Object entity) {
    if (!field.isAnnotationPresent(Column.class)) {
      return Collections.emptyList();
    }

    return Collections.singletonList(field);
  }

  @Override
  public List<String> getColumns(Field field) {
    if (!field.isAnnotationPresent(Column.class)) {
      return Collections.emptyList();
    }

    Column column = field.getAnnotation(Column.class);
    return Collections.singletonList(column.name());
  }

  @Override
  public List<String> getValues(Field field, Object entity) throws IllegalAccessException {
    if (!field.isAnnotationPresent(Column.class)) {
      return Collections.emptyList();
    }

    Object value = field.get(entity);
    return Collections.singletonList(formatValue(value));
  }
}
