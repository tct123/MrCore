package me.mrletsplay.mrcore.mysql.protocol.type;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.MathContext;
import java.sql.Date;
import java.sql.Time;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public class MySQLDataTypes {
	
	protected static final List<MySQLDataType<?>> DEFAULT_TYPES = new ArrayList<>();

	public static final MySQLDataType<BigDecimal>
				DECIMAL = new MySQLDataType<>(0x00, BigDecimal.class, s -> new BigDecimal(s.toString()), d -> d.toString()),
				NEWDECIMAL = new MySQLDataType<>(0xf6, BigDecimal.class, s -> new BigDecimal(s.toString()), d -> d.toString());
	
	public static final MySQLDataType<Byte> TINY = new MySQLDataType<>(0x01, Byte.class, s -> Byte.valueOf(s.toString()), b -> b.toString(), r -> new BigInteger(r.readReversed(1)).byteValue());
	
	public static final MySQLDataType<Short>
				SHORT = new MySQLDataType<>(0x02, Short.class, s -> Short.valueOf(s.toString()), s -> s.toString(), r -> new BigInteger(r.readReversed(2)).shortValue()),
				YEAR = new MySQLDataType<>(0x0d, Short.class, s -> Short.valueOf(s.toString()), s -> s.toString(), r -> new BigInteger(r.readReversed(2)).shortValue());
	
	public static final MySQLDataType<Integer>
				LONG = new MySQLDataType<>(0x03, Integer.class, s -> Integer.valueOf(s.toString()), i -> i.toString(), r -> new BigInteger(r.readReversed(4)).intValue()),
				INT24 = new MySQLDataType<>(0x09, Integer.class, s -> Integer.valueOf(s.toString()), i -> i.toString(), r -> new BigInteger(r.readReversed(4)).intValue());
	
	public static final MySQLDataType<Float> FLOAT = new MySQLDataType<>(0x04, Float.class, s -> Float.valueOf(s.toString()), f -> f.toString(), r -> new BigDecimal(asldasdasdasdnew BigInteger(r.readReversed(3)), r.readReversed(1)[0]).floatValue());
	
	public static final MySQLDataType<Double> DOUBLE = new MySQLDataType<>(0x05, Double.class, s -> Double.valueOf(s.toString()), f -> f.toString(), r -> new BigDecimal(new BigInteger(r.readReversed(7)), r.readReversed(1)[0]).doubleValue());
	
	public static final MySQLDataType<Void> NULL = new MySQLDataType<>(0x06, Void.class, s -> null, v -> null);
	public static final MySQLDataType<Timestamp>
				TIMESTAMP = new MySQLDataType<>(0x07, Timestamp.class, s -> Timestamp.valueOf(s.toString()), t -> t.toString()),
				DATETIME = new MySQLDataType<>(0x0c, Timestamp.class, s -> Timestamp.valueOf(s.toString()), t -> t.toString());
	
	public static final MySQLDataType<Long> LONGLONG = new MySQLDataType<>(0x08, Long.class, s -> Long.valueOf(s.toString()), l -> l.toString(), r -> new BigInteger(r.readReversed(8)).longValue());
	
	public static final MySQLDataType<Date> DATE = new MySQLDataType<>(0x0a, Date.class, s -> Date.valueOf(s.toString()), d -> d.toString());
	
	public static final MySQLDataType<Time> TIME = new MySQLDataType<>(0x0b, Time.class, s -> Time.valueOf(s.toString()), t -> t.toString());
	
	public static final MySQLDataType<String>
				VARCHAR = new MySQLDataType<>(0x0f, String.class, s -> s.toString(), s -> s),
				VAR_STRING = new MySQLDataType<>(0xfd, String.class, s -> s.toString(), s -> s),
				STRING = new MySQLDataType<>(0xfe, String.class, s -> s.toString(), s -> s);
	
	public static final MySQLDataType<MySQLString>
				TINY_BLOB = new MySQLDataType<>(0xf9, MySQLString.class, s -> s, s -> s.toString()),
				MEDIUM_BLOB = new MySQLDataType<>(0xfa, MySQLString.class, s -> s, s -> s.toString()),
				LONG_BLOB = new MySQLDataType<>(0xfb, MySQLString.class, s -> s, s -> s.toString()),
				BLOB = new MySQLDataType<>(0xfc, MySQLString.class, s -> s, s -> s.toString());
	
	public static MySQLDataType<?> getTypeById(byte identifier) {
		return DEFAULT_TYPES.stream()
				.filter(t -> t.getSQLIdentifier() == identifier)
				.findFirst().orElseThrow(() -> new RuntimeException("Invalid/unsupported data type identifier: "+Integer.toHexString(identifier)));
	}
	
	
}
