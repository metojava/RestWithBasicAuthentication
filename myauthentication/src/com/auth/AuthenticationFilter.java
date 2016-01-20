package com.auth;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class AuthenticationFilter implements Filter {

	public static final String auth_filter = "authenticate";

	@Override
	public void destroy() {
		// TODO Auto-generated method stub

	}

	@Override
	public void doFilter(ServletRequest req, ServletResponse res,
			FilterChain filter) throws IOException, ServletException {
		HttpServletResponse resp = null;
		
		HttpServletRequest request = (HttpServletRequest) req;
		String credentials = request.getHeader(auth_filter);

		String ipAddress = request.getRemoteAddr();
		String remoteHost = request.getRemoteHost();
		List<String> clientInfo = new ArrayList<String>();
		clientInfo.add(ipAddress);
		clientInfo.add(remoteHost);
		clientInfo.add(request.getAuthType());
		clientInfo.add(request.getHeader(auth_filter));
		AuthService auth = new AuthService();
		if (auth.authenticate(credentials,clientInfo)) {
			filter.doFilter(req, res);
		} else {
			if (res instanceof HttpServletResponse)
				resp = (HttpServletResponse) res;
			resp.setStatus(HttpServletResponse.SC_UNAUTHORIZED);

		}
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
		// TODO Auto-generated method stub

	}

}
