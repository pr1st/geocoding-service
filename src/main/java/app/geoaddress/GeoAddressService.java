package app.geoaddress;

import app.geocoding.GeoCodingService;
import app.model.GeoAddress;
import org.springframework.stereotype.Service;

@Service
public class GeoAddressService {

    private final GeoAddressRepository geoAddressRepository;
    private final GeoCodingService geoCodingService;

    public GeoAddressService(GeoAddressRepository geoAddressRepository,
                             GeoCodingService geoCodingService) {
        this.geoAddressRepository = geoAddressRepository;
        this.geoCodingService = geoCodingService;
    }

    public GeoAddress getGeoAddressByCoordinates(GeoAddress.Coordinates coordinates) {
        return null;
    }

    public GeoAddress getGeoAddressByAddressString(GeoAddress.Address address) {
        return null;
    }
}
