package org.example.ormbulkinsert.application;

import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;

public class CustomBeanPropertySqlParameterSource extends BeanPropertySqlParameterSource {

  public CustomBeanPropertySqlParameterSource(Object object) {
    super(object);
  }

  @Override
  public Object getValue(String paramName) throws IllegalArgumentException {
    Object value = super.getValue(paramName);

    if (value instanceof Enum) {
      return ((Enum<?>) value).name();
    }

    return value;
  }
}
