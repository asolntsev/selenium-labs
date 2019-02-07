package labs.redirect;

import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

class RedirectSelenideTest {
  @Test
  void redirect() {
    Configuration.browser = "chrome";
    Configuration.headless = true;

    for (int i = 0; i < 10000; i++) {
      if (i % 100 == 0 || i < 10000-50) {
        System.out.println(" RedirectSelenideTest.redirect " + i);
      }

      open(getClass().getResource("/redirect/404.html"));
      $("h1").shouldHave(text("This link has no power here"));
    }
  }
}
