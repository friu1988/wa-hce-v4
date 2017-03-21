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
public class TPersonalSaludPK implements Serializable {

    @Basic(optional = false)
    @NotNull
    @Column(name = "per_serial", nullable = false)
    private int perSerial;
    @Basic(optional = false)
    @NotNull
    @Column(name = "h_serial", nullable = false)
    private int hSerial;

    public TPersonalSaludPK() {
    }

    public TPersonalSaludPK(int perSerial, int hSerial) {
        this.perSerial = perSerial;
        this.hSerial = hSerial;
    }

    public int getPerSerial() {
        return perSerial;
    }

    public void setPerSerial(int perSerial) {
        this.perSerial = perSerial;
    }

    public int getHSerial() {
        return hSerial;
    }

    public void setHSerial(int hSerial) {
        this.hSerial = hSerial;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) perSerial;
        hash += (int) hSerial;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TPersonalSaludPK)) {
            return false;
        }
        TPersonalSaludPK other = (TPersonalSaludPK) object;
        if (this.perSerial != other.perSerial) {
            return false;
        }
        if (this.hSerial != other.hSerial) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.capa.datos.TPersonalSaludPK[ perSerial=" + perSerial + ", hSerial=" + hSerial + " ]";
    }

}
