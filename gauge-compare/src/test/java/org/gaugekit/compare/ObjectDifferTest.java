package org.gaugekit.compare;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import org.gaugekit.common.io.TableReader;
import com.thoughtworks.gauge.Table;
import org.javers.core.diff.Diff;
import org.junit.Test;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;

import static org.assertj.core.api.Assertions.assertThat;

public class ObjectDifferTest {

    private static  final Gson gson = new Gson();

    @Test
    public void tableDiffTest() throws IOException {
        Table source = TableReader.readCsvTable(getFile("source.csv"));
        Table target = TableReader.readCsvTable(getFile("simple.target.csv"));
        ObjectDiffer differ = new ObjectDiffer();
        Diff diff = differ.diff(source, target);
        System.out.println(differ.toJson(diff));
        assertThat(diff.hasChanges()).isFalse();
    }

    @Test
    public void jsonDiffTest() throws IOException {
        JsonElement source = gson.fromJson(new FileReader(getFile("source.json")), JsonElement.class);
        JsonElement target = gson.fromJson(new FileReader(getFile("simple.target.json")), JsonElement.class);
        ObjectDiffer differ = new ObjectDiffer();
        Diff diff = differ.diff(source, target);
        System.out.println(differ.toJson(diff));
        assertThat(diff.hasChanges()).isFalse();
    }

    private File getFile(String fileName) throws IOException {
        ClassLoader classLoader = getClass().getClassLoader();
        URL resource = classLoader.getResource(fileName);
        if (resource == null) {
            throw new IllegalArgumentException("file is not found!");
        } else {
            return new File(resource.getFile());
        }
    }

}
