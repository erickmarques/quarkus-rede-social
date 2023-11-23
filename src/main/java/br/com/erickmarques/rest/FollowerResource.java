package br.com.erickmarques.rest;

import br.com.erickmarques.domain.model.Follower;
import br.com.erickmarques.domain.model.User;
import br.com.erickmarques.domain.model.repository.FollowerRepository;
import br.com.erickmarques.domain.model.repository.UserRepository;
import br.com.erickmarques.rest.dto.FollowersPerUserResponse;
import br.com.erickmarques.rest.dto.FollowerRequest;
import br.com.erickmarques.rest.dto.FollowerResponse;
import br.com.erickmarques.rest.mapper.ModelMapperConverter;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import lombok.AllArgsConstructor;

import java.util.stream.Collectors;

@Path("/users/{userId}/followers")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
@AllArgsConstructor
public class FollowerResource {

    private FollowerRepository repository;
    private UserRepository userRepository;

    @PUT
    @Transactional
    public Response follow( @PathParam("userId") Long userId,
                            @Valid FollowerRequest followerRequest ) {

        User user = userRepository.findById(userId);
        User follower = userRepository.findById(followerRequest.getFollewerId());

        if (userId.equals(followerRequest.getFollewerId())){
            return Response.status(Response.Status.CONFLICT).build();
        }

        if (user == null || follower == null){
            return Response.status(Response.Status.NOT_FOUND).build();
        }

        if (!repository.isFollow(follower, user)){
            var entity = dtoToEntity(followerRequest);

            entity.setUser(user);
            entity.setFollower(follower);

            repository.persist(entity);
        }


        return Response
                .status(Response.Status.NO_CONTENT)
                .build();
    }

    @GET
    public Response listPosts( @PathParam("userId") Long userId ){

        User user = userRepository.findById(userId);

        if (user == null){
            return Response.status(Response.Status.NOT_FOUND).build();
        }

        var list = repository.findByUser(userId);
        var listFollowers = list.stream().map( FollowerResponse::new ).collect(Collectors.toList());

        FollowersPerUserResponse response = new FollowersPerUserResponse();

        response.setFollewersCount(list.size());
        response.setContent(listFollowers);

        return Response.ok(response).build();
    }

    @DELETE
    @Transactional
    public Response unFollow( @PathParam("userId") Long userId,
                              @QueryParam("followerId") Long followerId) {

        User user = userRepository.findById(userId);
        User follower = userRepository.findById(followerId);

        if (user == null || follower == null){
            return Response.status(Response.Status.NOT_FOUND).build();
        }

        repository.deleteByFollowerAndUser(followerId, userId);

        return Response
                .status(Response.Status.NO_CONTENT)
                .build();
    }

    private Follower dtoToEntity(FollowerRequest dto){
        return ModelMapperConverter.parseObject(dto, Follower.class);
    }

    private FollowerResponse entityToDto(Follower follower){
        return ModelMapperConverter.parseObject(follower, FollowerResponse.class);
    }
}
