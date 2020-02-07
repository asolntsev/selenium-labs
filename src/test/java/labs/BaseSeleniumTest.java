package labs;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public abstract class BaseSeleniumTest {
  protected WebDriver webDriver;

  @BeforeEach
  void open() {
    WebDriverManager.chromedriver().setup();
    webDriver = new ChromeDriver();
  }

  @AfterEach
  void tearDown() {
    if (webDriver != null) webDriver.quit();
  }

  protected void pause() {
    try {
      Thread.sleep(100);
    }
    catch (InterruptedException e) {
      e.printStackTrace();
    }
  }
}
