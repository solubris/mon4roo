package solubris.mon4roo.core;

import org.springframework.roo.addon.dod.RooDataOnDemand;

@RooDataOnDemand(entity = MonitorMetric.class)
public class MonitorMetricDataOnDemand {

	public MonitorMetric getNewTransientMonitorMetric(int index) {
        MonitorMetric obj = new MonitorMetric();
        setName(obj, index);
        setAffectedAvgTime(obj, index);
        setAffectedRate(obj, index);
        setAvgTime(obj, index);
        setCountAffected(obj, index);
        setCountFinished(obj, index);
        setCountStarted(obj, index);
        setFirstAttempt(obj, index);
        setFirstFailure(obj, index);
        setFirstSuccess(obj, index);
        setLastAttempt(obj, index);
        setLastFailure(obj, index);
        setLastSuccess(obj, index);
        setMaxTime(obj, index);
        setMinTime(obj, index);
        setRate(obj, index);
        setTotTime(obj, index);
        return obj;
    }

	private void setName(MonitorMetric obj, int index) {
		obj.setName("MonitorMetric." + index);
	}
}
