package com.Xoot.CreditoParaTi.services.interfaces;

import com.Xoot.CreditoParaTi.entity.Usuario;

import java.util.List;

public interface IUserboardService {
        public Usuario findById(Integer id);

        public Usuario save(Usuario user);

        public List<Usuario> findAllActive();

        public Usuario findByUsername(String username);

        public Usuario findByemail(String email);

        public Usuario Login(String userName, String password);
}
