package app.geoaddress;

import app.model.GeoAddress;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class GeoAddressToDtoConverter implements Converter<GeoAddress, GeoAddressDto> {
    @Override
    public GeoAddressDto convert(GeoAddress source) {
        return new GeoAddressDto(
                new GeoAddressDto.Address(source.address().string()),
                new GeoAddressDto.Coordinates(source.coordinates().x(), source.coordinates().y())
        );
    }
}
