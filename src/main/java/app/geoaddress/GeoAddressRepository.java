package app.geoaddress;

import app.model.GeoAddress;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface GeoAddressRepository extends CrudRepository<GeoAddress, Long> {
    Optional<GeoAddress> findGeoAddressByCoordinates(GeoAddress.Coordinates coordinates);
    Optional<GeoAddress> findGeoAddressByAddress(GeoAddress.Address address);
}
