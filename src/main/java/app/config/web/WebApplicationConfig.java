package app.config.web;

import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Component
public class WebApplicationConfig implements WebMvcConfigurer {
    private final IncomingRequestLogInterceptor incomingRequestLogInterceptor;

    public WebApplicationConfig(IncomingRequestLogInterceptor incomingRequestLogInterceptor) {
        this.incomingRequestLogInterceptor = incomingRequestLogInterceptor;
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(incomingRequestLogInterceptor);
    }

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }
}
