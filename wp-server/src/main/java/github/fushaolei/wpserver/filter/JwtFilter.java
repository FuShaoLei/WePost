package github.fushaolei.wpserver.filter;


import com.auth0.jwt.interfaces.Claim;
import github.fushaolei.wpserver.utils.JwtUtil;
import github.fushaolei.wpserver.utils.TextUtil;


import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

@WebFilter(filterName = "JwtFilter", urlPatterns = "/*")
public class JwtFilter implements Filter {
    List<String> unMatch = Arrays.asList("/login", "/register");

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        Filter.super.init(filterConfig);
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws ServletException, IOException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        response.setCharacterEncoding("UTF-8");

        String path = request.getServletPath();
        System.out.println("path = " + path);

        if (!TextUtil.isInList(unMatch, path)) {
            String token = request.getHeader("authorization");
            if (token == null || token.isEmpty()) {
                response.getWriter().write("no token,Can not access");
                System.out.println("no token,Can not access");
                return;
            }
            Map<String, Claim> data = JwtUtil.verifyToken(token);
            if (data == null) {
                response.getWriter().write("token illegal");
                System.out.println("token illegal");
                return;
            }
            int id = data.get("id").asInt();
            request.setAttribute("id", id);
        }
        filterChain.doFilter(servletRequest, servletResponse);

    }

    @Override
    public void destroy() {
        Filter.super.destroy();
    }

}