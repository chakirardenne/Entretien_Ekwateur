package fr.joul.cie.test.springtechnicaltest.infrastructure.configuration;

import fr.joul.cie.test.springtechnicaltest.application.converter.MapperTool;
import fr.joul.cie.test.springtechnicaltest.application.converter.MapperToolImpl;
import fr.joul.cie.test.springtechnicaltest.domain.ports.out.EkwateurApiPort;
import fr.joul.cie.test.springtechnicaltest.infrastructure.adapters.api.EkwateurApiAdpapter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ConfigurationClass {
    @Bean
    EkwateurApiPort ekwateurApiPort(MapperTool mapperTool) { return new EkwateurApiAdpapter(mapperTool);}

    @Bean
    MapperTool mapperTool() {
        return new MapperToolImpl();
    }
}
