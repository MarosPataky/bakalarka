package sk.pataky.dto;

import org.springframework.data.mongodb.core.geo.GeoJsonPoint;

/**
 *
 */
public class StoreDto {
    public String id;
    public String brand;

    public CoordinateDto location;
}
