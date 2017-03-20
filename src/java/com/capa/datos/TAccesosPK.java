/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.capa.datos;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;

/**
 *
 * @author FREDDY
 */
@Embeddable
public class TAccesosPK implements Serializable {

    @Basic(optional = false)
    @NotNull
    @Column(name = "men_serial", nullable = false)
    private int menSerial;
    @Basic(optional = false)
    @NotNull
    @Column(name = "u_serial", nullable = false)
    private int uSerial;

    public TAccesosPK() {
    }

    public TAccesosPK(int menSerial, int uSerial) {
        this.menSerial = menSerial;
        this.uSerial = uSerial;
    }

    public int getMenSerial() {
        return menSerial;
    }

    public void setMenSerial(int menSerial) {
        this.menSerial = menSerial;
    }

    public int getUSerial() {
        return uSerial;
    }

    public void setUSerial(int uSerial) {
        this.uSerial = uSerial;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) menSerial;
        hash += (int) uSerial;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TAccesosPK)) {
            return false;
        }
        TAccesosPK other = (TAccesosPK) object;
        if (this.menSerial != other.menSerial) {
            return false;
        }
        if (this.uSerial != other.uSerial) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.capa.datos.TAccesosPK[ menSerial=" + menSerial + ", uSerial=" + uSerial + " ]";
    }

}
