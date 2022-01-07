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

    /**
     * Retourne toutes les factures associé à l'id de l'utilisateur fournit
     *
     * @param id_utilisateur id de l'utilisateur dont on souhaite récupéré l'id
     * @return list de Facture de l'utilisateur
     */
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

    /**
     * Recherche la facture associé à l'id fournit
     *
     * @param file_id l'id de la facture que l'on souhaite récupéré
     * @return retourne la facture associé à l'id
     */
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

    /**
     * Recherche le nombre de facture associé à l'utilisateur +1
     *
     * @param utilisateur_id l'id de l'utilisateur sur lequel est effectuer la recherche
     * @return retourne un long contenant le nombre de facture associé à l'utilisateur +1
     */
    public Long getNbFacture(long utilisateur_id){
        long i = 0;
        try{

            String query = "SELECT count(*) from Facture where idUser =: utilisateur_id";

            Session session = this.factory.openSession();
            Query q = session.createQuery(query);
            q.setParameter("utilisateur_id", utilisateur_id);

            i = (Long) q.uniqueResult();

            session.close();

        } catch (Exception e){
            e.printStackTrace();
        }
        i+=1;
        System.out.println("test i : "+i);
        return i;
    }

}
