package com.jack.jdbc.jdbc.table;

import com.jack.jdbc.jdbc.annonation.JDBCTable;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.reflect.FieldUtils;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 描述PO模型
 */
public class JDBCTableEntity {

    private Class<?> entityClazz;

    private String tableName;

    private List<JDBCTableField> fields;

    public JDBCTableEntity(Class<?> clazz) {
        this.entityClazz = clazz;
        this.tableName = tableName(clazz);
        this.fields = fields(clazz);

        System.out.println("xx");
    }

    private String tableName(Class<?> clazz) {
        JDBCTable tableAnnotation = clazz.getAnnotation(JDBCTable.class);

        String tableName = null;
        if (tableAnnotation != null) {
            tableName = tableAnnotation.value();
        }

        if (StringUtils.isBlank(tableName)) {
            tableName = clazz.getSimpleName();
        }
        return tableName;
    }

    private List<JDBCTableField> fields(Class<?> clazz) {
        List<Field> fields = FieldUtils.getAllFieldsList(clazz);
        return fields.stream().map(JDBCTableField::new).collect(Collectors.toList());
    }

    public Class<?> getEntityClazz() {
        return entityClazz;
    }

    public String getTableName() {
        return tableName;
    }

    public List<JDBCTableField> getFields() {
        return fields;
    }

    public String selectSql() {
        String s = fields.stream().map(JDBCTableField::getColumnName).collect(Collectors.joining(","));
        return String.format("SELECT %s FROM %s", s, this.tableName);
    }

    public String insertSql(Object object) {

        List<String> columnNames = new ArrayList<>(this.fields.size());
        List<String> values = new ArrayList<>(this.fields.size());

        for (JDBCTableField field : this.fields) {
            columnNames.add(field.getColumnName());
            Object value = field.readField(object);
            if (value instanceof Number) {
                values.add(field.readField(object).toString());
            } else {
                values.add("'" + field.readField(object).toString() + "'");
            }

        }

        return String.format("INSERT INTO %s(%s) VALUES(%s)", this.tableName,
                String.join(",", columnNames),
                String.join(",", values)
                );
    }
}
