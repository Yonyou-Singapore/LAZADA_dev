package nc.impl.so.restapi.jsonservice.vo.lazada.util;

import java.util.HashMap;

/**
 * 平台类型枚举
 *s
 * @author u
 * @version 1.0
 */
public enum EnumPlatType {
	top("�Ա�", (short)1),
	jd("京东", (short)2),
	suning("苏宁", (short)3);

	private String name;
	private short value;

	private EnumPlatType(String name, short value) {
		this.name = name;
		this.value = value;
	}

	public String getName() {
		return name;
	}

	public short getValue() {
		return value;
	}

	private static HashMap<Short, EnumPlatType> map = null;

	private synchronized static void initMap() {
		if (map != null) {
			return;
		}
		map = new HashMap<Short, EnumPlatType>();
		EnumPlatType[] items = EnumPlatType.values();
		for (EnumPlatType item : items) {
			map.put(item.getValue(), item);
		}
	}

	public static EnumPlatType find(Number value) {
		if (value == null) {
			return null;
		}
		if (map == null) {
			initMap();
		}
		return map.get(value.shortValue());
	}
}
