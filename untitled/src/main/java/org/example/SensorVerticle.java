package org.example;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.Promise;
import io.vertx.core.impl.logging.Logger;
import io.vertx.core.impl.logging.LoggerFactory;

import java.util.Random;
import java.util.UUID;

public class SensorVerticle extends AbstractVerticle {
    private static final Logger logger = LoggerFactory.getLogger(SensorVerticle.class);
    private static final int httpPort = Integer.parseInt(System.getenv().getOrDefault(
            "HTTP_PORT", "8080"));

    private final String uuid = UUID.randomUUID().toString();
    private double temperature = 21.0;
    private final Random random = new Random();

    @Override
    public void start(Promise<Void> startPromise) {
        vertx.setPeriodic(2000, this::updateTemperature);

    }

    private void updateTemperature(Long aLong) {
        temperature = temperature + (random.nextGaussian() / 2.0d);
        logger.info("Temperature updated: ".concat(String.valueOf(temperature)));
    }
}
