package com.bit.academy.model;

public class BoardPaging {

    private int totalCount; // 전체 게시물 갯수

    private int currentPage; // 현재 페이지 수

    private int articleCount; // 한 페이지당 게시물 갯수 - 20

    private int pageCount; // 1 2 3 4 ~ 10 // 페이지 갯수

    private int prevPage; // 이전 페이지

    private int nextPage; // 다음 페이지

    private int beginPage; // 첫 페이지

    private int lastPage; // 마지막 페이지

    private String searchKeyword; // 검색어


    private int limitBegin;

    /**
     * MyBatis LIMIT 시작
     */
    public int getLimitBegin() {
        return (currentPage - 1) *  articleCount;
    }


    public int getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(int totalCount) {
        this.totalCount = totalCount;
    }

    public int getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
    }

    public int getArticleCount() {
        return articleCount;
    }

    public void setArticleCount(int articleCount) {
        this.articleCount = articleCount;
    }

    public int getPageCount() {
        return pageCount;
    }

    public void setPageCount(int pageCount) {
        this.pageCount = pageCount;
    }

    public int getPrevPage() {
        return prevPage;
    }

    public void setPrevPage(int prevPage) {
        this.prevPage = prevPage;
    }

    public int getNextPage() {
        return nextPage;
    }

    public void setNextPage(int nextPage) {
        this.nextPage = nextPage;
    }

    public int getBeginPage() {
        beginPage = beginPage==0?1:beginPage;
        return beginPage;
    }

    public void setBeginPage(int beginPage) {
        this.beginPage = beginPage;
    }

    public int getLastPage() {

        lastPage = this.totalCount / this.articleCount + 1;

        return lastPage;
    }

    public void setLastPage(int lastPage) {
        this.lastPage = lastPage;
    }

    public String getSearchKeyword() {
        searchKeyword = searchKeyword==null? new String() : searchKeyword;
        return searchKeyword;
    }

    public void setSearchKeyword(String searchKeyword) {
        this.searchKeyword = searchKeyword;
    }

    public void setLimitBegin(int limitBegin) {
        this.limitBegin = limitBegin;
    }
}
