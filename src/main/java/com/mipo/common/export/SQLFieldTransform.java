package com.mipo.common.export;

public interface SQLFieldTransform {
	/**
	 * 转换为需要的显示格式
	 * @return
	 */
	public String transform(Object o, boolean bool);
}
