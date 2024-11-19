package org.example.ormbulkinsert.genrator.v2;

import jakarta.persistence.Column;
import org.example.ormbulkinsert.genrator.BulkInsertGenerator;

import java.lang.reflect.Field;
import java.util.Collections;
import java.util.List;

public class ColumnBulkInsertGeneratorV2 implements BulkInsertGeneratorV2 {

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
