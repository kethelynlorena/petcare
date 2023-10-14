package com.itb.tcc.inf2a.petcare.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Base64;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.itb.tcc.inf2a.petcare.model.entity.Usuario;
import com.itb.tcc.inf2a.petcare.model.enums.EnumTipoUsuario;
import com.itb.tcc.inf2a.petcare.repository.UsuarioRepository;

@Controller
@RequestMapping("/petcare/usuario")
public class UsuarioController {
	
	@Autowired // Injeção de depêndencia (o objeto será criado automaticamente pelo spring)
	private UsuarioRepository usuarioRepository;

	
	// Carregar o formulário de Cadastro do Usuário
	
    public UsuarioRepository getUsuarioRepository() {
		return usuarioRepository;
	}

	public void setUsuarioRepository(UsuarioRepository usuarioRepository) {
		this.usuarioRepository = usuarioRepository;
	}

	@GetMapping("/novo-usuario")
    public String novoUsuario(Usuario usuario, Model model) {
    	model.addAttribute("usuario", usuario);
    	return "formulario-cadastro";
    }
    
    // Inserir usuário
    @PostMapping("/add-usuario")
    public String addUsuario(Usuario usuario, BindingResult result, Model model) {
    	
    	usuario.setCodStatusUsuario(true);
    	
    	usuarioRepository.save(usuario);
    	
    	return "redirect:/petcare/usuario/login";
   
    }
    
    @GetMapping("/login") 
    public String showFormLogin() {
    	
    	return "fazer-login";
    }
    
    @PostMapping("/login")
	public String autenticarUsuario(Usuario usuario, Model model) {
		Usuario acesso = this.usuarioRepository.acessar(usuario.getEmail(), usuario.getSenha());
		if (acesso != null){
			return "fazer-login";
		}
		return "home";
		
    }
    
    @GetMapping("/editar-usuario/{id}")
	public String showUpdateForm(@PathVariable Long id, Model model) {
    	Usuario usuario = usuarioRepository.findById(id)
				.orElseThrow(() -> new IllegalArgumentException("ID de usuário inválido: " + id));
	

		model.addAttribute("usuario", usuario);
		model.addAttribute("tipos", EnumTipoUsuario.values());
		
		return "editar-perfil";
	}
    
    @PostMapping("/atualizar-usuario/{id}")
	public String updateUser(
            @RequestParam(required = false) MultipartFile file, @PathVariable int id,
            @ModelAttribute Usuario usuario, BindingResult result) {

		if (result.hasErrors()) {
			usuario.setId(id);
			
			usuario.setNome(usuario.getNome());
			
			usuario.setEmail(usuario.getEmail());
			
			usuario.setCpf(usuario.getCpf());
			
			usuario.setTelefone(usuario.getTelefone());
			
		    usuario.setData_nascimento(usuario.getData_nascimento());    
		    
		    usuario.setGenero(usuario.getGenero());
		    
		    usuario.setSenha(usuario.getSenha());
		    
			return "editar-perfil";
		}

		usuarioRepository.save(usuario);
		return "redirect:/petcare/usuario/home";
	}
    
    @PostMapping
	public String upload(@RequestParam MultipartFile file, RedirectAttributes redirectAttributes) {
		redirectAttributes.addFlashAttribute("message",
				"You successfully uploaded " + file.getOriginalFilename() + "!");

		return "redirect:/home";
	}
    
    @GetMapping("/consulta-b")
    public String consultaUsuarios(Model model) {
    	List<Usuario> listaUsuarios = new ArrayList<>();
		listaUsuarios = usuarioRepository.findAll();
		model.addAttribute("usuario", listaUsuarios);
		return "consulta";
    }
    
    List<String> tipos = carregarAtributos();
    
    public List<String> carregarAtributos() {
        List<EnumTipoUsuario> lista = Arrays.asList(EnumTipoUsuario.values());
        List<String> retorno = new ArrayList<String>();
        for (int i = 0; i < lista.size(); i++) {
            retorno.add(lista.get(i).toString());
        }
        return retorno;
    }
    
    @PostMapping("/excluir-usuario/{id}")
    public String excluirUsuario(@PathVariable Long id) {
        usuarioRepository.deleteById(id);
        return "redirect:/petcare/usuario/consulta";
    }
}
