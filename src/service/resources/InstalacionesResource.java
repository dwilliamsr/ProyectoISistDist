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

import service.beans.Instalacion;
import service.storage.InstalacionStore;

@Path("/instalaciones")
public class InstalacionesResource {
	
	@Context
	UriInfo uriInfo;
	@Context
	Request request;

	@GET
	@Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
	public List<Instalacion> getInstalaciones() {
		List<Instalacion> instalaciones = new ArrayList<Instalacion>();
		instalaciones.addAll( InstalacionStore.getStore().values() );
		return instalaciones;
	}

	@GET
	@Path("count")
	@Produces(MediaType.TEXT_PLAIN)
	public String getCount() {
		int count = InstalacionStore.getStore().size();
		return String.valueOf(count);
	}

	@POST
	@Produces(MediaType.TEXT_HTML)
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	public void newInstalacion(
@FormParam("id") String id,
@FormParam("numconcent") String numconcent,
@FormParam("cantmetcable") String cantmetcable,
@FormParam("inspropia") String inspropia,
@FormParam("canttel") String canttel			,@Context HttpServletResponse servletResponse
	) throws IOException {
		Instalacion p = new Instalacion(id,numconcent,cantmetcable,inspropia,canttel);
		InstalacionStore.getStore().put(id, p);

		URI uri = uriInfo.getAbsolutePathBuilder().path(id).build();
		Response.created(uri).build();

		servletResponse.sendRedirect("../pages/new_instalacion.html");
	}

	@Path("{instalacion}")
	public InstalacionResource getInstalacion(
	@PathParam("instalacion") String instalacion ) {
		return new InstalacionResource(uriInfo, request, instalacion );
	}

}
