package com.bd.siv.servicios;

import java.util.List;

import com.bd.siv.modelo.Usuario;

public interface LoginServicio {
	public Usuario obtenerLogin(Usuario usuario);
    public void agregarUsuarioLogin(Usuario usuario);
    public List<Usuario> obtenerUsuarios();
    public Usuario obtenerUsuario(Integer id);
    public Usuario buscarPorID(Integer id);
    public void eliminarUsuario(Integer id);
    public void actualizarUsuario(Usuario usuario);
}
