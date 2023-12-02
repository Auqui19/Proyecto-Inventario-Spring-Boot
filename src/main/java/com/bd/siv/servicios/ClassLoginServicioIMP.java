package com.bd.siv.servicios;

import java.util.List;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bd.siv.modelo.Usuario;
import com.bd.siv.repositorio.UsuarioRepositorio;

@Service
public class ClassLoginServicioIMP implements LoginServicio {
	
	@Autowired
	private UsuarioRepositorio usuarioRepositorio;

	@Override
	public Usuario obtenerLogin(Usuario usuario) {
		 return usuarioRepositorio.findByUsuarioAndContrasena(usuario.getUsuario(), usuario.getPassword());
	}

	@Override
	public void agregarUsuarioLogin(Usuario usu) {
		usuarioRepositorio.save(usu);
	}

	@Override
	public List<Usuario> obtenerUsuarios() {
		 return (List<Usuario>) usuarioRepositorio.findAll();
	}

	@Override
	public void actualizarUsuario(Usuario usu) {
		Usuario usuarioExistente = usuarioRepositorio.findById(usu.getIdusuario()).orElse(null);
		if (usuarioExistente != null) {
			usuarioExistente.setNombreusuario(usu.getNombreusuario());
			usuarioExistente.setPassword(usu.getPassword());
			usuarioExistente.setUsuario(usu.getUsuario());
	        usuarioRepositorio.save(usuarioExistente);
	    } else {
	        throw new RuntimeException("No se puede actualizar. El usuario no existe.");
	    }
	}

	@Override
	public Usuario obtenerUsuario(Integer id) {
		Optional<Usuario> usuarioOptional = usuarioRepositorio.findById(id);
        return usuarioOptional.orElse(null);
	}

	@Override
	public void eliminarUsuario(Integer id) {
		usuarioRepositorio.deleteById(id);	
	}

	@Override
	public Usuario buscarPorID(Integer id) {
		return usuarioRepositorio.findById(id).orElse(null);
	}
}
