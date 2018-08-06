package org.bossky.web;

import javax.servlet.ServletException;

/**
 * 用户认证失败
 * 
 * @author daibo
 * 
 */
public class AuthorizeException extends ServletException {

	private static final long serialVersionUID = 1L;

	public AuthorizeException() {
	}

	public AuthorizeException(String message, Throwable cause) {
		super(message, cause);
	}

	public AuthorizeException(String message) {
		super(message);
	}

	public AuthorizeException(Throwable cause) {
		super(cause);
	}

}
