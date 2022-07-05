package com.example.mindaid.Model;

import javax.persistence.*;

@Entity
@Table(name="concern")
public class Concern {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "iconcernid")
    public int concernId;
    @Column(name = "concernname")
    public String concernName;

    public int getConcernId() {
        return concernId;
    }

    public void setConcernId(int concernId) {
        this.concernId = concernId;
    }

    public String getConcernName() {
        return concernName;
    }

    public void setConcernName(String concernName) {
        this.concernName = concernName;
    }
}
