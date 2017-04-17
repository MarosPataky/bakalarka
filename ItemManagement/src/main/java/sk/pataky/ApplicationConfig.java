package sk.pataky;

import org.apache.catalina.Executor;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.task.SimpleAsyncTaskExecutor;
import org.springframework.scheduling.TaskScheduler;
import org.springframework.scheduling.concurrent.ConcurrentTaskScheduler;
import org.springframework.web.client.RestTemplate;

/**
 * Created by macbook on 14/03/2017.
 */

@Configuration
public class ApplicationConfig {

    @LoadBalanced
    @Bean
    public RestTemplate itemManagementServiceRestTemplate() {
        return new RestTemplate();
    }

}
