package org.gaugekit.screenplay;

import com.google.common.base.Preconditions;

import java.util.Optional;

public class Stage {

    private Actor actorInSpotlight;

    private final Cast cast;

    public Stage(Cast cast) {
        this.cast = cast;
    }

    public Actor shineSpotlightOn(String actorName) {

        Optional<Actor> knownActor = cast.getActors()
                .stream()
                .filter(actor -> actor.getName().equalsIgnoreCase(actorName))
                .findFirst();

        actorInSpotlight = knownActor.orElseGet(() -> cast.actorNamed(actorName));
        return actorInSpotlight();
    }

    public Actor actorInSpotlight() {
        Preconditions.checkNotNull(actorInSpotlight);
        return actorInSpotlight;
    }

    public void drawCurtain() {
        cast.dismissAll();
    }

    public boolean isActorOnStage() {
        return actorInSpotlight != null;
    }

}
