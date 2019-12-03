/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifsul.dao;

import br.edu.ifsul.jpa.EntityManagerUtil;
import br.edu.ifsul.modelo.Locatario;
import br.edu.ifsul.util.Util;
import java.io.Serializable;
import java.util.List;
import javax.persistence.EntityManager;
import org.postgresql.translation.messages_bg;

public class LocatarioDAO<TIPO> extends DAOGenerico<Locatario> implements Serializable {

    public LocatarioDAO() {
        super();
        classePersistente = Locatario.class;
        ordem = "nome";
    }

}
