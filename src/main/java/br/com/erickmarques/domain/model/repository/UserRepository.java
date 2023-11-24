package br.com.erickmarques.domain.model.repository;

import br.com.erickmarques.domain.model.User;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import io.quarkus.panache.common.Parameters;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class UserRepository implements PanacheRepository<User> {

    public User findByPerEmailAndPass(String email, String password){
        var params = Parameters
                .with("email", email)
                .and("password", password);

        return find("email = :email and password = :password", params).firstResult();
    }
}
