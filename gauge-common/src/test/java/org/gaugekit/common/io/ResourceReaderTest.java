package org.gaugekit.common.io;

import org.gaugekit.common.property.DefaultProperties;
import org.gaugekit.common.property.CommonProperties;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import java.io.File;
import java.nio.file.Paths;
import java.util.*;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.when;
import static org.powermock.api.mockito.PowerMockito.mockStatic;

@RunWith(PowerMockRunner.class)
@PrepareForTest({FileReader.class, DefaultProperties.class, CommonProperties.class})
public class ResourceReaderTest {

    @Test
    public void getEnvironment() {
        mockStatic(System.class);

        when(System.getenv("GAUGE_ENVIRONMENT")).thenReturn("int");
        List<String> environments = DefaultProperties.getEnvironments();

        assertEquals("int", environments.get(0));
    }

    @Test
    public void getFileFromDefaultEnvTest() {
        mockStatic(DefaultProperties.class);
        mockStatic(CommonProperties.class);

        when(DefaultProperties.getEnvironments())
                .thenReturn(new ArrayList<>(Collections.singletonList("default")));

        when(DefaultProperties.getDataDir())
                .thenReturn(new File(getClass().getResource("/").getFile()));

        assertTrue(FileReader.fileAt("employees.csv").getPath().endsWith(Paths.get("default", "employees.csv").toString()));
    }

    @Test
    public void getFileByDefaultEnvFallback() {
        mockStatic(DefaultProperties.class);
        mockStatic(CommonProperties.class);

        when(DefaultProperties.getEnvironments())
                .thenReturn(new ArrayList<>(Arrays.asList("default", "test")));

        when(DefaultProperties.getDataDir())
                .thenReturn(new File(getClass().getResource("/").getFile()));

        assertTrue(FileReader.fileAt("employees.csv").getPath().endsWith(Paths.get("default", "employees.csv").toString()));
    }

    @Test
    public void getFileFromCustomEnv() {
        mockStatic(DefaultProperties.class);
        mockStatic(CommonProperties.class);

        when(DefaultProperties.getEnvironments())
                .thenReturn(new ArrayList<>(Arrays.asList("default", "test")));

        when(DefaultProperties.getDataDir())
                .thenReturn(new File(getClass().getResource("/").getFile()));

        assertTrue(FileReader.fileAt("people.csv").getPath().endsWith(Paths.get("test", "people.csv").toString()));
    }

}
