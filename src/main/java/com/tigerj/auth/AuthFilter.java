package com.tigerj.auth;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

import com.tigerj.common.AuthHelper;
import com.tigerj.common.AuthUtil;
import com.tigerj.common.RequestWrapper;

import lombok.extern.log4j.Log4j2;

@Log4j2
@Component
public class AuthFilter implements Filter,InitializingBean {
	
	@Value("${auth.auth.do}")
	private Boolean doAuth;
	
	@Value("${auth.token.do}")
	private Boolean doToken;
	
	private Set<String> tokenWhiteList;
	
	private Set<String> authWhiteList;
	
	@Autowired
	private AuthUtil authUtil;

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		HttpServletResponse res = (HttpServletResponse) response;
		HttpServletRequest req = new RequestWrapper((HttpServletRequest) request);
		//check sign
		signCheck(req, res);
		//check token
		tokenCheck(req, res);
		//check risk rule
		authUtil.checkRisk(req);
		chain.doFilter(req, response);
	}
	
	
	
	void tokenCheck(HttpServletRequest req,HttpServletResponse res) throws IOException{
		if (doToken && !tokenWhiteList.contains(req.getRequestURI())
				&& !authUtil.checkToken(req)) {
			AuthHelper.returnMsg(res, AuthHelper.CODE_TOKEN_FAIL);
			return;
		}
		log.debug("token check pass.");
	}
	
	
	
	void signCheck(HttpServletRequest req,HttpServletResponse res) throws IOException{
		if (doAuth && !authWhiteList.contains(req.getRequestURI())) {
			String platform = req.getParameter("platform");
			Assert.hasText(platform,"'platform' must not be empty.");
			String srcret = getSecret(platform);
			Assert.hasText(srcret,"'srcret' must not be empty.");
			log.debug("platform:{},srcret:{}",platform,srcret);
			
			int signResultCode = authUtil.checkSign(req, srcret);
			log.debug("sign code:{}",signResultCode);
			if (AuthHelper.CODE_SUCCESS != signResultCode) {
				AuthHelper.returnMsg(res, signResultCode);
				return;
			}
			log.debug("sign check pass.");
		}
	}

	@Override
	public void destroy() {

	}
	
	/**
	 * 获取平台对应的secret
	 * @param platform
	 * @return
	 */
	private String getSecret(String platform){
		return "";
	}

	@Override
	public void afterPropertiesSet() throws Exception {
		authWhiteList = new HashSet<>();
		tokenWhiteList = new HashSet<>();
		
		authWhiteList.add("");
		tokenWhiteList.add("");
		
	}
	
	

}
