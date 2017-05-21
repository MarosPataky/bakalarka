package sk.pataky.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.geo.Point;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.geo.GeoJsonPoint;
import org.springframework.data.mongodb.core.geo.GeoJsonPolygon;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import sk.pataky.dto.CoordinateDto;
import sk.pataky.dto.CreateShippingOptionDto;
import sk.pataky.dto.PolygonDto;
import sk.pataky.dto.ShippingOptionDto;
import sk.pataky.model.ShippingOption;
import sk.pataky.repository.ShippingOptionRepository;
import sk.pataky.security.UserDetailsImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 *
 */
@Service
public class ShippingOptionServiceImpl implements ShippingOptionService {

    @Autowired
    private ShippingOptionRepository shippingOptionRepository;

    @Autowired
    private MongoTemplate mongoTemplate;

    @Override
    public String createShippingOption(CreateShippingOptionDto createShippingOptionDto) {
        String userId = ((UserDetailsImpl) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getId();

        ShippingOption shippingOption = fromDto(createShippingOptionDto);
        shippingOption.setCreatedBy(userId);

        return shippingOptionRepository.save(shippingOption).getId();
    }

    private ShippingOption fromDto(CreateShippingOptionDto createShippingOptionDto) {
        ShippingOption shippingOption = new ShippingOption();
        shippingOption.setShippingProviderName(createShippingOptionDto.name);
        shippingOption.setShippingCost(createShippingOptionDto.shippingCost);

        List<Point> polygonPoints = new ArrayList<>();
        createShippingOptionDto.area.polygon.forEach(coordinateDto -> {
            polygonPoints.add(new Point(coordinateDto.lat, coordinateDto.lon));
        });
        GeoJsonPolygon geoJsonPolygon = new GeoJsonPolygon(polygonPoints);
        shippingOption.setLocation(geoJsonPolygon);
        return shippingOption;
    }

    @Override
    public List<ShippingOptionDto> findAll(Double latitude, Double longitude) {
        List<ShippingOption> shippingOptions;
        if (latitude != null && longitude != null) {
            shippingOptions = mongoTemplate.find(new Query(
                    Criteria.where("location").intersects((new GeoJsonPoint(latitude, longitude)))), ShippingOption.class);
//            return shippingOptionRepository.findByLocationNear(new Point(latitude, longitude), new Distance(distance, Metrics.KILOMETERS));
        } else {
            shippingOptions = shippingOptionRepository.findAll();
        }

        return shippingOptions.stream().map(this::toDto).collect(Collectors.toList());

    }

    @Override
    public void updateShippingOption(String id, CreateShippingOptionDto createShippingOptionDto) {
        ShippingOption option = shippingOptionRepository.findOne(id);
        if (option == null) {
            // todo throw exception
            return;
        }

        ShippingOption shippingOption = fromDto(createShippingOptionDto);
        shippingOption.setId(option.getId());

        shippingOptionRepository.save(shippingOption);
    }

    @Override
    public void deleteShippingOption(String id) {
        shippingOptionRepository.delete(id);
    }

    private ShippingOptionDto toDto(ShippingOption shippingOption) {
        ShippingOptionDto dto = new ShippingOptionDto();
        dto.id = shippingOption.getId();
        dto.shippingCost = shippingOption.getShippingCost();
        dto.name = shippingOption.getShippingProviderName();

        PolygonDto polygonDto = new PolygonDto();
        List<CoordinateDto> coordinateDtos = new ArrayList<>();
        for (Point point : shippingOption.getLocation().getPoints()) {
            CoordinateDto coordinateDto = new CoordinateDto();
            coordinateDto.lat = point.getX();
            coordinateDto.lon = point.getY();
            coordinateDtos.add(coordinateDto);
        }
        polygonDto.polygon = coordinateDtos;

        dto.area = polygonDto;
        return dto;
    }
}
