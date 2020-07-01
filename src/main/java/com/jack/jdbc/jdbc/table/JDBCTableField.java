package com.jack.jdbc.jdbc.table;

import com.jack.jdbc.jdbc.annonation.JDBCField;
import org.apache.commons.lang3.StringUtils;

import java.lang.reflect.Field;

public class JDBCTableField {

    private Field field;

    private String columnName;

    public JDBCTableField(Field field) {
        this.columnName = columnName(field);
        this.field = field;
    }

    private String columnName(Field field) {
        JDBCField fieldAnnotation = field.getAnnotation(JDBCField.class);
        String fieldName = null;
        if (fieldAnnotation != null) {
            fieldName = fieldAnnotation.value();
        }

        if (StringUtils.isBlank(fieldName)) {
            fieldName = field.getName();
        }
        return fieldName;
    }

    public String getColumnName() {
        return columnName;
    }

    public Object readField(Object object)  {
        try {
            this.field.setAccessible(true);
            return this.field.get(object);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }
}
