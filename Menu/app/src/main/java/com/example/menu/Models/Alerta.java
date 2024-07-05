package com.example.menu.Models;

public class Alerta {

    private String strCodAlerta;
    private String strDepartamento;
    private String strProvincia;
    private String strDistrito;
    private String strDireccion;
    private String strLatitud;
    private String strLongitud;
    private String strDescripcion;
    private String strNombreGrupoPersonal;

    public Alerta() {
        this.strCodAlerta = "";
        this.strDepartamento = "";
        this.strProvincia = "";
        this.strDistrito = "";
        this.strDireccion = "";
        this.strLatitud = "";
        this.strLongitud = "";
        this.strDescripcion = "";
        this.strNombreGrupoPersonal = "";
    }

    public String getStrCodAlerta() {
        return strCodAlerta;
    }

    public void setStrCodAlerta(String strCodAlerta) {
        this.strCodAlerta = strCodAlerta;
    }

    public String getStrDepartamento() {
        return strDepartamento;
    }

    public void setStrDepartamento(String strDepartamento) {
        this.strDepartamento = strDepartamento;
    }

    public String getStrProvincia() {
        return strProvincia;
    }

    public void setStrProvincia(String strProvincia) {
        this.strProvincia = strProvincia;
    }

    public String getStrDistrito() {
        return strDistrito;
    }

    public void setStrDistrito(String strDistrito) {
        this.strDistrito = strDistrito;
    }

    public String getStrDireccion() {
        return strDireccion;
    }

    public void setStrDireccion(String strDireccion) {
        this.strDireccion = strDireccion;
    }

    public String getStrLatitud() {
        return strLatitud;
    }

    public void setStrLatitud(String strLatitud) {
        this.strLatitud = strLatitud;
    }

    public String getStrLongitud() {
        return strLongitud;
    }

    public void setStrLongitud(String strLongitud) {
        this.strLongitud = strLongitud;
    }

    public String getStrDescripcion() {
        return strDescripcion;
    }

    public void setStrDescripcion(String strDescripcion) {
        this.strDescripcion = strDescripcion;
    }

    public String getStrNombreGrupoPersonal() {
        return strNombreGrupoPersonal;
    }

    public void setStrNombreGrupoPersonal(String strNombreGrupoPersonal) {
        this.strNombreGrupoPersonal = strNombreGrupoPersonal;
    }
}
