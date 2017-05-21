package sk.pataky;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.core.env.Environment;
import org.springframework.data.geo.Distance;
import org.springframework.data.geo.Metrics;
import org.springframework.data.mongodb.core.geo.GeoJsonPoint;
import sk.pataky.model.Store;
import sk.pataky.repository.StoreRepository;

import java.util.List;

@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients
@EnableCircuitBreaker
public class Application implements CommandLineRunner {

    private static final Logger LOGGER = LoggerFactory.getLogger(Application.class);

	@Autowired
    private StoreRepository storeRepository;

    @Autowired
    private Environment environment;

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	@Override
	public void run(String... strings) throws Exception {
        Store store = new Store();
        store.setBrand("Tesco");
        store.setLocation(new GeoJsonPoint(48.6993873, 21.6624062));
//        storeRepository.save(store);

        Store store2 = new Store();
        store2.setBrand("Fresh");
        store2.setLocation(new GeoJsonPoint(48.6976396,21.6570515));
//        storeRepository.save(store2);

        // 500m dalej 48.6976396,21.6570515
		List<Store> allStores = storeRepository.findAll();

        List<Store> storesNear = storeRepository.findByLocationNear(new GeoJsonPoint(48.6993873, 21.6624062), new Distance(0.2, Metrics.KILOMETERS));
        LOGGER.info("Found these stores in DB: " + allStores);
        // TODO: remove, just testing
        LOGGER.info("Shop-propert has value {}", environment.getProperty("shop-property"));
    }

}
