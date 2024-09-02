package com.nobel.test.rockpaperscissors.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "game")
@Getter
@Setter
public class GameConfig {
    private int maxRounds;
}
