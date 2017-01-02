/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fmf.jersey;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import entities.Lieu;
import entities.Personne;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import org.codehaus.jackson.map.ObjectMapper;
import sessions.LieuFacadeLocal;
import sessions.PersonneFacadeLocal;

/**
 *
 * @author ilias
 */
//Path: http://localhost/<appln-folder-name>/login
@Path("/login")
public class Login {

    PersonneFacadeLocal personneFacade = lookupPersonneFacadeLocal();

    LieuFacadeLocal lieuFacade = lookupLieuFacadeLocal();

    // HTTP Get Method
    @GET
    // Path: http://localhost/<appln-folder-name>/login/dologin
    @Path("/dologin")
    // Produces JSON as response
    @Produces(MediaType.APPLICATION_JSON)
    // Query parameters are parameters: http://localhost/<appln-folder-name>/login/dologin?username=abc&password=xyz
    public String doLogin(@QueryParam("email") String uname, @QueryParam("motDePasse") String pwd, @QueryParam("lieu") String lieu) {
        String response = "";
        Personne P = null;
        Lieu L;
        Gson gson = new GsonBuilder()
                .setPrettyPrinting()
                .setDateFormat("MMM d, yyyy HH:mm:ss")
                .setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
                .create();

        P = personneFacade.findByMailAndPass(gson.fromJson(uname, String.class), gson.fromJson(pwd, String.class));
        if (P != null) {
        if (P.getLieu() != null) {
            Lieu lieuToRemove = P.getLieu();
            P.setLieu(null);
            personneFacade.edit(P);
            lieuFacade.remove(lieuToRemove);
        }

        L = gson.fromJson(lieu, Lieu.class);

        lieuFacade.create(L);

        P.setLieu(L);
        personneFacade.edit(P);

        
            response = gson.toJson(P);
        }

        return response;
    }

    private LieuFacadeLocal lookupLieuFacadeLocal() {
        try {
            Context c = new InitialContext();
            return (LieuFacadeLocal) c.lookup("java:global/FindMyFriendsWS/FindMyFriendsWS-ejb/LieuFacade!sessions.LieuFacadeLocal");
        } catch (NamingException ne) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "exception caught", ne);
            throw new RuntimeException(ne);
        }
    }

    private PersonneFacadeLocal lookupPersonneFacadeLocal() {
        try {
            Context c = new InitialContext();
            return (PersonneFacadeLocal) c.lookup("java:global/FindMyFriendsWS/FindMyFriendsWS-ejb/PersonneFacade!sessions.PersonneFacadeLocal");
        } catch (NamingException ne) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "exception caught", ne);
            throw new RuntimeException(ne);
        }
    }

    /**
     * Method to check whether the entered credential is valid
     *
     * @param uname
     * @param pwd
     * @return
     */
}
