package org.gaugekit.core.table;

import org.gaugekit.core.screenplay.Ability;

public class TableAbility implements Ability {

    public static TableAbility table() {
        return new TableAbility();
    }

}
