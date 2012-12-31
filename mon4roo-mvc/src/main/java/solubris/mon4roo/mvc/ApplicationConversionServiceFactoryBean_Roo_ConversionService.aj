// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package solubris.mon4roo.mvc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.core.convert.converter.Converter;
import org.springframework.format.FormatterRegistry;
import solubris.mon4roo.core.MonitorMetric;
import solubris.mon4roo.jpa.MonitorMetricRepository;
import solubris.mon4roo.mvc.ApplicationConversionServiceFactoryBean;

privileged aspect ApplicationConversionServiceFactoryBean_Roo_ConversionService {
    
    declare @type: ApplicationConversionServiceFactoryBean: @Configurable;
    
    @Autowired
    MonitorMetricRepository ApplicationConversionServiceFactoryBean.monitorMetricRepository;
    
    public Converter<MonitorMetric, String> ApplicationConversionServiceFactoryBean.getMonitorMetricToStringConverter() {
        return new org.springframework.core.convert.converter.Converter<solubris.mon4roo.core.MonitorMetric, java.lang.String>() {
            public String convert(MonitorMetric monitorMetric) {
                return new StringBuilder().append(monitorMetric.getAvgTime()).append(' ').append(monitorMetric.getAffectedAvgTime()).append(' ').append(monitorMetric.getRate()).append(' ').append(monitorMetric.getAffectedRate()).toString();
            }
        };
    }
    
    public Converter<String, MonitorMetric> ApplicationConversionServiceFactoryBean.getIdToMonitorMetricConverter() {
        return new org.springframework.core.convert.converter.Converter<java.lang.String, solubris.mon4roo.core.MonitorMetric>() {
            public solubris.mon4roo.core.MonitorMetric convert(java.lang.String id) {
                return monitorMetricRepository.findOne(id);
            }
        };
    }
    
    public void ApplicationConversionServiceFactoryBean.installLabelConverters(FormatterRegistry registry) {
        registry.addConverter(getMonitorMetricToStringConverter());
        registry.addConverter(getIdToMonitorMetricConverter());
    }
    
    public void ApplicationConversionServiceFactoryBean.afterPropertiesSet() {
        super.afterPropertiesSet();
        installLabelConverters(getObject());
    }
    
}
