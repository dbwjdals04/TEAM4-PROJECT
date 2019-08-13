package com.bit.academy.model;


import lombok.Data;

import java.util.Date;

@Data
public class BoardVO {

    private int qna_no;
    private int qna_originno;
    private int m_no;
    private String qna_title;
    private String qna_context;
    private String qna_passwd;
    private Date qna_regdate;
    private  int qna_groupOrd;
    private int privateck;

    private String m_id;
    private String m_name;
}
