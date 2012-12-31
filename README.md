mon4roo
=======

Monitoring via aspects for Spring ROO apps


install in another roo project
=======

Add dependancy to pom:

		<dependency>
			<groupId>solubris.mon4roo.core</groupId>
			<artifactId>mon4roo-core</artifactId>
			<version>0.1.0.BUILD-SNAPSHOT</version>
		</dependency>
		<dependency>
			<groupId>solubris.mon4roo.mvc</groupId>
			<artifactId>mon4roo-mvc</artifactId>
			<version>0.1.0.BUILD-SNAPSHOT</version>
			<exclusions>
				<exclusion>
					<artifactId>mon4roo-sample</artifactId>
					<groupId>solubris.mon4roo.sample</groupId>
				</exclusion>
			</exclusions>
		</dependency>

Add library to aspectj compiler plugin:

						<aspectLibrary>
							<groupId>solubris.mon4roo.core</groupId>
							<artifactId>mon4roo-core</artifactId>
						</aspectLibrary>

Add annotation to methods to monitor:

import solubris.mon4roo.core.Monitor;
...
	@Monitor
	...
						
copy views from mon4roo-mvc into your webapp:
mon4roo-mvc/src/main/webapp/WEB-INF/views/monitormetrics/**
dont need to have controller in project
 						
add to menu.jspx

        <menu:category id="c_monitormetric">
            <menu:item id="i_monitormetric_list" messageCode="global_menu_list" url="/monitormetrics?page=1&amp;size=${empty param.size ? 10 : param.size}"/>
        </menu:category>


Add label resource to message source:

webmvc-config.xml - ReloadableResourceBundleMessageSource
classpath:META-INF/spring/mon4roo

Should look like this after addition:
    <bean class="org.springframework.context.support.ReloadableResourceBundleMessageSource" id="messageSource" p:basenames="WEB-INF/i18n/messages,WEB-INF/i18n/application,classpath:META-INF/spring/mon4roo" p:fallbackToSystemLocale="false" p:cacheSeconds="0"/>

Add mvc package to component scan in webmvc-config.xml
	,solubris.mon4roo.mvc

Add this to persistence.xml:

		<class>solubris.mon4roo.core.MonitorMetric</class>

		
		