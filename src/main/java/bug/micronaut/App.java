package bug.micronaut;

import io.micronaut.context.annotation.Factory;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
import io.micronaut.runtime.Micronaut;

import javax.inject.Inject;
import javax.inject.Named;
import javax.inject.Singleton;
import java.util.function.Supplier;

@Factory
public class App {

  @Controller
  public static class Control {

    private final Supplier<String> supplier;

    @Inject
    public Control(@Named("my-str-supplier") Supplier<String> supplier) {
      this.supplier = supplier;
    }

    @Get("/example/bug")
    public String ep() {
      return supplier.get();
    }
  }

  @Singleton
  @Named("my-str-supplier")
  Supplier<String> myStrSupplier() {
    return () -> "foo";
  }

  public static void main(String[] args) {
    Micronaut.run(App.class, args);
  }
}
