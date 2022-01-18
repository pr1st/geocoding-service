package app.geocoding;

import app.model.GeoAddress;
import org.springframework.stereotype.Service;

@Service
public interface GeoCodingService {
    GeoAddress.Address getAddressFromCoordinates(GeoAddress.Coordinates coordinates);
    GeoAddress.Coordinates getCoordinatesFromAddress(GeoAddress.Address coordinates);
}
