package org.gaugekit.browser.supplier;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxOptions;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import static org.gaugekit.browser.supplier.BrowserType.CHROME;
import static org.gaugekit.browser.supplier.BrowserType.FIREFOX;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assumptions.assumeTrue;

class LocalWebDriverSupplierTest {

    private static final Map<BrowserType, String> COMMANDS;

    private static final Map<BrowserType, Capabilities> HEADLESS;

    static {
        COMMANDS = new HashMap<>(2);
        COMMANDS.put(CHROME, "chromedriver");
        COMMANDS.put(FIREFOX, "geckodriver");

        HEADLESS = new HashMap<>(2);
        HEADLESS.put(CHROME, new ChromeOptions().setHeadless(true));
        HEADLESS.put(FIREFOX, new FirefoxOptions().setHeadless(true));

    }

    @ParameterizedTest(name = "get returns a {0}")
    @EnumSource(BrowserType.class)
    void getTest1(BrowserType browserType) throws IOException, InterruptedException {
        assumeTrue(COMMANDS.containsKey(browserType));
        assumeTrue(new ProcessBuilder("where", COMMANDS.get(browserType)).start().waitFor() == 0);

        final LocalWebDriverSupplier localWebDriverSupplier = new LocalWebDriverSupplier(browserType, HEADLESS.get(browserType));

        assertThat(localWebDriverSupplier.get())
                .isNotNull()
                .isExactlyInstanceOf(browserType.getWebDriverClass());

        localWebDriverSupplier.close();
    }

    @Test
    @DisplayName("get caches the WebDriver")
    void getTest2() throws IOException, InterruptedException {
        assumeTrue(COMMANDS.containsKey(FIREFOX));
        assumeTrue(new ProcessBuilder("where", COMMANDS.get(FIREFOX)).start().waitFor() == 0);

        final LocalWebDriverSupplier localWebDriverSupplier = new LocalWebDriverSupplier(FIREFOX, HEADLESS.get(FIREFOX));

        final WebDriver webDriver = localWebDriverSupplier.get();
        assertThat(webDriver).isSameAs(localWebDriverSupplier.get());

        localWebDriverSupplier.close();
    }

    @Test
    @DisplayName("close does nothing when no WebDriver was created")
    void closeTest1() {
        new LocalWebDriverSupplier(FIREFOX).close();
    }
}