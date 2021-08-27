package org.gaugekit.core.io;

import org.gaugekit.core.property.GaugeProperties;
import org.gaugekit.core.util.DataUtils;
import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;
import org.mockito.Mockito;

import java.net.URISyntaxException;
import java.nio.file.Paths;
import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;

public class ResourceReaderTest {


    @Test
    public void getFileFromDefaultEnvTest() throws URISyntaxException {
        try (MockedStatic mocked = Mockito.mockStatic(GaugeProperties.class)) {
            mocked.when(GaugeProperties::GAUGE_ENVIRONMENT).thenReturn(Arrays.asList("default"));
            mocked.when(GaugeProperties::gauge_data_dir).thenReturn(Paths.get(Thread.currentThread().getContextClassLoader().getResource(".").toURI()));
            assertThat(DataUtils.fileAt("employees.csv").endsWith(Paths.get("default", "employees.csv").toString())).isTrue();
        }
    }

    @Test
    public void getFileFromDefaultEnvFallback() throws URISyntaxException {
        try (MockedStatic mocked = Mockito.mockStatic(GaugeProperties.class)) {
            mocked.when(GaugeProperties::GAUGE_ENVIRONMENT).thenReturn(Arrays.asList("test", "default"));
            mocked.when(GaugeProperties::gauge_data_dir).thenReturn(Paths.get(Thread.currentThread().getContextClassLoader().getResource(".").toURI()));

            assertThat(DataUtils.fileAt("employees.csv").endsWith(Paths.get("default", "employees.csv").toString())).isTrue();
        }
    }

    @Test
    public void getFileFromCustomEnv() throws URISyntaxException {
        try (MockedStatic mocked = Mockito.mockStatic(GaugeProperties.class)) {
            mocked.when(GaugeProperties::GAUGE_ENVIRONMENT).thenReturn(Arrays.asList("test", "default"));
            mocked.when(GaugeProperties::gauge_data_dir).thenReturn(Paths.get(Thread.currentThread().getContextClassLoader().getResource(".").toURI()));
            assertThat(DataUtils.fileAt("people.csv").endsWith(Paths.get("test", "people.csv").toString())).isTrue();
        }
    }

}
