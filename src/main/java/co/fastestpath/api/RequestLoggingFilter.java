package co.fastestpath.api;

import com.google.common.io.ByteSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.container.ResourceInfo;
import javax.ws.rs.core.Context;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;

public class RequestLoggingFilter implements ContainerRequestFilter {

  private static final Logger LOG = LoggerFactory.getLogger(RequestLoggingFilter.class);

  @Context
  private HttpServletRequest webRequest;

  @Context
  private ResourceInfo resourceInfo;

  @Override
  public void filter(ContainerRequestContext requestContext) throws IOException {
    LOG.info("Intercepted request.");
    final ByteSource byteSource = new ByteSource() {
      @Override
      public InputStream openStream() throws IOException {
        return webRequest.getInputStream();
      }
    };
    final String contents = byteSource.asCharSource(Charset.forName(webRequest.getCharacterEncoding())).read();
    LOG.info(contents);
  }
}
