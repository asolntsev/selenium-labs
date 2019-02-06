package labs.clickmoving;

import labs.BaseSeleniumTest;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;

class MovingClickSeleniumTest extends BaseSeleniumTest {
  @Test
  void clickMovingElement() {
    webDriver.navigate().to(getClass().getResource("/clickmoving/click.html"));

    for (int i = 0; i < 100; i++) {
      webDriver.findElement(By.id("target")).click();
      pause();
    }
  }

  @Test
  void clickResizingElement() {
    webDriver.navigate().to(getClass().getResource("/clickmoving/click-transition.html"));

    for (int i = 0; i < 100; i++) {
      webDriver.findElement(By.id("target")).click();
      pause();
    }
  }
}
