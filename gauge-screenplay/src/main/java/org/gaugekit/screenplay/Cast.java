package org.gaugekit.screenplay;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Cast {

    Map<String, Actor> actors = new HashMap();

    public Actor actorNamed(String actorName, Ability... abilities) {

        if (!actors.containsKey(actorName)) {
            Actor newActor = Actor.named(actorName);

            for (Ability doSomething : abilities) {
                newActor.can(doSomething);
            }

            actors.put(actorName, newActor);
        }
        return actors.get(actorName);
    }

    public Actor actorNamed(String actorName, Pronoun pronoun, Ability... abilities) {

        if (!actors.containsKey(actorName)) {
            Actor newActor = Actor.named(actorName, pronoun);

            for (Ability doSomething : abilities) {
                newActor.can(doSomething);
            }

            actors.put(actorName, newActor);
        }
        return actors.get(actorName);
    }

    public List<Actor> getActors() {
        return actors.values().stream()
                .collect(Collectors.toList());
    }

    public void dismissAll() {
        actors.clear();
    }

}
