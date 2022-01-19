package app.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;
import org.springframework.data.redis.core.TimeToLive;
import org.springframework.data.redis.core.index.Indexed;

@RedisHash("geo-addresses")
public record GeoAddress(
        @Id Long id,
        @TimeToLive Long ttl,
        Address address,
        Coordinates coordinates
) {
    public static GeoAddress of(Address address, Coordinates coordinates) {
        return new GeoAddress(null, 120L, address, coordinates);
    }

    public static record Address(
            @Indexed String fullName
    ) {
    }

    public static record Coordinates(
            @Indexed double x,
            @Indexed double y
    ) {
    }
}
