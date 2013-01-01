package solubris.mon4roo.core;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import solubris.mon4roo.jpa.MonitorMetricRepository;

/**
 * 
 * Override the monitorMetricRepository to use an in memory repo: @MonitorMetricInMemoryRepository
 * 
 * @author walterst
 */
public class MonitorMetricServiceImpl implements MonitorMetricService {
    @Autowired
	@Qualifier("MonitorMetricInMemoryRepository")
    MonitorMetricRepository monitorMetricRepository;
}
