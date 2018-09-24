/*
package com.crudvideojuegos.model.usuario;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Service
public class UsuarioServicio implements UserDetailsService {
    @Autowired
    private UsuarioFacade userInfoJpaRepository;
    @Override
    public UserDetails loadUserByUsername(String username)
            throws UsernameNotFoundException {
        Usuario user = userInfoJpaRepository.findUsuarioByNombre(username).get(0);
        if (user == null) {
            throw new UsernameNotFoundException(
                    "Opps! user not found with user-name: " + username);
        }
        return new org.springframework.security.core.userdetails.User(
                user.getNombre(), "{noop}"+user.getContrasenya(),
                getAuthorities(user));
    }
    private Collection<GrantedAuthority> getAuthorities(Usuario user) {
        List<GrantedAuthority> authorities = new ArrayList<>();
        authorities = AuthorityUtils.createAuthorityList(user.getRole());
        return authorities;
    }
}
*/