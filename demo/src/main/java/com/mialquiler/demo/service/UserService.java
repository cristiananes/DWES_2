package com.mialquiler.demo.service;

import com.mialquiler.demo.entity.Usuario;
import com.mialquiler.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ModelAttribute;

import java.util.Optional;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;


    //cambiar rol a propietario
    public Usuario cambiarRolToPropietario(Usuario usuario){
    usuario.setRol("PROPIETARIO");
    userRepository.save(usuario);
        return usuario;
    }
    //cambiar rol a inquilino
    public Usuario cambiarRolToInquilino(Usuario usuario){
        usuario.setRol("INQUILINO");
        userRepository.save(usuario);
        return usuario;
    }

    //comprobar si existe un usuario a la hora de crear
    public Boolean CompraracionUsername(Usuario usuario){
       Optional<Usuario> user= userRepository.findById(usuario.getUsername());
        if (user.isEmpty()){
            userRepository.save(usuario);
            return true;
        }else {
            return false;
        }
    }

    public long contarUsuarios() {
        return userRepository.count();
    }
//actualizacion de usuario

//eliminacion logica del usuario

//busqueda por id

}

