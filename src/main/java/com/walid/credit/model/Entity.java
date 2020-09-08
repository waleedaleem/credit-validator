package com.walid.credit.model;

import java.math.BigDecimal;
import java.util.List;

/**
 * @author Walid Moustafa
 */
public class Entity {

    private String id;
    private Entity parent;
    private List<Entity> children;
    private BigDecimal limit;
    private BigDecimal childTotalLimit;
    private BigDecimal directUtilisation;
    private BigDecimal totalUtilisation;
}
