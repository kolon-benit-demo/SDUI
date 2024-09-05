package com.example.sdui.config;

import org.apache.catalina.connector.Connector;
import org.apache.coyote.ajp.AbstractAjpProtocol;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.boot.web.servlet.server.ServletWebServerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AJPConfig {
	private static final Logger log = LoggerFactory.getLogger(AJPConfig.class);

	@Value("${server.port}")
	int redirectPort;

	@Value("${tomcat.ajp.protocol}")
	String ajpProtocol;

	@Value("${tomcat.ajp.port}")
	int ajpPort;

	@Value("${tomcat.ajp.enable}")
	boolean ajpEnable;

	@Value("${tomcat.ajp.scheme}")
	String scheme;

	public AJPConfig() {
	}

	@Bean
	public ServletWebServerFactory servletContainer() {
		log.debug("### create ajp! ###");
		TomcatServletWebServerFactory tomcat = new TomcatServletWebServerFactory();
		if (this.ajpEnable) {
			log.debug("### Enable AJP -- protocol:{}, port:{} ###", this.ajpProtocol, this.ajpPort);
			tomcat.addAdditionalTomcatConnectors(new Connector[] {this.createAjpConnector()});
		}

		return tomcat;
	}

	private Connector createAjpConnector() {
		Connector ajpConnector = new Connector(this.ajpProtocol);
		ajpConnector.setPort(this.ajpPort);
		ajpConnector.setSecure("https".equalsIgnoreCase(this.scheme));
		ajpConnector.setAllowTrace(false);
		ajpConnector.setScheme(this.scheme);
		ajpConnector.setRedirectPort(this.redirectPort);
		ajpConnector.setProperty("address", "0.0.0.0");
		ajpConnector.setProperty("allowedRequestAttributesPattern", ".*");
		((AbstractAjpProtocol)ajpConnector.getProtocolHandler()).setSecretRequired(false);
		return ajpConnector;
	}
}
