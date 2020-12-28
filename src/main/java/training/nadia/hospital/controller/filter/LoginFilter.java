package training.nadia.hospital.controller.filter;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class LoginFilter implements Filter {

    private FilterConfig filterConfig = null;
    private boolean active = false;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

        this.filterConfig = filterConfig;
        if (filterConfig.getInitParameter("active") != null) {
            active = filterConfig.getInitParameter("active").toUpperCase().equals("TRUE");
        }
    }

    @Override
    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws IOException, ServletException {

        if (active) {

            HttpServletRequest request = (HttpServletRequest) req;
            HttpServletResponse response = (HttpServletResponse) resp;

            if (request.getSession().getAttribute("user") == null) {

                RequestDispatcher requestDispatcher = request.getRequestDispatcher("/page/login.jsp");
                requestDispatcher.forward(request, response);
            }
        }

        chain.doFilter(req, resp);
    }

    @Override
    public void destroy() {

        filterConfig = null;
    }
}
