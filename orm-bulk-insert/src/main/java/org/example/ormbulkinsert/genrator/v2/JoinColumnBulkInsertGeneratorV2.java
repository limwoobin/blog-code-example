package org.example.ormbulkinsert.genrator.v2;

import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import org.example.ormbulkinsert.genrator.BulkInsertGenerator;

import java.lang.reflect.Field;
import java.util.Collections;
import java.util.List;

public class JoinColumnBulkInsertGeneratorV2 implements BulkInsertGeneratorV2 {

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
