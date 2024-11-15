package org.example.ormbulkinsert.genrator;

import jakarta.persistence.Column;
import jakarta.persistence.Embedded;
import jakarta.persistence.Id;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

public class BulkInsertFactory {
  private final BulkInsertGenerator noopBulkInsertGenerator = new NoopBulkInsertGenerator();
  private final BulkInsertGenerator columnBulkInsertGenerator = new ColumnBulkInsertGenerator();
  private final BulkInsertGenerator embeddedBulkInsertGenerator = new EmbeddedBulkInsertGenerator();

  public String bulkInsertQueryV2(List<?> entities) throws IllegalAccessException {
    if (entities == null || entities.isEmpty()) {
      throw new IllegalArgumentException("Object list is null or empty");
    }

    Class<?> clazz = entities.get(0).getClass();
    String tableName = clazz.getSimpleName().toLowerCase();

    List<String> columnList = new ArrayList<>();
    for (Field field : clazz.getDeclaredFields()) {
      field.setAccessible(true);
      BulkInsertGenerator generator = getGenerator(field);
      List<String> columnNames = generator.getColumns(field);
      columnList.addAll(columnNames);
    }

    List<String> valuesList = new ArrayList<>();
    for (Object entity : entities) {
      List<String> values = new ArrayList<>();
      for (Field field : clazz.getDeclaredFields()) {
        field.setAccessible(true);
        BulkInsertGenerator generator = getGenerator(field);
        List<String> data = generator.getValues(field, entity);
        values.addAll(data);
      }

      valuesList.add("(" + String.join(", ", values) + ")");
    }

    String columnPart = String.join(", ", columnList);
    String valuePart = String.join(", ", valuesList);

    return String.format("INSERT INTO %s (%s) VALUES %s;", tableName, columnPart, valuePart);
  }

  private BulkInsertGenerator getGenerator(Field field) {
    if (field.isAnnotationPresent(Id.class)) {
      return noopBulkInsertGenerator;
    } else if (field.isAnnotationPresent(Column.class)) {
      return columnBulkInsertGenerator;
    } else if (field.isAnnotationPresent(Embedded.class)) {
      return embeddedBulkInsertGenerator;
    }

    return noopBulkInsertGenerator;
  }
}
