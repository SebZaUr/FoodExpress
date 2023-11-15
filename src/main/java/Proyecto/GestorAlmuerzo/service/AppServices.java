package Proyecto.GestorAlmuerzo.service;

import Proyecto.GestorAlmuerzo.Repository.AppRepository;
import Proyecto.GestorAlmuerzo.Repository.RoleRepository;
import Proyecto.GestorAlmuerzo.exceptions.GestorAlmuerzosAppException;
import Proyecto.GestorAlmuerzo.model.Role;
import Proyecto.GestorAlmuerzo.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.lang.reflect.InvocationTargetException;

@Service
public class AppServices {

    @Autowired
    private AppRepository appRepository;
    @Autowired
    private RoleRepository roleRepository;

    /**
     * Me devuelve el rol
     * 
     * @param id El nombre del rol
     * @return El rol seleccionado.
     */
    public Optional<Role> getRol(String id) {
        return roleRepository.findById(id);
    };

    /**
     * Me añade un nuevo rol
     * 
     * @param rol El nuevo rol que voy agregar.
     * @return El rol que agrege
     */
    public Role addRol(Role rol) {
        return roleRepository.save(rol);
    }

    /**
     * Me actualiza la información de un usuario.
     * 
     * @param user El usuario que voy actualizar.
     * @return El usuarío actualizado en la base de datos.
     */
    public Role updateRole(Role rol) {
        if (roleRepository.findById(rol.getId()) == null) {
            return roleRepository.save(rol);
        }

        return roleRepository.save(rol);
    }

    /**
     * Me obtiene todos los usuaríos en la base de datos.
     * 
     * @return La lista de los usuariós de la base de datos.
     */
    public List<Role> getAllRoles() {
        return roleRepository.findAll();
    }

    /**
     * Me elimina un usuarío.
     * 
     * @param id El id del usuarío que voy a eliminar.
     */
    public void deleteUser(String id) {
        roleRepository.deleteById(id);
    }
}
