package me.mrletsplay.mrcore.config.v2;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.stream.Collectors;

import me.mrletsplay.mrcore.json.JSONArray;
import me.mrletsplay.mrcore.json.JSONObject;
import me.mrletsplay.mrcore.misc.ClassUtils;
import me.mrletsplay.mrcore.misc.Complex;
import me.mrletsplay.mrcore.misc.NullableOptional;

public interface ConfigSection {
	
	// Must be implemented

	/**
	 * Returns the CustomConfig this section belongs to.<br>
	 * Will return the same instance, if this instance is already a CustomConfig
	 * @return The CustomConfig this section belongs to
	 */
	public CustomConfig getConfig();
	
	/**
	 * Returns a map containing all the properties & subsections of this section.<br>
	 * The values of this map may contain all {@link ConfigValueType valid value types}, including other ConfigSection instances.<br>
	 * Implementations may return an unmodifiable map
	 * @return A map containing all the properties & subsections of this section
	 */
	public Map<String, ConfigProperty> getAllProperties();
	
	/**
	 * Returns a map containing all comments of this section.<br>
	 * The header comment is represented by the {@code null} key.<br>
	 * Implementations may return an unmodifiable map
	 * @return A map containing all comments of this section
	 */
	public Map<String, String> getComments();
	
	/**
	 * Returns an exisiting subsection of this section or creates it, if it doesn't exist.<br>
	 * The {@code name} parameter does not allow subpaths (e.g. "{@code somepath.somesubpath}")
	 * @param name The name of the subsection
	 * @return The subsection by that name
	 * @throws ConfigException If the value specified by that key represents a property, not a subsection
	 */
	public ConfigSection getOrCreateSubsection(String name) throws ConfigException;
	
	/**
	 * Sets a key in this section to a value (represented by a {@link ConfigValueType valid value type}).<br>
	 * The {@code key} parameter allows for subpaths (e.g. "{@code somepath.somesubpath}")
	 * @param key The key of the property
	 * @param value The value of the property
	 */
	public void set(String key, Object value);
	
	/**
	 * Unsets a key in this section.<br>
	 * The {@code key} parameter allows for subpaths (e.g. "{@code somepath.somesubpath}").<br>
	 * If the property represented by that key is a subsection, the entire subsection will be removed
	 * @param key The key of the property
	 */
	public void unset(String key);
	
	/**
	 * Clears this section.<br>
	 * Every property in this section will be unset
	 * @param key The key of the property
	 * @param value The value of the property
	 */
	public void clear();
	
	/**
	 * Gets a property by a specific key.<br>
	 * The {@code key} parameter allows for subpaths (e.g. "{@code somepath.somesubpath}")
	 * @param key The key of the property
	 * @return The property by that name, {@code null} if none
	 * @throws ConfigException If the value specified by that key represents a subsection, not a property
	 */
	public ConfigProperty getProperty(String key) throws ConfigException;
	
	/**
	 * Sets a comment in this section.<br>
	 * The {@code key} parameter allows for subpaths (e.g. "{@code somepath.somesubpath}")
	 * @param key The key of the property
	 * @param value The value of the property
	 */
	public void setComment(String key, String value);
	
	/**
	 * Gets a comment by a specific key.<br>
	 * The {@code key} parameter allows for subpaths (e.g. "{@code somepath.somesubpath}")
	 * @param key The key of the comment
	 * @return The comment by that key, {@code null} if none
	 */
	public String getComment(String key);
	
	// Default methods
	
	/**
	 * Gets a subsection by the specified name.<br>
	 * This implementation uses {@link #getAllProperties()} to retrieve the specified section.<br>
	 * To create a subsection, use {@link #getOrCreateSubsection(String)}
	 * @param name The name of the subsection
	 * @return The subsection by that name, null if none
	 */
	public default ConfigSection getSubsection(String name) throws ConfigException {
		ConfigProperty p = getAllProperties().get(name);
		return p != null && p.isSubsection() ? (ConfigSection) p.getValue() : null;
	}
	
	
	/**
	 * Returns a map containing all the properties (excluding subsections) of this section.<br>
	 * This implementation uses {@link #getAllProperties()} to retrieve the properties.<br>
	 * Implementations may return an unmodifiable map
	 * @return A map containing all the properties (excluding subsections) of this section 
	 */
	public default Map<String, ConfigProperty> getProperties() {
		return getAllProperties().entrySet().stream()
				.filter(en -> !en.getValue().isSubsection())
				.collect(Collectors.toMap(en -> en.getKey(), en -> en.getValue()));
	}
	
	/**
	 * Returns a map containing all the raw properties (excluding subsections) of this section.<br>
	 * Raw properties are - different to {@link ConfigProperty} properties returned by {@link #getProperties()} - the raw values of this section, represented by {@link ConfigValueType valid value types}.<br>
	 * This implementation uses {@link #getProperties()} to retrieve the properties.<br>
	 * Implementations may return an unmodifiable map
	 * @return A map containing all the properties (excluding subsections) of this section 
	 */
	public default Map<String, Object> getRawProperties() {
		Map<String, Object> map = new LinkedHashMap<>();
		for(Map.Entry<String, ConfigProperty> en : getProperties().entrySet()) {
			map.put(en.getKey(), en.getValue().getValue());
		}
		return map;
	}
	
	/**
	 * Returns a map containing all the subsections of this section.<br>
	 * This implementation uses {@link #getAllProperties()} to retrieve the subsections.<br>
	 * Implementations may return an unmodifiable map
	 * @return A map containing all the properties (excluding subsections) of this section 
	 */
	public default Map<String, ConfigSection> getSubsections() {
		return getAllProperties().entrySet().stream()
				.filter(en -> en.getValue().isSubsection())
				.collect(Collectors.toMap(en -> en.getKey(), en -> (ConfigSection) en.getValue().getValue()));
	}
	
	/**
	 * Returns a map containing all the (raw) properties as well as subsections (represented by other {@link Map}s) of this section.<br>
	 * The Map returned by this function may be passed to {@link #loadFromMap(Map)}
	 * @return A map containing all the (raw) properties as well as subsections of this section
	 */
	public default Map<String, Object> toMap() {
		Map<String, Object> map = new LinkedHashMap<>(getRawProperties());
		for(Entry<String, ConfigSection> sub : getSubsections().entrySet()) {
			if(!sub.getValue().isEmpty()) map.put(sub.getKey(), sub.getValue().toMap());
		}
		return map;
	}
	
	/**
	 * Sets all the values & subsections (represented by other {@link Map}s) of the specified map in this subsection.<br>
	 * Any map created by {@link #toMap()} may be passed to this function
	 * @param map A map containing all the values & subsections
	 */
	public default void loadFromMap(Map<String, Object> map) {
		map.forEach(this::set);
	}
	
	/**
	 * Returns a map containing all the comments as well as subsections' comments (represented by other {@link Map}s) of this section.<br>
	 * The Map returned by this function may be passed to {@link #loadCommentsFromMap(Map)}
	 * @return A map containing all the comments as well as subsections' comments of this section
	 */
	public default Map<String, String> commentsToMap() {
		Map<String, String> map = new LinkedHashMap<>(getComments());
		for(Entry<String, ConfigSection> sub : getSubsections().entrySet()) {
			sub.getValue().commentsToMap().forEach((ck, cv) -> {
				map.put(sub.getKey() + ck, cv);
			});
		}
		return map;
	}
	
	/**
	 * Sets all the comments & subsections' comments (represented by other {@link Map}s) of the specified map in this subsection.<br>
	 * Any map created by {@link #commentsToMap()} may be passed to this function
	 * @param map A map containing all the comments & subsections' comments
	 */
	public default void loadCommentsFromMap(Map<String, String> map) {
		map.forEach((k, v) -> {
			setComment(k, v);
		});
	}
	
	public default boolean isEmpty() {
		return getProperties().values().stream().allMatch(ConfigProperty::isEmpty) && getSubsections().values().stream().allMatch(ConfigSection::isEmpty);
	}
	
	/**
	 * Returns a JSONObject containing all the (raw) properties as well as subsections (represented by other {@link JSONObject}s) of this section.<br>
	 * Lists will be converted to {@link JSONArray}s.<br>
	 * The JSONObject returned by this function may be passed to {@link #loadFromJSON(JSONObject)}
	 * @return A JSONObject containing all the (raw) properties as well as subsections of this section
	 */
	public default JSONObject toJSON() {
		Map<String, Object> props = new LinkedHashMap<>();
		for(Map.Entry<String, ConfigProperty> en : getProperties().entrySet()) {
			props.put(en.getKey(), en.getValue().getJSONValue());
		}
		JSONObject o = new JSONObject(props);
		for(Entry<String, ConfigSection> sub : getSubsections().entrySet()) {
			o.put(sub.getKey(), sub.getValue().toJSON());
		}
		return o;
	}
	
	/**
	 * Sets all the values & subsections (represented by other {@link JSONObject}s) of the specified JSONObject in this subsection.<br>
	 * Any JSONObject created by {@link #toJSON()} may be passed to this function
	 * @param map A JSONObject containing all the values & subsections
	 */
	public default void loadFromJSON(JSONObject json) {
		json.forEach(this::set);
	}
	
	public static <T> NullableOptional<T> defaultCast(ConfigSection section, Object o, Class<T> typeClass, boolean allowComplex) {
		if(ClassUtils.isPrimitiveTypeClass(typeClass)) throw new IllegalArgumentException("Primitive types are not allowed");
		if(o == null) return NullableOptional.of(null);
		if(typeClass.isInstance(o)) return NullableOptional.of(typeClass.cast(o));
		if(Number.class.isAssignableFrom(typeClass)) {
			if(!(o instanceof Number)) return NullableOptional.empty();
			Number n = (Number) o;
			if(typeClass.equals(Byte.class)) {
				return NullableOptional.of(typeClass.cast(n.byteValue()));
			}else if(typeClass.equals(Short.class)) {
				return NullableOptional.of(typeClass.cast(n.shortValue()));
			}else if(typeClass.equals(Integer.class)) {
				return NullableOptional.of(typeClass.cast(n.intValue()));
			}else if(typeClass.equals(Long.class)) {
				return NullableOptional.of(typeClass.cast(n.longValue()));
			}else if(typeClass.equals(Float.class)) {
				return NullableOptional.of(typeClass.cast(n.floatValue()));
			}else if(typeClass.equals(Double.class)) {
				return NullableOptional.of(typeClass.cast(n.doubleValue()));
			}else {
				return NullableOptional.empty();
			}
		}else if(typeClass.equals(String.class)
				|| typeClass.equals(Boolean.class)
				|| typeClass.equals(Character.class)
				|| typeClass.equals(List.class) || typeClass.equals(ConfigSection.class)) {
			if(!typeClass.isInstance(o)) return NullableOptional.empty();
			return NullableOptional.of(typeClass.cast(o));
		}else {
			if(!allowComplex) return NullableOptional.empty();
			List<List<ObjectMapper<?, ?>>> paths = calculateCompatiblePaths(section, o, Complex.typeOf(o), Complex.value(typeClass), new ArrayList<>());
			if(paths.isEmpty()) return NullableOptional.empty();
			for(List<ObjectMapper<?, ?>> path : paths) {
				Object val = o;
				try {
					for(ObjectMapper<?, ?> mapper : path) {
						val = mapper.constructRawObject(section, val, section::castPrimitiveType);
					}
				}catch(ObjectMappingException e) {
					continue;
				}
				return NullableOptional.of(typeClass.cast(val));
			}
			return NullableOptional.empty();
		}
	}
	
	public static List<List<ObjectMapper<?, ?>>> calculateCompatiblePaths(ConfigSection section, Object object, Complex<?> fromClass, Complex<?> toClass, List<Complex<?>> classes) {
		if(toClass.equals(fromClass)) return new ArrayList<>();
		classes.add(fromClass);
		Comparator<Map.Entry<ObjectMapper<?, ?>, Integer>> c = Comparator.comparingInt(en -> en.getKey().getClassDepth(fromClass));
		c = c.thenComparingInt(Map.Entry::getValue);
		List<ObjectMapper<?, ?>> mappers = section.getConfig().getMappers().entrySet().stream()
				.filter(m -> !classes.contains(m.getKey().getMappingClass()) && m.getKey().canConstruct(object, section::castPrimitiveType))
				.sorted(c)
				.map(Map.Entry::getKey)
				.collect(Collectors.toList());
		List<List<ObjectMapper<?, ?>>> pths = new ArrayList<>();
		for(ObjectMapper<?, ?> mapper : mappers) {
			Object res;
			try {
				res = mapper.constructRawObject(section, object, section::castPrimitiveType);
			}catch(ObjectMappingException e) {
				if(mapper.getMappingClass().getFriendlyClassName().equals("me.eglp.gv2.util.base.guild.GuildTempBan")) {
					e.printStackTrace();
				}
				continue;
			}
			if(toClass.cast(res).isPresent()) pths.add(new ArrayList<>(Arrays.asList(mapper)));
			List<List<ObjectMapper<?, ?>>> paths = calculateCompatiblePaths(section, res, mapper.getMappingClass(), toClass, classes);
			if(!paths.isEmpty()) {
				for(List<ObjectMapper<?, ?>> pt : paths) {
					pt.add(0, mapper);
				}
				pths.addAll(paths);
			}
		}
		return pths;
	}
	
	public default <T> NullableOptional<T> castType(Object o, Class<T> clazz) {
		return defaultCast(this, o, clazz, true);
	}
	
	public default <T> NullableOptional<T> castPrimitiveType(Object o, Class<T> clazz) {
		return defaultCast(this, o, clazz, false);
	}
	
	public default ConfigValueType getTypeOf(String key) {
		ConfigProperty p = getProperty(key);
		if(p == null || p.isUndefined()) return ConfigValueType.UNDEFINED;
		return p.getValueType();
	}
	
	public default <T> T getComplex(String key, Complex<T> complex, T defaultValue, boolean applyDefault) {
		ConfigProperty prop = getProperty(key);
		if(prop == null || prop.isUndefined()) {
			if(applyDefault) set(key, defaultValue);
			return defaultValue;
		}else {
			NullableOptional<T> value = complex.cast(prop.getValue(), this::castType);
			if(!value.isPresent()) {
				throw new IncompatibleTypeException("Incompatible types, " + prop.getValue().getClass().getName() + " cannot be cast/formatted to " + complex.getFriendlyClassName());
			}
			return value.get();
		}
	}
	
	public default <T> T getGeneric(String key, Class<T> clazz, T defaultValue, boolean applyDefault) {
		return getComplex(key, Complex.value(clazz), defaultValue, applyDefault);
	}

	public default <T> List<T> getGenericList(String key, Class<T> clazz, List<T> defaultValue, boolean applyDefault) {
		return getComplex(key, Complex.list(clazz), defaultValue, applyDefault);
	}

	public default <T> Map<String, T> getGenericMap(Class<T> valueClass, String key, Map<String, T> defaultValue, boolean applyDefault) {
		return getComplex(key, Complex.map(String.class, valueClass), defaultValue, applyDefault);
	}
	
	public default String getString(String key, String defaultValue, boolean applyDefault) {
		return getGeneric(key, String.class, defaultValue, applyDefault);
	}
	
	public default boolean getBoolean(String key, boolean defaultValue, boolean applyDefault) {
		return getGeneric(key, Boolean.class, defaultValue, applyDefault);
	}
	
	public default byte getByte(String key, byte defaultValue, boolean applyDefault) {
		return getGeneric(key, Byte.class, defaultValue, applyDefault);
	}
	
	public default short getShort(String key, short defaultValue, boolean applyDefault) {
		return getGeneric(key, Short.class, defaultValue, applyDefault);
	}
	
	public default int getInt(String key, int defaultValue, boolean applyDefault) {
		return getGeneric(key, Integer.class, defaultValue, applyDefault);
	}
	
	public default long getLong(String key, long defaultValue, boolean applyDefault) {
		return getGeneric(key, Long.class, defaultValue, applyDefault);
	}
	
	public default float getFloat(String key, float defaultValue, boolean applyDefault) {
		return getGeneric(key, Float.class, defaultValue, applyDefault);
	}
	
	public default double getDouble(String key, double defaultValue, boolean applyDefault) {
		return getGeneric(key, Double.class, defaultValue, applyDefault);
	}
	
	public default List<?> getList(String key, List<?> defaultValue, boolean applyDefault) {
		return getGeneric(key, List.class, defaultValue, applyDefault);
	}
	
	public default Map<?, ?> getMap(String key, Map<?, ?> defaultValue, boolean applyDefault) {
		return getGeneric(key, Map.class, defaultValue, applyDefault);
	}
	
	public default List<String> getStringList(String key, List<String> defaultValue, boolean applyDefault) {
		return getGenericList(key, String.class, defaultValue, applyDefault);
	}
	
	public default List<Boolean> getBooleanList(String key, List<Boolean> defaultValue, boolean applyDefault) {
		return getGenericList(key, Boolean.class, defaultValue, applyDefault);
	}
	
	public default List<Byte> getByteList(String key, List<Byte> defaultValue, boolean applyDefault) {
		return getGenericList(key, Byte.class, defaultValue, applyDefault);
	}
	
	public default List<Short> getShortList(String key, List<Short> defaultValue, boolean applyDefault) {
		return getGenericList(key, Short.class, defaultValue, applyDefault);
	}
	
	public default List<Integer> getIntegerList(String key, List<Integer> defaultValue, boolean applyDefault) {
		return getGenericList(key, Integer.class, defaultValue, applyDefault);
	}
	
	public default List<Long> getLongList(String key, List<Long> defaultValue, boolean applyDefault) {
		return getGenericList(key, Long.class, defaultValue, applyDefault);
	}
	
	public default List<Float> getFloatList(String key, List<Float> defaultValue, boolean applyDefault) {
		return getGenericList(key, Float.class, defaultValue, applyDefault);
	}
	
	public default List<Double> getDoubleList(String key, List<Double> defaultValue, boolean applyDefault) {
		return getGenericList(key, Double.class, defaultValue, applyDefault);
	}
	
	public default <T> T getGeneric(String key, Class<T> clazz) {
		return getGeneric(key, clazz, null, false);
	}
	
	public default <T> T getComplex(String key, Complex<T> complex) {
		return getComplex(key, complex, null, false);
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
		return getMap(key, new LinkedHashMap<>(), false);
	}
	
	public default List<String> getStringList(String key) {
		return getGenericList(key, String.class, new ArrayList<>(), false);
	}
	
	public default List<Boolean> getBooleanList(String key) {
		return getGenericList(key, Boolean.class, new ArrayList<>(), false);
	}
	
	public default List<Byte> getByteList(String key) {
		return getGenericList(key, Byte.class, new ArrayList<>(), false);
	}
	
	public default List<Short> getShortList(String key) {
		return getGenericList(key, Short.class, new ArrayList<>(), false);
	}
	
	public default List<Integer> getIntegerList(String key) {
		return getGenericList(key, Integer.class, new ArrayList<>(), false);
	}
	
	public default List<Long> getLongList(String key) {
		return getGenericList(key, Long.class, new ArrayList<>(), false);
	}
	
	public default List<Float> getFloatList(String key) {
		return getGenericList(key, Float.class, new ArrayList<>(), false);
	}
	
	public default List<Double> getDoubleList(String key) {
		return getGenericList(key, Double.class, new ArrayList<>(), false);
	}
	
	public default List<String> getKeys(String key, boolean deep, boolean fullKeys) {
		ConfigPath path = new ConfigPath(key, 0);
		if(path.hasSubpaths()) {
			return getOrCreateSubsection(path.getName()).getKeys(path.traverseDown().toRawPath(), deep, fullKeys);
		}
		ConfigSection ss = getSubsection(key);
		if(ss == null) return new ArrayList<>();
		return ss.getKeys(deep, fullKeys);
	}
	
	public default List<String> getKeys(String key) {
		return getKeys(key, false, true);
	}
	
	public default List<String> getKeys(boolean deep, boolean fullKeys) {
		List<String> keys = new ArrayList<>();
		getAllProperties().forEach((k, v) -> keys.add(k));
		if(deep) {
			for(Map.Entry<String, ConfigSection> ss : getSubsections().entrySet()) {
				List<String> sK = ss.getValue().getKeys(deep, fullKeys);
				if(fullKeys) sK = sK.stream().map(k -> ss.getKey() + "." + k).collect(Collectors.toList());
				keys.addAll(sK);
			}
		}
		return keys;
	}
	
	public default List<String> getKeys() {
		List<String> keys = new ArrayList<>();
		getAllProperties().forEach((k, v) -> keys.add(k));
		return keys;
	}
	
}
