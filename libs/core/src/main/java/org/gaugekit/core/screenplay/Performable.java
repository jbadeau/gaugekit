package org.gaugekit.core.screenplay;

@FunctionalInterface
public interface Performable {

    <T extends Actor> void performAs(T actor);
    default Performable then(Performable nextPerformable) {
        return CompositePerformable.from(this, nextPerformable);
    };

}