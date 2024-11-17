package org.example.ormbulkinsert.genrator;

import java.lang.reflect.Field;
import java.util.Collections;
import java.util.List;

public class NoopBulkInsertGenerator implements BulkInsertGenerator {

  @Override
  public List<String> getColumns(Field field) {
    return Collections.emptyList();
  }

  @Override
  public List<String> getValues(Field field, Object entity) throws IllegalAccessException {
    return Collections.emptyList();
  }
}
