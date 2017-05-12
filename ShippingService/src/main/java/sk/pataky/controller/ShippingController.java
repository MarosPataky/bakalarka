package sk.pataky.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 */
@RestController()
@RequestMapping(path = "/shipping")
@RefreshScope
public class ShippingController {

    @Value("${findShippingOptions.calculation.delay:100}")
    Long delay;

    @RequestMapping(method = RequestMethod.GET)
    public void findShippingOptions() throws InterruptedException {
        Thread.sleep(delay);
    }
}
