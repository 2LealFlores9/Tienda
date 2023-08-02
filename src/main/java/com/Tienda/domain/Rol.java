/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.Tienda.domain;

import jakarta.persistence.*;
import java.io.Serializable;
import javax.validation.constraints.NotEmpty;
import lombok.Data;


@Entity
@Data
@Table(name = "rol")
public class Rol implements Serializable{
    
        private static final long serialVersionUID = 1L;
        
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        @Column(name="id_rol")
        private Long idRol;
        
        @NotEmpty
        private String nombre;
        
        @Column(name="id_usuario")
        private Long idUsuario;
    
}
