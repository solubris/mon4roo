package solubris.mon4roo.core;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.NumberFormat;
import org.springframework.format.annotation.NumberFormat.Style;
import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.jpa.entity.RooJpaEntity;
import org.springframework.roo.addon.json.RooJson;
import org.springframework.roo.addon.tostring.RooToString;

import solubris.mon4roo.jpa.Identifiable;

@RooJavaBean
@RooToString
@RooJpaEntity(versionField="", identifierField="name", identifierType=String.class, versionType=Byte.class)
//@RooJpaActiveRecord(versionField="", identifierField="name", identifierType=String.class, versionType=Byte.class)
@RooJson
public class MonitorMetric implements Identifiable<String> {
	
    /**
     * Need to define name field here, so roo doesn't create it as auto increment
     */
    @Id
    private String name;

    long totTime;
	long minTime=Long.MAX_VALUE;
	long maxTime=Long.MIN_VALUE;
	int countStarted;
	int countFinished;
	int countAffected;

    @DateTimeFormat(style = "SM")
    private Date firstAttempt;
    @DateTimeFormat(style = "SM")
	private Date firstSuccess;
    @DateTimeFormat(style = "SM")
    private Date firstFailure;

    @DateTimeFormat(style = "SM")
    private Date lastAttempt;
    @DateTimeFormat(style = "SM")
	private Date lastSuccess;
    @DateTimeFormat(style = "SM")
    private Date lastFailure;

    ///// Dummy fields which force ROO to create application labels for these
    Double avgTime;
    Double affectedAvgTime;
    Double rate;
    Double affectedRate;

    @NumberFormat(style = Style.NUMBER, pattern = "#,###.##")
    public Double getAvgTime() {
        return (countFinished>0 ? ((double)totTime)/((double)countFinished) : null);
    }

    @NumberFormat(style = Style.NUMBER, pattern = "#,###.##")
    public Double getAffectedAvgTime() {
        return (countAffected>0 ? ((double)totTime)/((double)countAffected) : null);
    }

    @NumberFormat(style = Style.NUMBER, pattern = "#,###.##")
    public Double getRate() {
        return (countFinished>0 && lastSuccess!=null && firstAttempt != null ? ((double)countFinished)/((double)(lastSuccess.getTime() - firstAttempt.getTime())/1000) : null);
    }

    @NumberFormat(style = Style.NUMBER, pattern = "#,###.##")
    public Double getAffectedRate() {
        return (countAffected>0 && lastSuccess!=null && firstAttempt != null  ? ((double)countAffected)/((double)(lastSuccess.getTime() - firstAttempt.getTime())/1000) : null);
    }

    @Override
	public String getId() {
		return getName();
	}
}
