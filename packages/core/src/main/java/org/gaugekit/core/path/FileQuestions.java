package org.gaugekit.core.path;

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
                return FileReader.fileAt(file);
            }
        };
    }

    default Question<String> contentsOf(String file) {
        return new Question<String>() {
            @Override
            public String answerAs(Actor actor) {
                FileAbility ability = actor.uses(FileAbility.class);
                return FileReader.contentsOf(file);
            }
        };
    }

    default Question<String> contentsOf(Path file) {
        return new Question<String>() {
            @Override
            public String answerAs(Actor actor) {
                FileAbility ability = actor.uses(FileAbility.class);
                return FileReader.contentsOf(file);
            }
        };
    }

    default Question<String> contentsOf(File file) {
        return new Question<String>() {
            @Override
            public String answerAs(Actor actor) {
                FileAbility ability = actor.uses(FileAbility.class);
                return FileReader.contentsOf(file);
            }
        };
    }

}