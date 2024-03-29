package org.gaugekit.browser.supplier;

import org.gaugekit.browser.webdriver.BrowserType;
import org.gaugekit.browser.webdriver.LocalWebDriverSupplier;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.condition.EnabledOnOs;
import org.junit.jupiter.api.condition.OS;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.NoSuchSessionException;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxOptions;

import java.io.IOException;
import java.util.Map;

import static org.assertj.core.api.Assertions.*;
import static org.assertj.core.api.Assumptions.assumeThat;
import static org.gaugekit.browser.webdriver.BrowserType.CHROME;
import static org.gaugekit.browser.webdriver.BrowserType.FIREFOX;


class LocalWebDriverSupplierTest {

    private static final Map<BrowserType, String> COMMANDS = Map.of(
            CHROME, "google-chrome",
            FIREFOX, "firefox");

    private static final Map<BrowserType, Capabilities> HEADLESS = Map.of(
            CHROME, new ChromeOptions().setHeadless(true),
            FIREFOX, new FirefoxOptions().setHeadless(true));

    @EnabledOnOs(OS.LINUX)
    @EnumSource(BrowserType.class)
    @ParameterizedTest(name = "get returns a {0}")
    void getTest1(BrowserType browserType) throws IOException, InterruptedException {
        assumeThat(COMMANDS).containsKey(browserType);
        assumeThat(new ProcessBuilder("which", COMMANDS.get(browserType)).start().waitFor()).isEqualTo(0);

        final var localWebDriverSupplier = new LocalWebDriverSupplier(browserType, HEADLESS.get(browserType));

        assertThat(localWebDriverSupplier.get())
                .isNotNull()
                .isExactlyInstanceOf(browserType.getWebDriverClass());

        localWebDriverSupplier.close();
    }

    @Test
    @EnabledOnOs(OS.LINUX)
    @DisplayName("get caches the WebDriver")
    void getTest2() throws IOException, InterruptedException {
        assumeThat(COMMANDS).containsKey(FIREFOX);
        assumeThat(new ProcessBuilder("which", COMMANDS.get(FIREFOX)).start().waitFor()).isEqualTo(0);

        final var localWebDriverSupplier = new LocalWebDriverSupplier(FIREFOX, HEADLESS.get(FIREFOX));

        final var webDriver = localWebDriverSupplier.get();
        assertThat(webDriver).isSameAs(localWebDriverSupplier.get());

        localWebDriverSupplier.close();
    }

    @Test
    @EnabledOnOs(OS.LINUX)
    @DisplayName("close quits the WebDriver")
    void closeTest1() throws IOException, InterruptedException {
        assumeThat(COMMANDS).containsKey(FIREFOX);
        assumeThat(new ProcessBuilder("which", COMMANDS.get(FIREFOX)).start().waitFor()).isEqualTo(0);

        final var localWebDriverSupplier = new LocalWebDriverSupplier(FIREFOX, HEADLESS.get(FIREFOX));

        final var webDriver = localWebDriverSupplier.get();

        assertThatNoException()
                .isThrownBy(() -> webDriver.manage().window().getSize());

        localWebDriverSupplier.close();

        assertThatExceptionOfType(NoSuchSessionException.class)
                .isThrownBy(() -> webDriver.manage().window().getSize());
    }
}