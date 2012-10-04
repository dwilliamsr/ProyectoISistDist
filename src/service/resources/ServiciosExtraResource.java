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

import service.beans.Servicioextra;
import service.storage.ServicioextraStore;

@Path("/serviciosextra")
public class ServiciosextraResource {
	
	@Context
	UriInfo uriInfo;
	@Context
	Request request;

	@GET
	@Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
	public List<Servicioextra> getServiciosextra() {
		List<Servicioextra> serviciosextra = new ArrayList<Servicioextra>();
		serviciosextra.addAll( ServicioextraStore.getStore().values() );
		return serviciosextra;
	}

	@GET
	@Path("count")
	@Produces(MediaType.TEXT_PLAIN)
	public String getCount() {
		int count = ServicioextraStore.getStore().size();
		return String.valueOf(count);
	}

	@POST
	@Produces(MediaType.TEXT_HTML)
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	public void newServicioextra(
@FormParam("id") String id,
@FormParam("nombre") String nombre,
@FormParam("servicio") String servicio			,@Context HttpServletResponse servletResponse
	) throws IOException {
		Servicioextra p = new Servicioextra(id,nombre,servicio);
		ServicioextraStore.getStore().put(id, p);

		URI uri = uriInfo.getAbsolutePathBuilder().path(id).build();
		Response.created(uri).build();

		servletResponse.sendRedirect("../pages/new_servicioextra.html");
	}

	@Path("{servicioextra}")
	public ServicioextraResource getServicioextra(
	@PathParam("servicioextra") String servicioextra ) {
		return new ServicioextraResource(uriInfo, request, servicioextra );
	}

}
