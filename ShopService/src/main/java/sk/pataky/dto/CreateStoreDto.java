package sk.pataky.dto;

import org.springframework.data.mongodb.core.geo.GeoJsonPoint;

/**
 *
 */
public class CreateStoreDto {

    public String brand;

    public CoordinateDto location;
}
