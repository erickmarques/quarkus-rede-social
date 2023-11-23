package br.com.erickmarques.domain.model.repository;

import br.com.erickmarques.domain.model.Follower;
import br.com.erickmarques.domain.model.User;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import io.quarkus.panache.common.Parameters;
import jakarta.enterprise.context.ApplicationScoped;

import java.util.List;

@ApplicationScoped
public class FollowerRepository implements PanacheRepository<Follower> {

    public boolean isFollow(User follower, User user){

        var params = Parameters
                        .with("follower", follower)
                        .and("user", user);

        var query = find("follower = :follower and user = :user", params);

        var result = query.firstResultOptional();

        return result.isPresent();
    }

    public List<Follower> findByUser(Long userId){

        var query = find("user.id", userId);

        return query.list();
    }

    public void deleteByFollowerAndUser(Long followerId, Long userId){

        var params = Parameters
                .with("followerId", followerId)
                .and("userId", userId);

        delete("follower.id = :followerId and user.id = :userId", params);
    }
}
