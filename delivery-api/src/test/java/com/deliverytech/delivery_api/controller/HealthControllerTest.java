package com.deliverytech.delivery_api.controller;

import org.junit.jupiter.api.Test;
import java.util.Map;
import static org.junit.jupiter.api.Assertions.*;

class HealthControllerTest {

    private final HealthController controller = new HealthController();

    @Test
    void deveRetornarHealthComStatusUp() {
        Map<String, String> result = controller.health();

        assertNotNull(result);
        assertEquals("UP", result.get("status"));
        assertEquals("Delivery API", result.get("service"));
        assertNotNull(result.get("timestamp"));
        assertFalse(result.get("timestamp").isBlank());
        assertEquals(System.getProperty("java.version"), result.get("javaVersion"));
    }

    @Test
    void deveRetornarInfoComValoresEsperados() {
        HealthController.AppInfo info = controller.info();

        assertNotNull(info);
        assertEquals("Delivery Tech API", info.application());
        assertEquals("1.0.0", info.version());
        assertEquals("[Seu Nome]", info.developer());
        assertEquals("JDK 21", info.javaVersion());
        assertEquals("Spring Boot 3.2.x", info.framework());
    }
}
