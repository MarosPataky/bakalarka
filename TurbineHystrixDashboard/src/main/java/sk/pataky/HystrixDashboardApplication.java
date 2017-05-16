package sk.pataky;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;
import org.springframework.cloud.netflix.turbine.EnableTurbine;
import org.springframework.core.env.Environment;

@SpringBootApplication
@EnableHystrixDashboard
@EnableTurbine
@EnableDiscoveryClient
public class HystrixDashboardApplication implements CommandLineRunner {

	@Autowired
	Environment environment;

	public static void main(String[] args) {

		SpringApplication.run(HystrixDashboardApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		System.out.println("Testing property value = " + environment.getProperty("testing.property"));
	}
}
