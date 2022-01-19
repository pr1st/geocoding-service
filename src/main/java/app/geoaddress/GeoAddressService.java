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
        var found = geoAddressRepository.findGeoAddressByCoordinatesXAndCoordinatesY(coordinates.x(), coordinates.y());
        if (!found.isEmpty()) {
            if (log.isInfoEnabled()) log.info("Cache hit on: " + coordinates);
            return found.get(0);
        }

        var requested = geoCodingService.getAddressFromCoordinates(coordinates);
        requested = geoAddressRepository.save(requested);
        if (log.isInfoEnabled()) log.info("Saved to cache: " + requested);
        return requested;
    }

    public GeoAddress getGeoAddressByAddressString(GeoAddress.Address address) {
        var found = geoAddressRepository.findGeoAddressByAddressFullName(address.fullName());
        if (!found.isEmpty()) {
            if (log.isInfoEnabled()) log.info("Cache hit on: " + address);
            return found.get(0);
        }


        var requested = geoCodingService.getCoordinatesFromAddress(address);
        requested = geoAddressRepository.save(requested);
        if (log.isInfoEnabled()) log.info("Saved to cache: " + requested);
        return requested;
    }
}
