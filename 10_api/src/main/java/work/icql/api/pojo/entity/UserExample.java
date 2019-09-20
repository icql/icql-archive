package work.icql.api.pojo.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author icql
 * @version 1.0
 * @date 2018/12/23 14:10
 * @Title UserExample
 * @Description UserExample
 */
public class UserExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<CriteriaBase> oredCriteria;

    public UserExample() {
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

        public CriteriaBase andNameIsNull() {
            addCriterion("name is null");
            return (CriteriaBase) this;
        }

        public CriteriaBase andNameIsNotNull() {
            addCriterion("name is not null");
            return (CriteriaBase) this;
        }

        public CriteriaBase andNameEqualTo(String value) {
            addCriterion("name =", value, "name");
            return (CriteriaBase) this;
        }

        public CriteriaBase andNameNotEqualTo(String value) {
            addCriterion("name <>", value, "name");
            return (CriteriaBase) this;
        }

        public CriteriaBase andNameGreaterThan(String value) {
            addCriterion("name >", value, "name");
            return (CriteriaBase) this;
        }

        public CriteriaBase andNameGreaterThanOrEqualTo(String value) {
            addCriterion("name >=", value, "name");
            return (CriteriaBase) this;
        }

        public CriteriaBase andNameLessThan(String value) {
            addCriterion("name <", value, "name");
            return (CriteriaBase) this;
        }

        public CriteriaBase andNameLessThanOrEqualTo(String value) {
            addCriterion("name <=", value, "name");
            return (CriteriaBase) this;
        }

        public CriteriaBase andNameLike(String value) {
            addCriterion("name like", value, "name");
            return (CriteriaBase) this;
        }

        public CriteriaBase andNameNotLike(String value) {
            addCriterion("name not like", value, "name");
            return (CriteriaBase) this;
        }

        public CriteriaBase andNameIn(List<String> values) {
            addCriterion("name in", values, "name");
            return (CriteriaBase) this;
        }

        public CriteriaBase andNameNotIn(List<String> values) {
            addCriterion("name not in", values, "name");
            return (CriteriaBase) this;
        }

        public CriteriaBase andNameBetween(String value1, String value2) {
            addCriterion("name between", value1, value2, "name");
            return (CriteriaBase) this;
        }

        public CriteriaBase andNameNotBetween(String value1, String value2) {
            addCriterion("name not between", value1, value2, "name");
            return (CriteriaBase) this;
        }

        public CriteriaBase andEmailIsNull() {
            addCriterion("email is null");
            return (CriteriaBase) this;
        }

        public CriteriaBase andEmailIsNotNull() {
            addCriterion("email is not null");
            return (CriteriaBase) this;
        }

        public CriteriaBase andEmailEqualTo(String value) {
            addCriterion("email =", value, "email");
            return (CriteriaBase) this;
        }

        public CriteriaBase andEmailNotEqualTo(String value) {
            addCriterion("email <>", value, "email");
            return (CriteriaBase) this;
        }

        public CriteriaBase andEmailGreaterThan(String value) {
            addCriterion("email >", value, "email");
            return (CriteriaBase) this;
        }

        public CriteriaBase andEmailGreaterThanOrEqualTo(String value) {
            addCriterion("email >=", value, "email");
            return (CriteriaBase) this;
        }

        public CriteriaBase andEmailLessThan(String value) {
            addCriterion("email <", value, "email");
            return (CriteriaBase) this;
        }

        public CriteriaBase andEmailLessThanOrEqualTo(String value) {
            addCriterion("email <=", value, "email");
            return (CriteriaBase) this;
        }

        public CriteriaBase andEmailLike(String value) {
            addCriterion("email like", value, "email");
            return (CriteriaBase) this;
        }

        public CriteriaBase andEmailNotLike(String value) {
            addCriterion("email not like", value, "email");
            return (CriteriaBase) this;
        }

        public CriteriaBase andEmailIn(List<String> values) {
            addCriterion("email in", values, "email");
            return (CriteriaBase) this;
        }

        public CriteriaBase andEmailNotIn(List<String> values) {
            addCriterion("email not in", values, "email");
            return (CriteriaBase) this;
        }

        public CriteriaBase andEmailBetween(String value1, String value2) {
            addCriterion("email between", value1, value2, "email");
            return (CriteriaBase) this;
        }

        public CriteriaBase andEmailNotBetween(String value1, String value2) {
            addCriterion("email not between", value1, value2, "email");
            return (CriteriaBase) this;
        }

        public CriteriaBase andPasswordIsNull() {
            addCriterion("password is null");
            return (CriteriaBase) this;
        }

        public CriteriaBase andPasswordIsNotNull() {
            addCriterion("password is not null");
            return (CriteriaBase) this;
        }

        public CriteriaBase andPasswordEqualTo(String value) {
            addCriterion("password =", value, "password");
            return (CriteriaBase) this;
        }

        public CriteriaBase andPasswordNotEqualTo(String value) {
            addCriterion("password <>", value, "password");
            return (CriteriaBase) this;
        }

        public CriteriaBase andPasswordGreaterThan(String value) {
            addCriterion("password >", value, "password");
            return (CriteriaBase) this;
        }

        public CriteriaBase andPasswordGreaterThanOrEqualTo(String value) {
            addCriterion("password >=", value, "password");
            return (CriteriaBase) this;
        }

        public CriteriaBase andPasswordLessThan(String value) {
            addCriterion("password <", value, "password");
            return (CriteriaBase) this;
        }

        public CriteriaBase andPasswordLessThanOrEqualTo(String value) {
            addCriterion("password <=", value, "password");
            return (CriteriaBase) this;
        }

        public CriteriaBase andPasswordLike(String value) {
            addCriterion("password like", value, "password");
            return (CriteriaBase) this;
        }

        public CriteriaBase andPasswordNotLike(String value) {
            addCriterion("password not like", value, "password");
            return (CriteriaBase) this;
        }

        public CriteriaBase andPasswordIn(List<String> values) {
            addCriterion("password in", values, "password");
            return (CriteriaBase) this;
        }

        public CriteriaBase andPasswordNotIn(List<String> values) {
            addCriterion("password not in", values, "password");
            return (CriteriaBase) this;
        }

        public CriteriaBase andPasswordBetween(String value1, String value2) {
            addCriterion("password between", value1, value2, "password");
            return (CriteriaBase) this;
        }

        public CriteriaBase andPasswordNotBetween(String value1, String value2) {
            addCriterion("password not between", value1, value2, "password");
            return (CriteriaBase) this;
        }

        public CriteriaBase andVersionIsNull() {
            addCriterion("version is null");
            return (CriteriaBase) this;
        }

        public CriteriaBase andVersionIsNotNull() {
            addCriterion("version is not null");
            return (CriteriaBase) this;
        }

        public CriteriaBase andVersionEqualTo(Integer value) {
            addCriterion("version =", value, "version");
            return (CriteriaBase) this;
        }

        public CriteriaBase andVersionNotEqualTo(Integer value) {
            addCriterion("version <>", value, "version");
            return (CriteriaBase) this;
        }

        public CriteriaBase andVersionGreaterThan(Integer value) {
            addCriterion("version >", value, "version");
            return (CriteriaBase) this;
        }

        public CriteriaBase andVersionGreaterThanOrEqualTo(Integer value) {
            addCriterion("version >=", value, "version");
            return (CriteriaBase) this;
        }

        public CriteriaBase andVersionLessThan(Integer value) {
            addCriterion("version <", value, "version");
            return (CriteriaBase) this;
        }

        public CriteriaBase andVersionLessThanOrEqualTo(Integer value) {
            addCriterion("version <=", value, "version");
            return (CriteriaBase) this;
        }

        public CriteriaBase andVersionIn(List<Integer> values) {
            addCriterion("version in", values, "version");
            return (CriteriaBase) this;
        }

        public CriteriaBase andVersionNotIn(List<Integer> values) {
            addCriterion("version not in", values, "version");
            return (CriteriaBase) this;
        }

        public CriteriaBase andVersionBetween(Integer value1, Integer value2) {
            addCriterion("version between", value1, value2, "version");
            return (CriteriaBase) this;
        }

        public CriteriaBase andVersionNotBetween(Integer value1, Integer value2) {
            addCriterion("version not between", value1, value2, "version");
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

        public CriteriaBase andIsDeletedIsNull() {
            addCriterion("is_deleted is null");
            return (CriteriaBase) this;
        }

        public CriteriaBase andIsDeletedIsNotNull() {
            addCriterion("is_deleted is not null");
            return (CriteriaBase) this;
        }

        public CriteriaBase andIsDeletedEqualTo(Byte value) {
            addCriterion("is_deleted =", value, "isDeleted");
            return (CriteriaBase) this;
        }

        public CriteriaBase andIsDeletedNotEqualTo(Byte value) {
            addCriterion("is_deleted <>", value, "isDeleted");
            return (CriteriaBase) this;
        }

        public CriteriaBase andIsDeletedGreaterThan(Byte value) {
            addCriterion("is_deleted >", value, "isDeleted");
            return (CriteriaBase) this;
        }

        public CriteriaBase andIsDeletedGreaterThanOrEqualTo(Byte value) {
            addCriterion("is_deleted >=", value, "isDeleted");
            return (CriteriaBase) this;
        }

        public CriteriaBase andIsDeletedLessThan(Byte value) {
            addCriterion("is_deleted <", value, "isDeleted");
            return (CriteriaBase) this;
        }

        public CriteriaBase andIsDeletedLessThanOrEqualTo(Byte value) {
            addCriterion("is_deleted <=", value, "isDeleted");
            return (CriteriaBase) this;
        }

        public CriteriaBase andIsDeletedIn(List<Byte> values) {
            addCriterion("is_deleted in", values, "isDeleted");
            return (CriteriaBase) this;
        }

        public CriteriaBase andIsDeletedNotIn(List<Byte> values) {
            addCriterion("is_deleted not in", values, "isDeleted");
            return (CriteriaBase) this;
        }

        public CriteriaBase andIsDeletedBetween(Byte value1, Byte value2) {
            addCriterion("is_deleted between", value1, value2, "isDeleted");
            return (CriteriaBase) this;
        }

        public CriteriaBase andIsDeletedNotBetween(Byte value1, Byte value2) {
            addCriterion("is_deleted not between", value1, value2, "isDeleted");
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