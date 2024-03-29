package org.gaugekit.browser.supplier;

import org.gaugekit.browser.webdriver.BrowserType;
import org.gaugekit.browser.webdriver.DockerWebDriverSupplier;
import org.junit.jupiter.api.condition.EnabledOnOs;
import org.junit.jupiter.api.condition.OS;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.io.IOException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assumptions.assumeThat;

class DockerWebDriverSupplierTest {

    @EnabledOnOs(OS.LINUX)
    @ParameterizedTest(name = "get works for {0}")
    @EnumSource(value = BrowserType.class, names = {"CHROME", "FIREFOX"})
    void getTest1(BrowserType browserType) throws IOException, InterruptedException {
        assumeThat(new ProcessBuilder("which", "docker").start().waitFor()).isEqualTo(0);

        final var dockerWebDriverSupplier = new DockerWebDriverSupplier(browserType);

        assertThat(dockerWebDriverSupplier.get())
                .isNotNull()
                .isExactlyInstanceOf(RemoteWebDriver.class);

        dockerWebDriverSupplier.close();
    }
}