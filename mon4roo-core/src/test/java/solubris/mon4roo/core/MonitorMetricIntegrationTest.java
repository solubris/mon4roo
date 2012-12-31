package solubris.mon4roo.core;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.roo.addon.test.RooIntegrationTest;

@RooIntegrationTest(entity = MonitorMetric.class)
public class MonitorMetricIntegrationTest {

    @Test
    public void testMarkerMethod() {
    }

	@Test
    public void testSave() {
        Assert.assertNotNull("Data on demand for 'MonitorMetric' failed to initialize correctly", dod.getRandomMonitorMetric());
        MonitorMetric obj = dod.getNewTransientMonitorMetric(Integer.MAX_VALUE);
        Assert.assertNotNull("Data on demand for 'MonitorMetric' failed to provide a new transient entity", obj);
//        Assert.assertNull("Expected 'MonitorMetric' identifier to be null", obj.getName());
        monitorMetricRepository.save(obj);
        monitorMetricRepository.flush();
        Assert.assertNotNull("Expected 'MonitorMetric' identifier to no longer be null", obj.getName());
    }
}
