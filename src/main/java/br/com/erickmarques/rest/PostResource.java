package br.com.erickmarques.rest;

import br.com.erickmarques.domain.model.Post;
import br.com.erickmarques.domain.model.User;
import br.com.erickmarques.domain.model.repository.FollowerRepository;
import br.com.erickmarques.domain.model.repository.PostRepository;
import br.com.erickmarques.domain.model.repository.UserRepository;
import br.com.erickmarques.rest.dto.PostRequest;
import br.com.erickmarques.rest.dto.PostResponse;
import br.com.erickmarques.rest.mapper.ModelMapperConverter;
import io.quarkus.hibernate.orm.panache.PanacheQuery;
import io.quarkus.panache.common.Sort;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import lombok.AllArgsConstructor;

@Path("/users/{userId}/posts")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
@AllArgsConstructor
public class PostResource {

    private PostRepository repository;
    private UserRepository userRepository;
    private FollowerRepository followerRepository;

    @POST
    @Transactional
    public Response createPost( @PathParam("userId") Long userId,
                                @Valid PostRequest postRequest ) {

        User user = userRepository.findById(userId);

        if (user == null){
            return Response.status(Response.Status.NOT_FOUND).build();
        }

        var post = dtoToEntity(postRequest);
        post.setUser(user);

        repository.persist(post);

        return Response
                .status(Response.Status.CREATED.getStatusCode())
                .entity(entityToDto(post))
                .build();
    }

    @GET
    public Response listPosts( @PathParam("userId") Long userId,
                               @HeaderParam("followerId") Long followerId){

        if (followerId == null){
            return Response
                    .status(Response.Status.BAD_REQUEST)
                    .build();
        }

        User user = userRepository.findById(userId);
        User follower = userRepository.findById(followerId);

        if (user == null || follower == null){
            return Response.status(Response.Status.NOT_FOUND).build();
        }

        if (!followerRepository.isFollow(follower, user)){
            return Response
                    .status(Response.Status.FORBIDDEN)
                    .build();
        }

        PanacheQuery<Post> query = repository.find("user",
                                                    Sort.by("dateCreate", Sort.Direction.Descending),
                                                    user);

        return Response
                .ok(ModelMapperConverter.parseListObjects(query.list(), PostResponse.class))
                .build();
    }

    private Post dtoToEntity(PostRequest dto){
        return ModelMapperConverter.parseObject(dto, Post.class);
    }

    private PostResponse entityToDto(Post post){
        return ModelMapperConverter.parseObject(post, PostResponse.class);
    }
}
