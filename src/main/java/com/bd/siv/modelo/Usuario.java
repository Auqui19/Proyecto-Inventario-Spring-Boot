package com.bd.siv.modelo;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="usuario")
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idusuario")
    private Integer idusuario;
    
    @Column(name = "nombreusuario")
    private String nombreusuario;
 
    @Column(name = "usuario")
    private String usuario;

    @Column(name = "password")
    private String password;

    // constructores, getters y setters
    
    public Usuario() {
        // Constructor vacío necesario para JPA
    }

    public Usuario(String nombreusuario, String usuario, String password) {
        this.nombreusuario = nombreusuario;
        this.usuario = usuario;
        this.password = password;
    }

    // Getters y Setters

    public Integer getIdusuario() {
        return idusuario;
    }

    public void setIdusuario(Integer idusuario) {
        this.idusuario = idusuario;
    }

    public String getNombreusuario() {
        return nombreusuario;
    }

    public void setNombreusuario(String nombreusuario) {
        this.nombreusuario = nombreusuario;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
