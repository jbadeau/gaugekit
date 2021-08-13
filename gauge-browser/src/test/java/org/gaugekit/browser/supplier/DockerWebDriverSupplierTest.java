package org.gaugekit.browser.supplier;

import org.junit.jupiter.api.condition.EnabledOnOs;
import org.junit.jupiter.api.condition.OS;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.io.IOException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assumptions.assumeTrue;

class DockerWebDriverSupplierTest {

    @ParameterizedTest(name = "get works for {0}")
    @EnumSource(value = BrowserType.class, names = {"CHROME", "FIREFOX"})
    @EnabledOnOs(OS.LINUX)
    void getTest1(BrowserType browserType) throws IOException, InterruptedException {
        assumeTrue(new ProcessBuilder("where", "docker").start().waitFor() == 0);

        final DockerWebDriverSupplier dockerWebDriverSupplier = new DockerWebDriverSupplier(browserType);

        assertThat(dockerWebDriverSupplier.get())
                .isNotNull()
                .isExactlyInstanceOf(RemoteWebDriver.class);

        dockerWebDriverSupplier.close();
    }
}