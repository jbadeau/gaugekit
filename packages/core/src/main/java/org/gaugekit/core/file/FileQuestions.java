package org.gaugekit.core.file;

import org.gaugekit.core.screenplay.Actor;
import org.gaugekit.core.screenplay.Question;

import java.io.File;
import java.nio.file.Path;

public interface FileQuestions {

    default Question<Path> fileAt(String file) {
        return new Question<Path>() {
            @Override
            public Path answerAs(Actor actor) {
                FileAbility ability = actor.uses(FileAbility.class);
                return FileUtils.resolveProjectFile(file);
            }
        };
    }

    default Question<String> contentsOf(String file) {
        return new Question<String>() {
            @Override
            public String answerAs(Actor actor) {
                FileAbility ability = actor.uses(FileAbility.class);
                return FileReader.read(file);
            }
        };
    }

    default Question<String> contentsOf(Path file) {
        return new Question<String>() {
            @Override
            public String answerAs(Actor actor) {
                FileAbility ability = actor.uses(FileAbility.class);
                return FileReader.read(file);
            }
        };
    }

    default Question<String> contentsOf(File file) {
        return new Question<String>() {
            @Override
            public String answerAs(Actor actor) {
                FileAbility ability = actor.uses(FileAbility.class);
                return FileReader.read(file);
            }
        };
    }

}