package br.com.ifsudestemg.resource;

import java.util.List;
import java.util.stream.Collectors;

import br.com.ifsudestemg.dao.DAO;
import br.com.ifsudestemg.model.Agendamento;
import br.com.ifsudestemg.model.PessoaAgendada;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("agendamentos")
public class AgendamentoResource {

    private DAO<Agendamento> dao = new DAO<Agendamento>(Agendamento.class);

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Agendamento> listar(@QueryParam("museu") Long museuId, @QueryParam("data") String data) {
        
        List<Agendamento> lista = dao.listaTodos();
        
        if (museuId != null) {
            lista = lista.stream()
                    .filter(a -> a.getMuseu().getId().equals(museuId))
                    .collect(Collectors.toList());
        }
        
        if (data != null && !data.isEmpty()) {
            lista = lista.stream()
                    .filter(a -> a.getDataVisita().toString().equals(data))
                    .collect(Collectors.toList());
        }
        
        return lista;
    }

    @GET
    @Path("{codigo}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response buscaPorCodigo(@PathParam("codigo") String codigo) {
        Agendamento agendamento = buscarAgendamentoPorCodigo(codigo);
        
        if (agendamento != null) {
            return Response.ok(agendamento).build();
        }
        return Response.status(Response.Status.NOT_FOUND).build();
    }

    @GET
    @Path("{codigo}/pessoas")
    @Produces(MediaType.APPLICATION_JSON)
    public Response listarPessoas(@PathParam("codigo") String codigo) {
        Agendamento agendamento = buscarAgendamentoPorCodigo(codigo);
        
        if (agendamento != null) {
            return Response.ok(agendamento.getPessoas()).build();
        }
        return Response.status(Response.Status.NOT_FOUND).build();
    }

    @DELETE
    @Path("{codigo}")
    @Produces(MediaType.TEXT_PLAIN)
    public Response cancelar(@PathParam("codigo") String codigo) {
        Agendamento agendamento = buscarAgendamentoPorCodigo(codigo);
        
        if (agendamento != null) {
            dao.remove(agendamento);
            return Response.ok("Agendamento cancelado com sucesso!").build();
        }
        return Response.status(Response.Status.NOT_FOUND).entity("Agendamento não encontrado.").build();
    }

    private Agendamento buscarAgendamentoPorCodigo(String codigo) {
        return dao.listaTodos().stream()
                .filter(a -> a.getCodigoConfirmacao().equalsIgnoreCase(codigo))
                .findFirst()
                .orElse(null);
    }
}