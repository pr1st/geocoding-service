package app.geoaddress;

import app.geocoding.GeoCodingService;
import app.model.GeoAddress;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Service;

@Service
public class GeoAddressService {
    private static final Log log = LogFactory.getLog(GeoAddressService.class);

    private final GeoAddressRepository geoAddressRepository;
    private final GeoCodingService geoCodingService;

    public GeoAddressService(GeoAddressRepository geoAddressRepository,
                             GeoCodingService geoCodingService) {
        this.geoAddressRepository = geoAddressRepository;
        this.geoCodingService = geoCodingService;
    }

    public GeoAddress getGeoAddressByCoordinates(GeoAddress.Coordinates coordinates) {
        var found = geoAddressRepository.findGeoAddressByCoordinates(coordinates);
        if (log.isDebugEnabled()) log.debug("Cache hit on: " + coordinates);
        if (found.isPresent()) return found.get();

        var requested = geoCodingService.getAddressFromCoordinates(coordinates);
        geoAddressRepository.save(requested);
        if (log.isDebugEnabled()) log.debug("Saved to cache: " + requested);
        return requested;
    }

    public GeoAddress getGeoAddressByAddressString(GeoAddress.Address address) {
        var found = geoAddressRepository.findGeoAddressByAddress(address);
        if (log.isDebugEnabled()) log.debug("Cache hit on: " + address);
        if (found.isPresent()) return found.get();

        var requested = geoCodingService.getCoordinatesFromAddress(address);
        geoAddressRepository.save(requested);
        if (log.isDebugEnabled()) log.debug("Saved to cache: " + requested);
        return requested;
    }
}
