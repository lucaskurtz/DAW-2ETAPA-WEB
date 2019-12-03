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
import javax.faces.bean.SessionScoped;


@ManagedBean(name = "controleLogin")
@SessionScoped
public class ControleLogin implements Serializable {
    private PessoaDAO<Pessoa> dao;
    private Pessoa usuarioLogado;
    private String usuario;
    private String senha;
    
    public ControleLogin(){
        dao = new PessoaDAO<>(); 
    }
    
    public String paginaLogin(){
        return "/login?faces-redirect=true";
    }
    
    public String efetuarLogin(){
        if(dao.login(usuario, senha)){
            usuarioLogado = dao.localizaPorNomeUsuario(usuario);
            Util.mensagemInformacao("Login Realizado com sucesso!");
            usuario = "";
            senha = "";
            return "/index?faces-redirect=true";
        } else {
            Util.mensagemErro("Usuario ou senha invalidos!");
            return "/login?faces-redirect=true";
        }
    }
    
    public String efetuarLogout(){
        usuarioLogado = null;
        Util.mensagemInformacao("Logout com sucesso!");
        return "/index?faces-redirect=true";
    }

    public PessoaDAO<Pessoa> getDao() {
        return dao;
    }

    public void setDao(PessoaDAO<Pessoa> dao) {
        this.dao = dao;
    }

    public Pessoa getUsuarioLogado() {
        return usuarioLogado;
    }

    public void setUsuarioLogado(Pessoa usuarioLogado) {
        this.usuarioLogado = usuarioLogado;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }
}
