package com.lnlr.common.jpa.query;

import com.lnlr.common.jpa.enums.Operator;
import com.lnlr.common.jpa.model.MatchModel;
import com.lnlr.common.jpa.model.NgPager;
import com.lnlr.common.jpa.model.SearchFilter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.util.CollectionUtils;

import javax.validation.constraints.NotNull;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @author:leihfei
 * @description 设置分页工具类
 * @date:Create in 12:48 2018/9/5
 * @email:leihfein@gmail.com
 */
@Slf4j
public class PageUtils {
    /**
     * 降序配置
     */
    public static final String DESC = "desc";

    /**
     * 升序排序
     */
    public static final String ASC = "asc";

    /**
     * @param pager 分页对象
     * @return java.util.Collection<com.lnlr.authority.common.jpa.model.SearchFilter>
     * @author: leihfei
     * @description 得到过滤set集合
     * @date: 13:04 2018/9/5
     * @email: leihfein@gmail.com
     */
    public static Collection<SearchFilter> buildSearchFilter(NgPager pager) {
        Set<SearchFilter> searchFilters = new HashSet<>();
        if (pager == null || CollectionUtils.isEmpty(pager.getFilters())) {
            return searchFilters;
        }
        pager.getFilters().forEach((k, v) -> searchFilters.add(new SearchFilter(k, parse(v.getMatchMode()), v.getValue())));
        return searchFilters;
    }

    /**
     * @param pager 分页对象
     * @return org.springframework.data.domain.Sort
     * @author: leihfei
     * @description 通过分页对象构建一个sort排序对象，将所有的排序规则和字段都封装进去
     * @date: 13:05 2018/9/5
     * @email: leihfein@gmail.com
     */
    public static Sort buildSort(NgPager pager) {
        if (null != pager.getMultiSortMeta() && pager.getMultiSortMeta().length > 0) {
            List<Sort.Order> orders = java.util.Arrays.stream(pager.getMultiSortMeta())
                    .map(k -> new Sort.Order(Sort.Direction.fromString(k.getOrder() == -1 ? DESC : ASC), k.getField()))
                    .collect(Collectors.toList());
            return Sort.by(orders);
        }
        return new Sort(Sort.Direction.ASC, "id");
    }

    /**
     * @param pager 分页对象
     * @return org.springframework.data.domain.PageRequest
     * @author: leihfei
     * @description 通过分页对象转化为jpa分页排序对象，是对整个数据排序
     * @date: 13:02 2018/9/5
     * @email: leihfein@gmail.com
     */
    public static PageRequest buildPageRequest(NgPager pager) {
        if (null != pager.getMultiSortMeta() && pager.getMultiSortMeta().length > 0) {
            List<Sort.Order> orders = java.util.Arrays.stream(pager.getMultiSortMeta())
                    .map(k -> new Sort.Order(Sort.Direction.fromString(k.getOrder() == -1 ? DESC : ASC), k.getField()))
                    .collect(Collectors.toList());
            return buildPageRequest(pager.getFirst() / pager.getRows(), pager.getRows(), Sort.by(orders));
        }
        if (pager.getSortField() != null) {
            Sort sort = new Sort(Sort.Direction.fromString(pager.getSortOrder() == -1 ? DESC : ASC), pager.getSortField());
            return buildPageRequest(pager.getFirst() / pager.getRows(), pager.getRows(), sort);
        }
        return PageRequest.of(pager.getFirst() / pager.getRows(), pager.getRows());
    }

    /**
     * @param pager 分页对象
     * @param sort  排序对象
     * @return org.springframework.data.domain.PageRequest
     * @author: leihfei
     * @description 通过分页对象转化为jpa分页对象
     * @date: 13:02 2018/9/5
     * @email: leihfein@gmail.com
     */
    public static PageRequest buildPageRequest(NgPager pager, Sort sort) {
        if (null != pager.getMultiSortMeta() && pager.getMultiSortMeta().length > 0) {
            List<Sort.Order> orders = java.util.Arrays.stream(pager.getMultiSortMeta())
                    .map(k -> new Sort.Order(Sort.Direction.fromString(k.getOrder() == -1 ? DESC : ASC), k.getField()))
                    .collect(Collectors.toList());
            return buildPageRequest(pager.getFirst() / pager.getRows(), pager.getRows(), Sort.by(orders));
        }
        return PageRequest.of(pager.getFirst() / pager.getRows(), pager.getRows(), sort);
    }

    /**
     * @param startRow 开始行
     * @param rows     行数
     * @param sort     排序
     * @return org.springframework.data.domain.PageRequest
     * @author: leihfei
     * @description 设置分页排序对象
     * @date: 13:02 2018/9/5
     * @email: leihfein@gmail.com
     */
    public static PageRequest buildPageRequest(@NotNull Integer startRow, @NotNull Integer rows, Sort sort) {
        return PageRequest.of(startRow, rows, sort);
    }

    /**
     * @param matchMode 过滤匹配模式
     * @return com.lnlr.authority.common.jpa.enums.Operator
     * @author: leihfei
     * @description 匹配模式
     * @date: 13:01 2018/9/5
     * @email: leihfein@gmail.com
     */
    private static Operator parse(String matchMode) {
        if (matchMode == null) {
            return Operator.EQ;
        }
        Operator op;
        switch (matchMode) {
            case MatchModel.CONTAINS:
                op = Operator.LIKE;
                break;
            case MatchModel.EQUALS:
                op = Operator.EQ;
                break;
            case MatchModel.IN:
                op = Operator.IN;
                break;
            case MatchModel.STARTS_WITH:
                op = Operator.RLIKE;
                break;
            case MatchModel.ENDS_WITH:
                op = Operator.LLIKE;
                break;
            case MatchModel.ISNULL:
                op = Operator.ISNULL;
                break;
            case MatchModel.LT:
                op = Operator.LT;
                break;
            case MatchModel.GT:
                op = Operator.GT;
                break;
            case MatchModel.GTE:
                op = Operator.GTE;
                break;
            case MatchModel.LTE:
                op = Operator.LTE;
                break;
            case MatchModel.BETWEEN:
                op = Operator.BETWEEN;
                break;
            case MatchModel.NOTEQ:
                op = Operator.NOTEQ;
                break;
            default:
                op = Operator.EQ;
        }
        return op;
    }
}
