package nl.han.se.cnp.bewd.resources;

import nl.han.se.cnp.bewd.annotations.DiyGetMapping;
import nl.han.se.cnp.bewd.annotations.DiyRestController;

@DiyRestController
public class HealthCheckResource
{
    @DiyGetMapping("/health")
    public String healthy()
    {
        return "Up & Running";
    }
}
