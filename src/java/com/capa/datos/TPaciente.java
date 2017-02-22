/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.capa.datos;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author FREDDY
 */
@Entity
@Table(name = "t_paciente", catalog = "db_hospital_dia_v4", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TPaciente.findAll", query = "SELECT t FROM TPaciente t ORDER BY t.hclNumeroHistoria DESC"),
    @NamedQuery(name = "TPaciente.findMaxHCU", query = "SELECT MAX(t.hclNumeroHistoria) FROM TPaciente t"),
    @NamedQuery(name = "TPaciente.findByPacCedula", query = "SELECT t FROM TPaciente t WHERE t.pacCedula = :pacCedula"),
    @NamedQuery(name = "TPaciente.findByPacApellidoPaterno", query = "SELECT t FROM TPaciente t WHERE t.pacApellidoPaterno = :pacApellidoPaterno"),
    @NamedQuery(name = "TPaciente.findByPacApellidoMaterno", query = "SELECT t FROM TPaciente t WHERE t.pacApellidoMaterno = :pacApellidoMaterno"),
    @NamedQuery(name = "TPaciente.findByPacPrimerNombre", query = "SELECT t FROM TPaciente t WHERE t.pacPrimerNombre = :pacPrimerNombre"),
    @NamedQuery(name = "TPaciente.findByPacSegundoNombre", query = "SELECT t FROM TPaciente t WHERE t.pacSegundoNombre = :pacSegundoNombre"),
    @NamedQuery(name = "TPaciente.findByPacDireccionResidencial", query = "SELECT t FROM TPaciente t WHERE t.pacDireccionResidencial = :pacDireccionResidencial"),
    @NamedQuery(name = "TPaciente.findByPacTelefono", query = "SELECT t FROM TPaciente t WHERE t.pacTelefono = :pacTelefono"),
    @NamedQuery(name = "TPaciente.findByPacFechaNacimiento", query = "SELECT t FROM TPaciente t WHERE t.pacFechaNacimiento = :pacFechaNacimiento"),
    @NamedQuery(name = "TPaciente.findByPacLugarNacimiento", query = "SELECT t FROM TPaciente t WHERE t.pacLugarNacimiento = :pacLugarNacimiento"),
    @NamedQuery(name = "TPaciente.findByPacNacionalidad", query = "SELECT t FROM TPaciente t WHERE t.pacNacionalidad = :pacNacionalidad"),
    @NamedQuery(name = "TPaciente.findByPacGrupoCultural", query = "SELECT t FROM TPaciente t WHERE t.pacGrupoCultural = :pacGrupoCultural"),
    @NamedQuery(name = "TPaciente.findByPacSexo", query = "SELECT t FROM TPaciente t WHERE t.pacSexo = :pacSexo"),
    @NamedQuery(name = "TPaciente.findByPacEstadoCivil", query = "SELECT t FROM TPaciente t WHERE t.pacEstadoCivil = :pacEstadoCivil"),
    @NamedQuery(name = "TPaciente.findByPacInstruccion", query = "SELECT t FROM TPaciente t WHERE t.pacInstruccion = :pacInstruccion"),
    @NamedQuery(name = "TPaciente.findByPacOcupacion", query = "SELECT t FROM TPaciente t WHERE t.pacOcupacion = :pacOcupacion"),
    @NamedQuery(name = "TPaciente.findByPacEmpresaLabora", query = "SELECT t FROM TPaciente t WHERE t.pacEmpresaLabora = :pacEmpresaLabora"),
    @NamedQuery(name = "TPaciente.findByPacSeguroSalud", query = "SELECT t FROM TPaciente t WHERE t.pacSeguroSalud = :pacSeguroSalud"),
    @NamedQuery(name = "TPaciente.findByPacReferidoDe", query = "SELECT t FROM TPaciente t WHERE t.pacReferidoDe = :pacReferidoDe"),
    @NamedQuery(name = "TPaciente.findByPacFamiliar", query = "SELECT t FROM TPaciente t WHERE t.pacFamiliar = :pacFamiliar"),
    @NamedQuery(name = "TPaciente.findByPacParentesco", query = "SELECT t FROM TPaciente t WHERE t.pacParentesco = :pacParentesco"),
    @NamedQuery(name = "TPaciente.findByPacDireccion", query = "SELECT t FROM TPaciente t WHERE t.pacDireccionPariente = :pacDireccionPariente"),
    @NamedQuery(name = "TPaciente.findByPacTelefonoFamiliar", query = "SELECT t FROM TPaciente t WHERE t.pacTelefonoFamiliar = :pacTelefonoFamiliar"),
    @NamedQuery(name = "TPaciente.findByHclInstitucion", query = "SELECT t FROM TPaciente t WHERE t.hclInstitucion = :hclInstitucion"),
    @NamedQuery(name = "TPaciente.findByHclUnidadOperativa", query = "SELECT t FROM TPaciente t WHERE t.hclUnidadOperativa = :hclUnidadOperativa"),
    @NamedQuery(name = "TPaciente.findByHclCodigoOu", query = "SELECT t FROM TPaciente t WHERE t.hclCodigoOu = :hclCodigoOu"),
    @NamedQuery(name = "TPaciente.findByHclClParroquia", query = "SELECT t FROM TPaciente t WHERE t.hclClParroquia = :hclClParroquia"),
    @NamedQuery(name = "TPaciente.findByHclClCanton", query = "SELECT t FROM TPaciente t WHERE t.hclClCanton = :hclClCanton"),
    @NamedQuery(name = "TPaciente.findByHclClProvincia", query = "SELECT t FROM TPaciente t WHERE t.hclClProvincia = :hclClProvincia"),
    @NamedQuery(name = "TPaciente.findByHclNumeroHistoria", query = "SELECT t FROM TPaciente t WHERE t.hclNumeroHistoria = :hclNumeroHistoria"),
    @NamedQuery(name = "TPaciente.findByHclTipoPaciente", query = "SELECT t FROM TPaciente t WHERE t.hclTipoPaciente = :hclTipoPaciente"),
    @NamedQuery(name = "TPaciente.findByHclSemestre", query = "SELECT t FROM TPaciente t WHERE t.hclSemestre = :hclSemestre"),
    @NamedQuery(name = "TPaciente.findByHclFechaAdmision", query = "SELECT t FROM TPaciente t WHERE t.hclFechaAdmision = :hclFechaAdmision"),
    @NamedQuery(name = "TPaciente.findByHclEstado", query = "SELECT t FROM TPaciente t WHERE t.hclEstado = :hclEstado")})
public class TPaciente implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 10)
    @Column(name = "pac_cedula", nullable = false, length = 10)
    private String pacCedula;
    @Size(max = 100)
    @Column(name = "pac_apellido_paterno", length = 100)
    private String pacApellidoPaterno;
    @Size(max = 100)
    @Column(name = "pac_apellido_materno", length = 100)
    private String pacApellidoMaterno;
    @Size(max = 100)
    @Column(name = "pac_primer_nombre", length = 100)
    private String pacPrimerNombre;
    @Size(max = 100)
    @Column(name = "pac_segundo_nombre", length = 100)
    private String pacSegundoNombre;
    @Size(max = 300)
    @Column(name = "pac_direccion_residencial", length = 300)
    private String pacDireccionResidencial;
    @Size(max = 200)
    @Column(name = "pac_barrio", length = 200)
    private String pacBarrio;
    @Size(max = 1)
    @Column(name = "pac_zona", length = 1)
    private String pacZona;
    @Size(max = 300)
    @Column(name = "pac_telefono", length = 300)
    private String pacTelefono;
    @Column(name = "pac_fecha_nacimiento")
    @Temporal(TemporalType.DATE)
    private Date pacFechaNacimiento;
    @Size(max = 300)
    @Column(name = "pac_lugar_nacimiento", length = 300)
    private String pacLugarNacimiento;
    @Size(max = 300)
    @Column(name = "pac_nacionalidad", length = 300)
    private String pacNacionalidad;
    @Size(max = 2)
    @Column(name = "pac_grupo_cultural", length = 2)
    private String pacGrupoCultural;
    @Size(max = 1)
    @Column(name = "pac_sexo", length = 1)
    private String pacSexo;
    @Size(max = 2)
    @Column(name = "pac_estado_civil", length = 2)
    private String pacEstadoCivil;
    @Size(max = 2)
    @Column(name = "pac_instruccion", length = 2)
    private String pacInstruccion;
    @Size(max = 300)
    @Column(name = "pac_ocupacion", length = 300)
    private String pacOcupacion;
    @Size(max = 300)
    @Column(name = "pac_empresa_labora", length = 300)
    private String pacEmpresaLabora;
    @Size(max = 300)
    @Column(name = "pac_seguro_salud", length = 300)
    private String pacSeguroSalud;
    @Size(max = 300)
    @Column(name = "pac_referido_de", length = 300)
    private String pacReferidoDe;
    @Size(max = 300)
    @Column(name = "pac_familiar", length = 300)
    private String pacFamiliar;
    @Size(max = 300)
    @Column(name = "pac_parentesco", length = 300)
    private String pacParentesco;
    @Size(max = 300)
    @Column(name = "pac_direccion_pariente", length = 300)
    private String pacDireccionPariente;
    @Size(max = 10)
    @Column(name = "pac_telefono_familiar", length = 10)
    private String pacTelefonoFamiliar;
    @Size(max = 10)
    @Column(name = "hcl_institucion", length = 10)
    private String hclInstitucion;
    @Size(max = 10)
    @Column(name = "hcl_unidad_operativa", length = 10)
    private String hclUnidadOperativa;
    @Size(max = 10)
    @Column(name = "hcl_codigo_ou", length = 10)
    private String hclCodigoOu;
    @Size(max = 50)
    @Column(name = "hcl_cl_parroquia", length = 50)
    private String hclClParroquia;
    @Size(max = 50)
    @Column(name = "hcl_cl_canton", length = 50)
    private String hclClCanton;
    @Size(max = 50)
    @Column(name = "hcl_cl_provincia", length = 50)
    private String hclClProvincia;
    @Size(max = 10)
    @Column(name = "hcl_numero_historia", length = 10)
    private String hclNumeroHistoria;
    @Size(max = 1)
    @Column(name = "hcl_tipo_paciente", length = 1)
    private String hclTipoPaciente;
    @Size(max = 200)
    @Column(name = "hcl_semestre", length = 200)
    private String hclSemestre;
    @Column(name = "hcl_fecha_admision")
    @Temporal(TemporalType.DATE)
    private Date hclFechaAdmision;
    @Column(name = "hcl_estado")
    private Boolean hclEstado;
    @JoinColumn(name = "lg_codigo", referencedColumnName = "lg_codigo", nullable = false)
    @ManyToOne(optional = false)
    private TLugarGeografico lgCodigo;
    @JoinColumn(name = "per_serial", referencedColumnName = "per_serial", nullable = false)
    @ManyToOne(optional = false)
    private TAdmisionista perSerial;
    @JoinColumn(name = "fac_serial", referencedColumnName = "fac_serial")
    @ManyToOne
    private TFacultad facSerial;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "pacCedula")
    private List<TTurno> tTurnoList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "pacCedula")
    private List<TKardex> tKardexList;

    public TPaciente() {
    }

    public TPaciente(String pacCedula) {
        this.pacCedula = pacCedula;
    }

    public String getPacCedula() {
        return pacCedula;
    }

    public void setPacCedula(String pacCedula) {
        this.pacCedula = pacCedula;
    }

    public String getPacApellidoPaterno() {
        return pacApellidoPaterno;
    }

    public void setPacApellidoPaterno(String pacApellidoPaterno) {
        this.pacApellidoPaterno = pacApellidoPaterno;
    }

    public String getPacApellidoMaterno() {
        return pacApellidoMaterno;
    }

    public void setPacApellidoMaterno(String pacApellidoMaterno) {
        this.pacApellidoMaterno = pacApellidoMaterno;
    }

    public String getPacPrimerNombre() {
        return pacPrimerNombre;
    }

    public void setPacPrimerNombre(String pacPrimerNombre) {
        this.pacPrimerNombre = pacPrimerNombre;
    }

    public String getPacSegundoNombre() {
        return pacSegundoNombre;
    }

    public void setPacSegundoNombre(String pacSegundoNombre) {
        this.pacSegundoNombre = pacSegundoNombre;
    }

    public String getPacDireccionResidencial() {
        return pacDireccionResidencial;
    }

    public void setPacDireccionResidencial(String pacDireccionResidencial) {
        this.pacDireccionResidencial = pacDireccionResidencial;
    }

    public String getPacTelefono() {
        return pacTelefono;
    }

    public void setPacTelefono(String pacTelefono) {
        this.pacTelefono = pacTelefono;
    }

    public Date getPacFechaNacimiento() {
        return pacFechaNacimiento;
    }

    public void setPacFechaNacimiento(Date pacFechaNacimiento) {
        this.pacFechaNacimiento = pacFechaNacimiento;
    }

    public String getPacLugarNacimiento() {
        return pacLugarNacimiento;
    }

    public void setPacLugarNacimiento(String pacLugarNacimiento) {
        this.pacLugarNacimiento = pacLugarNacimiento;
    }

    public String getPacNacionalidad() {
        return pacNacionalidad;
    }

    public void setPacNacionalidad(String pacNacionalidad) {
        this.pacNacionalidad = pacNacionalidad;
    }

    public String getPacGrupoCultural() {
        return pacGrupoCultural;
    }

    public void setPacGrupoCultural(String pacGrupoCultural) {
        this.pacGrupoCultural = pacGrupoCultural;
    }

    public String getPacSexo() {
        return pacSexo;
    }

    public void setPacSexo(String pacSexo) {
        this.pacSexo = pacSexo;
    }

    public String getPacEstadoCivil() {
        return pacEstadoCivil;
    }

    public void setPacEstadoCivil(String pacEstadoCivil) {
        this.pacEstadoCivil = pacEstadoCivil;
    }

    public String getPacInstruccion() {
        return pacInstruccion;
    }

    public void setPacInstruccion(String pacInstruccion) {
        this.pacInstruccion = pacInstruccion;
    }

    public String getPacOcupacion() {
        return pacOcupacion;
    }

    public void setPacOcupacion(String pacOcupacion) {
        this.pacOcupacion = pacOcupacion;
    }

    public String getPacEmpresaLabora() {
        return pacEmpresaLabora;
    }

    public void setPacEmpresaLabora(String pacEmpresaLabora) {
        this.pacEmpresaLabora = pacEmpresaLabora;
    }

    public String getPacSeguroSalud() {
        return pacSeguroSalud;
    }

    public void setPacSeguroSalud(String pacSeguroSalud) {
        this.pacSeguroSalud = pacSeguroSalud;
    }

    public String getPacReferidoDe() {
        return pacReferidoDe;
    }

    public void setPacReferidoDe(String pacReferidoDe) {
        this.pacReferidoDe = pacReferidoDe;
    }

    public String getPacFamiliar() {
        return pacFamiliar;
    }

    public void setPacFamiliar(String pacFamiliar) {
        this.pacFamiliar = pacFamiliar;
    }

    public String getPacParentesco() {
        return pacParentesco;
    }

    public void setPacParentesco(String pacParentesco) {
        this.pacParentesco = pacParentesco;
    }

    public String getPacDireccionPariente() {
        return pacDireccionPariente;
    }

    public void setPacDireccionPariente(String pacDireccionPariente) {
        this.pacDireccionPariente = pacDireccionPariente;
    }

    public String getPacTelefonoFamiliar() {
        return pacTelefonoFamiliar;
    }

    public void setPacTelefonoFamiliar(String pacTelefonoFamiliar) {
        this.pacTelefonoFamiliar = pacTelefonoFamiliar;
    }

    public String getHclInstitucion() {
        return hclInstitucion;
    }

    public void setHclInstitucion(String hclInstitucion) {
        this.hclInstitucion = hclInstitucion;
    }

    public String getHclUnidadOperativa() {
        return hclUnidadOperativa;
    }

    public void setHclUnidadOperativa(String hclUnidadOperativa) {
        this.hclUnidadOperativa = hclUnidadOperativa;
    }

    public String getHclCodigoOu() {
        return hclCodigoOu;
    }

    public void setHclCodigoOu(String hclCodigoOu) {
        this.hclCodigoOu = hclCodigoOu;
    }

    public String getHclClParroquia() {
        return hclClParroquia;
    }

    public void setHclClParroquia(String hclClParroquia) {
        this.hclClParroquia = hclClParroquia;
    }

    public String getHclClCanton() {
        return hclClCanton;
    }

    public void setHclClCanton(String hclClCanton) {
        this.hclClCanton = hclClCanton;
    }

    public String getHclClProvincia() {
        return hclClProvincia;
    }

    public void setHclClProvincia(String hclClProvincia) {
        this.hclClProvincia = hclClProvincia;
    }

    public String getHclNumeroHistoria() {
        return hclNumeroHistoria;
    }

    public void setHclNumeroHistoria(String hclNumeroHistoria) {
        this.hclNumeroHistoria = hclNumeroHistoria;
    }

    public String getHclTipoPaciente() {
        return hclTipoPaciente;
    }

    public void setHclTipoPaciente(String hclTipoPaciente) {
        this.hclTipoPaciente = hclTipoPaciente;
    }

    public String getHclSemestre() {
        return hclSemestre;
    }

    public void setHclSemestre(String hclSemestre) {
        this.hclSemestre = hclSemestre;
    }

    public Date getHclFechaAdmision() {
        return hclFechaAdmision;
    }

    public void setHclFechaAdmision(Date hclFechaAdmision) {
        this.hclFechaAdmision = hclFechaAdmision;
    }

    public Boolean getHclEstado() {
        return hclEstado;
    }

    public void setHclEstado(Boolean hclEstado) {
        this.hclEstado = hclEstado;
    }

    public TLugarGeografico getLgCodigo() {
        return lgCodigo;
    }

    public void setLgCodigo(TLugarGeografico lgCodigo) {
        this.lgCodigo = lgCodigo;
    }

    public TAdmisionista getPerSerial() {
        return perSerial;
    }

    public void setPerSerial(TAdmisionista perSerial) {
        this.perSerial = perSerial;
    }

    public TFacultad getFacSerial() {
        return facSerial;
    }

    public void setFacSerial(TFacultad facSerial) {
        this.facSerial = facSerial;
    }

    public String getPacBarrio() {
        return pacBarrio;
    }

    public void setPacBarrio(String pacBarrio) {
        this.pacBarrio = pacBarrio;
    }

    public String getPacZona() {
        return pacZona;
    }

    public void setPacZona(String pacZona) {
        this.pacZona = pacZona;
    }

    @XmlTransient
    public List<TTurno> getTTurnoList() {
        return tTurnoList;
    }

    public void setTTurnoList(List<TTurno> tTurnoList) {
        this.tTurnoList = tTurnoList;
    }

    @XmlTransient
    public List<TKardex> getTKardexList() {
        return tKardexList;
    }

    public void setTKardexList(List<TKardex> tKardexList) {
        this.tKardexList = tKardexList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (pacCedula != null ? pacCedula.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TPaciente)) {
            return false;
        }
        TPaciente other = (TPaciente) object;
        if ((this.pacCedula == null && other.pacCedula != null) || (this.pacCedula != null && !this.pacCedula.equals(other.pacCedula))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "TPaciente{" + "pacCedula=" + pacCedula + ", pacApellidoPaterno=" + pacApellidoPaterno + ", pacApellidoMaterno=" + pacApellidoMaterno + ", pacPrimerNombre=" + pacPrimerNombre + ", pacSegundoNombre=" + pacSegundoNombre + ", pacDireccionResidencial=" + pacDireccionResidencial + ", pacBarrio=" + pacBarrio + ", pacZona=" + pacZona + ", pacTelefono=" + pacTelefono + ", pacFechaNacimiento=" + pacFechaNacimiento + ", pacLugarNacimiento=" + pacLugarNacimiento + ", pacNacionalidad=" + pacNacionalidad + ", pacGrupoCultural=" + pacGrupoCultural + ", pacSexo=" + pacSexo + ", pacEstadoCivil=" + pacEstadoCivil + ", pacInstruccion=" + pacInstruccion + ", pacOcupacion=" + pacOcupacion + ", pacEmpresaLabora=" + pacEmpresaLabora + ", pacSeguroSalud=" + pacSeguroSalud + ", pacReferidoDe=" + pacReferidoDe + ", pacFamiliar=" + pacFamiliar + ", pacParentesco=" + pacParentesco + ", pacDireccionPariente=" + pacDireccionPariente + ", pacTelefonoFamiliar=" + pacTelefonoFamiliar + ", hclInstitucion=" + hclInstitucion + ", hclUnidadOperativa=" + hclUnidadOperativa + ", hclCodigoOu=" + hclCodigoOu + ", hclClParroquia=" + hclClParroquia + ", hclClCanton=" + hclClCanton + ", hclClProvincia=" + hclClProvincia + ", hclNumeroHistoria=" + hclNumeroHistoria + ", hclTipoPaciente=" + hclTipoPaciente + ", hclSemestre=" + hclSemestre + ", hclFechaAdmision=" + hclFechaAdmision + ", hclEstado=" + hclEstado + ", lgCodigo=" + lgCodigo + ", perSerial=" + perSerial + ", facSerial=" + facSerial + '}';
    }

}
