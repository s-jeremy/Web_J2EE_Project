package com.example.web_j2ee_project.hibernate.entites;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.Arrays;

@Table(name = "facture")
@Entity
public class Facture
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_facture", nullable = false)
    private Integer id;

    @Column(name = "id_user", nullable = false, columnDefinition = "int default 0")
    private Integer idUser;

    @Column(name = "date", nullable = false)
    private LocalDate date;

    @Column(name = "pdf_name", nullable = false)
    private String pdfName;

    @Column(name = "pdf_file", nullable = false)
    private byte[] pdfFile;

    public byte[] getPdfFile()
    {
        return pdfFile;
    }

    public void setPdfFile(byte[] pdfFile)
    {
        this.pdfFile = pdfFile;
    }

    public LocalDate getDate()
    {
        return date;
    }

    public void setDate(LocalDate date)
    {
        this.date = date;
    }

    public Integer getIdUser()
    {
        return idUser;
    }

    public void setIdUser(Integer idUser)
    {
        this.idUser = idUser;
    }

    public Integer getId()
    {
        return id;
    }

    public void setId(Integer id)
    {
        this.id = id;
    }

    public String getPdfName()
    {
        return pdfName;
    }

    public void setPdfName(String pdfName)
    {
        this.pdfName = pdfName;
    }

    @Override
    public String toString()
    {
        return "Facture{" +
                "id=" + id +
                ", idUser=" + idUser +
                ", date=" + date +
                ", pdfName=" + pdfName;
    }
}