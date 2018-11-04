package develop.app.luismiguelpaz.movistarapp.model;

import com.google.android.gms.maps.model.LatLng;

public class DataBaseRow {

    private String direccion;
    private String departamento;
    private String mobrelocalidad;
    private String barrio;
    private String direccionCaja;
    private LatLng coordenadaCaja;
    private LatLng coordenadaUsuario;
    private String predio;


    public DataBaseRow() {
    }

    public DataBaseRow(String direccion, String departamento, String mobrelocalidad, String barrio, String direccionCaja, LatLng coordenadaCaja, LatLng coordenadaUsuario) {
        this.direccion = direccion;
        this.departamento = departamento;
        this.mobrelocalidad = mobrelocalidad;
        this.barrio = barrio;
        this.direccionCaja = direccionCaja;
        this.coordenadaCaja = coordenadaCaja;
        this.coordenadaUsuario = coordenadaUsuario;
        if (direccion!=null){
            predio = IdentificadorPredios.getPredio(direccion);
        }

    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getDepartamento() {
        return departamento;
    }

    public void setDepartamento(String departamento) {
        this.departamento = departamento;
    }

    public String getMobrelocalidad() {
        return mobrelocalidad;
    }

    public void setMobrelocalidad(String mobrelocalidad) {
        this.mobrelocalidad = mobrelocalidad;
    }

    public String getBarrio() {
        return barrio;
    }

    public void setBarrio(String barrio) {
        this.barrio = barrio;
    }

    public String getDireccionCaja() {
        return direccionCaja;
    }

    public void setDireccionCaja(String direccionCaja) {
        this.direccionCaja = direccionCaja;
    }

    public LatLng getCoordenadaCaja() {
        return coordenadaCaja;
    }

    public void setCoordenadaCaja(LatLng coordenadaCaja) {
        this.coordenadaCaja = coordenadaCaja;
    }

    public LatLng getCoordenadaUsuario() {
        return coordenadaUsuario;
    }

    public void setCoordenadaUsuario(LatLng coordenadaUsuario) {
        this.coordenadaUsuario = coordenadaUsuario;
    }

    public String getPredio() {
        if (direccion!=null){
            predio = IdentificadorPredios.getPredio(direccion);
        }
        return predio;
    }

    public void setPredio(String predio) {
        this.predio = predio;
    }
}
