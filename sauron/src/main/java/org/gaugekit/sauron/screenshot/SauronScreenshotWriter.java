package org.gaugekit.sauron.screenshot;

import org.gaugekit.sauron.property.SauronProperties;
import com.thoughtworks.gauge.screenshot.CustomScreenshotWriter;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.FileFilter;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.UUID;

public class SauronScreenshotWriter implements CustomScreenshotWriter {

    @Override
    public String takeScreenshot() {
        File targetFile = new File(Paths.get(System.getenv("gauge_screenshots_dir"), UUID.randomUUID().toString()).toString() + ".png");
        File sourceFile = getTheNewestFile(SauronProperties.sauron_diff_dir().toFile());
        if (sourceFile == null) {
            return null;
        }
        try {
            FileUtils.copyFile(sourceFile, targetFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return targetFile.getName();
    }

    public static File getTheNewestFile(File dir) {
        File choice = null;
        if (dir.listFiles().length > 0) {
            File[] files = dir.listFiles(new FileFilter() {
                public boolean accept(File file) {
                    return file.isFile();
                }
            });
            long lastMod = Long.MIN_VALUE;

            for (File file : files) {
                if (file.lastModified() > lastMod) {
                    choice = file;
                    lastMod = file.lastModified();
                }
            }
        }
        return choice;
    }

}