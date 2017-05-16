package sk.pataky.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.geo.GeoJsonPoint;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 *
 */
@Document(collection = "shippingOptions")
public class ShippingOption {

    @Id
    private String id;

    private String shippingProviderName;

    private Long shippingCost;

    private GeoJsonPoint location;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getShippingProviderName() {
        return shippingProviderName;
    }

    public void setShippingProviderName(String shippingProviderName) {
        this.shippingProviderName = shippingProviderName;
    }

    public GeoJsonPoint getLocation() {
        return location;
    }

    public void setLocation(GeoJsonPoint location) {
        this.location = location;
    }

    public Long getShippingCost() {
        return shippingCost;
    }

    public void setShippingCost(Long shippingCost) {
        this.shippingCost = shippingCost;
    }
}
