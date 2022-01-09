package org.gaugekit.core.screenplay;

import org.aeonbits.owner.ConfigCache;
import org.gaugekit.core.ScreenplayProperties;

public class Director {

    private static final ScreenplayProperties SCREENPLAY_PROPERTIES = ConfigCache.getOrCreate(ScreenplayProperties.class);

    private static final ThreadLocal<Stage> STAGE = new ThreadLocal<>();

    public static Stage setStage(Cast cast) {
        STAGE.set(new Stage(cast));
        return stage();
    }

    public static Stage setStage(Stage stage) {
        STAGE.set(stage);
        return stage();
    }

    public static Actor actorCalled(String requiredActor) {
        if (SCREENPLAY_PROPERTIES.screenplay_pronouns().contains(requiredActor)) {
            return stage().actorInSpotlight().usingPronoun(requiredActor);
        }
        return stage().shineSpotlightOn(requiredActor);
    }

    private static boolean isActorOnStage() {
        return stage().isActorOnStage();
    }

    public static Actor actorInSpotlight() {
        return stage().actorInSpotlight();
    }

    public static Stage stage() {
        if (STAGE.get() == null) {
            throw new NoStageException("No stage available - it looks like you haven't called the setStage() method before calling this one.");
        } else {
            return STAGE.get();
        }
    }

    public static void drawCurtain() {
        if (STAGE.get() != null) {
            stage().drawCurtain();
        }
    }

}
