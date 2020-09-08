package com.walid.credit.repository;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.logging.Logger;

import org.apache.commons.csv.CSVRecord;
import org.apache.commons.lang3.StringUtils;

import com.walid.credit.model.Entity;

/**
 * @author Walid Moustafa
 */
public class CreditRepoImpl implements CreditRepo {

    public static final int CSV_FILED_COUNT = 4;
    private static final Logger logger = Logger.getLogger(CreditRepoImpl.class.getName());
    private static final CreditRepoImpl instance = new CreditRepoImpl();
    private Map<String, Entity> entities = new HashMap<>();

    private CreditRepoImpl() {
    }

    public static CreditRepo getInstance() {
        return instance;
    }

    @Override
    public Entity addEntity(CSVRecord record) {
        Entity entity = null;
        if (record.size() != CSV_FILED_COUNT) {
            logger.warning("Skipping a faulty CSV record");
        } else if (isDuplicate(record.get(0))) {
            logger.warning("Skipping a duplicate CSV record");
        } else {
            int index = 0;
            String id = record.get(index++);
            if (entities.containsKey(id)) {
                entity = entities.get(id);
            } else {
                entity = new Entity();
                entity.setId(id);
                entities.put(id, entity);
            }
            linkToParent(entity, record.get(index++));
            entity.setLimit(Long.parseLong(record.get(index++)));
            entity.setUtilisation(Long.parseLong(record.get(index++)));
            entity.setLoaded();
        }
        return entity;
    }

    private boolean isDuplicate(String entityId) {
        return entities.containsKey(entityId) && entities.get(entityId).alreadyLoaded();
    }

    private void linkToParent(Entity child, String parentId) {
        if (StringUtils.isNotBlank(parentId)) {
            Entity parent = putIfAbsent(parentId);
            child.linkToParent(parent);
        }
    }

    private Entity putIfAbsent(String entityId) {
        Entity parent = entities.get(entityId);
        if (Objects.isNull(parent)) {
            parent = new Entity();
            parent.setId(entityId);
            entities.put(entityId, parent);
        }
        return parent;
    }
}
