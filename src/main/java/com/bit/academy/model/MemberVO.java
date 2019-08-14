package com.bit.academy.model;

import lombok.Data;

@Data
public class MemberVO {
    private int m_no;
    private String m_name;
    private String m_id;
    private String m_password;
    private String m_phone;
    private String m_regdate;
    private String m_birth;
}
