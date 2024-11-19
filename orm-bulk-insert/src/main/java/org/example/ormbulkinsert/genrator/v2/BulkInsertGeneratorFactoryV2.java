package org.example.ormbulkinsert.genrator.v2;

import jakarta.persistence.Column;
import jakarta.persistence.Embedded;
import jakarta.persistence.JoinColumn;
import org.example.ormbulkinsert.genrator.*;

import java.lang.reflect.Field;

public class BulkInsertGeneratorFactoryV2 {
  private static final BulkInsertGeneratorV2 noopBulkInsertGenerator = new NoopBulkInsertGeneratorV2();
  private static final BulkInsertGeneratorV2 columnBulkInsertGenerator = new ColumnBulkInsertGeneratorV2();
  private static final BulkInsertGeneratorV2 embeddedBulkInsertGenerator = new EmbeddedBulkInsertGeneratorV2();
  private static final BulkInsertGeneratorV2 joinColumnBulkInsertGenerator = new JoinColumnBulkInsertGeneratorV2();

  public static BulkInsertGeneratorV2 getBulkInsertGenerator(Field field) {
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
