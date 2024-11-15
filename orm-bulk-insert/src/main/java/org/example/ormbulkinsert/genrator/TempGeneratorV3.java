package org.example.ormbulkinsert.genrator;

import jakarta.persistence.Column;
import jakarta.persistence.Embedded;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

public class TempGeneratorV3 {

  public static String generateBulkInsertStatement(List<?> objects) throws IllegalAccessException {
    if (objects == null || objects.isEmpty()) {
      throw new IllegalArgumentException("Object list is null or empty");
    }

    Class<?> clazz = objects.get(0).getClass();
    String tableName = clazz.getSimpleName().toLowerCase();

    // 컬럼 이름 추출
    List<String> columns = new ArrayList<>();
    for (Field field : clazz.getDeclaredFields()) {
      field.setAccessible(true);
      if (field.isAnnotationPresent(Id.class) && field.isAnnotationPresent(GeneratedValue.class)) {
        continue; // 자동 생성 ID는 제외
      }

      if (field.isAnnotationPresent(Column.class)) {
        Column column = field.getAnnotation(Column.class);
        columns.add(column.name());
      } else if (field.isAnnotationPresent(Embedded.class)) {
        Class<?> embeddedClass = field.getType();
        for (Field embeddedField : embeddedClass.getDeclaredFields()) {
          embeddedField.setAccessible(true);
          if (embeddedField.isAnnotationPresent(Column.class)) {
            Column embeddedColumn = embeddedField.getAnnotation(Column.class);
            columns.add(embeddedColumn.name());
          }
        }
      }
    }

    // VALUES 부분 생성
    List<String> valuesList = new ArrayList<>();
    for (Object obj : objects) {
      List<String> values = new ArrayList<>();
      for (Field field : clazz.getDeclaredFields()) {
        field.setAccessible(true);
        if (field.isAnnotationPresent(Id.class) && field.isAnnotationPresent(GeneratedValue.class)) {
          continue; // 자동 생성 ID는 제외
        }

        if (field.isAnnotationPresent(Column.class)) {
          Object value = field.get(obj);
          values.add(formatValue(value));
        } else if (field.isAnnotationPresent(Embedded.class)) {
          Object embeddedObject = field.get(obj);
          if (embeddedObject != null) {
            Class<?> embeddedClass = field.getType();
            for (Field embeddedField : embeddedClass.getDeclaredFields()) {
              embeddedField.setAccessible(true);
              if (embeddedField.isAnnotationPresent(Column.class)) {
                Object embeddedValue = embeddedField.get(embeddedObject);
                values.add(formatValue(embeddedValue));
              }
            }
          }
        }
      }
      valuesList.add("(" + String.join(", ", values) + ")");
    }

    // 최종 INSERT 문 생성
    String columnPart = String.join(", ", columns);
    String valuePart = String.join(", ", valuesList);
    return String.format("INSERT INTO %s (%s) VALUES %s;", tableName, columnPart, valuePart);
  }

  private static String formatValue(Object value) {
    if (value == null) {
      return "NULL";
    } else if (value instanceof Number) {
      return value.toString(); // 숫자는 그대로 반환
    } else {
      return "'" + value + "'"; // 문자열은 따옴표로 감쌈
    }
  }
}
// interface 분리
// Dynamic Insert/Update 처리 -> null 에 따라 쿼리에 포함할지 말지
// Embedded 처리
// enum, number 타입 처리
// null 처리