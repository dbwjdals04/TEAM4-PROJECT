package com.bit.academy.model;


import java.util.Date;

public class BoardVO {

    private int qna_no;
    private int m_no;
    private String qna_context;
    private String qna_passwd;
    private Date qna_regdate;

    private String m_id;
    private String m_name;

    @Override
    public String toString() {
        return "BoardVO{" +
                "qna_no=" + qna_no +
                ", m_no=" + m_no +
                ", qna_context='" + qna_context + '\'' +
                ", qna_passwd='" + qna_passwd + '\'' +
                ", qna_regdate=" + qna_regdate +
                ", m_id='" + m_id + '\'' +
                ", m_name='" + m_name + '\'' +
                '}';
    }

    public int getQna_no() {
        return qna_no;
    }

    public void setQna_no(int qna_no) {
        this.qna_no = qna_no;
    }

    public int getM_no() {
        return m_no;
    }

    public void setM_no(int m_no) {
        this.m_no = m_no;
    }

    public String getQna_context() {
        return qna_context;
    }

    public void setQna_context(String qna_context) {
        this.qna_context = qna_context;
    }

    public String getQna_passwd() {
        return qna_passwd;
    }

    public void setQna_passwd(String qna_passwd) {
        this.qna_passwd = qna_passwd;
    }

    public Date getQna_regdate() {
        return qna_regdate;
    }

    public void setQna_regdate(Date qna_regdate) {
        this.qna_regdate = qna_regdate;
    }


    public String getM_id() {
        return m_id;
    }

    public void setM_id(String m_id) {
        this.m_id = m_id;
    }

    public String getM_name() {
        return m_name;
    }

    public void setM_name(String m_name) {
        this.m_name = m_name;
    }
}
