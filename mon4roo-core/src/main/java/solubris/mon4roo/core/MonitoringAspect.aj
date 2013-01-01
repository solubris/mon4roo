package solubris.mon4roo.core;

import java.lang.reflect.Array;

import java.util.Collection;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.util.StopWatch;

import solubris.mon4roo.jpa.MonitorMetricInMemoryRepository;
import solubris.mon4roo.jpa.MonitorMetricRepository;

/**
 * Wire in monitoring based on @Monitor annotation
 * 
 * TODO store method name in metric somewhere
 * 
 * @author walterst
 */
@Configurable
// needed for @Autowire
public aspect MonitoringAspect {
	private static final String EXECUTION_PARAMS = "(..)";
	private static final String EXECUTION_EMPTY_PARAMS = "()";
	private static final String EXECUTION_PREFIX = "execution(";
	private static final String EXECUTION_SUFFIX = ")";
	private static final Logger logger = LoggerFactory.getLogger(MonitoringAspect.class);

	@Autowired
	MonitorMetricService monitorMetricService;

	pointcut methodToMonitor(Monitor monitorAnnotation) : execution(@Monitor(enabled=true) * *(..)) && @annotation(monitorAnnotation);

	// pointcut methodToMonitor() : execution(@Monitor * *(..));

	Object around(Monitor monitorAnnotation) : methodToMonitor(monitorAnnotation) {
		// MethodSignature methodSignature = (MethodSignature) thisJoinPointStaticPart.getSignature();
		// Method method = methodSignature.getMethod();
		// Monitor monitorAnnotation = method.getAnnotation(Monitor.class);

		String methodName = thisJoinPointStaticPart.toShortString();
		String name = determineMonitorName(monitorAnnotation, methodName);
		startMonitorMetric(name);
		StopWatch stopWatch = new StopWatch(name);
		stopWatch.start(name);

		Object result = proceed(monitorAnnotation);

		stopWatch.stop();

		int affectedCount = determineAffectedCount(monitorAnnotation, result);
		stopMonitorMetric(name, stopWatch, affectedCount);

		return result;
	}

	private String determineMonitorName(Monitor monitorAnnotation, String methodName) {
		String name;
		if (monitorAnnotation.name().isEmpty()) {
			name = methodName;
			// strip off "execution(..)"
			if (name.startsWith(EXECUTION_PREFIX) && name.endsWith(EXECUTION_SUFFIX)) {
				name = name.substring(EXECUTION_PREFIX.length(), name.length() - EXECUTION_SUFFIX.length());
			}
			if (name.endsWith(EXECUTION_EMPTY_PARAMS)) {
				name = name.substring(0, name.length() - EXECUTION_EMPTY_PARAMS.length());
			} else if (name.endsWith(EXECUTION_PARAMS)) {
				name = name.substring(0, name.length() - EXECUTION_PARAMS.length());
			}
		} else {
			name = monitorAnnotation.name();
		}

		// dots cause problem with controller, so replace with URL friendly char
		name = name.replace('.', '_');
		return name;
	}

	private int determineAffectedCount(Monitor monitorAnnotation, Object result) {
		int affectedCount = 0;
		if (monitorAnnotation.inspectResult()) {
			if (result instanceof Array) {
				affectedCount = Array.getLength(result);
			} else if (result instanceof Collection) {
				affectedCount = ((Collection<?>) result).size();
			} else if (result instanceof Number) {
				affectedCount = ((Number) result).intValue();
			}
		}
		return affectedCount;
	}

	/**
	 * This part needs to be synchronised
	 * If 2 threads update the same monitorMetric at the sametime, will have unpredictable results
	 * Could acquire lock on the monitorMetric so it wont block threads using other monitorMetrics
	 * 
	 * @param name
	 */
	private void startMonitorMetric(String name) {
		MonitorMetric metric = monitorMetricService.findMonitorMetric(name);
		Date now = new Date();
		if (metric != null) {
			metric.setCountStarted(metric.getCountStarted() + 1);
			metric.setLastAttempt(now);
			monitorMetricService.saveMonitorMetric(metric);
		} else {
			metric = new MonitorMetric();
			metric.setCountStarted(metric.getCountStarted() + 1);
			metric.setName(name);
			metric.setFirstAttempt(now);
			metric.setLastAttempt(now);
			monitorMetricService.saveMonitorMetric(metric);
		}
	}

	private void stopMonitorMetric(String name, StopWatch stopWatch, int affectedCount) {
		MonitorMetric metric = monitorMetricService.findMonitorMetric(name);
		metric.setCountFinished(metric.getCountFinished() + 1);
		metric.setTotTime(metric.getTotTime() + stopWatch.getLastTaskTimeMillis());
		Date now = new Date();
		if (metric.getFirstSuccess() == null) {
			metric.setFirstSuccess(now);
		}

		metric.setLastSuccess(now);
		metric.setCountAffected(metric.getCountAffected() + affectedCount);
		if (metric.getMinTime() >= stopWatch.getLastTaskTimeMillis()) {
			metric.setMinTime(stopWatch.getLastTaskTimeMillis());
		}
		if (metric.getMaxTime() <= stopWatch.getLastTaskTimeMillis()) {
			metric.setMaxTime(stopWatch.getLastTaskTimeMillis());
		}
		monitorMetricService.saveMonitorMetric(metric);

		logger.debug("monitor aspect {} {}", name, stopWatch.getLastTaskTimeMillis());
	}
}
