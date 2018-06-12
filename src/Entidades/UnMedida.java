/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entidades;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 *
 * @author Mayara Hakner
 */
@Entity
@Table(name = "un_medida")
@NamedQueries({
    @NamedQuery(name = "UnMedida.findAll", query = "SELECT u FROM UnMedida u")})
public class UnMedida implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "id_un_medida")
    private String idUnMedida;
    @Column(name = "nome_unidade_medida")
    private String nomeUnidadeMedida;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "unMedidaIdUnMedida")
    private List<Produto> produtoList;

    public UnMedida() {
    }

    public UnMedida(String idUnMedida) {
        this.idUnMedida = idUnMedida;
    }

    public String getIdUnMedida() {
        return idUnMedida;
    }

    public void setIdUnMedida(String idUnMedida) {
        this.idUnMedida = idUnMedida;
    }

    public String getNomeUnidadeMedida() {
        return nomeUnidadeMedida;
    }

    public void setNomeUnidadeMedida(String nomeUnidadeMedida) {
        this.nomeUnidadeMedida = nomeUnidadeMedida;
    }

    public List<Produto> getProdutoList() {
        return produtoList;
    }

    public void setProdutoList(List<Produto> produtoList) {
        this.produtoList = produtoList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idUnMedida != null ? idUnMedida.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof UnMedida)) {
            return false;
        }
        UnMedida other = (UnMedida) object;
        if ((this.idUnMedida == null && other.idUnMedida != null) || (this.idUnMedida != null && !this.idUnMedida.equals(other.idUnMedida))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entidades.UnMedida[ idUnMedida=" + idUnMedida + " ]";
    }
    
}
