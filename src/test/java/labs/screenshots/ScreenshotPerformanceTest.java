package labs.screenshots;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.Point;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.bidi.browsingcontext.BrowsingContext;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.chromium.HasCdp;
import org.openqa.selenium.devtools.DevTools;
import org.openqa.selenium.devtools.HasDevTools;
import org.openqa.selenium.devtools.v138.page.Page;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;
import java.util.Optional;

import static java.lang.System.currentTimeMillis;
import static org.assertj.core.api.Assertions.assertThat;
import static org.openqa.selenium.OutputType.BYTES;

/**
 * Compare speed of taking 100 screenshots by different ways.
 *
 * Results:
 * BiDi in Chrome is MUCH SLOWER than other means.
 */
public class ScreenshotPerformanceTest {
  private static final Logger log = LoggerFactory.getLogger(ScreenshotPerformanceTest.class);
  private static final int COUNT = 100;
  private static WebDriver webDriver;
  private long startTime;

  @BeforeAll
  static void openBrowser() {
    webDriver = new ChromeDriver(new ChromeOptions().enableBiDi());
    //webDriver = new EdgeDriver(new EdgeOptions().enableBiDi());
    //webDriver = new FirefoxDriver(new FirefoxOptions().enableBiDi()); // No CDP, but has BiDi
    //webDriver = new SafariDriver(new SafariOptions()); // No CDP, No BiDi

    webDriver.manage().window().setPosition(new Point(300, 100));
    webDriver.manage().window().setSize(new Dimension(1024, 768));
    webDriver.navigate().to("about:blank");
    // webDriver.navigate().to("https://selenide.org"); // this makes Chrome BiDi even slower
  }

  @AfterAll
  static void afterAll() {
    if (webDriver != null) {
      webDriver.quit();
      webDriver = null;
    }
  }

  @BeforeEach
  void setUp() {
    startTime = currentTimeMillis();
  }

  @AfterEach
  void tearDown() {
    log.info("Took {} screenshots in {} ms,", COUNT, currentTimeMillis() - startTime);
  }

  @Test
  public void bidi() {
    for (int i = 0; i < COUNT; i++) {
      String windowHandle = webDriver.getWindowHandle();
      BrowsingContext browsingContext = new BrowsingContext(webDriver, windowHandle);
      String screenshotBase64 = browsingContext.captureScreenshot();
      byte[] screenshot = BYTES.convertFromBase64Png(screenshotBase64);
      assertThat(screenshot).isNotEmpty();
    }
  }

  @Test
  public void webdriver() {
    for (int i = 0; i < COUNT; i++) {
      byte[] screenshot = ((TakesScreenshot) webDriver).getScreenshotAs(BYTES);
      assertThat(screenshot).isNotEmpty();
    }
  }

  @Test
  public void cdp() {
    for (int i = 0; i < COUNT; i++) {
      HasCdp cdp = (HasCdp) webDriver;

      Map<String, Object> result = cdp.executeCdpCommand("Page.captureScreenshot", Map.of());

      String base64 = (String) result.get("data");
      byte[] screenshot = BYTES.convertFromBase64Png(base64);
      assertThat(screenshot).isNotEmpty();
    }
  }

  @Test
  public void devtools() {
    for (int i = 0; i < COUNT; i++) {
      HasDevTools driver = (HasDevTools) webDriver;
      DevTools devTools = driver.getDevTools();
      devTools.createSessionIfThereIsNotOne(webDriver.getWindowHandle());

      String base64 = devTools.send(Page.captureScreenshot(
          Optional.empty(),
          Optional.empty(),
          Optional.empty(),
          Optional.empty(),
          Optional.empty(),
          Optional.of(true)
        )
      );

      byte[] screenshot = BYTES.convertFromBase64Png(base64);
      assertThat(screenshot).isNotEmpty();
    }
  }
}
