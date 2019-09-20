package work.icql.ssm.pojo;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class TbShiroFuncExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public TbShiroFuncExample() {
        oredCriteria = new ArrayList<Criteria>();
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

    public List<Criteria> getOredCriteria() {
        return oredCriteria;
    }

    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    public Criteria or() {
        Criteria criteria = createCriteriaInternal();
        oredCriteria.add(criteria);
        return criteria;
    }

    public Criteria createCriteria() {
        Criteria criteria = createCriteriaInternal();
        if (oredCriteria.size() == 0) {
            oredCriteria.add(criteria);
        }
        return criteria;
    }

    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria();
        return criteria;
    }

    public void clear() {
        oredCriteria.clear();
        orderByClause = null;
        distinct = false;
    }

    protected abstract static class GeneratedCriteria {
        protected List<Criterion> criteria;

        protected GeneratedCriteria() {
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

        public Criteria andFuncidIsNull() {
            addCriterion("funcId is null");
            return (Criteria) this;
        }

        public Criteria andFuncidIsNotNull() {
            addCriterion("funcId is not null");
            return (Criteria) this;
        }

        public Criteria andFuncidEqualTo(String value) {
            addCriterion("funcId =", value, "funcid");
            return (Criteria) this;
        }

        public Criteria andFuncidNotEqualTo(String value) {
            addCriterion("funcId <>", value, "funcid");
            return (Criteria) this;
        }

        public Criteria andFuncidGreaterThan(String value) {
            addCriterion("funcId >", value, "funcid");
            return (Criteria) this;
        }

        public Criteria andFuncidGreaterThanOrEqualTo(String value) {
            addCriterion("funcId >=", value, "funcid");
            return (Criteria) this;
        }

        public Criteria andFuncidLessThan(String value) {
            addCriterion("funcId <", value, "funcid");
            return (Criteria) this;
        }

        public Criteria andFuncidLessThanOrEqualTo(String value) {
            addCriterion("funcId <=", value, "funcid");
            return (Criteria) this;
        }

        public Criteria andFuncidLike(String value) {
            addCriterion("funcId like", value, "funcid");
            return (Criteria) this;
        }

        public Criteria andFuncidNotLike(String value) {
            addCriterion("funcId not like", value, "funcid");
            return (Criteria) this;
        }

        public Criteria andFuncidIn(List<String> values) {
            addCriterion("funcId in", values, "funcid");
            return (Criteria) this;
        }

        public Criteria andFuncidNotIn(List<String> values) {
            addCriterion("funcId not in", values, "funcid");
            return (Criteria) this;
        }

        public Criteria andFuncidBetween(String value1, String value2) {
            addCriterion("funcId between", value1, value2, "funcid");
            return (Criteria) this;
        }

        public Criteria andFuncidNotBetween(String value1, String value2) {
            addCriterion("funcId not between", value1, value2, "funcid");
            return (Criteria) this;
        }

        public Criteria andFuncnameIsNull() {
            addCriterion("funcName is null");
            return (Criteria) this;
        }

        public Criteria andFuncnameIsNotNull() {
            addCriterion("funcName is not null");
            return (Criteria) this;
        }

        public Criteria andFuncnameEqualTo(String value) {
            addCriterion("funcName =", value, "funcname");
            return (Criteria) this;
        }

        public Criteria andFuncnameNotEqualTo(String value) {
            addCriterion("funcName <>", value, "funcname");
            return (Criteria) this;
        }

        public Criteria andFuncnameGreaterThan(String value) {
            addCriterion("funcName >", value, "funcname");
            return (Criteria) this;
        }

        public Criteria andFuncnameGreaterThanOrEqualTo(String value) {
            addCriterion("funcName >=", value, "funcname");
            return (Criteria) this;
        }

        public Criteria andFuncnameLessThan(String value) {
            addCriterion("funcName <", value, "funcname");
            return (Criteria) this;
        }

        public Criteria andFuncnameLessThanOrEqualTo(String value) {
            addCriterion("funcName <=", value, "funcname");
            return (Criteria) this;
        }

        public Criteria andFuncnameLike(String value) {
            addCriterion("funcName like", value, "funcname");
            return (Criteria) this;
        }

        public Criteria andFuncnameNotLike(String value) {
            addCriterion("funcName not like", value, "funcname");
            return (Criteria) this;
        }

        public Criteria andFuncnameIn(List<String> values) {
            addCriterion("funcName in", values, "funcname");
            return (Criteria) this;
        }

        public Criteria andFuncnameNotIn(List<String> values) {
            addCriterion("funcName not in", values, "funcname");
            return (Criteria) this;
        }

        public Criteria andFuncnameBetween(String value1, String value2) {
            addCriterion("funcName between", value1, value2, "funcname");
            return (Criteria) this;
        }

        public Criteria andFuncnameNotBetween(String value1, String value2) {
            addCriterion("funcName not between", value1, value2, "funcname");
            return (Criteria) this;
        }

        public Criteria andFunccodeIsNull() {
            addCriterion("funcCode is null");
            return (Criteria) this;
        }

        public Criteria andFunccodeIsNotNull() {
            addCriterion("funcCode is not null");
            return (Criteria) this;
        }

        public Criteria andFunccodeEqualTo(String value) {
            addCriterion("funcCode =", value, "funccode");
            return (Criteria) this;
        }

        public Criteria andFunccodeNotEqualTo(String value) {
            addCriterion("funcCode <>", value, "funccode");
            return (Criteria) this;
        }

        public Criteria andFunccodeGreaterThan(String value) {
            addCriterion("funcCode >", value, "funccode");
            return (Criteria) this;
        }

        public Criteria andFunccodeGreaterThanOrEqualTo(String value) {
            addCriterion("funcCode >=", value, "funccode");
            return (Criteria) this;
        }

        public Criteria andFunccodeLessThan(String value) {
            addCriterion("funcCode <", value, "funccode");
            return (Criteria) this;
        }

        public Criteria andFunccodeLessThanOrEqualTo(String value) {
            addCriterion("funcCode <=", value, "funccode");
            return (Criteria) this;
        }

        public Criteria andFunccodeLike(String value) {
            addCriterion("funcCode like", value, "funccode");
            return (Criteria) this;
        }

        public Criteria andFunccodeNotLike(String value) {
            addCriterion("funcCode not like", value, "funccode");
            return (Criteria) this;
        }

        public Criteria andFunccodeIn(List<String> values) {
            addCriterion("funcCode in", values, "funccode");
            return (Criteria) this;
        }

        public Criteria andFunccodeNotIn(List<String> values) {
            addCriterion("funcCode not in", values, "funccode");
            return (Criteria) this;
        }

        public Criteria andFunccodeBetween(String value1, String value2) {
            addCriterion("funcCode between", value1, value2, "funccode");
            return (Criteria) this;
        }

        public Criteria andFunccodeNotBetween(String value1, String value2) {
            addCriterion("funcCode not between", value1, value2, "funccode");
            return (Criteria) this;
        }

        public Criteria andFuncdescIsNull() {
            addCriterion("funcDesc is null");
            return (Criteria) this;
        }

        public Criteria andFuncdescIsNotNull() {
            addCriterion("funcDesc is not null");
            return (Criteria) this;
        }

        public Criteria andFuncdescEqualTo(String value) {
            addCriterion("funcDesc =", value, "funcdesc");
            return (Criteria) this;
        }

        public Criteria andFuncdescNotEqualTo(String value) {
            addCriterion("funcDesc <>", value, "funcdesc");
            return (Criteria) this;
        }

        public Criteria andFuncdescGreaterThan(String value) {
            addCriterion("funcDesc >", value, "funcdesc");
            return (Criteria) this;
        }

        public Criteria andFuncdescGreaterThanOrEqualTo(String value) {
            addCriterion("funcDesc >=", value, "funcdesc");
            return (Criteria) this;
        }

        public Criteria andFuncdescLessThan(String value) {
            addCriterion("funcDesc <", value, "funcdesc");
            return (Criteria) this;
        }

        public Criteria andFuncdescLessThanOrEqualTo(String value) {
            addCriterion("funcDesc <=", value, "funcdesc");
            return (Criteria) this;
        }

        public Criteria andFuncdescLike(String value) {
            addCriterion("funcDesc like", value, "funcdesc");
            return (Criteria) this;
        }

        public Criteria andFuncdescNotLike(String value) {
            addCriterion("funcDesc not like", value, "funcdesc");
            return (Criteria) this;
        }

        public Criteria andFuncdescIn(List<String> values) {
            addCriterion("funcDesc in", values, "funcdesc");
            return (Criteria) this;
        }

        public Criteria andFuncdescNotIn(List<String> values) {
            addCriterion("funcDesc not in", values, "funcdesc");
            return (Criteria) this;
        }

        public Criteria andFuncdescBetween(String value1, String value2) {
            addCriterion("funcDesc between", value1, value2, "funcdesc");
            return (Criteria) this;
        }

        public Criteria andFuncdescNotBetween(String value1, String value2) {
            addCriterion("funcDesc not between", value1, value2, "funcdesc");
            return (Criteria) this;
        }

        public Criteria andUrlIsNull() {
            addCriterion("url is null");
            return (Criteria) this;
        }

        public Criteria andUrlIsNotNull() {
            addCriterion("url is not null");
            return (Criteria) this;
        }

        public Criteria andUrlEqualTo(String value) {
            addCriterion("url =", value, "url");
            return (Criteria) this;
        }

        public Criteria andUrlNotEqualTo(String value) {
            addCriterion("url <>", value, "url");
            return (Criteria) this;
        }

        public Criteria andUrlGreaterThan(String value) {
            addCriterion("url >", value, "url");
            return (Criteria) this;
        }

        public Criteria andUrlGreaterThanOrEqualTo(String value) {
            addCriterion("url >=", value, "url");
            return (Criteria) this;
        }

        public Criteria andUrlLessThan(String value) {
            addCriterion("url <", value, "url");
            return (Criteria) this;
        }

        public Criteria andUrlLessThanOrEqualTo(String value) {
            addCriterion("url <=", value, "url");
            return (Criteria) this;
        }

        public Criteria andUrlLike(String value) {
            addCriterion("url like", value, "url");
            return (Criteria) this;
        }

        public Criteria andUrlNotLike(String value) {
            addCriterion("url not like", value, "url");
            return (Criteria) this;
        }

        public Criteria andUrlIn(List<String> values) {
            addCriterion("url in", values, "url");
            return (Criteria) this;
        }

        public Criteria andUrlNotIn(List<String> values) {
            addCriterion("url not in", values, "url");
            return (Criteria) this;
        }

        public Criteria andUrlBetween(String value1, String value2) {
            addCriterion("url between", value1, value2, "url");
            return (Criteria) this;
        }

        public Criteria andUrlNotBetween(String value1, String value2) {
            addCriterion("url not between", value1, value2, "url");
            return (Criteria) this;
        }

        public Criteria andIsmenuIsNull() {
            addCriterion("isMenu is null");
            return (Criteria) this;
        }

        public Criteria andIsmenuIsNotNull() {
            addCriterion("isMenu is not null");
            return (Criteria) this;
        }

        public Criteria andIsmenuEqualTo(String value) {
            addCriterion("isMenu =", value, "ismenu");
            return (Criteria) this;
        }

        public Criteria andIsmenuNotEqualTo(String value) {
            addCriterion("isMenu <>", value, "ismenu");
            return (Criteria) this;
        }

        public Criteria andIsmenuGreaterThan(String value) {
            addCriterion("isMenu >", value, "ismenu");
            return (Criteria) this;
        }

        public Criteria andIsmenuGreaterThanOrEqualTo(String value) {
            addCriterion("isMenu >=", value, "ismenu");
            return (Criteria) this;
        }

        public Criteria andIsmenuLessThan(String value) {
            addCriterion("isMenu <", value, "ismenu");
            return (Criteria) this;
        }

        public Criteria andIsmenuLessThanOrEqualTo(String value) {
            addCriterion("isMenu <=", value, "ismenu");
            return (Criteria) this;
        }

        public Criteria andIsmenuLike(String value) {
            addCriterion("isMenu like", value, "ismenu");
            return (Criteria) this;
        }

        public Criteria andIsmenuNotLike(String value) {
            addCriterion("isMenu not like", value, "ismenu");
            return (Criteria) this;
        }

        public Criteria andIsmenuIn(List<String> values) {
            addCriterion("isMenu in", values, "ismenu");
            return (Criteria) this;
        }

        public Criteria andIsmenuNotIn(List<String> values) {
            addCriterion("isMenu not in", values, "ismenu");
            return (Criteria) this;
        }

        public Criteria andIsmenuBetween(String value1, String value2) {
            addCriterion("isMenu between", value1, value2, "ismenu");
            return (Criteria) this;
        }

        public Criteria andIsmenuNotBetween(String value1, String value2) {
            addCriterion("isMenu not between", value1, value2, "ismenu");
            return (Criteria) this;
        }

        public Criteria andZindexIsNull() {
            addCriterion("zindex is null");
            return (Criteria) this;
        }

        public Criteria andZindexIsNotNull() {
            addCriterion("zindex is not null");
            return (Criteria) this;
        }

        public Criteria andZindexEqualTo(Integer value) {
            addCriterion("zindex =", value, "zindex");
            return (Criteria) this;
        }

        public Criteria andZindexNotEqualTo(Integer value) {
            addCriterion("zindex <>", value, "zindex");
            return (Criteria) this;
        }

        public Criteria andZindexGreaterThan(Integer value) {
            addCriterion("zindex >", value, "zindex");
            return (Criteria) this;
        }

        public Criteria andZindexGreaterThanOrEqualTo(Integer value) {
            addCriterion("zindex >=", value, "zindex");
            return (Criteria) this;
        }

        public Criteria andZindexLessThan(Integer value) {
            addCriterion("zindex <", value, "zindex");
            return (Criteria) this;
        }

        public Criteria andZindexLessThanOrEqualTo(Integer value) {
            addCriterion("zindex <=", value, "zindex");
            return (Criteria) this;
        }

        public Criteria andZindexIn(List<Integer> values) {
            addCriterion("zindex in", values, "zindex");
            return (Criteria) this;
        }

        public Criteria andZindexNotIn(List<Integer> values) {
            addCriterion("zindex not in", values, "zindex");
            return (Criteria) this;
        }

        public Criteria andZindexBetween(Integer value1, Integer value2) {
            addCriterion("zindex between", value1, value2, "zindex");
            return (Criteria) this;
        }

        public Criteria andZindexNotBetween(Integer value1, Integer value2) {
            addCriterion("zindex not between", value1, value2, "zindex");
            return (Criteria) this;
        }

        public Criteria andPidIsNull() {
            addCriterion("pId is null");
            return (Criteria) this;
        }

        public Criteria andPidIsNotNull() {
            addCriterion("pId is not null");
            return (Criteria) this;
        }

        public Criteria andPidEqualTo(String value) {
            addCriterion("pId =", value, "pid");
            return (Criteria) this;
        }

        public Criteria andPidNotEqualTo(String value) {
            addCriterion("pId <>", value, "pid");
            return (Criteria) this;
        }

        public Criteria andPidGreaterThan(String value) {
            addCriterion("pId >", value, "pid");
            return (Criteria) this;
        }

        public Criteria andPidGreaterThanOrEqualTo(String value) {
            addCriterion("pId >=", value, "pid");
            return (Criteria) this;
        }

        public Criteria andPidLessThan(String value) {
            addCriterion("pId <", value, "pid");
            return (Criteria) this;
        }

        public Criteria andPidLessThanOrEqualTo(String value) {
            addCriterion("pId <=", value, "pid");
            return (Criteria) this;
        }

        public Criteria andPidLike(String value) {
            addCriterion("pId like", value, "pid");
            return (Criteria) this;
        }

        public Criteria andPidNotLike(String value) {
            addCriterion("pId not like", value, "pid");
            return (Criteria) this;
        }

        public Criteria andPidIn(List<String> values) {
            addCriterion("pId in", values, "pid");
            return (Criteria) this;
        }

        public Criteria andPidNotIn(List<String> values) {
            addCriterion("pId not in", values, "pid");
            return (Criteria) this;
        }

        public Criteria andPidBetween(String value1, String value2) {
            addCriterion("pId between", value1, value2, "pid");
            return (Criteria) this;
        }

        public Criteria andPidNotBetween(String value1, String value2) {
            addCriterion("pId not between", value1, value2, "pid");
            return (Criteria) this;
        }

        public Criteria andCreatedIsNull() {
            addCriterion("created is null");
            return (Criteria) this;
        }

        public Criteria andCreatedIsNotNull() {
            addCriterion("created is not null");
            return (Criteria) this;
        }

        public Criteria andCreatedEqualTo(Date value) {
            addCriterion("created =", value, "created");
            return (Criteria) this;
        }

        public Criteria andCreatedNotEqualTo(Date value) {
            addCriterion("created <>", value, "created");
            return (Criteria) this;
        }

        public Criteria andCreatedGreaterThan(Date value) {
            addCriterion("created >", value, "created");
            return (Criteria) this;
        }

        public Criteria andCreatedGreaterThanOrEqualTo(Date value) {
            addCriterion("created >=", value, "created");
            return (Criteria) this;
        }

        public Criteria andCreatedLessThan(Date value) {
            addCriterion("created <", value, "created");
            return (Criteria) this;
        }

        public Criteria andCreatedLessThanOrEqualTo(Date value) {
            addCriterion("created <=", value, "created");
            return (Criteria) this;
        }

        public Criteria andCreatedIn(List<Date> values) {
            addCriterion("created in", values, "created");
            return (Criteria) this;
        }

        public Criteria andCreatedNotIn(List<Date> values) {
            addCriterion("created not in", values, "created");
            return (Criteria) this;
        }

        public Criteria andCreatedBetween(Date value1, Date value2) {
            addCriterion("created between", value1, value2, "created");
            return (Criteria) this;
        }

        public Criteria andCreatedNotBetween(Date value1, Date value2) {
            addCriterion("created not between", value1, value2, "created");
            return (Criteria) this;
        }

        public Criteria andUpdatedIsNull() {
            addCriterion("updated is null");
            return (Criteria) this;
        }

        public Criteria andUpdatedIsNotNull() {
            addCriterion("updated is not null");
            return (Criteria) this;
        }

        public Criteria andUpdatedEqualTo(Date value) {
            addCriterion("updated =", value, "updated");
            return (Criteria) this;
        }

        public Criteria andUpdatedNotEqualTo(Date value) {
            addCriterion("updated <>", value, "updated");
            return (Criteria) this;
        }

        public Criteria andUpdatedGreaterThan(Date value) {
            addCriterion("updated >", value, "updated");
            return (Criteria) this;
        }

        public Criteria andUpdatedGreaterThanOrEqualTo(Date value) {
            addCriterion("updated >=", value, "updated");
            return (Criteria) this;
        }

        public Criteria andUpdatedLessThan(Date value) {
            addCriterion("updated <", value, "updated");
            return (Criteria) this;
        }

        public Criteria andUpdatedLessThanOrEqualTo(Date value) {
            addCriterion("updated <=", value, "updated");
            return (Criteria) this;
        }

        public Criteria andUpdatedIn(List<Date> values) {
            addCriterion("updated in", values, "updated");
            return (Criteria) this;
        }

        public Criteria andUpdatedNotIn(List<Date> values) {
            addCriterion("updated not in", values, "updated");
            return (Criteria) this;
        }

        public Criteria andUpdatedBetween(Date value1, Date value2) {
            addCriterion("updated between", value1, value2, "updated");
            return (Criteria) this;
        }

        public Criteria andUpdatedNotBetween(Date value1, Date value2) {
            addCriterion("updated not between", value1, value2, "updated");
            return (Criteria) this;
        }

        public Criteria andVersionIsNull() {
            addCriterion("version is null");
            return (Criteria) this;
        }

        public Criteria andVersionIsNotNull() {
            addCriterion("version is not null");
            return (Criteria) this;
        }

        public Criteria andVersionEqualTo(Long value) {
            addCriterion("version =", value, "version");
            return (Criteria) this;
        }

        public Criteria andVersionNotEqualTo(Long value) {
            addCriterion("version <>", value, "version");
            return (Criteria) this;
        }

        public Criteria andVersionGreaterThan(Long value) {
            addCriterion("version >", value, "version");
            return (Criteria) this;
        }

        public Criteria andVersionGreaterThanOrEqualTo(Long value) {
            addCriterion("version >=", value, "version");
            return (Criteria) this;
        }

        public Criteria andVersionLessThan(Long value) {
            addCriterion("version <", value, "version");
            return (Criteria) this;
        }

        public Criteria andVersionLessThanOrEqualTo(Long value) {
            addCriterion("version <=", value, "version");
            return (Criteria) this;
        }

        public Criteria andVersionIn(List<Long> values) {
            addCriterion("version in", values, "version");
            return (Criteria) this;
        }

        public Criteria andVersionNotIn(List<Long> values) {
            addCriterion("version not in", values, "version");
            return (Criteria) this;
        }

        public Criteria andVersionBetween(Long value1, Long value2) {
            addCriterion("version between", value1, value2, "version");
            return (Criteria) this;
        }

        public Criteria andVersionNotBetween(Long value1, Long value2) {
            addCriterion("version not between", value1, value2, "version");
            return (Criteria) this;
        }
    }

    public static class Criteria extends GeneratedCriteria {

        protected Criteria() {
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