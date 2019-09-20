package work.icql.api.pojo.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author icql
 * @version 1.0
 * @date 2018/12/23 14:10
 * @Title VipExample
 * @Description VipExample
 */
public class VipExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<CriteriaBase> oredCriteria;

    public VipExample() {
        oredCriteria = new ArrayList<CriteriaBase>();
    }

    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    public String getOrderByClause() {
        return orderByClause;
    }

    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
    }

    public boolean isDistinct() {
        return distinct;
    }

    public List<CriteriaBase> getOredCriteria() {
        return oredCriteria;
    }

    public void or(CriteriaBase criteria) {
        oredCriteria.add(criteria);
    }

    public CriteriaBase or() {
        CriteriaBase criteria = createCriteriaInternal();
        oredCriteria.add(criteria);
        return criteria;
    }

    public CriteriaBase createCriteria() {
        CriteriaBase criteria = createCriteriaInternal();
        if (oredCriteria.size() == 0) {
            oredCriteria.add(criteria);
        }
        return criteria;
    }

    protected CriteriaBase createCriteriaInternal() {
        CriteriaBase criteria = new CriteriaBase();
        return criteria;
    }

    public void clear() {
        oredCriteria.clear();
        orderByClause = null;
        distinct = false;
    }

    protected abstract static class BaseGeneratedCriteria {
        protected List<Criterion> criteria;

        protected BaseGeneratedCriteria() {
            super();
            criteria = new ArrayList<Criterion>();
        }

        public boolean isValid() {
            return criteria.size() > 0;
        }

        public List<Criterion> getAllCriteria() {
            return criteria;
        }

        public List<Criterion> getCriteria() {
            return criteria;
        }

        protected void addCriterion(String condition) {
            if (condition == null) {
                throw new RuntimeException("Value for condition cannot be null");
            }
            criteria.add(new Criterion(condition));
        }

        protected void addCriterion(String condition, Object value, String property) {
            if (value == null) {
                throw new RuntimeException("Value for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value));
        }

        protected void addCriterion(String condition, Object value1, Object value2, String property) {
            if (value1 == null || value2 == null) {
                throw new RuntimeException("Between values for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value1, value2));
        }

        public CriteriaBase andIdIsNull() {
            addCriterion("id is null");
            return (CriteriaBase) this;
        }

        public CriteriaBase andIdIsNotNull() {
            addCriterion("id is not null");
            return (CriteriaBase) this;
        }

        public CriteriaBase andIdEqualTo(Long value) {
            addCriterion("id =", value, "id");
            return (CriteriaBase) this;
        }

        public CriteriaBase andIdNotEqualTo(Long value) {
            addCriterion("id <>", value, "id");
            return (CriteriaBase) this;
        }

        public CriteriaBase andIdGreaterThan(Long value) {
            addCriterion("id >", value, "id");
            return (CriteriaBase) this;
        }

        public CriteriaBase andIdGreaterThanOrEqualTo(Long value) {
            addCriterion("id >=", value, "id");
            return (CriteriaBase) this;
        }

        public CriteriaBase andIdLessThan(Long value) {
            addCriterion("id <", value, "id");
            return (CriteriaBase) this;
        }

        public CriteriaBase andIdLessThanOrEqualTo(Long value) {
            addCriterion("id <=", value, "id");
            return (CriteriaBase) this;
        }

        public CriteriaBase andIdIn(List<Long> values) {
            addCriterion("id in", values, "id");
            return (CriteriaBase) this;
        }

        public CriteriaBase andIdNotIn(List<Long> values) {
            addCriterion("id not in", values, "id");
            return (CriteriaBase) this;
        }

        public CriteriaBase andIdBetween(Long value1, Long value2) {
            addCriterion("id between", value1, value2, "id");
            return (CriteriaBase) this;
        }

        public CriteriaBase andIdNotBetween(Long value1, Long value2) {
            addCriterion("id not between", value1, value2, "id");
            return (CriteriaBase) this;
        }

        public CriteriaBase andTypeIsNull() {
            addCriterion("type is null");
            return (CriteriaBase) this;
        }

        public CriteriaBase andTypeIsNotNull() {
            addCriterion("type is not null");
            return (CriteriaBase) this;
        }

        public CriteriaBase andTypeEqualTo(Byte value) {
            addCriterion("type =", value, "type");
            return (CriteriaBase) this;
        }

        public CriteriaBase andTypeNotEqualTo(Byte value) {
            addCriterion("type <>", value, "type");
            return (CriteriaBase) this;
        }

        public CriteriaBase andTypeGreaterThan(Byte value) {
            addCriterion("type >", value, "type");
            return (CriteriaBase) this;
        }

        public CriteriaBase andTypeGreaterThanOrEqualTo(Byte value) {
            addCriterion("type >=", value, "type");
            return (CriteriaBase) this;
        }

        public CriteriaBase andTypeLessThan(Byte value) {
            addCriterion("type <", value, "type");
            return (CriteriaBase) this;
        }

        public CriteriaBase andTypeLessThanOrEqualTo(Byte value) {
            addCriterion("type <=", value, "type");
            return (CriteriaBase) this;
        }

        public CriteriaBase andTypeIn(List<Byte> values) {
            addCriterion("type in", values, "type");
            return (CriteriaBase) this;
        }

        public CriteriaBase andTypeNotIn(List<Byte> values) {
            addCriterion("type not in", values, "type");
            return (CriteriaBase) this;
        }

        public CriteriaBase andTypeBetween(Byte value1, Byte value2) {
            addCriterion("type between", value1, value2, "type");
            return (CriteriaBase) this;
        }

        public CriteriaBase andTypeNotBetween(Byte value1, Byte value2) {
            addCriterion("type not between", value1, value2, "type");
            return (CriteriaBase) this;
        }

        public CriteriaBase andGmtCreateIsNull() {
            addCriterion("gmt_create is null");
            return (CriteriaBase) this;
        }

        public CriteriaBase andGmtCreateIsNotNull() {
            addCriterion("gmt_create is not null");
            return (CriteriaBase) this;
        }

        public CriteriaBase andGmtCreateEqualTo(Date value) {
            addCriterion("gmt_create =", value, "gmtCreate");
            return (CriteriaBase) this;
        }

        public CriteriaBase andGmtCreateNotEqualTo(Date value) {
            addCriterion("gmt_create <>", value, "gmtCreate");
            return (CriteriaBase) this;
        }

        public CriteriaBase andGmtCreateGreaterThan(Date value) {
            addCriterion("gmt_create >", value, "gmtCreate");
            return (CriteriaBase) this;
        }

        public CriteriaBase andGmtCreateGreaterThanOrEqualTo(Date value) {
            addCriterion("gmt_create >=", value, "gmtCreate");
            return (CriteriaBase) this;
        }

        public CriteriaBase andGmtCreateLessThan(Date value) {
            addCriterion("gmt_create <", value, "gmtCreate");
            return (CriteriaBase) this;
        }

        public CriteriaBase andGmtCreateLessThanOrEqualTo(Date value) {
            addCriterion("gmt_create <=", value, "gmtCreate");
            return (CriteriaBase) this;
        }

        public CriteriaBase andGmtCreateIn(List<Date> values) {
            addCriterion("gmt_create in", values, "gmtCreate");
            return (CriteriaBase) this;
        }

        public CriteriaBase andGmtCreateNotIn(List<Date> values) {
            addCriterion("gmt_create not in", values, "gmtCreate");
            return (CriteriaBase) this;
        }

        public CriteriaBase andGmtCreateBetween(Date value1, Date value2) {
            addCriterion("gmt_create between", value1, value2, "gmtCreate");
            return (CriteriaBase) this;
        }

        public CriteriaBase andGmtCreateNotBetween(Date value1, Date value2) {
            addCriterion("gmt_create not between", value1, value2, "gmtCreate");
            return (CriteriaBase) this;
        }

        public CriteriaBase andGmtModifiedIsNull() {
            addCriterion("gmt_modified is null");
            return (CriteriaBase) this;
        }

        public CriteriaBase andGmtModifiedIsNotNull() {
            addCriterion("gmt_modified is not null");
            return (CriteriaBase) this;
        }

        public CriteriaBase andGmtModifiedEqualTo(Date value) {
            addCriterion("gmt_modified =", value, "gmtModified");
            return (CriteriaBase) this;
        }

        public CriteriaBase andGmtModifiedNotEqualTo(Date value) {
            addCriterion("gmt_modified <>", value, "gmtModified");
            return (CriteriaBase) this;
        }

        public CriteriaBase andGmtModifiedGreaterThan(Date value) {
            addCriterion("gmt_modified >", value, "gmtModified");
            return (CriteriaBase) this;
        }

        public CriteriaBase andGmtModifiedGreaterThanOrEqualTo(Date value) {
            addCriterion("gmt_modified >=", value, "gmtModified");
            return (CriteriaBase) this;
        }

        public CriteriaBase andGmtModifiedLessThan(Date value) {
            addCriterion("gmt_modified <", value, "gmtModified");
            return (CriteriaBase) this;
        }

        public CriteriaBase andGmtModifiedLessThanOrEqualTo(Date value) {
            addCriterion("gmt_modified <=", value, "gmtModified");
            return (CriteriaBase) this;
        }

        public CriteriaBase andGmtModifiedIn(List<Date> values) {
            addCriterion("gmt_modified in", values, "gmtModified");
            return (CriteriaBase) this;
        }

        public CriteriaBase andGmtModifiedNotIn(List<Date> values) {
            addCriterion("gmt_modified not in", values, "gmtModified");
            return (CriteriaBase) this;
        }

        public CriteriaBase andGmtModifiedBetween(Date value1, Date value2) {
            addCriterion("gmt_modified between", value1, value2, "gmtModified");
            return (CriteriaBase) this;
        }

        public CriteriaBase andGmtModifiedNotBetween(Date value1, Date value2) {
            addCriterion("gmt_modified not between", value1, value2, "gmtModified");
            return (CriteriaBase) this;
        }
    }

    public static class CriteriaBase extends BaseGeneratedCriteria {

        protected CriteriaBase() {
            super();
        }
    }

    public static class Criterion {
        private String condition;

        private Object value;

        private Object secondValue;

        private boolean noValue;

        private boolean singleValue;

        private boolean betweenValue;

        private boolean listValue;

        private String typeHandler;

        public String getCondition() {
            return condition;
        }

        public Object getValue() {
            return value;
        }

        public Object getSecondValue() {
            return secondValue;
        }

        public boolean isNoValue() {
            return noValue;
        }

        public boolean isSingleValue() {
            return singleValue;
        }

        public boolean isBetweenValue() {
            return betweenValue;
        }

        public boolean isListValue() {
            return listValue;
        }

        public String getTypeHandler() {
            return typeHandler;
        }

        protected Criterion(String condition) {
            super();
            this.condition = condition;
            this.typeHandler = null;
            this.noValue = true;
        }

        protected Criterion(String condition, Object value, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.typeHandler = typeHandler;
            if (value instanceof List<?>) {
                this.listValue = true;
            } else {
                this.singleValue = true;
            }
        }

        protected Criterion(String condition, Object value) {
            this(condition, value, null);
        }

        protected Criterion(String condition, Object value, Object secondValue, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.secondValue = secondValue;
            this.typeHandler = typeHandler;
            this.betweenValue = true;
        }

        protected Criterion(String condition, Object value, Object secondValue) {
            this(condition, value, secondValue, null);
        }
    }
}