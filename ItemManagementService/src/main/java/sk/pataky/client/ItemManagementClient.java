package sk.pataky.client;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

/**
 * Created by macbook on 14/03/2017.
 */

@Component
public class ItemManagementClient {

    private static final Logger LOGGER = LoggerFactory.getLogger(ItemManagementClient.class);

    @Autowired
    private RestTemplate itemManagementServiceRestTemplate;


    public void getAllItems() {
        String response = itemManagementServiceRestTemplate.getForObject("http://itemManagement/items", String.class);
        LOGGER.info("GetAllItems response is: " + response);
    }



}
