package solubris.mon4roo.jpa;

import org.springframework.roo.addon.layers.repository.jpa.RooJpaRepository;

import solubris.mon4roo.core.MonitorMetric;

@RooJpaRepository(domainType = MonitorMetric.class)
public interface MonitorMetricRepository {
}
