package com.Tienda.controller;

import com.Tienda.service.UsuarioService;
import com.Tienda.domain.Usuario;
import com.Tienda.service.FirebaseStorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Controller
@RequestMapping("/usuario")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @GetMapping("/listado")
    public String listado(Model model) {
        var usuarios = usuarioService.getUsuarios();
        model.addAttribute("usuarios", usuarios);
        model.addAttribute("totalUsuarios", usuarios.size());
        return "/usuario/listado";
    }

    @GetMapping("/nuevo")
    public String usuarioNuevo(Usuario usuario) {
        return "/usuario/modifica";
    }

    @Autowired
    private FirebaseStorageService firebaseStorageService;

@PostMapping("/guardar")
    public String usuarioGuardar(Usuario usuario,
            @RequestParam("imagenFile") MultipartFile imagenFile) {
        
        if (usuario.getIdUsuario() != null && usuario.getIdUsuario() > 0) {
            var temp = usuarioService.getUsuario(usuario);
            usuario.setPassword(temp.getPassword());
            usuario.setUsername(temp.getUsername());
            usuario.setRoles(temp.getRoles());
        } else {
            usuario.setPassword(new BCryptPasswordEncoder().encode(usuario.getPassword()));
        }       
        
        if (!imagenFile.isEmpty()) {
            usuarioService.save(usuario, false);
            usuario.setRutaImagen(
                    firebaseStorageService.cargaImagen(
                            imagenFile,
                            "usuario",
                            usuario.getIdUsuario()));
        }
        
        if(usuario.getRoles() == null) {
            usuarioService.save(usuario, true);
        } else {
            usuarioService.save(usuario, false);
        }        
        
        return "redirect:/usuario/listado";
    }

    @GetMapping("/eliminar/{idUsuario}")
    public String usuarioEliminar(Usuario usuario) {
        usuarioService.delete(usuario);
        return "redirect:/usuario/listado";
    }

    @GetMapping("/modificar/{idUsuario}")
    public String usuarioModificar(Usuario usuario, Model model) {
        usuario = usuarioService.getUsuario(usuario);
        model.addAttribute("usuario", usuario);
        return "/usuario/modifica";
    }
}
