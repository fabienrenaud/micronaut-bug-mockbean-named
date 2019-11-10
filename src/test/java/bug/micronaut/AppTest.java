package bug.micronaut;

import io.micronaut.http.client.HttpClient;
import io.micronaut.http.client.annotation.Client;
import io.micronaut.runtime.server.EmbeddedServer;
import io.micronaut.test.annotation.MicronautTest;
import io.micronaut.test.annotation.MockBean;
import org.junit.jupiter.api.Test;

import javax.inject.Inject;
import javax.inject.Named;
import java.util.function.Supplier;

@MicronautTest
public class AppTest {

  @Inject EmbeddedServer server;

  @Inject
  @Client("/")
  HttpClient client;

//  @Named("my-str-supplier")
  @MockBean(bean = Supplier.class, named = "my-str-supplier")
  Supplier<String> supplier() {
    return () -> "bar";
  }

  @Test
  void test_bug() {
    String res = client.toBlocking().retrieve("/example/bug");
    System.out.println("response: " + res);
  }
}
