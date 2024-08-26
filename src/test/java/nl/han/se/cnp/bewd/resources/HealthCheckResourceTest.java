package nl.han.se.cnp.bewd.resources;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class HealthCheckResourceTest {
    @Test
    public void healthy_whenCalled_ShouldReturnUpAndRunning() {
        HealthCheckResource healthCheckResource = new HealthCheckResource();
        assertEquals("Up & Running", healthCheckResource.healthy());
    }
}
