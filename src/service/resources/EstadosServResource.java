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

import service.beans.Estadoserv;
import service.storage.EstadoservStore;

@Path("/estadosserv")
public class EstadosservResource {
	
	@Context
	UriInfo uriInfo;
	@Context
	Request request;

	@GET
	@Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
	public List<Estadoserv> getEstadosserv() {
		List<Estadoserv> estadosserv = new ArrayList<Estadoserv>();
		estadosserv.addAll( EstadoservStore.getStore().values() );
		return estadosserv;
	}

	@GET
	@Path("count")
	@Produces(MediaType.TEXT_PLAIN)
	public String getCount() {
		int count = EstadoservStore.getStore().size();
		return String.valueOf(count);
	}

	@POST
	@Produces(MediaType.TEXT_HTML)
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	public void newEstadoserv(
@FormParam("id") String id,
@FormParam("nombre") String nombre,
@FormParam("servicio") String servicio			,@Context HttpServletResponse servletResponse
	) throws IOException {
		Estadoserv p = new Estadoserv(id,nombre,servicio);
		EstadoservStore.getStore().put(id, p);

		URI uri = uriInfo.getAbsolutePathBuilder().path(id).build();
		Response.created(uri).build();

		servletResponse.sendRedirect("../pages/new_estadoserv.html");
	}

	@Path("{estadoserv}")
	public EstadoservResource getEstadoserv(
	@PathParam("estadoserv") String estadoserv ) {
		return new EstadoservResource(uriInfo, request, estadoserv );
	}

}
