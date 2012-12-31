package solubris.mon4roo.core;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Tag method to be monitored
 * 
 * can use method result to count number of actions performed
 * 
 * @author walterst
 */
@Target({ElementType.METHOD, ElementType.ANNOTATION_TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Monitor {

	boolean inspectResult() default false;
	
	/**
	 * Can be used disable a monitor without having to comment it out
	 * This is detected at the point cut level (not at runtime)
	 * 
	 * @return
	 */
	boolean enabled() default true;
	
	/**
	 * Can be used to override the name for this monitor point
	 * By default the method name is used
	 * 
	 * @return
	 */
	String name() default "";
}
