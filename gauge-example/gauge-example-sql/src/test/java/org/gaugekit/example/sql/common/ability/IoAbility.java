package org.gaugekit.example.sql.common.ability;

import org.gaugekit.screenplay.Ability;

public class IoAbility implements Ability {

    public IoAbility() {
    }

    public static Ability io() {
        return new IoAbility();
    }

}