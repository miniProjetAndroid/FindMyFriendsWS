/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fmf.jersey;

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
import sessions.PersonneFacadeLocal;

/**
 *
 * @author mkass
 */
@Path("/register")

public class Register {

    PersonneFacadeLocal personneFacade = lookupPersonneFacadeLocal();

   

    
    // HTTP Get Method
    @GET
    // Path: http://localhost/<appln-folder-name>/register/doregister
    @Path("/doregister")  
    // Produces JSON as response
    @Produces(MediaType.APPLICATION_JSON) 
    // Query parameters are parameters: http://localhost/<appln-folder-name>/register/doregister?name=pqrs&username=abc&password=xyz
    public String doLogin(@QueryParam("personne") String personne){
        String response = "";
        //System.out.println("Inside doLogin "+uname+"  "+pwd);
        ObjectMapper mapper = new ObjectMapper();
        int retCode;
        Personne P ;
        Lieu L;
        try {
            P = mapper.readValue(personne, Personne.class);
            L = P.getLieu();
            retCode = registerUser(P);
      
        if(retCode == 0){
            response = Utility.constructJSON("register",true);
        }else {
            response = Utility.constructJSON("register",false, "Cet E-mail est déjà utilisé par un utilisateur enregistré");
        }
          } catch (IOException ex) {
            Logger.getLogger(Register.class.getName()).log(Level.SEVERE, null, ex);
        }
        return response;
 
    }
 
    private int registerUser(Personne P){
        System.out.println("Inside checkCredentials");
        int result = 1;
        
           try{
                personneFacade.create(P);
                    System.out.println("RegisterUSer if");
                    result = 0;
           } catch (Exception  e){
                        System.out.println("user not registered");
                    result = 1;
                    }
                return result;
    }

    private PersonneFacadeLocal lookupPersonneFacadeLocal() {
        try {
            Context c = new InitialContext();
            return (PersonneFacadeLocal) c.lookup("java:global/ServiceWebAndroid/ServiceWebAndroid-ejb/PersonneFacade!sessions.PersonneFacadeLocal");
        } catch (NamingException ne) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "exception caught", ne);
            throw new RuntimeException(ne);
        }
    } 
}
