package org.bossky.web.impl;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.awt.image.RenderedImage;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;
import java.util.UUID;

import org.bossky.common.util.Misc;

/**
 * 用于生成/检查验证码及图片
 * 
 * @author daibo
 * 
 */
public class VerifyImage implements org.bossky.web.Verify {
	/** 验证码列表 */
	private HashMap<String, Verify> m_VerifyCode = new HashMap<String, Verify>();
	/** 验证码过期时间（毫秒） */
	private long timeout = 3 * 60 * 1000;
	/** 验证码类型 */
	private String type = TYPE_NUMBER;
	/** 验证码类型-数字 */
	public final static String TYPE_NUMBER = "number";
	/** 验证码类型-字母 */
	public final static String TYPE_CASE = "case";
	/** 验证码类型-数字字母 */
	public final static String TYPE_CASE_AND_NUMBER = "caseandnumber";
	/** 验证码类型-汉字 */
	public final static String TYPE_HANZI = "hanzi";
	/** 定时器 */
	private final Timer _Timer = new Timer(Misc.class.getSimpleName() + "_Timer", true);

	public VerifyImage() {
		_Timer.schedule(new TimerTask() {

			@Override
			public void run() {
				// 定时清理过期的验证码
				List<String> timeoutkeys = new ArrayList<String>();
				for (Entry<String, Verify> e : m_VerifyCode.entrySet()) {
					if (isOutTime(e.getValue())) {
						timeoutkeys.add(e.getKey());
					}
				}
				if (!timeoutkeys.isEmpty()) {
					synchronized (m_VerifyCode) {
						for (String key : timeoutkeys) {
							m_VerifyCode.remove(key);
						}
					}
				}
			}
		}, 10 * 60 * 1000, 10 * 60 * 1000);

	}

	/**
	 * 校验码是否过期了
	 * 
	 * @param v
	 * @return
	 */
	private boolean isOutTime(Verify v) {
		return (System.currentTimeMillis() - v.timestamp) > (timeout);
	}

	/**
	 * 设置验证码超时值
	 * 
	 * @param secs
	 *            超时值（秒）
	 */
	public void setTimeout(int secs) {
		timeout = secs * 1000;
	}

	/**
	 * 设置验证码类型 TYPE_XXX
	 * 
	 * @param type
	 */
	public void setType(String type) {
		this.type = type;
	}

	@Override
	public String genVerifyKey() {
		String key = UUID.randomUUID().toString();
		String code = randomCode();
		m_VerifyCode.put(key, new Verify(code));
		return key;
	}

	/**
	 * 随机码
	 * 
	 * @return
	 */
	private String randomCode() {
		if (Misc.eq(type, TYPE_NUMBER)) {
			return Misc.randomNumber(4);
		} else if (Misc.eq(type, TYPE_CASE)) {
			return Misc.randomCase(4);
		} else if (Misc.eq(type, TYPE_CASE_AND_NUMBER)) {
			return Misc.randomCaseAndNumber(4);
		} else if (Misc.eq(type, TYPE_HANZI)) {
			return Misc.randomHanzi(4);
		} else {
			throw new UnsupportedOperationException("不支持的类型" + type);
		}
	}

	@Override
	public RenderedImage getVerifyImage(String key) {
		Verify v = m_VerifyCode.get(key);
		if (null == v || isOutTime(v)) {
			return null;
		}
		char[] code = v.code.toCharArray();
		int width = 60;
		int height = 20;
		BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
		// 获取图形上下文
		Graphics graphics = image.getGraphics();
		// 生成随机类
		Random random = new Random();

		// 设定背景色
		graphics.setColor(getRandColor(200, 250, random));
		graphics.fillRect(0, 0, width, height);

		// 设定字体
		// graphics.setFont(new Font("Times New Roman", Font.PLAIN, 18));

		// 随机产生155条干扰线，使图象中的认证码不易被其它程序探测到
		graphics.setColor(getRandColor(160, 200, random));
		for (int i = 0; i < 155; i++) {
			int x = random.nextInt(width);
			int y = random.nextInt(height);
			int xl = random.nextInt(12);
			int yl = random.nextInt(12);
			graphics.drawLine(x, y, x + xl, y + yl);
		}

		// 显示认证码
		for (int i = 0; i < code.length; i++) {
			// 将认证码显示到图象中
			// graphics.setColor(new Color(20 + random.nextInt(110),20 +
			// random.nextInt(110),20 + random
			// .nextInt(110)));// 调用函数出来的颜色相同，可能是因为种子太接近，所以只能直接生成
			graphics.setColor(getRandColor(10, 140, random));
			graphics.drawChars(code, i, 1, 13 * i + 6, 16);
		}
		// 图象生效
		graphics.dispose();
		return image;
	}

	/**
	 * 给定范围获得随机颜色
	 * 
	 * @param min
	 *            最小值
	 * @param max
	 *            最大值
	 * @param random
	 *            随机数发生器
	 * @return 随机颜色
	 */
	static public Color getRandColor(int min, int max, Random random) {
		if (min > 255) {
			min = 255;
		}
		if (max > 255) {
			max = 255;
		}
		max -= min;
		int r = min + random.nextInt(max);
		int g = min + random.nextInt(max);
		int b = min + random.nextInt(max);
		return new Color(r, g, b);
	}

	@Override
	public boolean verify(String key, String code) {
		Verify v = m_VerifyCode.get(key);
		if (null == v || isOutTime(v)) {
			return false;
		}
		boolean isOk = Misc.eqIgnoreCase(v.code, code);
		return isOk;

	}

	/**
	 * 校验码项
	 * 
	 * @author daibo
	 * 
	 */
	public static class Verify {
		/** 校验码 */
		public final String code;
		/** 时间戳 */
		public final long timestamp;

		public Verify(String code) {
			this.code = code;
			this.timestamp = System.currentTimeMillis();
		}

	}
}
