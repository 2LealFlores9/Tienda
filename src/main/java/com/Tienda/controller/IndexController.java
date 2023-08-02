package com.Tienda.controller;

import com.Tienda.dao.UsuarioDao;
import com.Tienda.domain.Usuario;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class IndexController {
    
    @Autowired
    UsuarioDao usuarioDao;
    
    @RequestMapping("/")
    public String page(Model model, HttpSession httpSession) {
//       // Obtener usuario loggeado
//       Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
//       
//       UserDetails user = null;
//       
//       if (principal instanceof UserDetails){
//       
//           Usuario usuario = usuarioDao.findByUsername(user.getUsername());
//           
//           httpSession.setAttribute("Email", usuario.getCorreo());
//       }
        return "index";
    }
    
}
