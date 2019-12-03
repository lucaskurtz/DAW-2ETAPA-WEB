/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifsul.controle;

import br.edu.ifsul.dao.AluguelDAO;
import br.edu.ifsul.dao.LocatarioDAO;
import br.edu.ifsul.dao.UnidadeCondominialDAO;
import br.edu.ifsul.modelo.Aluguel;
import br.edu.ifsul.modelo.Locatario;
import br.edu.ifsul.modelo.Mensalidades;
import br.edu.ifsul.modelo.UnidadeCondominial;
import br.edu.ifsul.util.Util;
import java.io.Serializable;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;


@ManagedBean(name = "controleAluguel")
@ViewScoped
public class ControleAluguel implements Serializable {

    private AluguelDAO<Aluguel> dao;
    private Aluguel objeto;
    private LocatarioDAO<Locatario> daoLocatario;
    private UnidadeCondominialDAO<UnidadeCondominial> daoUC;
    private Mensalidades mensalidades;
    private Boolean novaMensalidade;

    public ControleAluguel() {
        dao = new AluguelDAO<>();
        daoLocatario = new LocatarioDAO<>();
        daoUC = new UnidadeCondominialDAO<>();
    }

    public void novaMensalidade() {
        mensalidades = new Mensalidades();
        novaMensalidade = true;
    }

    public void alterarMensalidade(int index) {
        mensalidades = objeto.getMensalidades().get(index);
        novaMensalidade = false;
    }

    public void salvarMensalidade() {
        if (novaMensalidade) {
            objeto.adicionarMensalidades(mensalidades);
        }
        Util.mensagemInformacao("Mensalidade inserida com sucesso!");
    }

    public void removerMensalidade(int index) {
        objeto.removerMensalidades(index);
        Util.mensagemInformacao("Mensalidade excluída com sucesso");
    }

    public String listar() {
        return "/privado/aluguel/listar?faces-redirect=true";
    }

    public void novo() {
        objeto = new Aluguel();
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

    public AluguelDAO getDao() {
        return dao;
    }

    public void setDao(AluguelDAO dao) {
        this.dao = dao;
    }

    public Aluguel getObjeto() {
        return objeto;
    }

    public void setObjeto(Aluguel objeto) {
        this.objeto = objeto;
    }

    public LocatarioDAO<Locatario> getDaoLocatario() {
        return daoLocatario;
    }

    public void setDaoLocatario(LocatarioDAO<Locatario> daoLocatario) {
        this.daoLocatario = daoLocatario;
    }

    public Mensalidades getMensalidades() {
        return mensalidades;
    }

    public void setMensalidades(Mensalidades novoMensalidade) {
        this.mensalidades = novoMensalidade;
    }

    public Boolean getNovaMensalidade() {
        return novaMensalidade;
    }

    public void setNovaMensalidade(Boolean novaMensalidade) {
        this.novaMensalidade = novaMensalidade;
    }

    public UnidadeCondominialDAO<UnidadeCondominial> getDaoUC() {
        return daoUC;
    }

    public void setDaoUnidadeCondominial(UnidadeCondominialDAO<UnidadeCondominial> daoUC) {
        this.daoUC = daoUC;
    }

}
