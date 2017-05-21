package sk.pataky.dto;

import java.util.List;

/**
 *
 */
public class CalculationResponseDto {
    public List<ShoppingOption> shoppingOptions;

    // either shops are filles, if shipping was not requried
    public List<StoreLocationResponseDto> stores;
    public List<ShippingOptionsResponseDto> shipping;
}
