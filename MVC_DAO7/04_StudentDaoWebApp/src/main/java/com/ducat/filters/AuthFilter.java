package com.ducat.filters;

import jakarta.annotation.Priority;
import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.FilterConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;

@WebFilter("/*") // Apply filter to all requests
@Priority(2)
public class AuthFilter extends HttpFilter implements Filter {

	private final String PUBLIC_URLS[]= { "/","/LoginController","/LoginHandler","/RegisterController"};
	private static long count=0;
	private static final long serialVersionUID = 1L;

	public void init(FilterConfig fConfig) throws ServletException {
		System.out.println("AuthFilter initialized!");
	}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
//		System.out.println("filtering..."+(++count));
		
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse res = (HttpServletResponse) response;
		HttpSession session = req.getSession(false);
		boolean isLoggedIn = (session != null && session.getAttribute("user") != null);
		boolean isPublicResource = req.getRequestURI().endsWith(".css") || req.getRequestURI().endsWith(".js") || req.getRequestURI().endsWith(".svg"); // Allow CSS and JS
		boolean isAuthenticated=false;
		for (String uri : PUBLIC_URLS) {
			String url=req.getContextPath()+uri;
//			System.out.println("Auth URL:"+url+" Req URI:"+uri);
			if(req.getRequestURI().equals(url) || isLoggedIn || isPublicResource)
			{
				isAuthenticated=true;
				chain.doFilter(request, response); // User is authenticated, proceed
				break;
			}
		}
		
		if (!isAuthenticated) {
			req.setAttribute("msg","pls login first");
			req.getRequestDispatcher("/LoginController").forward(req, res); // Redirect to login page
		}
		
	}

	public void destroy() {
		System.out.println("AuthFilter destroyed!");
	}
}
