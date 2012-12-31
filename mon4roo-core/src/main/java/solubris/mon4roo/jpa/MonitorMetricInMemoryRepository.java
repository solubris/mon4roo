package solubris.mon4roo.jpa;

import org.springframework.stereotype.Repository;

import solubris.mon4roo.core.MonitorMetric;

/**
 * Specify repository name so it can be used in the qualifier for the @MonitoringAspect
 * This is required for wiring to work in projects that depend on this as a jar
 * 
 * @author walterst
 */
@Repository("MonitorMetricInMemoryRepository")
public class MonitorMetricInMemoryRepository extends
		InMemoryRepositoryWithEmptyJpaSpecificationExecutor<MonitorMetric, String> implements MonitorMetricRepository {
}
