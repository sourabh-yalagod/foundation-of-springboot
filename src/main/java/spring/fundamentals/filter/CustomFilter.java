package spring.fundamentals.filter;

import jakarta.servlet.*;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.IOException;

public class CustomFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        Filter.super.init(filterConfig);
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        System.out.println("Before Filter");
        filterChain.doFilter(servletRequest, servletResponse);
        System.out.println("After Filter");
    }

    @Override
    public void destroy() {
        Filter.super.destroy();
    }
}

@Configuration
class RegisterFilterConfig {
    @Bean
    public FilterRegistrationBean<CustomFilter> runCustomFilter() {
        FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean();
        filterRegistrationBean.setFilter(new CustomFilter());
        filterRegistrationBean.addUrlPatterns("/api/*");
        filterRegistrationBean.setOrder(1);
        return filterRegistrationBean;
    }
}