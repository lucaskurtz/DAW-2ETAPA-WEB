/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifsul.controle;

import br.edu.ifsul.dao.LocatarioDAO;
import br.edu.ifsul.modelo.Locatario;
import br.edu.ifsul.util.Util;
import java.io.Serializable;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;


@ManagedBean(name = "controleLocatario")
@ViewScoped
public class ControleLocatario implements Serializable {

    private LocatarioDAO<Locatario> dao;
    private Locatario objeto;

    public ControleLocatario() {
        dao = new LocatarioDAO<>();
    }

    public String listar() {
        return "/privado/locatario/listar?faces-redirect=true";
    }

    public void novo() {
        objeto = new Locatario();
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
    public LocatarioDAO getDao() {
        return dao;
    }

    /**
     * @param da the da to set
     */
    public void setDao(LocatarioDAO dao) {
        this.dao = dao;
    }

    /**
     * @return the objeto
     */
    public Locatario getObjeto() {
        return objeto;
    }

    /**
     * @param objeto the objeto to set
     */
    public void setObjeto(Locatario objeto) {
        this.objeto = objeto;
    }

}
