package app.geoaddress;

import app.model.GeoAddress;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class GeoAddressToDtoConverter implements Converter<GeoAddress, GeoAddressDto> {
    private static final Log log = LogFactory.getLog(GeoAddressToDtoConverter.class);
    @Override
    public GeoAddressDto convert(GeoAddress source) {
        var geoAddressDto = new GeoAddressDto(
                new GeoAddressDto.Address(source.address().fullName()),
                new GeoAddressDto.Coordinates(source.coordinates().x(), source.coordinates().y())
        );

        if (log.isDebugEnabled()) log.debug("Converting: " + source + " ->  " + geoAddressDto);

        return geoAddressDto;
    }
}
