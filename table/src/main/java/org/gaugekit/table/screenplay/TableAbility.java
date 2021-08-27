package org.gaugekit.table.screenplay;

import org.gaugekit.screenplay.Ability;

public class TableAbility implements Ability {

    public static TableAbility table() {
        return new TableAbility();
    }

}
