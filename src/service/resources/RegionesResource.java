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

import service.beans.Region;
import service.storage.RegionStore;

@Path("/regiones")
public class RegionesResource {
	
	@Context
	UriInfo uriInfo;
	@Context
	Request request;

	@GET
	@Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
	public List<Region> getRegiones() {
		List<Region> regiones = new ArrayList<Region>();
		regiones.addAll( RegionStore.getStore().values() );
		return regiones;
	}

	@GET
	@Path("count")
	@Produces(MediaType.TEXT_PLAIN)
	public String getCount() {
		int count = RegionStore.getStore().size();
		return String.valueOf(count);
	}

	@POST
	@Produces(MediaType.TEXT_HTML)
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	public void newRegion(
@FormParam("id") String id,
@FormParam("encargado") String encargado,
@FormParam("sucursal") String sucursal			,@Context HttpServletResponse servletResponse
	) throws IOException {
		Region p = new Region(id,encargado,sucursal);
		RegionStore.getStore().put(id, p);

		URI uri = uriInfo.getAbsolutePathBuilder().path(id).build();
		Response.created(uri).build();

		servletResponse.sendRedirect("../pages/new_region.html");
	}

	@Path("{region}")
	public RegionResource getRegion(
	@PathParam("region") String region ) {
		return new RegionResource(uriInfo, request, region );
	}

}
