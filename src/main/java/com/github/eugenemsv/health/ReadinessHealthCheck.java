package com.github.eugenemsv.health;

import org.eclipse.microprofile.health.HealthCheck;
import org.eclipse.microprofile.health.HealthCheckResponse;
import org.eclipse.microprofile.health.Readiness;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
@Readiness
public class ReadinessHealthCheck implements HealthCheck {


    @Override
    public HealthCheckResponse call() {
        return HealthCheckResponse.named("Service is alive").up().build();
    }

}