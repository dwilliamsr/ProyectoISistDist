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

import service.beans.Cuadra;
import service.storage.CuadraStore;

@Path("/cuadras")
public class CuadrasResource {
	
	@Context
	UriInfo uriInfo;
	@Context
	Request request;

	@GET
	@Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
	public List<Cuadra> getCuadras() {
		List<Cuadra> cuadras = new ArrayList<Cuadra>();
		cuadras.addAll( CuadraStore.getStore().values() );
		return cuadras;
	}

	@GET
	@Path("count")
	@Produces(MediaType.TEXT_PLAIN)
	public String getCount() {
		int count = CuadraStore.getStore().size();
		return String.valueOf(count);
	}

	@POST
	@Produces(MediaType.TEXT_HTML)
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	public void newCuadra(
@FormParam("id") String id,
@FormParam("nombre") String nombre,
@FormParam("region") String region			,@Context HttpServletResponse servletResponse
	) throws IOException {
		Cuadra p = new Cuadra(id,nombre,region);
		CuadraStore.getStore().put(id, p);

		URI uri = uriInfo.getAbsolutePathBuilder().path(id).build();
		Response.created(uri).build();

		servletResponse.sendRedirect("../pages/new_cuadra.html");
	}

	@Path("{cuadra}")
	public CuadraResource getCuadra(
	@PathParam("cuadra") String cuadra ) {
		return new CuadraResource(uriInfo, request, cuadra );
	}

}
