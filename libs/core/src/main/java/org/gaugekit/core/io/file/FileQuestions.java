package org.gaugekit.core.io.file;

import org.gaugekit.core.io.file.screenplay.ManageFiles;
import org.gaugekit.core.screenplay.Actor;
import org.gaugekit.core.screenplay.Question;

import java.nio.file.Path;

public class FileQuestions {



    public static Question<Path> fileAt(String file) {
        return new Question<Path>() {
            @Override
            public Path answerAs(Actor actor) {
                ManageFiles ability = actor.uses(ManageFiles.class);
                return FileUtils.resolveDataFile(file);
            }
        };
    }

    public static Question<String> contentsOf(String file) {
        return new Question<String>() {
            @Override
            public String answerAs(Actor actor) {
                ManageFiles ability = actor.uses(ManageFiles.class);
                return FileReader.read(file);
            }
        };
    }

    public static Question<String> contentsOf(Path file) {
        return new Question<String>() {
            @Override
            public String answerAs(Actor actor) {
                ManageFiles ability = actor.uses(ManageFiles.class);
                return FileReader.read(file);
            }
        };
    }

}