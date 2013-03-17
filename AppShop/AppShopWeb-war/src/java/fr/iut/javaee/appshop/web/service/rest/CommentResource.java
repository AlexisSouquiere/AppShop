/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.iut.javaee.appshop.web.service.rest;

import fr.iut.javaee.appshop.commons.Comment;
import fr.iut.javaee.appshop.service.local.CommentServiceLocal;
import java.net.URI;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

/**
 *
 * @author Audrey
 */
@Path("comments")
@Stateless
public class CommentResource {
    
    @Context
    private UriInfo uriInfo;
    
    @EJB
    private CommentServiceLocal serviceComment;

    @Path("all")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Comment> getAllComments() {
        return serviceComment.findAll();
    } 
       
    @Path("comment/{id}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Comment getCommentsById(@PathParam("id") String id) {        
        return serviceComment.findCommentById(Integer.parseInt(id));        
    }
    
    @Path("app/{idApp}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Comment> getCommentsByIdApp(@PathParam("idApp") String id) {        
        return serviceComment.findCommentsByApplicationId(Integer.parseInt(id));        
    }
    
    @Path("user/{idUser}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Comment> getCommentsByIdUser(@PathParam("idUser") String id) {        
        return serviceComment.findCommentsByUserId(Integer.parseInt(id));        
    }
    
    @GET
    @Produces("text/plain")
    public String gettest() {
        return "Bienvenue sur le service rest de commentaire";
    } 
  
    @POST
    @Path("/post")
    @Consumes(MediaType.APPLICATION_JSON) 
    public Response newComment(Comment com){
        serviceComment.persist(com);    
        URI commUri = uriInfo.getAbsolutePathBuilder().path(com.getCommentId().toString()).build();
        return Response.created(commUri).build();
    }

    @PUT
    @Path("/put")
    @Consumes(MediaType.APPLICATION_JSON)     
    public Response editComment(Comment com){
        serviceComment.persist(com);
        URI commUri = uriInfo.getAbsolutePathBuilder().path(com.getCommentId().toString()).build();
        return Response.created(commUri).build();
    }

    @DELETE
    @Path("commentdelete/{id}")
    public Response deleteComment(@PathParam("id") String comId){
        Comment com = serviceComment.findCommentById(Integer.parseInt(comId));
        serviceComment.remove(com);  
        URI commUri = uriInfo.getAbsolutePathBuilder().path(com.getCommentId().toString()).build();
        return Response.created(commUri).build();
    }
    
}