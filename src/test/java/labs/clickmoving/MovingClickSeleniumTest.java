package labs.clickmoving;

import labs.BaseSeleniumTest;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;

class MovingClickSeleniumTest extends BaseSeleniumTest {
  @Test
  void clickMovingElement() {
    webDriver.navigate().to(getClass().getResource("/clickmoving/click.html"));

    for (int i = 0; i < 100; i++) {
      System.out.println(" " + "MovingClickSeleniumTest.clickMovingElement " + i);
      webDriver.findElement(By.id("target")).click();
      pause();
    }
  }

  @Test
  void clickResizingElement() {
    webDriver.navigate().to(getClass().getResource("/clickmoving/click-transition.html"));

    for (int i = 0; i < 100; i++) {
      System.out.println(" " + "MovingClickSeleniumTest.clickResizingElement " + i);
      webDriver.findElement(By.id("target")).click();
      pause();
    }
  }
}
