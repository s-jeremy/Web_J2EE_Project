package com.example.web_j2ee_project.hibernate.entites;

import jakarta.persistence.*;

import java.time.LocalDate;

@Table(name = "facture", indexes = {
        @Index(name = "id_user_idx", columnList = "id_user")
})
@Entity
public class Facture
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_facture", nullable = false)
    private Integer id;

    @JoinColumn(name = "id_user", nullable = false)
    private Integer idUser;

    @Column(name = "date", nullable = false)
    private LocalDate date;

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
}