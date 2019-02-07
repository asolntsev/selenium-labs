package labs.redirect;

import labs.BaseSeleniumTest;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.FluentWait;

import static java.time.Duration.ofSeconds;

class RedirectSeleniumTest extends BaseSeleniumTest {
  @Test
  void redirect() {
    for (int i = 0; i < 10000; i++) {
      if (i % 100 == 0 || i < 10000-50) {
        System.out.println(" RedirectSeleniumTest.redirect " + i);
      }

      webDriver.navigate().to(getClass().getResource("/redirect/404.html"));
      new FluentWait<>(webDriver)
          .withTimeout(ofSeconds(2))
          .until((wd) -> wd.findElement(By.tagName("h1")).getText().equals("This link has no power here"));
    }
  }
}
