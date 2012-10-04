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

import service.beans.Tipovivienda;
import service.storage.TipoviviendaStore;

@Path("/tiposvivienda")
public class TiposviviendaResource {
	
	@Context
	UriInfo uriInfo;
	@Context
	Request request;

	@GET
	@Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
	public List<Tipovivienda> getTiposvivienda() {
		List<Tipovivienda> tiposvivienda = new ArrayList<Tipovivienda>();
		tiposvivienda.addAll( TipoviviendaStore.getStore().values() );
		return tiposvivienda;
	}

	@GET
	@Path("count")
	@Produces(MediaType.TEXT_PLAIN)
	public String getCount() {
		int count = TipoviviendaStore.getStore().size();
		return String.valueOf(count);
	}

	@POST
	@Produces(MediaType.TEXT_HTML)
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	public void newTipovivienda(
@FormParam("id") String id,
@FormParam("nombre ") String nombre ,
@FormParam("servicio") String servicio			,@Context HttpServletResponse servletResponse
	) throws IOException {
		Tipovivienda p = new Tipovivienda(id,nombre ,servicio);
		TipoviviendaStore.getStore().put(id, p);

		URI uri = uriInfo.getAbsolutePathBuilder().path(id).build();
		Response.created(uri).build();

		servletResponse.sendRedirect("../pages/new_tipovivienda.html");
	}

	@Path("{tipovivienda}")
	public TipoviviendaResource getTipovivienda(
	@PathParam("tipovivienda") String tipovivienda ) {
		return new TipoviviendaResource(uriInfo, request, tipovivienda );
	}

}
