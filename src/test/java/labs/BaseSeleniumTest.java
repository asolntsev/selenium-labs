package labs;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public abstract class BaseSeleniumTest {
  protected WebDriver webDriver;

  @BeforeEach
  void open() {
    webDriver = new ChromeDriver();
  }

  @AfterEach
  void tearDown() {
    if (webDriver != null) webDriver.quit();
  }

  protected void pause() {
    pause(100);
  }
  protected void pause(long ms) {
    try {
      Thread.sleep(ms);
    }
    catch (InterruptedException e) {
      e.printStackTrace();
    }
  }
}
