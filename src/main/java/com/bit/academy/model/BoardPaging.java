package com.bit.academy.model;

import java.util.ArrayList;
import java.util.List;

public class BoardPaging {

    private int totalCount; // 전체 게시물 갯수

    private int currentPage; // 현재 페이지 수

    private int articleCount = 10; // 한 페이지당 게시물 갯수 - 20

    private int pageCount = 10; // 1 2 3 4 ~ 10 // 페이지 갯수

    private int prevPage; // 이전 페이지

    private int nextPage; // 다음 페이지

    private int beginPage; // 첫 페이지

    private int lastPage; // 마지막 페이지

    private String searchKeyword; // 검색어

    private int limitBegin;


    public static void main(String[] args){

        BoardPaging p = new BoardPaging();

        p.setTotalCount(2500);
        p.setCurrentPage(122);

        System.out.print(p.getPrevPage() + " <    ");

        List<Integer> pageRange = p.getPageRange();

        for(int page:pageRange){
            if(p.getCurrentPage()==page) System.out.print("[");

            System.out.print(page);

            if(p.getCurrentPage()==page) System.out.print("]");

            System.out.print(" ");

        }

        System.out.print("    > "+p.getNextPage());

    }

    private List<Integer> pageRange;

    public List<Integer> getPageRange(){
        pageRange = new ArrayList<>();

        int begin = currentPage / pageCount * pageCount + 1;

        if(currentPage%pageCount==0){
            begin-=pageCount;
        }

        int end = begin + pageCount -1 ;

        if(end>getLastPage()){
            end = getLastPage();
        }

        for( int i=begin ; i <= end ; i++){
            pageRange.add(i);
        }

        return pageRange;
    }

    public int getPrevPage(){
        this.prevPage = this.getPageRange().get(0);

        // 현재페이지가 pageCount 보다 작거나 같은 경우 0. 이전 첫 페이지 없음
        if(this.currentPage <= pageCount){
            this.prevPage = 0;
        }
        else{
            this.prevPage-=1;
        }
        return this.prevPage;
    }

    public int getNextPage(){

        this.nextPage = this.currentPage;

        if(this.nextPage%pageCount>0){
            this.nextPage+=pageCount;
        }

        this.nextPage = this.nextPage / pageCount * pageCount +1;

        if(this.nextPage>this.getLastPage()){
            this.nextPage = 0;
        }

        return this.nextPage;
    }

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

    public void setPrevPage(int prevPage) {
        this.prevPage = prevPage;
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
        lastPage = this.totalCount / this.articleCount;

        if(this.totalCount % this.articleCount>0){
            lastPage++;
        }

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
