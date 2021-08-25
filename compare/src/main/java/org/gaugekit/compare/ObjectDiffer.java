package org.gaugekit.compare;

import org.javers.core.Javers;
import org.javers.core.JaversBuilder;
import org.javers.core.diff.Diff;

public class ObjectDiffer {

    protected Javers javers;

    public ObjectDiffer() {
        javers = JaversBuilder.javers().build();
    }

    public Diff diff(Object source, Object target) {
        Diff diff = javers.compare(source, target);
        return diff;
    }

    public String toJson(Diff diff) {
        return javers.getJsonConverter().toJson(diff);
    }

}
