package training.nadia.hospital.controller.filter;

import javax.servlet.*;
import java.io.IOException;

public class LoginFilter implements Filter {

    private FilterConfig filterConfig = null;
    private boolean active = false;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

        this.filterConfig = filterConfig;

        String activeParameter = filterConfig.getInitParameter("active");

        if(activeParameter != null){
            active = activeParameter.toUpperCase().equals("TRUE");
        }
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

        if(active){
            //код
        }

        filterChain.doFilter(servletRequest, servletResponse);

    }

    @Override
    public void destroy() {

        filterConfig = null;
    }
}
