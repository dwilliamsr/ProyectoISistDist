package service.resources;

import java.io.IOException;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Request;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

import service.beans.Estadoaveria;
import service.storage.EstadoaveriaStore;

@Path("/estadosaveria")
public class EstadosaveriaResource {
	
	@Context
	UriInfo uriInfo;
	@Context
	Request request;

	@GET
	@Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
	public List<Estadoaveria> getEstadosaveria() {
		List<Estadoaveria> estadosaveria = new ArrayList<Estadoaveria>();
		estadosaveria.addAll( EstadoaveriaStore.getStore().values() );
		return estadosaveria;
	}

	@GET
	@Path("count")
	@Produces(MediaType.TEXT_PLAIN)
	public String getCount() {
		int count = EstadoaveriaStore.getStore().size();
		return String.valueOf(count);
	}

	@POST
	@Produces(MediaType.TEXT_HTML)
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	public void newEstadoaveria(
@FormParam("id") String id,
@FormParam("nombre") String nombre,
@FormParam("averia") String averia			,@Context HttpServletResponse servletResponse
	) throws IOException {
		Estadoaveria p = new Estadoaveria(id,nombre,averia);
		EstadoaveriaStore.getStore().put(id, p);

		URI uri = uriInfo.getAbsolutePathBuilder().path(id).build();
		Response.created(uri).build();

		servletResponse.sendRedirect("../pages/new_estadoaveria.html");
	}

	@Path("{estadoaveria}")
	public EstadoaveriaResource getEstadoaveria(
	@PathParam("estadoaveria") String estadoaveria ) {
		return new EstadoaveriaResource(uriInfo, request, estadoaveria );
	}

}
