/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifsul.dao;

import br.edu.ifsul.jpa.EntityManagerUtil;
import br.edu.ifsul.modelo.Pessoa;
import br.edu.ifsul.util.Util;
import java.io.Serializable;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import org.postgresql.translation.messages_bg;


public class PessoaDAO<TIPO> extends DAOGenerico<Pessoa> implements Serializable {

    public PessoaDAO() {
        super();
        classePersistente = Pessoa.class;
        ordem = "nome";
    }

    public boolean login(String usuario, String senha){
        Query query = em.createQuery("from Pessoa where upper(cpf) = :usuario and "
                + " upper(senha) = :senha");
        query.setParameter("usuario", usuario.toUpperCase());
        query.setParameter("senha", senha.toUpperCase());
        if(!query.getResultList().isEmpty()){
            return true;
        }else{
            return false;
        }
    }
    
    public Pessoa localizaPorNomeUsuario(String usuario){
        Query query = em.createQuery("from Pessoa where upper(cpf) = :usuario");
        query.setParameter("usuario", usuario.toUpperCase());
        return (Pessoa) query.getSingleResult();
    }
}
