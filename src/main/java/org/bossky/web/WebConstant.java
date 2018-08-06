package org.bossky.web;

/**
 * web类常量
 * 
 * @author bo
 *
 */
public interface WebConstant {

	/** 文本结果 */
	public static String CONTENT_TYPE_TEXT = "text/html";
	/** 请求格式 json */
	public static String CONTENT_TYPE_JSON = "application/json";
	/** 默认编码 */
	public static String DEFAULT_ENCODING = "UTF-8";
	/** 默认格式 */
	public static String DEFAULT_CONTENT_TYPE = CONTENT_TYPE_TEXT;
	/** 头部返回请求 */
	public static String HEADER_MESSAGE = "bossky_header_message";
	/** 单一参数的参数用于传递一个参数时省缺key */
	public static String SINGLETON_PARAM_NAME = "singleton_param";
	// 消息（1字头）
	/** 客户端应当继续发送请求 */
	public static final int SC_CONTINUE = 100;
	/** 服务器已经理解了客户端的请求,并将通过Upgrade 消息头通知客户端采用不同的协议来完成这个请求 */
	public static final int SC_SWITCHING_PROTOCOLS = 101;
	/** 由WebDAV（RFC 2518）扩展的状态码，代表处理将被继续执行 */
	public static final int SC_PROCESSING = 102;
	// 成功（2字头）
	/** 请求已成功，请求所希望的响应头或数据体将随此响应返回 */
	public static final int SC_OK = 200;
	/** 请求已经被实现，而且有一个新的资源已经依据请求的需要而建立，且其 URI 已经随Location 头信息返回。 */
	public static final int SC_CREATED = 201;
	/** 服务器已接受请求，但尚未处理 */
	public static final int SC_ACCEPTED = 202;
	/** 服务器已成功处理了请求，但返回的实体头部元信息不是在原始服务器上有效的确定集合，而是来自本地或者第三方的拷贝 */
	public static final int SC_NON_AUTHORITATIVE_INFORMATION = 203;
	/** 服务器成功处理了请求，但不需要返回任何实体内容，并且希望返回更新了的元信息 */
	public static final int SC_NO_CONTENT = 204;
	/** 服务器成功处理了请求，且没有返回任何内容 */
	public static final int SC_RESET_CONTENT = 205;
	/** 服务器已经成功处理了部分 GET 请求 */
	public static final int SC_PARTIAL_CONTENT = 206;
	/**
	 * 由WebDAV(RFC 2518)扩展的状态码，代表之后的消息体将是一个XML消息，并且可能依照之前子请求数量的不同，包含一系列独立的响应代码
	 */
	public static final int SC_MULTI_STATUS = 207;
	// 重定向（3字头）
	/** 被请求的资源有一系列可供选择的回馈信息，每个都有自己特定的地址和浏览器驱动的商议信息 */
	public static final int SC_MULTIPLE_CHOICES = 300;
	/** 被请求的资源已永久移动到新位置，并且将来任何对此资源的引用都应该使用本响应返回的若干个 URI 之一 */
	public static final int SC_MOVED_PERMANENTLY = 301;
	/** 请求的资源临时从不同的 URI响应请求。由于这样的重定向是临时的，客户端应当继续向原有地址发送以后的请求 */
	public static final int SC_MOVED_TEMPORARILY = 302;
	/** 对应当前请求的响应可以在另一个 URI 上被找到，而且客户端应当采用 GET 的方式访问那个资源 */
	public static final int SC_SEE_OTHER = 303;
	/** 对应当前请求的响应可以在另一个 URI 上被找到，而且客户端应当采用 GET 的方式访问那个资源 */
	public static final int SC_NOT_MODIFIED = 304;
	/** 被请求的资源必须通过指定的代理才能被访问 */
	public static final int SC_USE_PROXY = 305;
	/** 请求的资源临时从不同的URI 响应请求 */
	public static final int SC_TEMPORARY_REDIRECT = 307;
	// 请求错误（4字头）
	/** 语义有误，当前请求无法被服务器理解或请求参数有误 */
	public static final int SC_BAD_REQUEST = 400;
	/** 当前请求需要用户验证 */
	public static final int SC_UNAUTHORIZED = 401;
	/** 该状态码是为了将来可能的需求而预留的 */
	public static final int SC_PAYMENT_REQUIRED = 402;
	/** 服务器已经理解请求，但是拒绝执行它 */
	public static final int SC_FORBIDDEN = 403;
	/** 请求失败，请求所希望得到的资源未被在服务器上发现 */
	public static final int SC_NOT_FOUND = 404;
	/** 请求行中指定的请求方法不能被用于请求相应的资源 */
	public static final int SC_METHOD_NOT_ALLOWED = 405;
	/** 请求的资源的内容特性无法满足请求头中的条件，因而无法生成响应实体 */
	public static final int SC_NOT_ACCEPTABLE = 406;
	/** 与401响应类似，只不过客户端必须在代理服务器上进行身份验证 */
	public static final int SC_PROXY_AUTHENTICATION_REQUIRED = 407;
	/** 请求超时 */
	public static final int SC_REQUEST_TIMEOUT = 408;
	/** 由于和被请求的资源的当前状态之间存在冲突，请求无法完成 */
	public static final int SC_CONFLICT = 409;
	/** 被请求的资源在服务器上已经不再可用，而且没有任何已知的转发地址 */
	public static final int SC_GONE = 410;
	/** 服务器拒绝在没有定义 Content-Length 头的情况下接受请求 */
	public static final int SC_LENGTH_REQUIRED = 411;
	/** 服务器在验证在请求的头字段中给出先决条件时，没能满足其中的一个或多个 */
	public static final int SC_PRECONDITION_FAILED = 412;
	/** 服务器拒绝处理当前请求，因为该请求提交的实体数据大小超过了服务器愿意或者能够处理的范围 */
	public static final int SC_REQUEST_TOO_LONG = 413;
	/** 请求的URI 长度超过了服务器能够解释的长度，因此服务器拒绝对该请求提供服务 */
	public static final int SC_REQUEST_URI_TOO_LONG = 414;
	/** 对于当前请求的方法和所请求的资源，请求中提交的实体并不是服务器中所支持的格式，因此请求被拒绝 */
	public static final int SC_UNSUPPORTED_MEDIA_TYPE = 415;
	/**
	 * 如果请求中包含了 Range 请求头，并且 Range 中指定的任何数据范围都与当前资源的可用范围不重合，同时请求中又没有定义 If-Range
	 * 请求头，那么服务器就应当返回416状态码
	 */
	public static final int SC_REQUESTED_RANGE_NOT_SATISFIABLE = 416;
	/**
	 * 在请求头 Expect 中指定的预期内容无法被服务器满足，或者这个服务器是一个代理服务器，它有明显的证据证明在当前路由的下一个节点上，Expect
	 * 的内容无法被满足。
	 */
	public static final int SC_EXPECTATION_FAILED = 417;
	public static final int SC_INSUFFICIENT_SPACE_ON_RESOURCE = 419;
	public static final int SC_METHOD_FAILURE = 420;
	/** 请求格式正确，但是由于含有语义错误，无法响应 */
	public static final int SC_UNPROCESSABLE_ENTITY = 422;
	/** 当前资源被锁定 */
	public static final int SC_LOCKED = 423;
	/** 由于之前的某个请求发生的错误，导致当前请求失败 */
	public static final int SC_FAILED_DEPENDENCY = 424;
	// 服务器错误（5字头）
	/** 服务器遇到了一个未曾预料的状况，导致了它无法完成对请求的处理 */
	public static final int SC_INTERNAL_SERVER_ERROR = 500;
	/** 服务器不支持当前请求所需要的某个功能 */
	public static final int SC_NOT_IMPLEMENTED = 501;
	/** 作为网关或者代理工作的服务器尝试执行请求时，从上游服务器接收到无效的响应 */
	public static final int SC_BAD_GATEWAY = 502;
	/** 由于临时的服务器维护或者过载，服务器当前无法处理请求 */
	public static final int SC_SERVICE_UNAVAILABLE = 503;
	/**
	 * 作为网关或者代理工作的服务器尝试执行请求时，未能及时从上游服务器（URI标识出的服务器，例如HTTP、FTP、LDAP）或者辅助服务器（例如DNS
	 * ）收到响应
	 */
	public static final int SC_GATEWAY_TIMEOUT = 504;
	/** 服务器不支持，或者拒绝支持在请求中使用的 HTTP 版本 */
	public static final int SC_HTTP_VERSION_NOT_SUPPORTED = 505;
	/** 服务器无法存储完成请求所必须的内容。这个状况被认为是临时的 */
	public static final int SC_INSUFFICIENT_STORAGE = 507;
}