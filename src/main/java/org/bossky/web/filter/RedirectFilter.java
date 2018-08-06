package org.bossky.web.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletResponse;

import org.bossky.common.util.Misc;

/**
 * 重定向过滤器
 * 
 * @author bo
 *
 */
public class RedirectFilter implements Filter {
	/** 默认重定向的代码 */
	public static int DEFAULT_SEND_CODE = 404;
	/** 重定向的代码 */
	protected int sendCode;
	/** 重定向的url */
	protected String sendUrl;

	@Override
	public void init(FilterConfig fConfig) throws ServletException {
		sendCode = Misc.toInt(fConfig.getInitParameter("code"), DEFAULT_SEND_CODE);
		sendUrl = fConfig.getInitParameter("url");
	}

	@Override
	public void destroy() {

	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		if (Misc.isEmpty(sendUrl)) {
			((HttpServletResponse) response).sendError(sendCode);
		} else {
			((HttpServletResponse) response).sendRedirect(sendUrl);
		}

	}
}