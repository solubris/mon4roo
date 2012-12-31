// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package solubris.mon4roo.mvc;

import org.joda.time.format.DateTimeFormat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import solubris.mon4roo.jpa.MonitorMetricRepository;
import solubris.mon4roo.mvc.MonitorMetricController;

privileged aspect MonitorMetricController_Roo_Controller {
    
    @Autowired
    MonitorMetricRepository MonitorMetricController.monitorMetricRepository;
    
    @RequestMapping(value = "/{name}", produces = "text/html")
    public String MonitorMetricController.show(@PathVariable("name") String name, Model uiModel) {
        addDateTimeFormatPatterns(uiModel);
        uiModel.addAttribute("monitormetric", monitorMetricRepository.findOne(name));
        uiModel.addAttribute("itemId", name);
        return "monitormetrics/show";
    }
    
    @RequestMapping(produces = "text/html")
    public String MonitorMetricController.list(@RequestParam(value = "page", required = false) Integer page, @RequestParam(value = "size", required = false) Integer size, Model uiModel) {
        if (page != null || size != null) {
            int sizeNo = size == null ? 10 : size.intValue();
            final int firstResult = page == null ? 0 : (page.intValue() - 1) * sizeNo;
            uiModel.addAttribute("monitormetrics", monitorMetricRepository.findAll(new org.springframework.data.domain.PageRequest(firstResult / sizeNo, sizeNo)).getContent());
            float nrOfPages = (float) monitorMetricRepository.count() / sizeNo;
            uiModel.addAttribute("maxPages", (int) ((nrOfPages > (int) nrOfPages || nrOfPages == 0.0) ? nrOfPages + 1 : nrOfPages));
        } else {
            uiModel.addAttribute("monitormetrics", monitorMetricRepository.findAll());
        }
        addDateTimeFormatPatterns(uiModel);
        return "monitormetrics/list";
    }
    
    void MonitorMetricController.addDateTimeFormatPatterns(Model uiModel) {
        uiModel.addAttribute("monitorMetric_firstattempt_date_format", DateTimeFormat.patternForStyle("SM", LocaleContextHolder.getLocale()));
        uiModel.addAttribute("monitorMetric_firstsuccess_date_format", DateTimeFormat.patternForStyle("SM", LocaleContextHolder.getLocale()));
        uiModel.addAttribute("monitorMetric_firstfailure_date_format", DateTimeFormat.patternForStyle("SM", LocaleContextHolder.getLocale()));
        uiModel.addAttribute("monitorMetric_lastattempt_date_format", DateTimeFormat.patternForStyle("SM", LocaleContextHolder.getLocale()));
        uiModel.addAttribute("monitorMetric_lastsuccess_date_format", DateTimeFormat.patternForStyle("SM", LocaleContextHolder.getLocale()));
        uiModel.addAttribute("monitorMetric_lastfailure_date_format", DateTimeFormat.patternForStyle("SM", LocaleContextHolder.getLocale()));
    }
    
}
