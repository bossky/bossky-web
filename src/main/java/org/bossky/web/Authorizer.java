package org.bossky.web;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

/**
 * 验证器
 * 
 * @author daibo
 *
 */
public interface Authorizer {
	/**
	 * 认证
	 * 
	 * @param request
	 *            请求
	 * @param response
	 *            响应
	 * @return 未登陆返回false
	 * @throws AuthorizeException
	 *             认证失败抛出AuthorizeException异常
	 */
	public boolean auth(ServletRequest request, ServletResponse response) throws AuthorizeException;
}
