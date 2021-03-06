package cn.szuer.publicboard.model;

import java.util.ArrayList;
import java.util.List;

public class NewsTypeExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public NewsTypeExample() {
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

        public Criteria andNewstypeidIsNull() {
            addCriterion("newsTypeID is null");
            return (Criteria) this;
        }

        public Criteria andNewstypeidIsNotNull() {
            addCriterion("newsTypeID is not null");
            return (Criteria) this;
        }

        public Criteria andNewstypeidEqualTo(Integer value) {
            addCriterion("newsTypeID =", value, "newstypeid");
            return (Criteria) this;
        }

        public Criteria andNewstypeidNotEqualTo(Integer value) {
            addCriterion("newsTypeID <>", value, "newstypeid");
            return (Criteria) this;
        }

        public Criteria andNewstypeidGreaterThan(Integer value) {
            addCriterion("newsTypeID >", value, "newstypeid");
            return (Criteria) this;
        }

        public Criteria andNewstypeidGreaterThanOrEqualTo(Integer value) {
            addCriterion("newsTypeID >=", value, "newstypeid");
            return (Criteria) this;
        }

        public Criteria andNewstypeidLessThan(Integer value) {
            addCriterion("newsTypeID <", value, "newstypeid");
            return (Criteria) this;
        }

        public Criteria andNewstypeidLessThanOrEqualTo(Integer value) {
            addCriterion("newsTypeID <=", value, "newstypeid");
            return (Criteria) this;
        }

        public Criteria andNewstypeidIn(List<Integer> values) {
            addCriterion("newsTypeID in", values, "newstypeid");
            return (Criteria) this;
        }

        public Criteria andNewstypeidNotIn(List<Integer> values) {
            addCriterion("newsTypeID not in", values, "newstypeid");
            return (Criteria) this;
        }

        public Criteria andNewstypeidBetween(Integer value1, Integer value2) {
            addCriterion("newsTypeID between", value1, value2, "newstypeid");
            return (Criteria) this;
        }

        public Criteria andNewstypeidNotBetween(Integer value1, Integer value2) {
            addCriterion("newsTypeID not between", value1, value2, "newstypeid");
            return (Criteria) this;
        }

        public Criteria andTypenameIsNull() {
            addCriterion("typeName is null");
            return (Criteria) this;
        }

        public Criteria andTypenameIsNotNull() {
            addCriterion("typeName is not null");
            return (Criteria) this;
        }

        public Criteria andTypenameEqualTo(String value) {
            addCriterion("typeName =", value, "typename");
            return (Criteria) this;
        }

        public Criteria andTypenameNotEqualTo(String value) {
            addCriterion("typeName <>", value, "typename");
            return (Criteria) this;
        }

        public Criteria andTypenameGreaterThan(String value) {
            addCriterion("typeName >", value, "typename");
            return (Criteria) this;
        }

        public Criteria andTypenameGreaterThanOrEqualTo(String value) {
            addCriterion("typeName >=", value, "typename");
            return (Criteria) this;
        }

        public Criteria andTypenameLessThan(String value) {
            addCriterion("typeName <", value, "typename");
            return (Criteria) this;
        }

        public Criteria andTypenameLessThanOrEqualTo(String value) {
            addCriterion("typeName <=", value, "typename");
            return (Criteria) this;
        }

        public Criteria andTypenameLike(String value) {
            addCriterion("typeName like", value, "typename");
            return (Criteria) this;
        }

        public Criteria andTypenameNotLike(String value) {
            addCriterion("typeName not like", value, "typename");
            return (Criteria) this;
        }

        public Criteria andTypenameIn(List<String> values) {
            addCriterion("typeName in", values, "typename");
            return (Criteria) this;
        }

        public Criteria andTypenameNotIn(List<String> values) {
            addCriterion("typeName not in", values, "typename");
            return (Criteria) this;
        }

        public Criteria andTypenameBetween(String value1, String value2) {
            addCriterion("typeName between", value1, value2, "typename");
            return (Criteria) this;
        }

        public Criteria andTypenameNotBetween(String value1, String value2) {
            addCriterion("typeName not between", value1, value2, "typename");
            return (Criteria) this;
        }

        public Criteria andTypestateIsNull() {
            addCriterion("typeState is null");
            return (Criteria) this;
        }

        public Criteria andTypestateIsNotNull() {
            addCriterion("typeState is not null");
            return (Criteria) this;
        }

        public Criteria andTypestateEqualTo(Boolean value) {
            addCriterion("typeState =", value, "typestate");
            return (Criteria) this;
        }

        public Criteria andTypestateNotEqualTo(Boolean value) {
            addCriterion("typeState <>", value, "typestate");
            return (Criteria) this;
        }

        public Criteria andTypestateGreaterThan(Boolean value) {
            addCriterion("typeState >", value, "typestate");
            return (Criteria) this;
        }

        public Criteria andTypestateGreaterThanOrEqualTo(Boolean value) {
            addCriterion("typeState >=", value, "typestate");
            return (Criteria) this;
        }

        public Criteria andTypestateLessThan(Boolean value) {
            addCriterion("typeState <", value, "typestate");
            return (Criteria) this;
        }

        public Criteria andTypestateLessThanOrEqualTo(Boolean value) {
            addCriterion("typeState <=", value, "typestate");
            return (Criteria) this;
        }

        public Criteria andTypestateIn(List<Boolean> values) {
            addCriterion("typeState in", values, "typestate");
            return (Criteria) this;
        }

        public Criteria andTypestateNotIn(List<Boolean> values) {
            addCriterion("typeState not in", values, "typestate");
            return (Criteria) this;
        }

        public Criteria andTypestateBetween(Boolean value1, Boolean value2) {
            addCriterion("typeState between", value1, value2, "typestate");
            return (Criteria) this;
        }

        public Criteria andTypestateNotBetween(Boolean value1, Boolean value2) {
            addCriterion("typeState not between", value1, value2, "typestate");
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