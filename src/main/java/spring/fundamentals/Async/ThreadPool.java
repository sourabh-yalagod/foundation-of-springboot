package spring.fundamentals.Async;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.Executor;
import java.util.concurrent.ThreadPoolExecutor;

@Configuration
public class ThreadPool {
    @Bean
    public ThreadPoolTaskExecutor customThreadPoolExecutor() {
        ThreadPoolTaskExecutor threadPoolTaskExecutor = new ThreadPoolTaskExecutor();
        threadPoolTaskExecutor.setCorePoolSize(10);
        threadPoolTaskExecutor.setMaxPoolSize(15);
        threadPoolTaskExecutor.setThreadNamePrefix("custom-thread");
        threadPoolTaskExecutor.setQueueCapacity(3);
        threadPoolTaskExecutor.initialize();
        return threadPoolTaskExecutor;
    }
}
