package br.com.erickmarques.rest;

import br.com.erickmarques.domain.model.User;
import br.com.erickmarques.domain.model.repository.UserRepository;
import br.com.erickmarques.rest.dto.UserLogin;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.eclipse.microprofile.config.inject.ConfigProperty;

@Path("/oauth/token")
@AllArgsConstructor
@NoArgsConstructor
public class AuthResource {

    private UserRepository userRepository;

    //alterar em breve
    private final SecretKey KEY = Keys
            .hmacShaKeyFor("7f-j&CKk=coNzZc0y7_4obMP?#TfcYq%fcD0mDpenW2nc!lfGoZ|d?f&RNbDHUX6"
            .getBytes(StandardCharsets.UTF_8));

    @POST
    @Produces(MediaType.TEXT_PLAIN)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response token( UserLogin userLogin ) {
        try{
            User user = userRepository.findByPerEmailAndPass(userLogin.getEmail(), userLogin.getPassword());

            if(user != null) {
                String jwtToken = Jwts.builder()
                        .setSubject(userLogin.getEmail())
                        .setIssuer("localhost:8080")
                        .setIssuedAt(new Date())
                        .setExpiration(Date.from(LocalDateTime.now().plusMinutes(15L).atZone(ZoneId.systemDefault()).toInstant()))
                        .signWith(KEY, SignatureAlgorithm.HS512)
                        .compact();

                return Response.status(Response.Status.OK).entity(jwtToken).build();
            }
            else{
                return Response.status(Response.Status.UNAUTHORIZED).entity("Usuário e/ou senha inválidos").build();
            }
        }
        catch(Exception ex)
        {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(ex.getMessage()).build();
        }
    }
}
