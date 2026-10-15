package br.com.roger.api.bytepizza.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.Clock;
import java.time.ZoneId;

/*
 * Classe para configurar Horário
 * */
@Configuration
public class ClockConfig {
    public static final ZoneId FUSO_LOJA = ZoneId.of("America/Sao_Paulo");

    @Bean
    public Clock clock() {
        return Clock.system(FUSO_LOJA);
    }
}
