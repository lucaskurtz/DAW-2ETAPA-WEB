/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifsul.dao;

import br.edu.ifsul.jpa.EntityManagerUtil;
import br.edu.ifsul.modelo.Recurso;
import br.edu.ifsul.util.Util;
import java.io.Serializable;
import java.util.List;
import javax.persistence.EntityManager;
import org.postgresql.translation.messages_bg;

public class RecursoDAO<TIPO> extends DAOGenerico<Recurso> implements Serializable {

  public RecursoDAO() {
    super();
    classePersistente = Recurso.class;
    ordem = "descricao";
  }
    
}
