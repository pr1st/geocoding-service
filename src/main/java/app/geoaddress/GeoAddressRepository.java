package app.geoaddress;

import app.model.GeoAddress;
import org.springframework.data.repository.CrudRepository;

public interface GeoAddressRepository extends CrudRepository<GeoAddress, Long> {
}
