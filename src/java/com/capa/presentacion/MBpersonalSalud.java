/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.capa.presentacion;

import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;

/**
 *
 * @author FREDDY
 */
@Named(value = "mBpersonalSalud")
@SessionScoped
public class MBpersonalSalud implements Serializable {

    /**
     * Creates a new instance of MBpersonalSalud
     */
    public MBpersonalSalud() {
    }
    
}
