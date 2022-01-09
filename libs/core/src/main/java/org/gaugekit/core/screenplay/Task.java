package org.gaugekit.core.screenplay;

public interface Task extends Performable {

    void performAs(Actor actor);

}
