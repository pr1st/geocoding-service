package app.config.redis.converter;

import app.model.GeoAddress;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.core.convert.converter.Converter;
import org.springframework.data.convert.ReadingConverter;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.stereotype.Component;

@Component
@ReadingConverter
public class ByteToCoordinatesConverter implements Converter<byte[], GeoAddress.Coordinates> {
    private final Jackson2JsonRedisSerializer<GeoAddress.Coordinates> serializer;

    public ByteToCoordinatesConverter() {
        serializer = new Jackson2JsonRedisSerializer<>(GeoAddress.Coordinates.class);
        serializer.setObjectMapper(new ObjectMapper());
    }

    @Override
    public GeoAddress.Coordinates convert(byte[] source) {
        return serializer.deserialize(source);
    }
}