package com.walid.credit.model;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

/**
 * @author Walid Moustafa
 */
public class Entity {

    private String id;
    private Entity parent;
    private Set<Entity> children = new HashSet<>();
    private long limit;
    private long childTotalLimit;
    private long utilisation;
    private long totalUtilisation;
    private boolean isLoaded;
    private boolean utilisationBreach;
    private boolean limitMisconfig;

    public void linkToParent(Entity parent) {
        this.parent = parent;
        parent.addChild(this);
    }

    public long getLimit() {
        return limit;
    }

    public void setLimit(long limit) {
        this.limit = limit;
        if (Objects.nonNull(parent)) {
            parent.raiseChildTotalLimit(limit);
        }
    }

    public void raiseChildTotalLimit(long childLimit) {
        this.childTotalLimit += childLimit;
    }

    public long getUtilisation() {
        return utilisation;
    }

    public void setUtilisation(long utilisation) {
        this.utilisation = utilisation;
        raiseTotalUtilisation(utilisation);
    }

    public void raiseTotalUtilisation(long utilisation) {
        this.totalUtilisation += utilisation;
        if (Objects.nonNull(parent)) {
            if (alreadyLoaded()) {
                parent.raiseTotalUtilisation(utilisation);
            } else {
                parent.raiseTotalUtilisation(totalUtilisation);
            }
        }
    }

    public void setLoaded() {
        isLoaded = true;
    }

    public boolean alreadyLoaded() {
        return isLoaded;
    }

    public void addChild(Entity child) {
        children.add(child);
    }

    public boolean isUtilisationBreach() {
        return utilisationBreach;
    }

    public void setUtilisationBreach() {
        this.utilisationBreach = true;
    }

    public boolean isLimitMisconfig() {
        return limitMisconfig;
    }

    public void setLimitMisconfig() {
        this.limitMisconfig = true;
    }

    public long getChildTotalLimit() {
        return childTotalLimit;
    }

    public long getTotalUtilisation() {
        return totalUtilisation;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public String deepPrint() {
        String entityStr = "Entity[" + "id='" + id + "', limit=" + limit + ", childTotalLimit="
                + childTotalLimit + ", utilisation=" + utilisation + ", totalUtilisation="
                + totalUtilisation + ", isLoaded=" + isLoaded + ", utilisationBreach="
                + utilisationBreach + ", limitMisconfig=" + limitMisconfig + "]";
        StringBuilder sb = new StringBuilder();
        if (Objects.nonNull(parent)) {
            sb.append(parent.deepPrint()).append("\n").append(entityStr);
        } else {
            sb.append("\n").append(entityStr);
        }
        return sb.toString();
    }

    @Override
    public String toString() {
        return "\nEntity[" + "id='" + id + "', parent=" + (parent == null ? "" : parent.getId())
                + ", childrenCount=" + children.size() + ", limit=" + limit + ", childTotalLimit="
                + childTotalLimit + ", utilisation=" + utilisation + ", totalUtilisation="
                + totalUtilisation + ", isLoaded=" + isLoaded + ", utilisationBreach="
                + utilisationBreach + ", limitMisconfig=" + limitMisconfig + "]";
    }
}
