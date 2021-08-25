package org.gaugekit.screenplay;

public class OnStage {

    private static final ThreadLocal<Stage> STAGE = new ThreadLocal<>();

    public static Stage setTheStage(Cast cast) {
        STAGE.set(new Stage(cast));
        return stage();
    }

    public static Stage setTheStage(Stage stage) {
        STAGE.set(stage);
        return stage();
    }

    public static Actor theActorCalled(String requiredActor) {
        if (ScreenplayProperties.screenplay_pronouns().contains(requiredActor)) {
            return stage().actorInSpotlight().usingPronoun(requiredActor);
        }
        return stage().shineSpotlightOn(requiredActor);
    }

    private static boolean anActorIsOnStage() {
        return stage().isActorOnStage();
    }

    public static Actor actorInSpotlight() {
        return stage().actorInSpotlight();
    }

    public static Stage stage() {
        if (STAGE.get() == null) {
            throw new NoStageException("No stage available - it looks like you haven't called the setTheStage() method before calling this one.");
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
