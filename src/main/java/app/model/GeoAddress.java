package app.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;
import org.springframework.data.redis.core.index.Indexed;

@RedisHash("geo-addresses")
public record GeoAddress(
        @Id Long id,
        @Indexed Address address,
        @Indexed Coordinates coordinates
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
