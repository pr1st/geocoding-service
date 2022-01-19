package app.config.redis.converter;

import app.model.GeoAddress;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.core.convert.converter.Converter;
import org.springframework.data.convert.WritingConverter;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.stereotype.Component;

import java.nio.ByteBuffer;
import java.util.Map;

@Component
@WritingConverter
public class CoordinatesToByteConverter implements Converter<GeoAddress.Coordinates, byte[]>{
    private final Jackson2JsonRedisSerializer<GeoAddress.Coordinates> serializer;

    public CoordinatesToByteConverter() {
        serializer = new Jackson2JsonRedisSerializer<>(GeoAddress.Coordinates.class);
        serializer.setObjectMapper(new ObjectMapper());
    }

    @Override
    public byte[] convert(GeoAddress.Coordinates source) {
        return serializer.serialize(source);
    }
}
