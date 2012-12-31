// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package solubris.mon4roo.mvc;

import java.util.List;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import solubris.mon4roo.core.MonitorMetric;
import solubris.mon4roo.mvc.MonitorMetricController;

privileged aspect MonitorMetricController_Roo_Controller_Json {
    
    @RequestMapping(value = "/{name}", headers = "Accept=application/json")
    @ResponseBody
    public ResponseEntity<String> MonitorMetricController.showJson(@PathVariable("name") String name) {
        MonitorMetric monitorMetric = monitorMetricRepository.findOne(name);
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "application/json; charset=utf-8");
        if (monitorMetric == null) {
            return new ResponseEntity<String>(headers, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<String>(monitorMetric.toJson(), headers, HttpStatus.OK);
    }
    
    @RequestMapping(headers = "Accept=application/json")
    @ResponseBody
    public ResponseEntity<String> MonitorMetricController.listJson() {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "application/json; charset=utf-8");
        List<MonitorMetric> result = monitorMetricRepository.findAll();
        return new ResponseEntity<String>(MonitorMetric.toJsonArray(result), headers, HttpStatus.OK);
    }
    
    @RequestMapping(method = RequestMethod.POST, headers = "Accept=application/json")
    public ResponseEntity<String> MonitorMetricController.createFromJson(@RequestBody String json) {
        MonitorMetric monitorMetric = MonitorMetric.fromJsonToMonitorMetric(json);
        monitorMetricRepository.save(monitorMetric);
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "application/json");
        return new ResponseEntity<String>(headers, HttpStatus.CREATED);
    }
    
    @RequestMapping(value = "/jsonArray", method = RequestMethod.POST, headers = "Accept=application/json")
    public ResponseEntity<String> MonitorMetricController.createFromJsonArray(@RequestBody String json) {
        for (MonitorMetric monitorMetric: MonitorMetric.fromJsonArrayToMonitorMetrics(json)) {
            monitorMetricRepository.save(monitorMetric);
        }
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "application/json");
        return new ResponseEntity<String>(headers, HttpStatus.CREATED);
    }
    
    @RequestMapping(method = RequestMethod.PUT, headers = "Accept=application/json")
    public ResponseEntity<String> MonitorMetricController.updateFromJson(@RequestBody String json) {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "application/json");
        MonitorMetric monitorMetric = MonitorMetric.fromJsonToMonitorMetric(json);
        if (monitorMetricRepository.save(monitorMetric) == null) {
            return new ResponseEntity<String>(headers, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<String>(headers, HttpStatus.OK);
    }
    
    @RequestMapping(value = "/jsonArray", method = RequestMethod.PUT, headers = "Accept=application/json")
    public ResponseEntity<String> MonitorMetricController.updateFromJsonArray(@RequestBody String json) {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "application/json");
        for (MonitorMetric monitorMetric: MonitorMetric.fromJsonArrayToMonitorMetrics(json)) {
            if (monitorMetricRepository.save(monitorMetric) == null) {
                return new ResponseEntity<String>(headers, HttpStatus.NOT_FOUND);
            }
        }
        return new ResponseEntity<String>(headers, HttpStatus.OK);
    }
    
    @RequestMapping(value = "/{name}", method = RequestMethod.DELETE, headers = "Accept=application/json")
    public ResponseEntity<String> MonitorMetricController.deleteFromJson(@PathVariable("name") String name) {
        MonitorMetric monitorMetric = monitorMetricRepository.findOne(name);
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "application/json");
        if (monitorMetric == null) {
            return new ResponseEntity<String>(headers, HttpStatus.NOT_FOUND);
        }
        monitorMetricRepository.delete(monitorMetric);
        return new ResponseEntity<String>(headers, HttpStatus.OK);
    }
    
}