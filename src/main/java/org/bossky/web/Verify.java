package org.bossky.web;

import java.awt.image.RenderedImage;

/**
 * 验证接口
 * 
 * @author daibo
 *
 */
public interface Verify {
	/**
	 * 验证
	 * 
	 * @param key
	 * @param code
	 * @return
	 */
	public boolean verify(String key, String code);

	/**
	 * 生成一个验证用的key
	 * 
	 * @return
	 */
	public String genVerifyKey();

	/**
	 * 获取key对应的验证图片
	 * 
	 * @param key
	 * @return
	 */
	public RenderedImage getVerifyImage(String key);

}
