package org.example.ormbulkinsert.genrator;

import jakarta.persistence.Column;
import jakarta.persistence.Embedded;
import jakarta.persistence.JoinColumn;

import java.lang.reflect.Field;

public class BulkInsertGeneratorFactory {
  private static final BulkInsertGenerator noopBulkInsertGenerator = new NoopBulkInsertGenerator();
  private static final BulkInsertGenerator columnBulkInsertGenerator = new ColumnBulkInsertGenerator();
  private static final BulkInsertGenerator embeddedBulkInsertGenerator = new EmbeddedBulkInsertGenerator();
  private static final BulkInsertGenerator joinColumnBulkInsertGenerator = new JoinColumnBulkInsertGenerator();

  public static BulkInsertGenerator getBulkInsertGenerator(Field field) {
    if (field.isAnnotationPresent(Column.class)) {
      return columnBulkInsertGenerator;
    } else if (field.isAnnotationPresent(Embedded.class)) {
      return embeddedBulkInsertGenerator;
    } else if (field.isAnnotationPresent(JoinColumn.class)) {
      return joinColumnBulkInsertGenerator;
    }

    return noopBulkInsertGenerator;
  }
}
