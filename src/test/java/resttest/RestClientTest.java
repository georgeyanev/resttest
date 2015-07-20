package resttest;

import static javax.ws.rs.client.Entity.entity;
import static javax.ws.rs.client.Entity.xml;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;

import org.junit.BeforeClass;
import org.junit.Test;
import resttest.jaxb.Customer;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

public class RestClientTest {
  private static Client client;

  @BeforeClass
  public static void beforeClass() {
    client = ClientBuilder.newClient().register(MyJaxbContextProvider.class);
  }

  @Test
  public void testCreateCustomerWithEntity() { // Error
    Customer customer = new Customer();
    customer.setId(50);
    customer.setFirstName("Nikol");

    Response res = client.target("http://www.thomas-bayer.com/sqlrest/CUSTOMER/").request()
        .post(entity(customer, MediaType.APPLICATION_XML_TYPE));

  }

  @Test
  public void testCreateCustomerWithXmlString() { // OK
    String customer =
        "<CUSTOMER xmlns:xlink=\"http://www.w3.org/1999/xlink\">\n" +
        "  <ID>50</ID>\n" +
        "  <FIRSTNAME>Michael</FIRSTNAME>\n" +
        "</CUSTOMER>";

    Response res = client.target("http://www.thomas-bayer.com/sqlrest/CUSTOMER/").request()
        .post(xml(customer));

  }

  @Test
  public void testGetCustomer() { // Error
    Customer customer = client.target("http://www.thomas-bayer.com/sqlrest/CUSTOMER/3/").request()
        .get(new GenericType<Customer>() {});

    assertThat(customer.getId(), equalTo(3));
  }
}
