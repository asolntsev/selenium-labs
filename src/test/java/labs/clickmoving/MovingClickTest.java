package labs.clickmoving;

import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.Selenide.sleep;

class MovingClickTest {
  @Test
  void clickMovingElement() {
    Configuration.browser = "chrome";
    open(getClass().getResource("/clickmoving/click.html"));

    for (int i = 0; i < 100; i++) {
      $("#target").click();
      sleep(100);
    }
  }

  @Test
  void clickResizingElement() {
    Configuration.browser = "chrome";
    open(getClass().getResource("/clickmoving/click-transition.html"));

    for (int i = 0; i < 100; i++){
      $("#target").click();
      sleep(100);
    }
  }
}
