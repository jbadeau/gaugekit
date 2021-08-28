package org.gaugekit.core.file;

import org.gaugekit.core.screenplay.Ability;
public class FileAbility implements Ability {

    public static FileAbility io() {
        return new FileAbility();
    }

}
