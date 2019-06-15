package filters;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.UnavailableException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.context.ApplicationContext;

import database.BookDB;
import service.OrderServiceImpl;
import service.UserService;
import service.UserServiceImpl;

public class MainFilter implements Filter {
	private ApplicationContext ctx;

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		
		HttpServletRequest req = (HttpServletRequest)request;
		if (req.getRequestURI().endsWith("checklogin")) {
			String userid = (String)req.getParameter("userid");
			req.getSession().setAttribute("userid", userid);
			String pwd = req.getParameter("pwd");
			req.getSession().setAttribute("pwd", pwd);
			
			try {
				UserService us = (UserService)ctx.getBean("userServiceImpl");
				String username = us.checkuser(userid, pwd);
				req.getSession().setAttribute("username", username);
				HttpServletResponse resp = (HttpServletResponse)response;
				resp.sendRedirect(req.getContextPath()+"/enter");
				return;
			} catch (Exception e) {
				req.getSession().setAttribute("errmsg", e.getMessage());
				req.getRequestDispatcher("/WEB-INF/jsp/login.jsp").forward(request, response);
				return;
			}
		}
		
		if (req.getRequestURI().endsWith("logout")) {
			req.getSession().invalidate();
		}
		
		
		String username = (String)req.getSession().getAttribute("username");
		if (username == null) {
			req.getRequestDispatcher("/WEB-INF/jsp/login.jsp").forward(request, response);
			return;
		}
		chain.doFilter(request, response);
	}
		
	public void init(FilterConfig fConfig) throws ServletException {
    	ctx = (ApplicationContext) fConfig.getServletContext().getAttribute("applicationContext");
        if (ctx == null)
            throw new UnavailableException("Couldn't init Spring.");
	}

}
