package com.example.util;

import com.example.exception.JsonException;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.fasterxml.jackson.databind.type.SimpleType;
import com.fasterxml.jackson.databind.type.TypeFactory;
import com.fasterxml.jackson.datatype.jdk8.Jdk8Module;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalTimeSerializer;
import com.fasterxml.jackson.module.paramnames.ParameterNamesModule;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Set;

public final class JsonUtils {
    private static final ObjectMapper objectMapper;
    private static final ObjectMapper excludeNullObjectMapper;

    static {
        objectMapper = new ObjectMapper()
                .registerModules(new ParameterNamesModule(), new Jdk8Module(), javaTimeModule())
                .disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS)
                .disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES)
                .disable(JsonGenerator.Feature.WRITE_BIGDECIMAL_AS_PLAIN)
                .enable(JsonParser.Feature.ALLOW_SINGLE_QUOTES)
                .enable(JsonParser.Feature.ALLOW_UNQUOTED_FIELD_NAMES)
                .enable(DeserializationFeature.ACCEPT_EMPTY_STRING_AS_NULL_OBJECT);
        excludeNullObjectMapper = objectMapper.copy()
                .setSerializationInclusion(JsonInclude.Include.NON_NULL);
    }

    private JsonUtils() {
    }

    private static SimpleModule javaTimeModule() {
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern(DatePattern.DATE_TIME);
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern(DatePattern.DATE);
        DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern(DatePattern.TIME);
        return new JavaTimeModule()
                .addSerializer(LocalDateTime.class, new LocalDateTimeSerializer(dateTimeFormatter))
                .addSerializer(LocalDate.class, new LocalDateSerializer(dateFormatter))
                .addSerializer(LocalTime.class, new LocalTimeSerializer(timeFormatter))
                .addDeserializer(LocalDateTime.class, new LocalDateTimeDeserializer(dateTimeFormatter))
                .addDeserializer(LocalDate.class, new LocalDateDeserializer(dateFormatter))
                .addDeserializer(LocalTime.class, new LocalTimeDeserializer(timeFormatter));
    }

    public static ObjectMapper getObjectMapper() {
        return objectMapper.copy();
    }

    public static ObjectMapper getObjectMapper(boolean ignoreNullField) {
        return ignoreNullField ? excludeNullObjectMapper.copy() : objectMapper.copy();
    }

    public static ObjectNode newJSONObject() {
        return objectMapper.createObjectNode();
    }

    public static String toJSONString(Object obj) {
        return toJSONString(objectMapper, obj);
    }

    public static String toJSONString(Object obj, boolean ignoreNullField) {
        return toJSONString(ignoreNullField ? excludeNullObjectMapper : objectMapper, obj);
    }

    private static String toJSONString(ObjectMapper objectMapper, Object obj) {
        if (obj == null) {
            return null;
        }
        try {
            return objectMapper.writeValueAsString(obj);
        } catch (JsonProcessingException e) {
            throw new JsonException(e);
        }
    }

    public static <E> List<E> parseList(String json, Class<E> elementType) {
        if (json == null) {
            return Collections.emptyList();
        }
        try {
            JavaType javaType = objectMapper.getTypeFactory().constructParametricType(List.class, elementType);
            return objectMapper.readValue(json, javaType);
        } catch (IOException e) {
            throw new JsonException(e);
        }
    }

    public static <E> Set<E> parseSet(String json, Class<E> elementType) {
        if (json == null) {
            return Collections.emptySet();
        }
        try {
            JavaType javaType = objectMapper.getTypeFactory().constructParametricType(Set.class, elementType);
            return objectMapper.readValue(json, javaType);
        } catch (IOException e) {
            throw new JsonException(e);
        }
    }

    public static <K, V> Map<K, V> parseMap(String json) {
        return parseObject(json, Map.class);
    }

    public static <T> T parseObject(String json, Class<T> type) {
        if (json == null) {
            return null;
        }
        try {
            return objectMapper.readValue(json, type);
        } catch (IOException e) {
            throw new JsonException(e);
        }
    }

    public static <T> T parseObject(String json, JavaType javaType) {
        if (json == null) {
            return null;
        }
        try {
            return objectMapper.readValue(json, javaType);
        } catch (IOException e) {
            throw new JsonException(e);
        }
    }

    public static <T> T parseObject(String json, TypeReference<T> typeReference) {
        if (json == null) {
            return null;
        }
        try {
            return objectMapper.readValue(json, typeReference);
        } catch (IOException e) {
            throw new JsonException(e);
        }
    }

    public static JsonNode parse(String json) {
        if (json == null) {
            return null;
        }
        try {
            return objectMapper.readTree(json);
        } catch (IOException e) {
            throw new JsonException(e);
        }
    }

    public static boolean isValid(String json) {
        if (json == null) {
            return false;
        }
        try {
            objectMapper.readTree(json);
        } catch (IOException e) {
            return false;
        }
        return true;
    }

    public static <E> List<E> parseList(InputStream inputStream, Class<E> elementType) {
        if (inputStream == null) {
            return Collections.emptyList();
        }
        try {
            JavaType javaType = objectMapper.getTypeFactory().constructParametricType(List.class, elementType);
            return objectMapper.readValue(inputStream, javaType);
        } catch (IOException e) {
            throw new JsonException(e);
        }
    }

    public static <E> Set<E> parseSet(InputStream inputStream, Class<E> elementType) {
        if (inputStream == null) {
            return Collections.emptySet();
        }
        try {
            JavaType javaType = objectMapper.getTypeFactory().constructParametricType(Set.class, elementType);
            return objectMapper.readValue(inputStream, javaType);
        } catch (IOException e) {
            throw new JsonException(e);
        }
    }

    public static <K, V> Map<K, V> parseMap(InputStream inputStream) {
        return parseObject(inputStream, Map.class);
    }

    public static <T> T parseObject(InputStream inputStream, Class<T> type) {
        if (inputStream == null) {
            return null;
        }
        try {
            return objectMapper.readValue(inputStream, type);
        } catch (IOException e) {
            throw new JsonException(e);
        }
    }

    public static <T> T parseObject(InputStream inputStream, JavaType javaType) {
        if (inputStream == null) {
            return null;
        }
        try {
            return objectMapper.readValue(inputStream, javaType);
        } catch (IOException e) {
            throw new JsonException(e);
        }
    }

    public static <T> T parseObject(InputStream inputStream, TypeReference<T> typeReference) {
        if (inputStream == null) {
            return null;
        }
        try {
            return objectMapper.readValue(inputStream, typeReference);
        } catch (IOException e) {
            throw new JsonException(e);
        }
    }

    public static JsonNode parse(InputStream inputStream) {
        if (inputStream == null) {
            return null;
        }
        try {
            return objectMapper.readTree(inputStream);
        } catch (IOException e) {
            throw new JsonException(e);
        }
    }

    public static boolean isValid(InputStream inputStream) {
        if (inputStream == null) {
            return false;
        }
        try {
            objectMapper.readTree(inputStream);
        } catch (IOException e) {
            return false;
        }
        return true;
    }

    public static JavaType getJavaType(Type type) {
        if (type instanceof ParameterizedType) {
            Type[] actualTypeArguments = ((ParameterizedType) type).getActualTypeArguments();
            Class<?> rowClass = (Class<?>) ((ParameterizedType) type).getRawType();
            JavaType[] javaTypes = new JavaType[actualTypeArguments.length];
            for (int i = 0; i < actualTypeArguments.length; i++) {
                javaTypes[i] = getJavaType(actualTypeArguments[i]);
            }
            return TypeFactory.defaultInstance().constructParametricType(rowClass, javaTypes);
        } else {
            Class<?> clazz = (Class<?>) type;
            return TypeFactory.defaultInstance().constructParametricType(clazz, new JavaType[0]);
        }
    }

    public static JavaType buildSimpleJavaType(Class<?> rawType) {
        return SimpleType.constructUnsafe(rawType);
    }

    public static JavaType buildJavaType(Class<?> rawType, JavaType parameterType) {
        return TypeFactory.defaultInstance().constructParametricType(rawType, parameterType);
    }

    public static JavaType buildJavaType(Class<?> rawType, JavaType... parameterTypes) {
        return TypeFactory.defaultInstance().constructParametricType(rawType, parameterTypes);
    }

}
