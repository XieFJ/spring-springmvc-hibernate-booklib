package cn.edu.zucc.booklib.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

public class LoginInterceptor implements HandlerInterceptor {

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception arg3)
			throws Exception {
	}

	@Override
	public void postHandle(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2, ModelAndView arg3)
			throws Exception {
		// TODO Auto-generated method stub

	}

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		String url = request.getRequestURI();
		if(url.indexOf("/login")>=0) {
			return true;
		}
		if(url.indexOf("/checkUser")>=0) {
			return true;
		}
		if(url.indexOf("/loadCategory")>=0) {
			return true;
		}
		if(url.indexOf("/books/")>=0) {
			return true;
		}
		if(url.indexOf("/loadBook")>=0) {
			return true;
		}
		if(url.indexOf("/detail/")>=0) {
			return true;
		}
		if(url.indexOf("/loadDetail")>=0) {
			return true;
		}
		if(url.indexOf("/loadRecord")>=0) {
			return true;
		}
		if(url.indexOf("/search")>=0) {
			return true;
		}
		if(url.indexOf("/addItem/")>=0) {
			return true;
		}
		if(url.indexOf("/removeItem/")>=0) {
			return true;
		}
		if(url.indexOf("/showItem")>=0) {
			return true;
		}
		if(url.indexOf("/borrow")>=0) {
			return true;
		}
		if(url.indexOf("/showRecord")>=0) {
			return true;
		}
		if(url.indexOf("/renew/")>=0) {
			return true;
		}
		if(url.indexOf("/return/")>=0) {
			return true;
		}
		if(url.indexOf("BookType")>=0) {
			return true;
		}
		if(url.indexOf("ReaderType")>=0) {
			return true;
		}
		if(url.indexOf("Book")>=0) {
			return true;
		}
		if(url.indexOf("Reader")>=0) {
			return true;
		}
		if(url.indexOf("/calPenalty")>=0) {
			return true;
		}
		if(url.indexOf("Details")>=0) {
			return true;
		}
		if(url.indexOf("User")>=0) {
			return true;
		}
		HttpSession session = request.getSession();
		if(session.getAttribute("USER_SESSION") != null) {
			return true;
		}
		request.setAttribute("msg", "您还没有登录，请先登录！");
		request.getRequestDispatcher("/login").forward(request, response);
		
		return false;
	}

}
