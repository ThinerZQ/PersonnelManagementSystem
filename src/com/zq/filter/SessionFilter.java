package com.zq.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletResponseWrapper;

public class SessionFilter implements Filter {

	private FilterConfig config = null;
	private String redirectPath=null;
	private String logonStrings=null;
	public void destroy() {
		
		this.config=null;
	}

	
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain filterchain) throws IOException, ServletException {
		
			HttpServletRequest httpreq = (HttpServletRequest)request;
			HttpServletResponse httpresp = (HttpServletResponse)response;
			HttpServletResponseWrapper httpwrapper= new HttpServletResponseWrapper (httpresp);
			
			Object user = httpreq.getSession().getAttribute("user");
			redirectPath = httpreq.getContextPath()+config.getInitParameter("redirectPath");
			logonStrings = config.getInitParameter("logonStrings");
			String[] logonList = logonStrings.split(";");
			
			
			if(user==null){
				if (this.isContains(httpreq.getRequestURI(), logonList)) {
					
                     filterchain.doFilter(request, response);
                     return;
                     
               }

				httpwrapper.sendRedirect(redirectPath);
			}
			
			
	}

	
	public void init(FilterConfig config) throws ServletException {
		this.config=config;
		
	}
	public static boolean isContains(String container, String[] regx) {
        boolean result = false;

        for (int i = 0; i < regx.length; i++) {
              if (container.indexOf(regx[i]) != -1) {
                    return true;
              }
        }
        return result;
  }


}
