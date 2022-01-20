package app;

import app.geocoding.yandex.GeoCodingYandexClient;
import app.model.GeoAddress;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;

import java.util.Optional;

@SpringBootApplication
public class GeoCodingServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(GeoCodingServiceApplication.class, args);
	}


	@Bean
	public CommandLineRunner commandLineRunner(GeoCodingYandexClient client) {
		return args -> {
			var geoAddress = client.requestGeoAddress(new GeoAddress.Address("Москва, улица Новый Арбат, дом 24"));
			System.err.println(geoAddress.get());
		};
//		new RestTemplateBuilder().conve
	}

//	@Bean
//	public MappingJackson2HttpMessageConverter mappingJacksonHttpMessageConverter() {
//		MappingJackson2HttpMessageConverter converter = new MappingJackson2HttpMessageConverter();
//		converter.setObjectMapper(myObjectMapper());
//		return converter;
//	}
}
