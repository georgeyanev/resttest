package resttest;

import javax.ws.rs.Produces;
import javax.ws.rs.ext.ContextResolver;
import javax.ws.rs.ext.Provider;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;

@Provider
@Produces({"application/xml", "application/json"})
public class MyJaxbContextProvider implements ContextResolver<JAXBContext> {

  private JAXBContext context;

  public JAXBContext getContext(Class<?> type) {
    if (context == null) {
      try {
        context = JAXBContext.newInstance("resttest.jaxb");
      } catch (JAXBException e) {
        throw new RuntimeException(e);
      }
    }
    return context;
  }
}
