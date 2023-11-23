package br.com.erickmarques.rest;

import br.com.erickmarques.domain.model.User;
import br.com.erickmarques.domain.model.repository.UserRepository;
import br.com.erickmarques.rest.dto.UserRequest;
import br.com.erickmarques.rest.dto.UserResponse;
import br.com.erickmarques.rest.mapper.ModelMapperConverter;
import io.quarkus.hibernate.orm.panache.PanacheQuery;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import lombok.AllArgsConstructor;

@Path("/users")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
@AllArgsConstructor
public class UserResource {

    private UserRepository repository;

    @POST
    @Transactional
    public Response createUser(@Valid UserRequest userRequest ){
        var user = dtoToEntity(userRequest);

        repository.persist(user);

        return Response
                .status(Response.Status.CREATED.getStatusCode())
                .entity(entityToDto(user))
                .build();
    }

    @GET
    public Response listAllUsers(){
        PanacheQuery<User> query = repository.findAll();
        return Response
                .ok(ModelMapperConverter.parseListObjects(query.list(), UserResponse.class))
                .build();
    }

    @DELETE
    @Path("{id}")
    @Transactional
    public Response deleteUser( @PathParam("id") Long id){
        User user = repository.findById(id);

        if(user != null){
            repository.delete(user);
            return Response.ok().build();
        }

        return Response.status(Response.Status.NOT_FOUND).build();
    }

    @PUT
    @Path("{id}")
    @Transactional
    public Response updateUser( @PathParam("id") Long id, UserRequest userRequest ){
        User user = repository.findById(id);

        if(user != null){
            user.setName(userRequest.getName());
            user.setAge(userRequest.getAge());

            return Response.noContent().build();
        }

        return Response.status(Response.Status.NOT_FOUND).build();
    }

    private User dtoToEntity(UserRequest dto){
        return ModelMapperConverter.parseObject(dto, User.class);
    }

    private UserResponse entityToDto(User user){
        return ModelMapperConverter.parseObject(user, UserResponse.class);
    }
}
