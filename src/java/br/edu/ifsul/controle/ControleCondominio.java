/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifsul.controle;

import br.edu.ifsul.dao.CondominioDAO;
import br.edu.ifsul.dao.PessoaDAO;
import br.edu.ifsul.modelo.Condominio;
import br.edu.ifsul.modelo.Pessoa;
import br.edu.ifsul.modelo.UnidadeCondominial;
import br.edu.ifsul.util.Util;
import br.edu.ifsul.util.UtilRelatorios;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;


@ManagedBean(name = "controleCondominio")
@ViewScoped
public class ControleCondominio implements Serializable {

    private CondominioDAO<Condominio> dao;
    private Condominio objeto;
    private PessoaDAO<Pessoa> daoPessoa;
    private UnidadeCondominial uc;
    private Boolean novoUC;

    public ControleCondominio() {
        dao = new CondominioDAO<>();
        daoPessoa = new PessoaDAO<>();
    }

    public void imprimeCondominio(Integer id) {
        objeto = dao.localizar(id);
        List<Condominio> lista = new ArrayList<>();
        lista.add(objeto);
        HashMap parametros = new HashMap();
        UtilRelatorios.imprimeRelatorio("relatorioCondominio", parametros, lista);
    }
    
    public void imprimeTodosCondominios(){
        HashMap parametros = new HashMap();
        UtilRelatorios.imprimeRelatorio("relatorioCondominio", 
                parametros, dao.getListaTodos());
    }

    public void novoUnidadeCondominial() {
        uc = new UnidadeCondominial();
        novoUC = true;
    }

    public void alterarUnidadeCondominial(int index) {
        uc = objeto.getUcs().get(index);
        novoUC = false;
    }

    public void salvarUnidadeCondominial() {
        if (novoUC) {
            objeto.adicionarUC(uc);
        }
        Util.mensagemInformacao("Unidade Condominial inserida com sucesso!");
    }

    public void removerUnidadeCondominial(int index) {
        objeto.removerUnidadeCondominal(index);
        Util.mensagemInformacao("Unidade Condominial excluída com sucesso");
    }

    public String listar() {
        return "/privado/condominio/listar?faces-redirect=true";
    }

    public void novo() {
        objeto = new Condominio();
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

    public CondominioDAO getDao() {
        return dao;
    }

    public void setDao(CondominioDAO dao) {
        this.dao = dao;
    }

    public Condominio getObjeto() {
        return objeto;
    }

    public void setObjeto(Condominio objeto) {
        this.objeto = objeto;
    }

    public PessoaDAO<Pessoa> getDaoPessoa() {
        return daoPessoa;
    }

    public void setDaoPessoa(PessoaDAO<Pessoa> daoPessoa) {
        this.daoPessoa = daoPessoa;
    }

    public UnidadeCondominial getUnidadeCondominial() {
        return uc;
    }

    public void setUnidadeCondominial(UnidadeCondominial novoUC) {
        this.uc = novoUC;
    }

    public Boolean getNovoUnidadeCondominial() {
        return novoUC;
    }

    public void setNovoUnidadeCondominial(Boolean novoUC) {
        this.novoUC = novoUC;
    }

}
