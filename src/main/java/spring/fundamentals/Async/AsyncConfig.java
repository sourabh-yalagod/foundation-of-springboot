package spring.fundamentals.Async;

import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.AsyncConfigurer;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.*;

@Configuration
public class AsyncConfig implements AsyncConfigurer {
    public Executor getThreadPoolTaskExecutor() {
        ThreadPool threadPool = new ThreadPoolExecutor(10, 5, 5, TimeUnit.HOURS, new ArrayBlockingQueue<>(), new CustomThreadFactory());
        return threadPool;
    }
}

class CustomThreadFactory implements ThreadFactory {
    @Override
    public Thread newThread(Runnable r) {
        Thread thread = new Thread(r);
        thread.setName("custom-thread");
        return thread;
    }
}
