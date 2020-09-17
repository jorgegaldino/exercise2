package br.com.jm.mspatrimonio.config.converter;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeanConverter {

	@Bean
	public ModelMapper modelMapper() {
	    return new ModelMapper();
	}
}
