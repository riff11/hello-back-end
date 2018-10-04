package com.derkach.boot.hello_back_end;

import org.apache.catalina.connector.Connector;
import org.springframework.boot.web.embedded.tomcat.TomcatConnectorCustomizer;
import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.boot.web.server.WebServerFactoryCustomizer;
import org.springframework.stereotype.Component;

/**
 * Customizer for tomcat
 * 
 * @author alex
 *
 */
@Component
public class MyTomcatWebServerCustomizer implements WebServerFactoryCustomizer<TomcatServletWebServerFactory> {

	@Override
	public void customize(TomcatServletWebServerFactory factory) {
		factory.addConnectorCustomizers(new TomcatConnectorCustomizer() {
			@Override
			public void customize(Connector connector) {
				connector.setAttribute("relaxedPathChars", "!#$&'()*+,/:;=?@[]\"%-.<>\\^_`{|}~");
				connector.setAttribute("relaxedQueryChars", "!#$&'()*+,/:;=?@[]\"%-.<>\\^_`{|}~");
			}
		});
	}
}