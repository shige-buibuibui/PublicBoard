package cn.szuer.publicboard.utils;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.github.pagehelper.PageInfo;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Page<T> extends PageInfo<T> {

    /**
     * 当前页
     */
    private int pageNum;
    /**
     * 每页数量
     */
    private int pageSize;

    /**
     * 当前页的数量
     */
    private int size;

    /**
     * 总记录数
     */
    private  long  total;
    /**
     * 结果集
     */
    private List<T> list;

    // 由于startRow和endRow不常用，这里说个具体的用法
    // 可以在页面中"显示startRow到endRow 共size条数据"
    /**
     * 当前页面第一个元素在数据库中的行号
     */
    @JsonIgnore
    private long startRow;
    /**
     * 当前页面最后一个元素在数据库中的行号
     */
    @JsonIgnore
    private long endRow;
    /**
     * 总页数
     */
    private int pages;

    /**
     * 前一页
     */
    private int prePage;
    /**
     * 下一页
     */
    private int nextPage;

    /**
     * 是否为第一页
     */
    private boolean isFirstPage = false;
    /**
     * 是否为最后一页
     */
    private boolean isLastPage = false;
    /**
     * 是否有前一页
     */
    private boolean hasPreviousPage = false;
    /**
     * 是否有下一页
     */
    private boolean hasNextPage = false;

    /**
     * 导航页码数
     */
    private int navigatePages;
    /**
     * 所有导航页号
     */
    @JsonIgnore
    private int[] navigatepageNums;
    /**
     *  导航条上的第一页
     */
    private int navigateFirstPage;

    /**
     * 导航条上的最后一页
     */
    private int navigateLastPage;

    
}
