package br.com.ifsudestemg.resource;

import java.util.List;

import br.com.ifsudestemg.dao.DAO;
import br.com.ifsudestemg.model.Museu;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

@Path("museus")
public class MuseuResource {

    private DAO<Museu> dao = new DAO<Museu>(Museu.class);

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Museu> buscaTodos() {
        return dao.listaTodos();
    }
    
    @GET
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Museu buscaPorId(@PathParam("id") Long id) {
        return dao.buscaPorId(id);
    }
}