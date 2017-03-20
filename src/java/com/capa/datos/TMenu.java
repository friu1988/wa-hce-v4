/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.capa.datos;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author FREDDY
 */
@Entity
@Table(name = "t_menu", catalog = "db_hospital_dia_v4", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TMenu.findAll", query = "SELECT t FROM TMenu t"),
    @NamedQuery(name = "TMenu.findByMenSerial", query = "SELECT t FROM TMenu t WHERE t.menSerial = :menSerial"),
    @NamedQuery(name = "TMenu.findByMenNombre", query = "SELECT t FROM TMenu t WHERE t.menNombre = :menNombre"),
    @NamedQuery(name = "TMenu.findByMenUrl", query = "SELECT t FROM TMenu t WHERE t.menUrl = :menUrl"),
    @NamedQuery(name = "TMenu.findByMenTipo", query = "SELECT t FROM TMenu t WHERE t.menTipo = :menTipo"),
    @NamedQuery(name = "TMenu.findByMenOrden", query = "SELECT t FROM TMenu t WHERE t.menOrden = :menOrden")})
public class TMenu implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "men_serial", nullable = false)
    private Integer menSerial;
    @Size(max = 100)
    @Column(name = "men_nombre", length = 100)
    private String menNombre;
    @Size(max = 100)
    @Column(name = "men_url", length = 100)
    private String menUrl;
    @Size(max = 1)
    @Column(name = "men_tipo", length = 1)
    private String menTipo;
    @Column(name = "men_orden")
    private Integer menOrden;
    @OneToMany(mappedBy = "padreMenSerial")
    private List<TMenu> tMenuList;
    @JoinColumn(name = "padre_men_serial", referencedColumnName = "men_serial")
    @ManyToOne
    private TMenu padreMenSerial;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "tMenu")
    private List<TAccesos> tAccesosList;

    public TMenu() {
    }

    public TMenu(Integer menSerial) {
        this.menSerial = menSerial;
    }

    public Integer getMenSerial() {
        return menSerial;
    }

    public void setMenSerial(Integer menSerial) {
        this.menSerial = menSerial;
    }

    public String getMenNombre() {
        return menNombre;
    }

    public void setMenNombre(String menNombre) {
        this.menNombre = menNombre;
    }

    public String getMenUrl() {
        return menUrl;
    }

    public void setMenUrl(String menUrl) {
        this.menUrl = menUrl;
    }

    public String getMenTipo() {
        return menTipo;
    }

    public void setMenTipo(String menTipo) {
        this.menTipo = menTipo;
    }

    public Integer getMenOrden() {
        return menOrden;
    }

    public void setMenOrden(Integer menOrden) {
        this.menOrden = menOrden;
    }

    @XmlTransient
    public List<TMenu> getTMenuList() {
        return tMenuList;
    }

    public void setTMenuList(List<TMenu> tMenuList) {
        this.tMenuList = tMenuList;
    }

    public TMenu getPadreMenSerial() {
        return padreMenSerial;
    }

    public void setPadreMenSerial(TMenu padreMenSerial) {
        this.padreMenSerial = padreMenSerial;
    }

    @XmlTransient
    public List<TAccesos> getTAccesosList() {
        return tAccesosList;
    }

    public void setTAccesosList(List<TAccesos> tAccesosList) {
        this.tAccesosList = tAccesosList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (menSerial != null ? menSerial.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TMenu)) {
            return false;
        }
        TMenu other = (TMenu) object;
        if ((this.menSerial == null && other.menSerial != null) || (this.menSerial != null && !this.menSerial.equals(other.menSerial))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "TMenu{" + "menSerial=" + menSerial + ", menNombre=" + menNombre + ", menUrl=" + menUrl + ", menTipo=" + menTipo + ", menOrden=" + menOrden + '}';
    }

}
