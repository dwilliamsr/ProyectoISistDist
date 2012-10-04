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

import service.beans.Suscriptor;
import service.storage.SuscriptorStore;

@Path("/suscriptores")
public class SuscriptoresResource {
	
	@Context
	UriInfo uriInfo;
	@Context
	Request request;

	@GET
	@Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
	public List<Suscriptor> getSuscriptores() {
		List<Suscriptor> suscriptores = new ArrayList<Suscriptor>();
		suscriptores.addAll( SuscriptorStore.getStore().values() );
		return suscriptores;
	}

	@GET
	@Path("count")
	@Produces(MediaType.TEXT_PLAIN)
	public String getCount() {
		int count = SuscriptorStore.getStore().size();
		return String.valueOf(count);
	}

	@POST
	@Produces(MediaType.TEXT_HTML)
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	public void newSuscriptor(
@FormParam("id") String id,
@FormParam("nombre") String nombre,
@FormParam("identificacion") String identificacion,
@FormParam("telefono") String telefono,
@FormParam("direccion") String direccion			,@Context HttpServletResponse servletResponse
	) throws IOException {
		Suscriptor p = new Suscriptor(id,nombre,identificacion,telefono,direccion);
		SuscriptorStore.getStore().put(id, p);

		URI uri = uriInfo.getAbsolutePathBuilder().path(id).build();
		Response.created(uri).build();

		servletResponse.sendRedirect("../pages/new_suscriptor.html");
	}

	@Path("{suscriptor}")
	public SuscriptorResource getSuscriptor(
	@PathParam("suscriptor") String suscriptor ) {
		return new SuscriptorResource(uriInfo, request, suscriptor );
	}

}
