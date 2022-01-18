package app.geocoding;

public class GeoAddressNotFoundException extends RuntimeException {
    public GeoAddressNotFoundException(String message) {
        super(message);
    }
}
