package app.geoaddress;

public record GeoAddressDto(
        Address address,
        Coordinates coordinates
) {
    public static record Address(
            String string
    ) {
    }

    public static record Coordinates(
            double x,
            double y
    ) {
    }
}
