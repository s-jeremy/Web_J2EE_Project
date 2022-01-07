package com.example.web_j2ee_project.dao;

import com.example.web_j2ee_project.hibernate.entites.Categorie;
import com.example.web_j2ee_project.hibernate.entites.Client;
import com.example.web_j2ee_project.hibernate.entites.Facture;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

import java.util.List;

public class FactureDao
{

    private SessionFactory factory;

    public FactureDao(SessionFactory factory){
        this.factory = factory;
    }

    public List<Facture> getFactures(int id_utilisateur){
        List<Facture> factureList = null;
        try{

            String query = "from Facture where idUser =: id_utilisateur";

            Session session = this.factory.openSession();
            Query q = session.createQuery(query);
            q.setParameter("id_utilisateur", id_utilisateur);

            factureList =q.getResultList();


            session.close();


        } catch (Exception e){
            e.printStackTrace();
        }
        return factureList;
    }

    public Facture getFacture(int file_id){
        Facture facture = null;
        try{

            String query = "from Facture where id =: file_id";

            Session session = this.factory.openSession();
            Query q = session.createQuery(query);
            q.setParameter("file_id", file_id);

            facture = (Facture)q.uniqueResult();

            session.close();

        } catch (Exception e){
            e.printStackTrace();
        }
        return facture;
    }

}
