package sk.pataky.dto;

import org.springframework.data.mongodb.core.geo.GeoJsonPolygon;

/**
 *
 */
public class CreateShippingOptionDto {

    public String name;

    public Long shippingCost;

    public PolygonDto area;
}
