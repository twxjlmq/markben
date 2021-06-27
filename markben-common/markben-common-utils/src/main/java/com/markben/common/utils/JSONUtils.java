package com.markben.common.utils;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.markben.common.logger.Logger;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;

/**
 * JSON工具类
 * @author 乌草坡
 * @since 0.0.1
 */
public class JSONUtils {

    private static final Logger logger = LoggerUtils.getLogger(JSONUtils.class);

    private static ObjectMapper mapper = new ObjectMapper();

    static {
        mapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
    }

    /**
     * 将对象转换为json字符串
     * @param object 需要转换的对象
     * @return 返回转换后的JSON字符串
     */
    public static String toJson(Object object) {
        return toJson(object, null);
    }

    /**
     * 将对象转换为json字符串
     * @param object 需要转换的对象
     * @param consumer 函数式接口
     * @return 返回转换后的json字符串
     */
    public static String toJson(Object object, Consumer<ObjectMapper> consumer) {
        if(object == null) return "";
        try {
            if(null != consumer) {
                consumer.accept(mapper);
            }
            mapper.getSerializerProvider().setNullValueSerializer(new JsonSerializer<Object>() {
                @Override
                public void serialize(Object value, JsonGenerator gen, SerializerProvider serializers)
                        throws IOException {
                    gen.writeString("");
                }
            });
            return mapper.writeValueAsString(object);
        } catch (Exception e) {
            LoggerUtils.warn(logger, "write to json string error:" + object, e);
            return "";
        }
    }

    /**
     * 根据指定类型解析json字符串，并返回该类型的对象
     * @param jsonStr JSON字符串
     * @param consumer 函数式接口
     * @return 返回转换后的Map列表
     */
    public static List<Map<String, Object>> toListMap(String jsonStr, Consumer<ObjectMapper> consumer) {
        if (StringUtils.isEmpty(jsonStr)) {
            return null;
        }
        try {
            if(null != consumer) {
                consumer.accept(mapper);
            }
            if(StringUtils.isNotEmpty(jsonStr) && !",".equals(jsonStr.trim()) && !jsonStr.trim().startsWith(",")) {
                return mapper.readValue(jsonStr, new TypeReference<List<Map<String, Object>>>(){});
            } else {
                return null;
            }
        } catch (Exception e) {
            LoggerUtils.warn(logger, "parse json string error:" + jsonStr, e);
            return null;
        }
    }

    /**
     * 根据指定类型解析json字符串，并返回该类型的对象
     * @param jsonStr json字符串
     * @param clazz 需要转换对象的类型
     * @return 返回指定类型的对象
     */
    public static <T> T fromJson(String jsonStr, Class<T> clazz) {
        return fromJson(jsonStr, clazz, null);
    }

    /**
     * 根据指定类型解析json字符串，并返回该类型的对象
     * @param jsonStr JSON字符串
     * @param clazz 需要转换的对象类型
     * @param valueTypeRef 值类型引用
     * @return 返回转换后的对象
     */
    private static <T> T fromJson(String jsonStr, Class<T> clazz, TypeReference<T> valueTypeRef) {
        if (StringUtils.isEmpty(jsonStr)) {
            return null;
        }
        try {
            if(StringUtils.isNotEmpty(jsonStr) && !",".equals(jsonStr.trim()) && !jsonStr.trim().startsWith(",")) {
                if(null != clazz) {
                    return mapper.readValue(jsonStr, clazz);
                } else if(null != valueTypeRef) {
                    return mapper.readValue(jsonStr, valueTypeRef);
                } else {
                    return null;
                }
            } else {
                return null;
            }
        } catch (Exception e) {
            LoggerUtils.warn(logger, "parse json string error:" + jsonStr, e);
            throw new RuntimeException(e);
        }
    }

    /**
     * 根据指定类型解析json字符串，并返回该类型的对象
     * @param jsonStr JSON字符串
     * @param valueTypeRef 值类型引用对象
     * @return 返回转换后的对象
     */
    public static <T> T fromJson(String jsonStr, TypeReference<T> valueTypeRef) {
        return fromJson(jsonStr, null, valueTypeRef);
    }

    /**
     * json字符串解析为Map对象
     * @param jsonStr JSON字符串
     * @return 返回转换后的Map对象
     */
    @SuppressWarnings("unchecked")
    public static Map<String, Object> toMap(String jsonStr) {
        if (StringUtils.isEmpty(jsonStr)) {
            return null;
        }
        Map<String, Object> jsonMap = null;
        try {
            jsonMap = mapper.readValue(jsonStr, Map.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return jsonMap;
    }

}
