package solubris.mon4roo.jpa;

import solubris.mon4roo.core.MonitorMetric;

public class MonitorMetricInMemoryRepository extends
		InMemoryRepositoryWithEmptyJpaSpecificationExecutor<MonitorMetric, String> implements MonitorMetricRepository {
}
