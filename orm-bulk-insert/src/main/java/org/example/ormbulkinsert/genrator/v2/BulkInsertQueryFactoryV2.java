package org.example.ormbulkinsert.genrator.v2;

import jakarta.persistence.Table;
import org.example.ormbulkinsert.domain.BulkInsertDto;
import org.example.ormbulkinsert.genrator.BulkInsertGenerator;
import org.example.ormbulkinsert.genrator.BulkInsertGeneratorFactory;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class BulkInsertQueryFactoryV2 {
  private static final String INSERT_QUERY_TEMPLATE = "INSERT INTO %s (%s) VALUES %s;";

  public static String bulkInsertQuery(List<?> entities) throws IllegalAccessException {
    if (entities == null || entities.isEmpty()) {
      throw new IllegalArgumentException("Object list is empty");
    }

    Class<?> clazz = entities.get(0).getClass();
    Table table = clazz.getAnnotation(Table.class);
    String tableName = table.name();

    List<BulkInsertDto> dtos = new ArrayList<>();
    for (Field field : clazz.getDeclaredFields()) {
      field.setAccessible(true);
      BulkInsertGeneratorV2 bulkInsertGenerator = BulkInsertGeneratorFactoryV2.getBulkInsertGenerator(field);
    }

    List<String> columns = generateColumns(clazz);
    List<String> values = generateValues(clazz, entities);

    String columnPart = String.join(", ", columns);
    String valuePart = String.join(", ", values);

    return String.format(INSERT_QUERY_TEMPLATE, tableName, columnPart, valuePart);
  }

  private static List<String> generateColumns(Class<?> clazz) {
    List<String> columnList = new ArrayList<>();

    for (Field field : clazz.getDeclaredFields()) {
      field.setAccessible(true);
      BulkInsertGeneratorV2 bulkInsertGenerator = BulkInsertGeneratorFactoryV2.getBulkInsertGenerator(field);
      List<String> columnNames = bulkInsertGenerator.getColumns(field);
      columnList.addAll(columnNames);
    }

    return columnList;
  }

  private static List<String> generateValues(Class<?> clazz, List<?> entities) throws IllegalAccessException {
    List<String> valuesList = new ArrayList<>();

    for (Object entity : entities) {
      List<String> values = new ArrayList<>();
      for (Field field : clazz.getDeclaredFields()) {
        field.setAccessible(true);
        BulkInsertGenerator bulkInsertGenerator = BulkInsertGeneratorFactory.getBulkInsertGenerator(field);
        List<String> data = bulkInsertGenerator.getValues(field, entity);
        values.addAll(data);
      }

      valuesList.add("(" + String.join(", ", values) + ")");
    }

    return valuesList;
  }

}
