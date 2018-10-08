package com.crudvideojuegos.model.usuario;

/*import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import static java.util.Collections.emptyList;
@Service
public class UsuarioServicio implements UserDetailsService {
    @Autowired
    private UsuarioFacade userInfoJpaRepository;

    public UsuarioServicio(UsuarioFacade applicationUserRepository) {
        this.userInfoJpaRepository = applicationUserRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Usuario applicationUser = userInfoJpaRepository.findUsuarioByNombre(username).get(0);
        if (applicationUser == null) {
            throw new UsernameNotFoundException(username);
        }
        return new User(applicationUser.getNombre(), applicationUser.getContrasenya(), applicationUser.getRole(), emptyList());
    }
}*/
