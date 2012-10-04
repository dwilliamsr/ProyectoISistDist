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

import service.beans.Averia;
import service.storage.AveriaStore;

@Path("/averias")
public class AveriasResource {
	
	@Context
	UriInfo uriInfo;
	@Context
	Request request;

	@GET
	@Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
	public List<Averia> getAverias() {
		List<Averia> averias = new ArrayList<Averia>();
		averias.addAll( AveriaStore.getStore().values() );
		return averias;
	}

	@GET
	@Path("count")
	@Produces(MediaType.TEXT_PLAIN)
	public String getCount() {
		int count = AveriaStore.getStore().size();
		return String.valueOf(count);
	}

	@POST
	@Produces(MediaType.TEXT_HTML)
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	public void newAveria(
@FormParam("id") String id,
@FormParam("descripcion") String descripcion,
@FormParam("fecha") String fecha,
@FormParam("servicio") String servicio			,@Context HttpServletResponse servletResponse
	) throws IOException {
		Averia p = new Averia(id,descripcion,fecha,servicio);
		AveriaStore.getStore().put(id, p);

		URI uri = uriInfo.getAbsolutePathBuilder().path(id).build();
		Response.created(uri).build();

		servletResponse.sendRedirect("../pages/new_averia.html");
	}

	@Path("{averia}")
	public AveriaResource getAveria(
	@PathParam("averia") String averia ) {
		return new AveriaResource(uriInfo, request, averia );
	}

}
