package com.example.web_j2ee_project.dao;

import com.example.web_j2ee_project.hibernate.entites.Categorie;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

import java.util.List;

public class CategorieDao
{

    private SessionFactory factory;

    public CategorieDao(SessionFactory factory){
        this.factory = factory;
    }

    /**
     * Cherche si la catégorie existe dans la base
     *
     * @param category_titre Le nom de la catégorie à rechercher
     * @return retourne un boolean true si la catégorie existe dans la base
     */
    public boolean checkIfExist(String category_titre){
        boolean exist = true;
        try{
            String query = "from Categorie where titre =: category_titre";
            Session session = this.factory.openSession();
            Query q = session.createQuery(query);
            q.setParameter("category_titre", category_titre);
            Categorie cat=(Categorie) q.uniqueResult();
            if (cat==null){
                exist = false;
            }

        } catch (Exception e)
        {
            e.printStackTrace();
        }
        return exist;
    }

    /**
     * Récupération d'une list contenant toutes les catégories dans la base
     *
     * @return retourne une list de Catégorie contenant toutes les catégories dans la base
     */
    public List<Categorie> getCategories(){
        List<Categorie> categorieList = null;
        try{

            String query = "from Categorie";
            Session session = this.factory.openSession();
            Query q = session.createQuery(query);

            categorieList =q.getResultList();


            session.close();


        } catch (Exception e){
            e.printStackTrace();
        }
        return categorieList;
    }

    /**
     * Retourne la catégorié associé à l'id fournit
     *
     * @param id_categorie référence l'id de la catégorie à retourner
     * @return retourne la catégorie associé à l'id
     */
    public Categorie getCategorie(int id_categorie){
        Categorie categorie = null;
        try{

            String query = "from Categorie where id =: id_categorie";
            Session session = this.factory.openSession();
            Query q = session.createQuery(query);
            q.setParameter("id_categorie", id_categorie);

            categorie = (Categorie) q.uniqueResult();


            session.close();


        } catch (Exception e){
            e.printStackTrace();
        }
        return categorie;
    }
}
