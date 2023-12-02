package com.bd.siv.repositorio;

import org.springframework.data.repository.CrudRepository;
import com.bd.siv.modelo.Venta;

public interface VentasRepositorio extends CrudRepository<Venta, Integer>{
	
}
