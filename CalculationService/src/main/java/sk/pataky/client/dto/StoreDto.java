package sk.pataky.client.dto;

import org.springframework.data.mongodb.core.geo.GeoJsonPoint;

/**
 *
 */
public class StoreDto {
    public String id;
    public String brand;

    // todo: custom object for location
    public GeoJsonPoint location;
}
