package solubris.mon4roo.mvc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.roo.addon.web.mvc.controller.json.RooWebJson;
import org.springframework.roo.addon.web.mvc.controller.scaffold.RooWebScaffold;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import solubris.mon4roo.core.MonitorMetric;
import solubris.mon4roo.jpa.MonitorMetricRepository;

/**
 * Controller for @MonitorMetric entities
 * 
 * Override the monitorMetricRepository to use an in memory repo: @MonitorMetricInMemoryRepository
 * 
 * @author walterst
 */
@RequestMapping("/monitormetrics")
@Controller
@RooWebScaffold(path = "monitormetrics", formBackingObject = MonitorMetric.class, create = false, delete = false, update = false)
@RooWebJson(jsonObject = MonitorMetric.class)
public class MonitorMetricController {
    @Autowired
	@Qualifier("MonitorMetricInMemoryRepository")
    MonitorMetricRepository monitorMetricRepository;
}
