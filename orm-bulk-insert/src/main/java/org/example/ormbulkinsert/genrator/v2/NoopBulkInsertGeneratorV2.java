package org.example.ormbulkinsert.genrator.v2;

import org.example.ormbulkinsert.genrator.BulkInsertGenerator;

import java.lang.reflect.Field;
import java.util.Collections;
import java.util.List;

public class NoopBulkInsertGeneratorV2 implements BulkInsertGeneratorV2 {

  @Override
  public List<String> getColumns(Field field) {
    return Collections.emptyList();
  }

  @Override
  public List<String> getValues(Field field, Object entity) throws IllegalAccessException {
    return Collections.emptyList();
  }
}
