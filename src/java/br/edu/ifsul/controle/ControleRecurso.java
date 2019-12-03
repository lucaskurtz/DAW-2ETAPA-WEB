/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifsul.controle;

import br.edu.ifsul.dao.RecursoDAO;
import br.edu.ifsul.modelo.Recurso;
import br.edu.ifsul.util.Util;
import java.io.Serializable;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;


@ManagedBean(name = "controleRecurso")
@ViewScoped
public class ControleRecurso implements Serializable {

    private RecursoDAO<Recurso> dao;
    private Recurso objeto;

    public ControleRecurso() {
        dao = new RecursoDAO();
    }

    public String listar() {
        return "/privado/recurso/listar?faces-redirect=true";
    }

    public void novo() {
        objeto = new Recurso();
    }

    public void salvar() {
        boolean persistiu;
        if (objeto.getId() == null) {
            persistiu = dao.persist(objeto);
        } else {
            persistiu = dao.merge(objeto);
        }

        if (persistiu) {
            Util.mensagemInformacao(dao.getMensagem());
        } else {
            Util.mensagemErro(dao.getMensagem());
        }
    }

    public void editar(Integer id) {
        objeto = dao.localizar(id);
    }

    public void remover(Integer id) {
        objeto = dao.localizar(id);
        if (dao.remover(objeto)) {
            Util.mensagemInformacao(dao.getMensagem());
        } else {
            Util.mensagemErro(dao.getMensagem());
        }
    }

    /**
     * @return the da
     */
    public RecursoDAO getDao() {
        return dao;
    }

    /**
     * @param da the da to set
     */
    public void setDao(RecursoDAO dao) {
        this.dao = dao;
    }

    /**
     * @return the objeto
     */
    public Recurso getObjeto() {
        return objeto;
    }

    /**
     * @param objeto the objeto to set
     */
    public void setObjeto(Recurso objeto) {
        this.objeto = objeto;
    }

}
