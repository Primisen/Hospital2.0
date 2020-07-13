package training.nadia.hospital.controller.filter;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class LoginFilter implements Filter {

    private FilterConfig filterConfig = null;
    private boolean active = false;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

        this.filterConfig = filterConfig;

        String activeParameter = filterConfig.getInitParameter("active");

        if (activeParameter != null) {
            active = activeParameter.toUpperCase().equals("TRUE");
        }
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

        HttpServletRequest req = (HttpServletRequest) servletRequest;
        HttpServletResponse res = (HttpServletResponse) servletResponse;

        HttpSession session = req.getSession(false);

        if (active) {

            if (session == null || session.getAttribute("user") == null) {
                res.sendRedirect("/login");
                return;
            }
        }

        filterChain.doFilter(req, servletResponse);

    }

    @Override
    public void destroy() {

        filterConfig = null;
    }
}
