package labs.alert;

import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Selenide.confirm;
import static com.codeborne.selenide.Selenide.open;

class ConfirmTest {
  @Test
  void clickConfirm() {
    Configuration.browser = "chrome";
    Configuration.headless = true;

    for (int i = 0; i < 1000; i++) {
      System.out.println(" ConfirmTest.clickConfirm " + i);

      open(getClass().getResource("/alert/confirm.html"));
      confirm("Are you sure?");
    }
  }
}
