package br.com.erickmarques.rest.filters;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

import javax.crypto.SecretKey;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import jakarta.annotation.Priority;
import jakarta.ws.rs.Priorities;
import jakarta.ws.rs.container.ContainerRequestContext;
import jakarta.ws.rs.container.ContainerRequestFilter;
import jakarta.ws.rs.core.HttpHeaders;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.Provider;
import org.eclipse.microprofile.config.inject.ConfigProperty;


@Provider
@Authorize
@Priority(Priorities.AUTHENTICATION)
public class AuthorizeFilter implements ContainerRequestFilter {

    //alterar em breve
    private final SecretKey KEY = Keys
            .hmacShaKeyFor("###################ErickMarquesAndradeBackEnd###################"
            .getBytes(StandardCharsets.UTF_8));

    @Override
    public void filter(ContainerRequestContext requestContext) throws IOException {
        String authorizationHeader = requestContext.getHeaderString(HttpHeaders.AUTHORIZATION);

        try {
            String token = authorizationHeader.substring("Bearer".length()).trim();

            System.out.println("token erick = " + token);

            Jwts.parserBuilder().setSigningKey(KEY).build().parseClaimsJws(token);
        } catch (Exception e) {
            requestContext.abortWith(Response.status(Response.Status.UNAUTHORIZED).build());
        }

    }

}