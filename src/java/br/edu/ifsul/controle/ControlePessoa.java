/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifsul.controle;

import br.edu.ifsul.dao.PessoaDAO;
import br.edu.ifsul.modelo.Pessoa;
import br.edu.ifsul.util.Util;
import java.io.Serializable;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;


@ManagedBean(name = "controlePessoa")
@ViewScoped
public class ControlePessoa implements Serializable {

    private PessoaDAO<Pessoa> dao;
    private Pessoa objeto;

    public ControlePessoa() {
        dao = new PessoaDAO<>();
    }

    public String listar() {
        return "/privado/pessoa/listar?faces-redirect=true";
    }

    public String novo() {
        objeto = new Pessoa();
        return "formulario?faces-redirect=true";
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
    public PessoaDAO getDao() {
        return dao;
    }

    /**
     * @param da the da to set
     */
    public void setDao(PessoaDAO dao) {
        this.dao = dao;
    }

    /**
     * @return the objeto
     */
    public Pessoa getObjeto() {
        return objeto;
    }

    /**
     * @param objeto the objeto to set
     */
    public void setObjeto(Pessoa objeto) {
        this.objeto = objeto;
    }

}
