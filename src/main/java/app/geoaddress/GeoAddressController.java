package app.geoaddress;

import app.geocoding.GeoAddressNotFoundException;
import app.model.GeoAddress;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/geo")
public class GeoAddressController {
    private static final Log log = LogFactory.getLog(GeoAddressController.class);

    private final GeoAddressService geoAddressService;
    private final GeoAddressToDtoConverter geoAddressToDtoConverter;

    public GeoAddressController(GeoAddressService geoAddressService,
                                GeoAddressToDtoConverter geoAddressToDtoConverter) {
        this.geoAddressService = geoAddressService;
        this.geoAddressToDtoConverter = geoAddressToDtoConverter;
    }

    @GetMapping(params = {"x", "y"})
    public ResponseEntity<GeoAddressDto> getGeoAddressByCoordinates(@RequestParam double x,
                                                                    @RequestParam double y) {
        var coordinates = new GeoAddress.Coordinates(x, y);
        var geoAddress = geoAddressService.getGeoAddressByCoordinates(coordinates);
        var geoAddressDto = geoAddressToDtoConverter.convert(geoAddress);

        if (log.isDebugEnabled()) log.debug("Producing response : " + geoAddressDto);

        return ResponseEntity.ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(geoAddressDto);
    }

    @GetMapping(params = "address")
    public ResponseEntity<GeoAddressDto> getGeoAddressByStringAddress(@RequestParam(name = "address") String addressString) {
        var address = new GeoAddress.Address(addressString);
        var geoAddress = geoAddressService.getGeoAddressByAddressString(address);
        var geoAddressDto = geoAddressToDtoConverter.convert(geoAddress);

        if (log.isDebugEnabled()) log.debug("Producing response : " + geoAddressDto);

        return ResponseEntity.ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(geoAddressDto);
    }

    @ExceptionHandler({GeoAddressNotFoundException.class})
    public ResponseEntity<?> geoAddressNotFound(GeoAddressNotFoundException ex) {
        log.error("Address not found for: " + ex.getMessage());
        return ResponseEntity.badRequest()
                .body("Address not found: " + ex.getMessage());
    }
}
