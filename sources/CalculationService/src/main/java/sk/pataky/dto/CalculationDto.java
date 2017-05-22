package sk.pataky.dto;

import java.util.List;

/**
 *
 */
public class CalculationDto {
    public List<String> shopBrands;
    public List<CalculationItemEntry> items;
    public CalculationRequestShippingDto shipping;
    public CalculationUserPositionDto location;


}
