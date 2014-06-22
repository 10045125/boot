package jp.co.answernet.boot.core.web;

import lombok.Data;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.handler.ContextHandler;
import org.eclipse.jetty.util.resource.Resource;
import org.eclipse.jetty.webapp.WebAppClassLoader;
import org.eclipse.jetty.webapp.WebAppContext;
import org.springframework.boot.context.embedded.ConfigurableEmbeddedServletContainer;
import org.springframework.boot.context.embedded.EmbeddedServletContainerCustomizer;
import org.springframework.boot.context.embedded.jetty.JettyEmbeddedServletContainerFactory;
import org.springframework.boot.context.embedded.jetty.JettyServerCustomizer;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import javax.validation.constraints.NotNull;
import java.io.File;
import java.io.IOException;
import java.net.InetAddress;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by yasuhiro on 2014/05/30.
 */
@ConfigurationProperties(prefix = "jetty", ignoreUnknownFields = true)
@Data
@Component
public class JettyCustomizer implements EmbeddedServletContainerCustomizer, JettyServerCustomizer {

  private Integer port;

  private InetAddress address;

  private Integer sessionTimeout;

  private String contextPath;

  @NotNull
  private String servletPath = "/";

  private File documentRoot;

  private boolean enableAliases;

  @Override
  public void customize(ConfigurableEmbeddedServletContainer container) {
    if ( container instanceof JettyEmbeddedServletContainerFactory ) {
      customizeJetty((JettyEmbeddedServletContainerFactory)container);
    }
  }

  private void customizeJetty(JettyEmbeddedServletContainerFactory container) {
    container.setPort(this.getPort());
    container.setRegisterJspServlet(true);
    container.setDocumentRoot(documentRoot);
    container.addServerCustomizers(this);
  }

  private void enableAliases(ContextHandler webAppContext) {
    List<ContextHandler.AliasCheck> aliasChecks = new ArrayList<>();
    ContextHandler.ApproveAliases aliases = new ContextHandler.ApproveAliases();
    aliasChecks.add(aliases);
    webAppContext.setAliasChecks(aliasChecks);
  }


  @Override
  public void customize(Server server) {
    WebAppContext handler = (WebAppContext)Arrays.stream(server.getHandlers()).filter(h -> h instanceof WebAppContext).findFirst().orElse(null);
    if ( this.enableAliases ) {
      enableAliases(handler);
    }
    try {
      ClassLoader contextClassLoader = handler.getClassLoader();
      WebAppClassLoader webAppClassLoader = new WebAppClassLoader(contextClassLoader, handler);
      handler.setClassLoader(webAppClassLoader);
    } catch ( IOException e ) {
      e.printStackTrace();
    }

  }
}
