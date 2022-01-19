package app.config.redis;

import app.config.redis.converter.CoordinatesToByteConverter;
import app.config.redis.converter.ByteToCoordinatesConverter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.core.convert.RedisCustomConversions;

import java.util.Arrays;

@Configuration
public class RedisConfig {
    @Bean
    public RedisConnectionFactory redisConnectionFactory(
            @Value("${redis.hostName}") String hostName,
            @Value("${redis.port}") int port
    ) {
        return new LettuceConnectionFactory(hostName, port);
    }

    @Bean
    public RedisCustomConversions redisCustomConversions(CoordinatesToByteConverter coordinatesToByteConverter,
                                                         ByteToCoordinatesConverter byteToCoordinatesConverter) {
        return new RedisCustomConversions(Arrays.asList(coordinatesToByteConverter, byteToCoordinatesConverter));
    }
}
