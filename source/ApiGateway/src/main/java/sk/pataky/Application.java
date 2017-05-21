package sk.pataky;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.core.env.Environment;

@SpringBootApplication
@EnableZuulProxy
@EnableDiscoveryClient
public class Application implements CommandLineRunner {

	@Autowired
	Environment environment;

	public static void main(String[] args) {

		SpringApplication.run(Application.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		System.out.println("Testing property value = " + environment.getProperty("testing.property"));
	}
}
