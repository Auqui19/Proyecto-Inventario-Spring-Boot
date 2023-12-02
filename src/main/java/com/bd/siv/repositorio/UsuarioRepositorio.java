package com.bd.siv.repositorio;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.bd.siv.modelo.Usuario;


public interface UsuarioRepositorio extends CrudRepository<Usuario, Integer>{
	
	Usuario findFirstByIdusuario(Integer idusuario);
	// Consulta personalizada
    @Query("SELECT u FROM Usuario u WHERE u.usuario = :usuario AND u.password = :password")
    Usuario findByUsuarioAndContrasena(@Param("usuario") String usuario, @Param("password") String password);

}
