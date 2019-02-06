package labs.alert;

import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Selenide.confirm;
import static com.codeborne.selenide.Selenide.open;

class AlertTest {
  @Test
  void redirect() {
    Configuration.browser = "chrome";
    Configuration.headless = true;

    for (int i = 0; i < 1000; i++) {
      open(getClass().getResource("/alert/alert.html"));
      confirm("Are you sure?");
    }
  }
}
