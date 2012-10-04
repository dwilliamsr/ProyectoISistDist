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

import service.beans.Servicio;
import service.storage.ServicioStore;

@Path("/servicios")
public class ServiciosResource {
	
	@Context
	UriInfo uriInfo;
	@Context
	Request request;

	@GET
	@Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
	public List<Servicio> getServicios() {
		List<Servicio> servicios = new ArrayList<Servicio>();
		servicios.addAll( ServicioStore.getStore().values() );
		return servicios;
	}

	@GET
	@Path("count")
	@Produces(MediaType.TEXT_PLAIN)
	public String getCount() {
		int count = ServicioStore.getStore().size();
		return String.valueOf(count);
	}

	@POST
	@Produces(MediaType.TEXT_HTML)
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	public void newServicio(
@FormParam("id") String id,
@FormParam("fechainst") String fechainst,
@FormParam("direccion") String direccion,
@FormParam("region") String region,
@FormParam("suscriptor") String suscriptor			,@Context HttpServletResponse servletResponse
	) throws IOException {
		Servicio p = new Servicio(id,fechainst,direccion,region,suscriptor);
		ServicioStore.getStore().put(id, p);

		URI uri = uriInfo.getAbsolutePathBuilder().path(id).build();
		Response.created(uri).build();

		servletResponse.sendRedirect("../pages/new_servicio.html");
	}

	@Path("{servicio}")
	public ServicioResource getServicio(
	@PathParam("servicio") String servicio ) {
		return new ServicioResource(uriInfo, request, servicio );
	}

}
