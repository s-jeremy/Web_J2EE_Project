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
