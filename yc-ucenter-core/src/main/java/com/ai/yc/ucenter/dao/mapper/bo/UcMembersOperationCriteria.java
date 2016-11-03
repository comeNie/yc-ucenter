package com.ai.yc.ucenter.dao.mapper.bo;

import java.util.ArrayList;
import java.util.List;

public class UcMembersOperationCriteria {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    protected Integer limitStart;

    protected Integer limitEnd;

    public UcMembersOperationCriteria() {
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

    public void setLimitStart(Integer limitStart) {
        this.limitStart=limitStart;
    }

    public Integer getLimitStart() {
        return limitStart;
    }

    public void setLimitEnd(Integer limitEnd) {
        this.limitEnd=limitEnd;
    }

    public Integer getLimitEnd() {
        return limitEnd;
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

        public Criteria andOidIsNull() {
            addCriterion("oid is null");
            return (Criteria) this;
        }

        public Criteria andOidIsNotNull() {
            addCriterion("oid is not null");
            return (Criteria) this;
        }

        public Criteria andOidEqualTo(Integer value) {
            addCriterion("oid =", value, "oid");
            return (Criteria) this;
        }

        public Criteria andOidNotEqualTo(Integer value) {
            addCriterion("oid <>", value, "oid");
            return (Criteria) this;
        }

        public Criteria andOidGreaterThan(Integer value) {
            addCriterion("oid >", value, "oid");
            return (Criteria) this;
        }

        public Criteria andOidGreaterThanOrEqualTo(Integer value) {
            addCriterion("oid >=", value, "oid");
            return (Criteria) this;
        }

        public Criteria andOidLessThan(Integer value) {
            addCriterion("oid <", value, "oid");
            return (Criteria) this;
        }

        public Criteria andOidLessThanOrEqualTo(Integer value) {
            addCriterion("oid <=", value, "oid");
            return (Criteria) this;
        }

        public Criteria andOidIn(List<Integer> values) {
            addCriterion("oid in", values, "oid");
            return (Criteria) this;
        }

        public Criteria andOidNotIn(List<Integer> values) {
            addCriterion("oid not in", values, "oid");
            return (Criteria) this;
        }

        public Criteria andOidBetween(Integer value1, Integer value2) {
            addCriterion("oid between", value1, value2, "oid");
            return (Criteria) this;
        }

        public Criteria andOidNotBetween(Integer value1, Integer value2) {
            addCriterion("oid not between", value1, value2, "oid");
            return (Criteria) this;
        }

        public Criteria andUidIsNull() {
            addCriterion("uid is null");
            return (Criteria) this;
        }

        public Criteria andUidIsNotNull() {
            addCriterion("uid is not null");
            return (Criteria) this;
        }

        public Criteria andUidEqualTo(Integer value) {
            addCriterion("uid =", value, "uid");
            return (Criteria) this;
        }

        public Criteria andUidNotEqualTo(Integer value) {
            addCriterion("uid <>", value, "uid");
            return (Criteria) this;
        }

        public Criteria andUidGreaterThan(Integer value) {
            addCriterion("uid >", value, "uid");
            return (Criteria) this;
        }

        public Criteria andUidGreaterThanOrEqualTo(Integer value) {
            addCriterion("uid >=", value, "uid");
            return (Criteria) this;
        }

        public Criteria andUidLessThan(Integer value) {
            addCriterion("uid <", value, "uid");
            return (Criteria) this;
        }

        public Criteria andUidLessThanOrEqualTo(Integer value) {
            addCriterion("uid <=", value, "uid");
            return (Criteria) this;
        }

        public Criteria andUidIn(List<Integer> values) {
            addCriterion("uid in", values, "uid");
            return (Criteria) this;
        }

        public Criteria andUidNotIn(List<Integer> values) {
            addCriterion("uid not in", values, "uid");
            return (Criteria) this;
        }

        public Criteria andUidBetween(Integer value1, Integer value2) {
            addCriterion("uid between", value1, value2, "uid");
            return (Criteria) this;
        }

        public Criteria andUidNotBetween(Integer value1, Integer value2) {
            addCriterion("uid not between", value1, value2, "uid");
            return (Criteria) this;
        }

        public Criteria andOperationtypeIsNull() {
            addCriterion("operationtype is null");
            return (Criteria) this;
        }

        public Criteria andOperationtypeIsNotNull() {
            addCriterion("operationtype is not null");
            return (Criteria) this;
        }

        public Criteria andOperationtypeEqualTo(String value) {
            addCriterion("operationtype =", value, "operationtype");
            return (Criteria) this;
        }

        public Criteria andOperationtypeNotEqualTo(String value) {
            addCriterion("operationtype <>", value, "operationtype");
            return (Criteria) this;
        }

        public Criteria andOperationtypeGreaterThan(String value) {
            addCriterion("operationtype >", value, "operationtype");
            return (Criteria) this;
        }

        public Criteria andOperationtypeGreaterThanOrEqualTo(String value) {
            addCriterion("operationtype >=", value, "operationtype");
            return (Criteria) this;
        }

        public Criteria andOperationtypeLessThan(String value) {
            addCriterion("operationtype <", value, "operationtype");
            return (Criteria) this;
        }

        public Criteria andOperationtypeLessThanOrEqualTo(String value) {
            addCriterion("operationtype <=", value, "operationtype");
            return (Criteria) this;
        }

        public Criteria andOperationtypeLike(String value) {
            addCriterion("operationtype like", value, "operationtype");
            return (Criteria) this;
        }

        public Criteria andOperationtypeNotLike(String value) {
            addCriterion("operationtype not like", value, "operationtype");
            return (Criteria) this;
        }

        public Criteria andOperationtypeIn(List<String> values) {
            addCriterion("operationtype in", values, "operationtype");
            return (Criteria) this;
        }

        public Criteria andOperationtypeNotIn(List<String> values) {
            addCriterion("operationtype not in", values, "operationtype");
            return (Criteria) this;
        }

        public Criteria andOperationtypeBetween(String value1, String value2) {
            addCriterion("operationtype between", value1, value2, "operationtype");
            return (Criteria) this;
        }

        public Criteria andOperationtypeNotBetween(String value1, String value2) {
            addCriterion("operationtype not between", value1, value2, "operationtype");
            return (Criteria) this;
        }

        public Criteria andOperationcodeIsNull() {
            addCriterion("operationcode is null");
            return (Criteria) this;
        }

        public Criteria andOperationcodeIsNotNull() {
            addCriterion("operationcode is not null");
            return (Criteria) this;
        }

        public Criteria andOperationcodeEqualTo(String value) {
            addCriterion("operationcode =", value, "operationcode");
            return (Criteria) this;
        }

        public Criteria andOperationcodeNotEqualTo(String value) {
            addCriterion("operationcode <>", value, "operationcode");
            return (Criteria) this;
        }

        public Criteria andOperationcodeGreaterThan(String value) {
            addCriterion("operationcode >", value, "operationcode");
            return (Criteria) this;
        }

        public Criteria andOperationcodeGreaterThanOrEqualTo(String value) {
            addCriterion("operationcode >=", value, "operationcode");
            return (Criteria) this;
        }

        public Criteria andOperationcodeLessThan(String value) {
            addCriterion("operationcode <", value, "operationcode");
            return (Criteria) this;
        }

        public Criteria andOperationcodeLessThanOrEqualTo(String value) {
            addCriterion("operationcode <=", value, "operationcode");
            return (Criteria) this;
        }

        public Criteria andOperationcodeLike(String value) {
            addCriterion("operationcode like", value, "operationcode");
            return (Criteria) this;
        }

        public Criteria andOperationcodeNotLike(String value) {
            addCriterion("operationcode not like", value, "operationcode");
            return (Criteria) this;
        }

        public Criteria andOperationcodeIn(List<String> values) {
            addCriterion("operationcode in", values, "operationcode");
            return (Criteria) this;
        }

        public Criteria andOperationcodeNotIn(List<String> values) {
            addCriterion("operationcode not in", values, "operationcode");
            return (Criteria) this;
        }

        public Criteria andOperationcodeBetween(String value1, String value2) {
            addCriterion("operationcode between", value1, value2, "operationcode");
            return (Criteria) this;
        }

        public Criteria andOperationcodeNotBetween(String value1, String value2) {
            addCriterion("operationcode not between", value1, value2, "operationcode");
            return (Criteria) this;
        }

        public Criteria andOperationtimeIsNull() {
            addCriterion("operationtime is null");
            return (Criteria) this;
        }

        public Criteria andOperationtimeIsNotNull() {
            addCriterion("operationtime is not null");
            return (Criteria) this;
        }

        public Criteria andOperationtimeEqualTo(String value) {
            addCriterion("operationtime =", value, "operationtime");
            return (Criteria) this;
        }

        public Criteria andOperationtimeNotEqualTo(String value) {
            addCriterion("operationtime <>", value, "operationtime");
            return (Criteria) this;
        }

        public Criteria andOperationtimeGreaterThan(String value) {
            addCriterion("operationtime >", value, "operationtime");
            return (Criteria) this;
        }

        public Criteria andOperationtimeGreaterThanOrEqualTo(String value) {
            addCriterion("operationtime >=", value, "operationtime");
            return (Criteria) this;
        }

        public Criteria andOperationtimeLessThan(String value) {
            addCriterion("operationtime <", value, "operationtime");
            return (Criteria) this;
        }

        public Criteria andOperationtimeLessThanOrEqualTo(String value) {
            addCriterion("operationtime <=", value, "operationtime");
            return (Criteria) this;
        }

        public Criteria andOperationtimeLike(String value) {
            addCriterion("operationtime like", value, "operationtime");
            return (Criteria) this;
        }

        public Criteria andOperationtimeNotLike(String value) {
            addCriterion("operationtime not like", value, "operationtime");
            return (Criteria) this;
        }

        public Criteria andOperationtimeIn(List<String> values) {
            addCriterion("operationtime in", values, "operationtime");
            return (Criteria) this;
        }

        public Criteria andOperationtimeNotIn(List<String> values) {
            addCriterion("operationtime not in", values, "operationtime");
            return (Criteria) this;
        }

        public Criteria andOperationtimeBetween(String value1, String value2) {
            addCriterion("operationtime between", value1, value2, "operationtime");
            return (Criteria) this;
        }

        public Criteria andOperationtimeNotBetween(String value1, String value2) {
            addCriterion("operationtime not between", value1, value2, "operationtime");
            return (Criteria) this;
        }

        public Criteria andUserinfoIsNull() {
            addCriterion("userinfo is null");
            return (Criteria) this;
        }

        public Criteria andUserinfoIsNotNull() {
            addCriterion("userinfo is not null");
            return (Criteria) this;
        }

        public Criteria andUserinfoEqualTo(String value) {
            addCriterion("userinfo =", value, "userinfo");
            return (Criteria) this;
        }

        public Criteria andUserinfoNotEqualTo(String value) {
            addCriterion("userinfo <>", value, "userinfo");
            return (Criteria) this;
        }

        public Criteria andUserinfoGreaterThan(String value) {
            addCriterion("userinfo >", value, "userinfo");
            return (Criteria) this;
        }

        public Criteria andUserinfoGreaterThanOrEqualTo(String value) {
            addCriterion("userinfo >=", value, "userinfo");
            return (Criteria) this;
        }

        public Criteria andUserinfoLessThan(String value) {
            addCriterion("userinfo <", value, "userinfo");
            return (Criteria) this;
        }

        public Criteria andUserinfoLessThanOrEqualTo(String value) {
            addCriterion("userinfo <=", value, "userinfo");
            return (Criteria) this;
        }

        public Criteria andUserinfoLike(String value) {
            addCriterion("userinfo like", value, "userinfo");
            return (Criteria) this;
        }

        public Criteria andUserinfoNotLike(String value) {
            addCriterion("userinfo not like", value, "userinfo");
            return (Criteria) this;
        }

        public Criteria andUserinfoIn(List<String> values) {
            addCriterion("userinfo in", values, "userinfo");
            return (Criteria) this;
        }

        public Criteria andUserinfoNotIn(List<String> values) {
            addCriterion("userinfo not in", values, "userinfo");
            return (Criteria) this;
        }

        public Criteria andUserinfoBetween(String value1, String value2) {
            addCriterion("userinfo between", value1, value2, "userinfo");
            return (Criteria) this;
        }

        public Criteria andUserinfoNotBetween(String value1, String value2) {
            addCriterion("userinfo not between", value1, value2, "userinfo");
            return (Criteria) this;
        }

        public Criteria andCountryCodeIsNull() {
            addCriterion("country_code is null");
            return (Criteria) this;
        }

        public Criteria andCountryCodeIsNotNull() {
            addCriterion("country_code is not null");
            return (Criteria) this;
        }

        public Criteria andCountryCodeEqualTo(String value) {
            addCriterion("country_code =", value, "countryCode");
            return (Criteria) this;
        }

        public Criteria andCountryCodeNotEqualTo(String value) {
            addCriterion("country_code <>", value, "countryCode");
            return (Criteria) this;
        }

        public Criteria andCountryCodeGreaterThan(String value) {
            addCriterion("country_code >", value, "countryCode");
            return (Criteria) this;
        }

        public Criteria andCountryCodeGreaterThanOrEqualTo(String value) {
            addCriterion("country_code >=", value, "countryCode");
            return (Criteria) this;
        }

        public Criteria andCountryCodeLessThan(String value) {
            addCriterion("country_code <", value, "countryCode");
            return (Criteria) this;
        }

        public Criteria andCountryCodeLessThanOrEqualTo(String value) {
            addCriterion("country_code <=", value, "countryCode");
            return (Criteria) this;
        }

        public Criteria andCountryCodeLike(String value) {
            addCriterion("country_code like", value, "countryCode");
            return (Criteria) this;
        }

        public Criteria andCountryCodeNotLike(String value) {
            addCriterion("country_code not like", value, "countryCode");
            return (Criteria) this;
        }

        public Criteria andCountryCodeIn(List<String> values) {
            addCriterion("country_code in", values, "countryCode");
            return (Criteria) this;
        }

        public Criteria andCountryCodeNotIn(List<String> values) {
            addCriterion("country_code not in", values, "countryCode");
            return (Criteria) this;
        }

        public Criteria andCountryCodeBetween(String value1, String value2) {
            addCriterion("country_code between", value1, value2, "countryCode");
            return (Criteria) this;
        }

        public Criteria andCountryCodeNotBetween(String value1, String value2) {
            addCriterion("country_code not between", value1, value2, "countryCode");
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