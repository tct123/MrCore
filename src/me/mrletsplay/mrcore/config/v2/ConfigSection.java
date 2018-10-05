package me.mrletsplay.mrcore.config.v2;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import me.mrletsplay.mrcore.misc.ClassUtils;
import me.mrletsplay.mrcore.misc.Complex;
import me.mrletsplay.mrcore.misc.JSON.JSONObject;

public interface ConfigSection {
	
	// Must be implemented

	public CustomConfig getConfig();
	
	public ConfigSection getParent();
	
	public String getName();
	
	public Map<String, Object> getAllProperties();
	
	public Map<String, String> getComments();
	
	public ConfigSection getOrCreateSubsection(String name);
	
	public void set(String key, Object value);
	
	public ConfigProperty getProperty(String key);
	
	public String saveToString();
	
	// Default methods
	
	public default ConfigSection getSubsection(String name) {
		return (ConfigSection) getAllProperties().get(name);
	}
	
	public default Map<String, ConfigProperty> getProperties() {
		return getAllProperties().entrySet().stream()
				.filter(en -> en.getValue() instanceof ConfigProperty)
				.collect(Collectors.toMap(en -> en.getKey(), en -> (ConfigProperty) en.getValue()));
	}
	
	public default Map<String, ?> getRawProperties() {
		return getProperties().entrySet().stream()
				.collect(Collectors.toMap(en -> en.getKey(), en -> en.getValue().getValue()));
	}
	
	public default Map<String, ConfigSection> getSubsections() {
		return getAllProperties().entrySet().stream()
				.filter(en -> en.getValue() instanceof ConfigSection)
				.collect(Collectors.toMap(en -> en.getKey(), en -> (ConfigSection) en.getValue()));
	}
	
	public default Map<String, ?> toMap() {
		Map<String, Object> map = new LinkedHashMap<>(getRawProperties());
		for(ConfigSection sub : getSubsections().values()) {
			map.put(sub.getName(), sub.toMap());
		}
		return map;
	}
	
	public default void loadFromMap(Map<String, ?> map) {
		map.forEach(this::set);
	}
	
	public default JSONObject toJSON() {
		JSONObject o = new JSONObject(getRawProperties());
		for(ConfigSection sub : getSubsections().values()) {
			o.put(sub.getName(), sub.toJSON());
		}
		return o;
	}
	
	public default void loadFromJSON(JSONObject json) {
		json.forEach(this::set);
	}
	
	public default <T> T getGeneric(Class<T> clazz, String key, T defaultValue, boolean applyDefault) {
		return getComplex(Complex.value(clazz), key, defaultValue, applyDefault);
	}

	public default <T> List<T> getGenericList(Class<T> clazz, String key, List<T> defaultValue, boolean applyDefault) {
		return getComplex(Complex.list(clazz), key, defaultValue, applyDefault);
	}

	public default <T> Map<String, T> getGenericMap(Class<T> valueClass, String key, Map<String, T> defaultValue, boolean applyDefault) {
		return getComplex(Complex.map(String.class, valueClass), key, defaultValue, applyDefault);
	}
	
	public static <T> Optional<T> defaultCast(Object o, Class<T> typeClass) {
		if(ClassUtils.isPrimitiveTypeClass(typeClass)) throw new IllegalArgumentException("Primitive types are not allowed");
		if(o == null) return Optional.ofNullable(null);
		if(Number.class.isAssignableFrom(typeClass)) {
			if(!(o instanceof Number)) return Optional.empty();
			Number n = (Number) o;
			if(typeClass.equals(Byte.class)) {
				return Optional.of(typeClass.cast(n.byteValue()));
			}else if(typeClass.equals(Short.class)) {
				return Optional.of(typeClass.cast(n.shortValue()));
			}else if(typeClass.equals(Integer.class)) {
				return Optional.of(typeClass.cast(n.intValue()));
			}else if(typeClass.equals(Long.class)) {
				return Optional.of(typeClass.cast(n.longValue()));
			}else if(typeClass.equals(Float.class)) {
				return Optional.of(typeClass.cast(n.floatValue()));
			}else if(typeClass.equals(Double.class)) {
				return Optional.of(typeClass.cast(n.doubleValue()));
			}else {
				return Optional.empty();
			}
		}else if(typeClass.equals(Map.class)) {
			if(!(o instanceof ConfigSection)) return Optional.empty();
			return Optional.of(typeClass.cast(((ConfigSection) o).toMap()));
		}else if(typeClass.equals(String.class)
				|| typeClass.equals(Boolean.class)
				|| typeClass.equals(Character.class)
				|| typeClass.equals(List.class) || typeClass.equals(ConfigSection.class)) {
			if(!typeClass.isInstance(o)) return Optional.empty();
			return Optional.of(typeClass.cast(o));
		}else {
			return Optional.empty();
		}
	}
	
	public default <T> T getComplex(Complex<T> complex, String key, T defaultValue, boolean applyDefault) {
		ConfigProperty prop = getProperty(key);
		if(prop.isUndefined()) {
			if(applyDefault) set(key, defaultValue);
			return defaultValue;
		}else {
			Optional<T> value = complex.cast(prop.getValue(), ConfigSection::defaultCast);
			if(!value.isPresent()) throw new IncompatibleTypeException("Incompatible types, " + value.getClass() + " is not a compatible with " + complex.getFriendlyClassName());
			return value.get();
		}
	}
	
	public default String getString(String key, String defaultValue, boolean applyDefault) {
		return getGeneric(String.class, key, defaultValue, applyDefault);
	}
	
	public default boolean getBoolean(String key, boolean defaultValue, boolean applyDefault) {
		return getGeneric(Boolean.class, key, defaultValue, applyDefault);
	}
	
	public default byte getByte(String key, byte defaultValue, boolean applyDefault) {
		return getGeneric(Byte.class, key, defaultValue, applyDefault);
	}
	
	public default short getShort(String key, short defaultValue, boolean applyDefault) {
		return getGeneric(Short.class, key, defaultValue, applyDefault);
	}
	
	public default int getInt(String key, int defaultValue, boolean applyDefault) {
		return getGeneric(Integer.class, key, defaultValue, applyDefault);
	}
	
	public default long getLong(String key, long defaultValue, boolean applyDefault) {
		return getGeneric(Long.class, key, defaultValue, applyDefault);
	}
	
	public default float getFloat(String key, float defaultValue, boolean applyDefault) {
		return getGeneric(Float.class, key, defaultValue, applyDefault);
	}
	
	public default double getDouble(String key, double defaultValue, boolean applyDefault) {
		return getGeneric(Double.class, key, defaultValue, applyDefault);
	}
	
	public default List<?> getList(String key, List<?> defaultValue, boolean applyDefault) {
		return getGeneric(List.class, key, defaultValue, applyDefault);
	}
	
	public default Map<?, ?> getMap(String key, Map<?, ?> defaultValue, boolean applyDefault) {
		return getGeneric(Map.class, key, defaultValue, applyDefault);
	}
	
	public default <T> T getGeneric(Class<T> clazz, String key) {
		return getGeneric(clazz, key, null, false);
	}
	
	public default <T> T getComplex(Complex<T> complex, String key) {
		return getComplex(complex, key, null, false);
	}
	
	public default String getString(String key) {
		return getString(key, null, false);
	}
	
	public default boolean getBoolean(String key) {
		return getBoolean(key, false, false);
	}
	
	public default byte getByte(String key) {
		return getByte(key, (byte) 0, false);
	}
	
	public default short getShort(String key) {
		return getShort(key, (short) 0, false);
	}
	
	public default int getInt(String key) {
		return getInt(key, 0, false);
	}
	
	public default long getLong(String key) {
		return getLong(key, 0l, false);
	}
	
	public default float getFloat(String key) {
		return getFloat(key, 0f, false);
	}
	
	public default double getDouble(String key) {
		return getDouble(key, 0d, false);
	}
	
	public default List<?> getList(String key) {
		return getList(key, new ArrayList<>(), false);
	}
	
	public default Map<?, ?> getMap(String key) {
		return getMap(key, new HashMap<>(), false);
	}
	
}