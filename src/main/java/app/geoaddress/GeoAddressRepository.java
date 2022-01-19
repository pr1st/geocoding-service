package app.geoaddress;

import app.model.GeoAddress;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface GeoAddressRepository extends CrudRepository<GeoAddress, Long> {
    List<GeoAddress> findGeoAddressByCoordinatesXAndCoordinatesY(double x, double y);
    List<GeoAddress> findGeoAddressByAddressFullName(String addressFullName);
}
