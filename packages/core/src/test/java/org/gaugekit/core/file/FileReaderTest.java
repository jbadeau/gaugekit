package org.gaugekit.core.file;

import org.gaugekit.core.GaugeProperties;
import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;
import org.mockito.Mockito;

import java.net.URISyntaxException;
import java.nio.file.Paths;
import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;

public class FileReaderTest {

    @Test
    public void getFileFromDevEnv() throws URISyntaxException {
        try (MockedStatic mocked = Mockito.mockStatic(GaugeProperties.class)) {
            mocked.when(GaugeProperties::gaugeEnvs).thenReturn(Arrays.asList("dev", "default"));
            mocked.when(GaugeProperties::dataDir).thenReturn(Paths.get(Thread.currentThread().getContextClassLoader().getResource("./data/dev").toURI()));
            assertThat(PathUtils.resolveProjectPath("employees.csv").endsWith(Paths.get("dev", "employees.csv").toString())).isTrue();
        }
    }

    @Test
    public void getFileFromDefaultEnv() throws URISyntaxException {
        try (MockedStatic mocked = Mockito.mockStatic(GaugeProperties.class)) {
            mocked.when(GaugeProperties::gaugeEnvs).thenReturn(Arrays.asList("default"));
            mocked.when(GaugeProperties::dataDir).thenReturn(Paths.get(Thread.currentThread().getContextClassLoader().getResource("./data/default").toURI()));
            assertThat(PathUtils.resolveProjectPath("people.csv").endsWith(Paths.get("default", "people.csv").toString())).isTrue();
        }
    }

}
