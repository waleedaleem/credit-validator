package com.walid.credit.model;

import java.util.List;
import java.util.Objects;

/**
 * @author Walid Moustafa
 */
public class Entity {

    private String id;
    private Entity parent;
    private List<Entity> children;
    private long limit;
    private long childTotalLimit;
    private long utilisation;
    private long totalUtilisation;
    private boolean isLoaded;

    public void setId(String id) {
        this.id = id;
    }

    public void setParent(Entity parent) {
        this.parent = parent;
    }

    public void setChildren(List<Entity> children) {
        this.children = children;
    }

    public void setLimit(long limit) {
        this.limit = limit;
    }

    public void setChildTotalLimit(long childTotalLimit) {
        this.childTotalLimit = childTotalLimit;
    }

    public void setUtilisation(long utilisation) {
        this.utilisation = utilisation;
    }

    public void setTotalUtilisation(long totalUtilisation) {
        this.totalUtilisation = totalUtilisation;
    }

    public void setLoaded() {
        isLoaded = true;
    }

    public boolean alreadyLoaded() {
        return isLoaded;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        Entity entity = (Entity) o;
        return id.equals(entity.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
