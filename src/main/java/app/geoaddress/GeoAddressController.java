package app.geoaddress;

import app.geocoding.GeoAddressNotFoundException;
import app.model.GeoAddress;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/geo")
public class GeoAddressController {
    private final GeoAddressService geoAddressService;
    private final GeoAddressToDtoConverter geoAddressToDtoConverter;

    public GeoAddressController(GeoAddressService geoAddressService,
                                GeoAddressToDtoConverter geoAddressToDtoConverter) {
        this.geoAddressService = geoAddressService;
        this.geoAddressToDtoConverter = geoAddressToDtoConverter;
    }

    @GetMapping("/coordinates")
    public ResponseEntity<GeoAddressDto> getGeoAddressByCoordinates(@PathVariable double x,
                                                                    @PathVariable double y) {
        var coordinates = new GeoAddress.Coordinates(x, y);
        var geoAddress = geoAddressService.getGeoAddressByCoordinates(coordinates);
        var geoAddressDto = geoAddressToDtoConverter.convert(geoAddress);
        return ResponseEntity.ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(geoAddressDto);
    }

    @GetMapping("/address")
    public ResponseEntity<GeoAddressDto> getGeoAddressByStringAddress(@PathVariable(name = "address") String addressString) {
        var address = new GeoAddress.Address(addressString);
        var geoAddress = geoAddressService.getGeoAddressByAddressString(address);
        var geoAddressDto = geoAddressToDtoConverter.convert(geoAddress);
        return ResponseEntity.ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(geoAddressDto);
    }

    @ExceptionHandler({GeoAddressNotFoundException.class})
    public ResponseEntity<?> geoAddressNotFound(GeoAddressNotFoundException ex) {
        return ResponseEntity.badRequest()
                .body("Address not found: " + ex.getMessage());
    }
}
