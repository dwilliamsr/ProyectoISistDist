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

import service.beans.Sucursal;
import service.storage.SucursalStore;

@Path("/sucursales")
public class SucursalesResource {
	
	@Context
	UriInfo uriInfo;
	@Context
	Request request;

	@GET
	@Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
	public List<Sucursal> getSucursales() {
		List<Sucursal> sucursales = new ArrayList<Sucursal>();
		sucursales.addAll( SucursalStore.getStore().values() );
		return sucursales;
	}

	@GET
	@Path("count")
	@Produces(MediaType.TEXT_PLAIN)
	public String getCount() {
		int count = SucursalStore.getStore().size();
		return String.valueOf(count);
	}

	@POST
	@Produces(MediaType.TEXT_HTML)
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	public void newSucursal(
@FormParam("id") String id,
@FormParam("telefono") String telefono,
@FormParam("ciudad") String ciudad,
@FormParam("direccion") String direccion,
@FormParam("encargado") String encargado			,@Context HttpServletResponse servletResponse
	) throws IOException {
		Sucursal p = new Sucursal(id,telefono,ciudad,direccion,encargado);
		SucursalStore.getStore().put(id, p);

		URI uri = uriInfo.getAbsolutePathBuilder().path(id).build();
		Response.created(uri).build();

		servletResponse.sendRedirect("../pages/new_sucursal.html");
	}

	@Path("{sucursal}")
	public SucursalResource getSucursal(
	@PathParam("sucursal") String sucursal ) {
		return new SucursalResource(uriInfo, request, sucursal );
	}

}
