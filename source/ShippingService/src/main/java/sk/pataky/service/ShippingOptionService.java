package sk.pataky.service;

import sk.pataky.dto.CreateShippingOptionDto;
import sk.pataky.dto.ShippingOptionDto;

import java.util.List;

/**
 *
 */
public interface ShippingOptionService {
    String createShippingOption(CreateShippingOptionDto createShippingOptionDto);

    List<ShippingOptionDto> findAll(Double latitude, Double longitude);

    void updateShippingOption(String id, CreateShippingOptionDto createShippingOptionDto);

    void deleteShippingOption(String id);
}
