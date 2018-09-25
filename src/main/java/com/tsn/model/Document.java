package com.tsn.model;

import javax.persistence.*;


@Entity
@Table(name = "DOCUMENTS")
public class Document {

    @Id
    @Column(name = "id", nullable = false, unique = true)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Column(name = "name", nullable = false, unique = true, length = 200)
    private String name;

    @Lob
    private byte[] document;

    @Column(name = "creationDate", nullable = false, length = 50)
    private String creationDate;

    public Document() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public byte[] getDocument() {
        return document;
    }

    public void setDocument(byte[] document) {
        this.document = document;
    }

    public String getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(String creationDate) {
        this.creationDate = creationDate;
    }

    @Override
    public String toString() {
        return "Document{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", document length =" + document.length +
                ", creationDate='" + creationDate + '\'' +
                '}';
    }
}
