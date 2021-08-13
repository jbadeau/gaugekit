package org.gaugekit.common.io;

import org.gaugekit.common.property.DefaultProperties;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Collections;
import java.util.List;

public final class FileReader {

    private FileReader() {
    }

    public static String contentOf(String file) throws IOException {
        return new String(Files.readAllBytes(fileAt(file).toPath()));
    }

    public static String contentOf(File file) throws IOException {
        return new String(Files.readAllBytes(file.toPath()));
    }

    public static String contentOf(Path file) throws IOException {
        return new String(Files.readAllBytes(file));
    }

    public static File fileAt(String file) {
        List<String> envs = DefaultProperties.getEnvironments();
        Collections.reverse(envs);
        return new File(envs.stream()
                .map(env -> Paths.get(DefaultProperties.getDataDir().getPath(), env, file).toFile())
                .filter(f -> ((File) f).exists())
                .findFirst()
                .get().getAbsolutePath());
    }

    public static String parseEnv(File file) {
        String dataPath = DefaultProperties.getDataDir().getAbsolutePath();
        String env = file.getAbsolutePath().substring(dataPath.length() + 1);
        return new File(env.substring(0, env.indexOf(File.separator))).getName();
    }
}