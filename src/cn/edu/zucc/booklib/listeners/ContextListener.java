/*
 * 
 * Copyright 2001 Sun Microsystems, Inc. All Rights Reserved.
 * 
 * This software is the proprietary information of Sun Microsystems, Inc. Use is
 * subject to license terms.
 *  
 */

package cn.edu.zucc.booklib.listeners;


import javax.servlet.*;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;


public final class ContextListener implements ServletContextListener {
    private ServletContext context = null;
    
    public void contextInitialized(ServletContextEvent event) {
        context = event.getServletContext();
        String url = context.getInitParameter("contextConfigLocation");  

        ApplicationContext ctx = new ClassPathXmlApplicationContext(url);
        context.setAttribute("applicationContext", ctx);

    }

    public void contextDestroyed(ServletContextEvent event) {
        context = event.getServletContext();
        context.removeAttribute("applicationContext");
//        context.removeAttribute("bookDB");
    }
}
