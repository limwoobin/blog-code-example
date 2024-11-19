package org.example.ormbulkinsert.genrator.v2;

import org.example.ormbulkinsert.domain.BulkInsertDto;

import java.lang.reflect.Field;
import java.util.List;

public interface BulkInsertGeneratorV2 {

  List<String> getColumns(Field field);

  List<String> getValues(Field field, Object entity) throws IllegalAccessException;

//  List<BulkInsertDto> getColumnsV2(Field field);
//
//  List<BulkInsertDto> getValuesV2(Field field, Object entity) throws IllegalAccessException;

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
