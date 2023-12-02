package com.bd.siv.controlador;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;


import com.bd.siv.repositorio.VentasRepositorio;


@Controller
@RequestMapping(path = "/ventas")
public class VentasController {

	@Autowired
	VentasRepositorio ventasRepository;

	@GetMapping(value = "/")
	public String mostrarVentas(Model model) {
		model.addAttribute("ventas", ventasRepository.findAll());
		return "ventas/ver_ventas";
	}

	
}