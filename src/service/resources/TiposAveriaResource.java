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

import service.beans.Tipoaveria;
import service.storage.TipoaveriaStore;

@Path("/tiposaveria")
public class TiposaveriaResource {
	
	@Context
	UriInfo uriInfo;
	@Context
	Request request;

	@GET
	@Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
	public List<Tipoaveria> getTiposaveria() {
		List<Tipoaveria> tiposaveria = new ArrayList<Tipoaveria>();
		tiposaveria.addAll( TipoaveriaStore.getStore().values() );
		return tiposaveria;
	}

	@GET
	@Path("count")
	@Produces(MediaType.TEXT_PLAIN)
	public String getCount() {
		int count = TipoaveriaStore.getStore().size();
		return String.valueOf(count);
	}

	@POST
	@Produces(MediaType.TEXT_HTML)
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	public void newTipoaveria(
@FormParam("id") String id,
@FormParam("nombre") String nombre,
@FormParam("averia") String averia			,@Context HttpServletResponse servletResponse
	) throws IOException {
		Tipoaveria p = new Tipoaveria(id,nombre,averia);
		TipoaveriaStore.getStore().put(id, p);

		URI uri = uriInfo.getAbsolutePathBuilder().path(id).build();
		Response.created(uri).build();

		servletResponse.sendRedirect("../pages/new_tipoaveria.html");
	}

	@Path("{tipoaveria}")
	public TipoaveriaResource getTipoaveria(
	@PathParam("tipoaveria") String tipoaveria ) {
		return new TipoaveriaResource(uriInfo, request, tipoaveria );
	}

}
