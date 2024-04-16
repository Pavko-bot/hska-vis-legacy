package com.hka.iwi.usermanagement;

import jakarta.persistence.*;

// TODO: check whether to comment out something

/**
 * This class contains details about roles.
 */
@Entity
@Table(name = "role")
public class Role implements java.io.Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private int id;

    @Column(name = "type")
    private String type;

    @Column(name = "level1")
    private int level;

    public Role() {
    }

    public Role(String type, int level) {
        this.type = type;
        this.level = level;
    }

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTyp() {
        return this.type;
    }

    public void setTyp(String type) {
        this.type = type;
    }

    public int getLevel() {
        return this.level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

}
