package sk.pataky.dto;

import java.util.List;

/**
 *
 */
public class CalculationResponseDto {
    public List<CalculationResponseShoppingOption> shoppingOptions;

    // either shops are filles, if shipping was not requried
    public List<CalculationResponseStoreLocationDto> stores;
    public List<CalculationResponseShippingOptionDto> shipping;
}
