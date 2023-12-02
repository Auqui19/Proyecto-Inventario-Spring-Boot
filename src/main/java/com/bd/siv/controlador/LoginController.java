package com.bd.siv.controlador;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.support.SessionStatus;

import com.bd.siv.modelo.Usuario;
import com.bd.siv.repositorio.UsuarioRepositorio;
import com.bd.siv.servicios.LoginServicio;
import com.bd.siv.servicios.UsuarioValidation;
import com.bd.siv.util.UsuarioExporterPDF;
import com.lowagie.text.DocumentException;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;

@Controller
@RequestMapping("/app")
public class LoginController {

	@Autowired
	private final UsuarioRepositorio usuariorepositorio;

	@Autowired
	private final LoginServicio loginServicio;

	public LoginController(LoginServicio loginServicio) {
		this.usuariorepositorio = null;
		this.loginServicio = loginServicio;
	}

	@InitBinder
	protected void initBinder(WebDataBinder binder) {
		binder.setValidator(new UsuarioValidation());
	}

	@GetMapping("/login")
	public String loginUser(Model model) {
		model.addAttribute("usuario", new Usuario());
		return "login";
	}

	@GetMapping("/exportarPDFUsuario")
	public void exportarListadoDeProductos(HttpServletResponse response) throws DocumentException, IOException {
		response.setContentType("application/pdf");
		DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");
		String fechaActual = dateFormatter.format(new Date());
		String cabecera = "Content-Disposition";
		String valor = "attachment; filename=ListaDeUsuarios_" + fechaActual + ".pdf";
		response.setHeader(cabecera, valor);
		List<Usuario> usuarios = (List<Usuario>) usuariorepositorio.findAll();
		UsuarioExporterPDF exporter = new UsuarioExporterPDF(usuarios);
		exporter.exportar(response);
	}

	@GetMapping("/home")
	public String mostrarHome(HttpSession session, Model model) {
		List<Usuario> usuarioList = loginServicio.obtenerUsuarios();
		model.addAttribute("usuarioList", usuarioList);
		return "home";
	}

	@PostMapping("/verificarLogin")
	public String verificarLogin(Model model, @ModelAttribute Usuario usuarioObj) {
		System.out.println(usuarioObj);
		if (usuarioObj.getUsuario().trim().length() != 0 && usuarioObj.getPassword().trim().length() != 0) {
			Usuario usuarioEncontrado = loginServicio.obtenerLogin(usuarioObj);
			if (usuarioEncontrado != null) {
				model.addAttribute("usuario", usuarioEncontrado);
				model.addAttribute("userObj", usuarioEncontrado);
				return "home";
			} else {
				model.addAttribute("falla", "usuario o contraseña incorrectos");
			}
		} else {
			model.addAttribute("vacio", "falta un dato: usuario o contraseña vacíos");
		}
		model.addAttribute("usuario", usuarioObj);
		return "redirect:/app/login";
	}

	@GetMapping("/agregarUsuario")
	public String agregarUsuario(Model model) {
		model.addAttribute("usuario", new Usuario());
		return "usuarios/agregar_usuario";
	}

	@PostMapping("/guardarUsuario")
	public String guardarUsuario(@ModelAttribute Usuario objusuario) {
		loginServicio.agregarUsuarioLogin(objusuario);
		return "redirect:/app/verUsuarios";
	}

	@GetMapping("/verUsuarios")
	public String verUsuarios(Model model) {
		List<Usuario> usuarioList = loginServicio.obtenerUsuarios();
		model.addAttribute("usuarioList", usuarioList);

		return "usuarios/ver_usuarios";
	}

	@GetMapping("/editarUsuario/{id}")
	public String editarUsuario(@PathVariable Integer id, Model model) {
		Usuario usuario = loginServicio.buscarPorID(id);
		model.addAttribute("editusuarioObj", usuario);
		return "usuarios/editar_usuario";
	}

	@PostMapping("/modificarUsuario")
	public String modificarUsuario(Model model, @Valid Usuario usuario, BindingResult result) {
		loginServicio.actualizarUsuario(usuario);
		return "redirect:/app/verUsuarios";
	}

	@GetMapping("/eliminarUsuario/{id}")
	public String eliminarUsuario(@PathVariable("id") Integer id, Model modelo) {
		loginServicio.eliminarUsuario(id);
		List<Usuario> usuarioList = loginServicio.obtenerUsuarios();
		modelo.addAttribute("listadoproductos", usuarioList);
		return "redirect:/app/verUsuarios";
	}

	@GetMapping("/salir")
	public String salir(HttpServletRequest request, SessionStatus status) {
		status.setComplete();
		HttpSession session = request.getSession(false);
		if (session != null) {
			session.invalidate();
		}
		return "redirect:/app/login";
	}
}