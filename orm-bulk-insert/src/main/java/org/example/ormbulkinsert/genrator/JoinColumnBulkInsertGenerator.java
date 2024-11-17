package org.example.ormbulkinsert.genrator;

import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Collections;
import java.util.List;

public class JoinColumnBulkInsertGenerator implements BulkInsertGenerator {

  @Override
  public List<String> getColumns(Field field) {
    if (!field.isAnnotationPresent(JoinColumn.class)) {
      return Collections.emptyList();
    }

    JoinColumn joinColumn = field.getAnnotation(JoinColumn.class);
    return Collections.singletonList(joinColumn.name());
  }

  @Override
  public List<String> getValues(Field field, Object entity) throws IllegalAccessException {
    if (!field.isAnnotationPresent(JoinColumn.class)) {
      return Collections.emptyList();
    }

    Object joinColumnObject = field.get(entity);
    Class<?> joinColumnClass = field.getType();
    Object value = null;

    for (Field joinColumnField : joinColumnClass.getDeclaredFields()) {
      joinColumnField.setAccessible(true);

      if (joinColumnField.isAnnotationPresent(Id.class)) {
        Object joinId = joinColumnField.get(joinColumnObject);
        value = joinId;
        break;
      }
    }

    return Collections.singletonList(formatValue(value));
  }
}
