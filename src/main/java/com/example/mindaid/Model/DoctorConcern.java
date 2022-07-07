package com.example.mindaid.Model;

import net.bytebuddy.dynamic.loading.InjectionClassLoader;

import javax.persistence.*;

@Entity
@Table(name = "doctorconcern")
public class DoctorConcern {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="docconcern_id")
    public int docconcern_id;
    @Column(name="concern_id")
    public int concern_id;
    @Column(name = "doc_id")
    public int doc_id;

    public int getDocconcern_id() {
        return docconcern_id;
    }

    public void setDocconcern_id(int docconcern_id) {
        this.docconcern_id = docconcern_id;
    }

    public int getConcern_id() {
        return concern_id;
    }

    public void setConcern_id(int concern_id) {
        this.concern_id = concern_id;
    }

    public int getDoc_id() {
        return doc_id;
    }

    public void setDoc_id(int doc_id) {
        this.doc_id = doc_id;
    }
}
