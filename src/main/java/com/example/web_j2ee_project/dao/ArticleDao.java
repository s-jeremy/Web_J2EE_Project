package com.example.web_j2ee_project.dao;

import com.example.web_j2ee_project.hibernate.entites.Article;
import com.example.web_j2ee_project.hibernate.entites.Categorie;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

import java.util.List;

public class ArticleDao
{

    private SessionFactory factory;

    public ArticleDao(SessionFactory factory){
        this.factory = factory;
    }

    public boolean checkIfExist(String nomArticle){
        boolean exist = true;
        try{
            String query = "from Article where nomProduit =: nomArticle";
            Session session = this.factory.openSession();
            Query q = session.createQuery(query);
            q.setParameter("nomArticle", nomArticle);
            Article art=(Article) q.uniqueResult();
            if (art==null){
                exist = false;
            }

        } catch (Exception e)
        {
            e.printStackTrace();
        }
        return exist;
    }

    public List<Article> getArticles(){
        List<Article> articleList = null;
        try{

            String query = "from Article";
            Session session = this.factory.openSession();
            Query q = session.createQuery(query);

            articleList =q.getResultList();


            session.close();


        } catch (Exception e){
            e.printStackTrace();
        }
        return articleList;
    }

    public List<Article> getArticlesCategorie(int categorie_id){
        List<Article> articleList = null;
        try{

            String query = "from Article where idCategorie =: categorie_id";
            Session session = this.factory.openSession();
            Query q = session.createQuery(query);
            q.setParameter("categorie_id", categorie_id);

            articleList =q.getResultList();

            session.close();

        } catch (Exception e){
            e.printStackTrace();
        }
        return articleList;
    }

    public Article getArticle(int id_article){
        Article article = null;
        try{

            String query = "from Article where id =: id_article";
            Session session = this.factory.openSession();
            Query q = session.createQuery(query);
            q.setParameter("id_article", id_article);

            article = (Article) q.uniqueResult();


            session.close();


        } catch (Exception e){
            e.printStackTrace();
        }
        return article;
    }
}
