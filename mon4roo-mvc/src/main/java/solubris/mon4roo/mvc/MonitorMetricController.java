package solubris.mon4roo.mvc;

import org.springframework.roo.addon.web.mvc.controller.json.RooWebJson;
import org.springframework.roo.addon.web.mvc.controller.scaffold.RooWebScaffold;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import solubris.mon4roo.core.MonitorMetric;

@RequestMapping("/monitormetrics")
@Controller
@RooWebScaffold(path = "monitormetrics", formBackingObject = MonitorMetric.class, create = false, delete = false, update = false)
@RooWebJson(jsonObject = MonitorMetric.class)
public class MonitorMetricController {
}
