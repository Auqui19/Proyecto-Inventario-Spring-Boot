package com.bd.siv.repositorio;

import org.springframework.data.repository.CrudRepository;

import com.bd.siv.modelo.Producto;

public interface ProductosRepositorio extends CrudRepository<Producto, Integer>{
	Producto findFirstByCodigo(String codigo);
}
