package org.gaugekit.core.io.file;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public final class FileReader {

    private FileReader() {
    }

    public static String read(String path) {
        try {
            return new String(Files.readAllBytes(FileUtils.resolveDataFile(path)));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static String read(Path file) {
        try {
            return new String(Files.readAllBytes(file));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}