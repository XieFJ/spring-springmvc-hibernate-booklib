package filters;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import util.IBusi;

/**
 * Servlet Filter implementation class filter
 */
public class filter implements Filter {

	private String pack;
    /**
     * Default constructor. 
     */
    public filter() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
		// TODO Auto-generated method stub
	}

	private Map<String,IBusi> busiMap = new HashMap<>(); 		//»º´æ
	private synchronized IBusi get(String className) {
		IBusi result=busiMap.get(className);
		if(result==null) {
			try {
				Class<?> cls = Class.forName(className);
				Object obj = cls.newInstance();
				result = (IBusi) obj;
				busiMap.put(className, result);			
			} catch (ClassNotFoundException | InstantiationException | IllegalAccessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else {
			return result;
		}
		return null;
	}
	 
	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest)request;
		String url = req.getRequestURI();
		System.out.println(url); 					//Àý"/practice3/enter"
		url = url.replaceFirst(req.getContextPath(), "");		//Àý"/enter"
		url = url.replaceFirst("/", "").replaceAll("/", ".");		//Àý"enter"
		String className = pack + "." +url;			//Àý"filters.main.enter"
		IBusi busi = this.get(className);
		if(busi==null)
			chain.doFilter(request, response);
		else
			busi.execute(req, (HttpServletResponse)response);
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
		pack = fConfig.getInitParameter("pack");
	}

}
