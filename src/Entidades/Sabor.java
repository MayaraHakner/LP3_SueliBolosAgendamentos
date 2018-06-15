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
@Table(name = "sabor")
@NamedQueries({
    @NamedQuery(name = "Sabor.findAll", query = "SELECT s FROM Sabor s")})
public class Sabor implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "id_sabor")
    private Integer idSabor;
    @Column(name = "nome_sabor")
    private String nomeSabor;
    @Column(name = "status")
    private Boolean status;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "saborIdSabor")
    private List<Produto> produtoList;

    public Sabor() {
    }

    public Sabor(Integer idSabor) {
        this.idSabor = idSabor;
    }

    public Integer getIdSabor() {
        return idSabor;
    }

    public void setIdSabor(Integer idSabor) {
        this.idSabor = idSabor;
    }

    public String getNomeSabor() {
        return nomeSabor;
    }

    public void setNomeSabor(String nomeSabor) {
        this.nomeSabor = nomeSabor;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
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
        hash += (idSabor != null ? idSabor.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Sabor)) {
            return false;
        }
        Sabor other = (Sabor) object;
        if ((this.idSabor == null && other.idSabor != null) || (this.idSabor != null && !this.idSabor.equals(other.idSabor))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entidades.Sabor[ idSabor=" + idSabor + " ]";
    }
    
}
