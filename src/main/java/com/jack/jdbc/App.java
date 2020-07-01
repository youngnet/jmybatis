package com.jack.jdbc;

import com.jack.jdbc.jdbc.table.JDBCTableEntity;
import org.apache.commons.lang3.reflect.FieldUtils;

import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

public class App {
    public static void main(String[] args) throws IllegalAccessException {
        User user = new User();

        for (Field field : user.getClass().getDeclaredFields()) {
            System.out.println(field.getName());
            field.setAccessible(true);
            System.out.println(field.get(user));
        }

        Type[] types = UserRepository.class.getGenericInterfaces();
        Type type = types[0];
        if (type instanceof ParameterizedType) {
            Type[] argumentTypes = ((ParameterizedType) type).getActualTypeArguments();
            Type argumentType = argumentTypes[0];
            if (argumentType instanceof Class) {
                System.out.println(((Class<?>)argumentType).getName());
            }
        }
    }
}
