package com.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Famliy {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String famliyName;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFamliyName() {
        return famliyName;
    }

    public void setFamliyName(String famliyName) {
        this.famliyName = famliyName;
    }

    @Override
    public String toString() {
        return "Famliy [id=" + id + ", famliyName=" + famliyName + "]";
    }

}
