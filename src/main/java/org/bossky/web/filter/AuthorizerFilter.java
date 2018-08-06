package org.bossky.web.filter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.bossky.common.util.Misc;
import org.bossky.web.AuthorizeException;
import org.bossky.web.Authorizer;
import org.bossky.web.WebConstant;

/**
 * 认证过滤器
 * 
 * @author daibo
 *
 */
public class AuthorizerFilter implements Filter {
	/** 认证器 */
	private Authorizer authorizer;
	/** 未登陆要跳转的url 不设置则返回{@link WebConstant#SC_UNAUTHORIZED}状态码页面 */
	private String loginUrl;
	/** 是否要带上原来的url,默认是true */
	private boolean withOriginalUrl = true;
	/** 要忽略验证的url */
	protected List<Pattern> ignoreUris;
	/** 编码集 */
	public static String CHARACTERENCODING = "UTF-8";

	public AuthorizerFilter(Authorizer authorizer) {
		this.authorizer = authorizer;
	}

	/**
	 * 登陆页面的url
	 * 
	 * @param url
	 */
	public void setLoginUrl(String url) {
		loginUrl = url;
	}

	/**
	 * 是否要带上原url
	 * 
	 * @param isWith
	 */
	public void setwithOriginalUrl(boolean isWith) {
		withOriginalUrl = isWith;
	}

	/**
	 * 要忽略验证的url,支持正则表达式
	 * 
	 * @param urls
	 */
	public void setIgnoreUris(List<String> urls) {
		List<Pattern> ps = new ArrayList<Pattern>(urls.size());
		for (String s : urls) {
			ps.add(Pattern.compile(s));
		}
		ignoreUris = ps;
	}

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {

	}

	/**
	 * 是否排除
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	private boolean isExclude(HttpServletRequest request, HttpServletResponse response) {
		if (Misc.eq(loginUrl, request.getRequestURI())) {
			return true;
		}
		if (null != ignoreUris) {
			for (Pattern s : ignoreUris) {
				if (s.matcher(request.getRequestURI()).matches()) {
					return true;
				}
			}
		}
		return false;

	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest httpRequest = (HttpServletRequest) request;
		HttpServletResponse httpResponse = (HttpServletResponse) response;
		httpRequest.setCharacterEncoding(CHARACTERENCODING);
		httpResponse.setCharacterEncoding(CHARACTERENCODING);
		if (isExclude(httpRequest, httpResponse)) {
			chain.doFilter(request, response);
			return;
		}
		boolean isOk;
		try {
			isOk = authorizer.auth(request, response);
		} catch (AuthorizeException e) {
			httpResponse.sendError(WebConstant.SC_FORBIDDEN);
			return;
		}
		if (isOk) {
			chain.doFilter(request, response);
			return;
		}
		if (Misc.isEmpty(loginUrl)) {
			httpResponse.sendError(WebConstant.SC_UNAUTHORIZED);
			return;
		}
		String originalurl = httpRequest.getRequestURI();
		String target;
		if (withOriginalUrl) {
			String queryString = httpRequest.getQueryString();
			if (!Misc.isEmpty(queryString)) {
				originalurl += "?" + queryString;
			}
			target = loginUrl + "?original=" + originalurl;
		} else {
			target = loginUrl;
		}
		httpResponse.sendRedirect(target);
		return;

	}

	@Override
	public void destroy() {
		ignoreUris = null;
		authorizer = null;
	}

}
