import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebFilter(filterName = "CheckAdminFilter")
public class CheckAdminFilter implements Filter {
    public void destroy() {
    }

    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
        if (((HttpServletRequest) req).getSession().getAttribute("type") == null) {
            ((HttpServletResponse) resp).sendRedirect("../index");
        } else {
            int type = (int) ((HttpServletRequest) req).getSession().getAttribute("type");
            if (type == 3)
                chain.doFilter(req, resp);
            else
                ((HttpServletResponse) resp).sendRedirect("../index");

        }
    }

    public void init(FilterConfig config) throws ServletException {

    }

}
