package nc.vo.so.component;

import nc.md.model.IEnumValue;
import nc.md.model.impl.MDEnum;

public class PlatformEnum extends MDEnum {

	public PlatformEnum(IEnumValue enumValue) {
		super(enumValue);
	}
	
	public static final PlatformEnum TMALL= MDEnum.valueOf(PlatformEnum.class, "1");
	public static final PlatformEnum LAZADA= MDEnum.valueOf(PlatformEnum.class, "2");
}
