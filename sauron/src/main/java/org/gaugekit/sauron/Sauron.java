package org.gaugekit.sauron;

import org.apache.commons.io.FileUtils;
import org.gaugekit.sauron.property.SauronProperties;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import ru.yandex.qatools.ashot.AShot;
import ru.yandex.qatools.ashot.Screenshot;
import ru.yandex.qatools.ashot.comparison.ImageDiff;
import ru.yandex.qatools.ashot.comparison.ImageDiffer;
import ru.yandex.qatools.ashot.coordinates.JqueryCoordsProvider;
import ru.yandex.qatools.ashot.shooting.ShootingStrategies;

import javax.imageio.ImageIO;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class Sauron {

    private static final String PNG = "PNG";

    private static final String PNG_EXTENSION = ".png";

    private SauronProperties config;

    private WebDriver driver;

    private AShot aShot;

    private List<ImageDiff> imageDiffs;

    public Sauron(SauronProperties config) {
        this.config = config;
    }

    public Sauron openEye(WebDriver driver) {
        this.driver = driver;
        this.imageDiffs = new ArrayList();
        return this;
    }

    public List<ImageDiff> gazeUpon(String name) {
        hideScollbars();
        hideCursor();
        Screenshot snapshot = new AShot()
                .shootingStrategy(ShootingStrategies.viewportPasting(100))
                .coordsProvider(new JqueryCoordsProvider())
                .takeScreenshot(driver);
        showScollbars();
        saveSnapshot(name, snapshot);
        compareToBaseline(name, snapshot);
        return imageDiffs;
    }

    public List<ImageDiff> gazeUpon(String name, Set<By> ignores) {
        hideScollbars();
        hideCursor();
        Screenshot snapshot = new AShot()
                .shootingStrategy(ShootingStrategies.viewportPasting(100))
                .coordsProvider(new JqueryCoordsProvider())
                .ignoredElements(ignores)
                .takeScreenshot(driver);
        showScollbars();
        showElements(ignores);
        saveSnapshot(name, snapshot);
        compareToBaseline(name, snapshot);
        return imageDiffs;
    }

    public List<ImageDiff> gazeUpon(String name, By ignore) {
        hideScollbars();
        hideCursor();
        Screenshot snapshot = new AShot()
                .shootingStrategy(ShootingStrategies.viewportPasting(100))
                .coordsProvider(new JqueryCoordsProvider())
                .addIgnoredElement(ignore)
                .takeScreenshot(driver);
        showScollbars();
        showElement(ignore);
        saveSnapshot(name, snapshot);
        compareToBaseline(name, snapshot);
        return imageDiffs;
    }

    public void closeEye() {
    }

    private void compareToBaseline(String name, Screenshot snapshot) {
        try {
            Screenshot baseline = getBaseline(name, snapshot);
            baseline.setIgnoredAreas(snapshot.getIgnoredAreas());
            baseline.setCoordsToCompare(snapshot.getCoordsToCompare());
            ImageDiff diff = new ImageDiffer().makeDiff(baseline, snapshot);
            if (diff.hasDiff()) {
                saveDiff(name, diff);
            }
            imageDiffs.add(diff);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void saveDiff(String name, ImageDiff diff) throws IOException {
        Path diffFile = config.diffDir().resolve(name + PNG_EXTENSION);
        FileUtils.forceMkdirParent(diffFile.toFile());
        ImageIO.write(diff.getMarkedImage(), PNG, diffFile.toFile());
    }

    private void deleteDiff(String name, ImageDiff diff) throws IOException {
        Path diffFile = config.diffDir().resolve(name + PNG_EXTENSION);
        if (Files.exists(diffFile)) {
            Files.delete(diffFile);
        }
    }

    private void saveSnapshot(String name, Screenshot snapshot) {
        Path  snapshotFile = config.snapshotDir().resolve(name + PNG_EXTENSION);
        try {
            FileUtils.forceMkdirParent(snapshotFile.toFile());
            ImageIO.write(snapshot.getImage(), PNG, snapshotFile.toFile());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private Screenshot getBaseline(String name, Screenshot snapshot) throws IOException {
        Path baselineFile = config.baselineDir().resolve(name + PNG_EXTENSION);
        if (!Files.exists(baselineFile)) {
            FileUtils.forceMkdirParent(baselineFile.toFile());
            ImageIO.write(snapshot.getImage(), PNG, baselineFile.toFile());
        }
        return new Screenshot(ImageIO.read(baselineFile.toFile()));
    }

    private void hideElements(Set<By> bys) {
        for (By by : bys) {
            hideElement(by);
        }
    }

    private void showElements(Set<By> bys) {
        for (By by : bys) {
            showElement(by);
        }
    }

    private void hideElement(By by) {
        List<WebElement> webElements = by.findElements(driver);
        for (WebElement webElement : webElements) {
            ((JavascriptExecutor) driver).executeScript("arguments[0].style.visibility='hidden'", webElement);
        }
    }

    private void showElement(By by) {
        List<WebElement> webElements = by.findElements(driver);
        for (WebElement webElement : webElements) {
            ((JavascriptExecutor) driver).executeScript("arguments[0].style.visibility='visible'", webElement);
        }
    }

    private void hideCursor() {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("{ var el = document.activeElement; el && el.blur(); }");
    }

    private void hideScollbars() {
        ((JavascriptExecutor) driver).executeScript("document.body.style.overflow = 'hidden';");
    }

    private void showScollbars() {
        ((JavascriptExecutor) driver).executeScript("document.body.style.overflow = 'visible';");
    }

}
