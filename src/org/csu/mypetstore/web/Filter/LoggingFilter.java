package org.csu.mypetstore.web.Filter;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;

//public class LoggingFilter implements Filter {
//    public void destroy() {
//    }
//
//    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
//        chain.doFilter(req, resp);
//    }
//
//    public void init(FilterConfig config) throws ServletException {
//
//    }
public class LoggingFilter implements Filter{
    //声明两个变量 PrintWriter用来编写文本文件 prefix作为每个日志条目的前缀
    private PrintWriter logger;
    private String prefix;

    @Override
    public void destroy() {
        // TODO Auto-generated method stub
        //执行init会创建一个日志文件，如果应用程序目录下已经有同名文件存在，该文件将会被新文件覆盖
        System.out.println("destroying filter");
        if(logger != null){
            logger.close();
        }
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response,
                         FilterChain filterChain) throws IOException, ServletException {
        // TODO Auto-generated method stub
        System.out.println("LoggingFilter.doFilter");
        /*doFilter方法通过将ServletRequest向下装换成HttpServletRequest,并调用其getRequestURI方法，记录下所有请求的日志。
         * 然后将getRequestURI的结果填入PrintWriter的println方法中
         */
        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        logger.println("时间："+new Date()+" \n"+"prefix:"+prefix+"\nURI:"+httpServletRequest.getRequestURI());
        /*
         * 每个日志都有一个时间戳和前缀，以便识别。
         * 之后doFilter方法接着刷新PrintWriter,并调用FilterChain.doFilter来调用资源。
         */
        logger.flush();
        filterChain.doFilter(request, response);
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        /*
         * init方法在传入的FilterConfig中调用getInitParameter方法，获取prefix和logFileName的初始值。
         * prefix参数值赋给类级变量prefix,并与用logFileName创建一个PrintWriter
         */
        prefix = filterConfig.getInitParameter("prefix");
        String logFileName = filterConfig.getInitParameter("logFileName");
        /*
         * 为了在应用程序目录中创建一个日志文件，需要有一个它的绝对路径。
         * 利用getRealPath获取，也就是将应用程序的logFileName初始参数合并
         */
        String appPath = filterConfig.getServletContext().getRealPath("/");
        System.out.println(appPath);
        System.out.println("logFileName:"+logFileName);
        try {
            logger = new PrintWriter(new File(appPath,logFileName));
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}
