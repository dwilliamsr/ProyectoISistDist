package service.resources;

import java.util.ArrayList;
import java.util.Map;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Request;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import javax.xml.bind.JAXBElement;


import service.beans.Servicioextra;
import service.storage.ServicioextraStore;
import service.util.ParamUtil;

import com.sun.jersey.api.NotFoundException;

public class ServicioextraResource {
	@Context
	UriInfo uriInfo;
	@Context
	Request request;
	String servicioextra;

	public ServicioextraResource(UriInfo uriInfo, Request request, String servicioextra) {
		this.uriInfo = uriInfo;
		this.request = request;
		this.servicioextra = servicioextra;
	}

	@GET
	@Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
	public Servicioextra getServicioextra() {
		Servicioextra aux = ServicioextraStore.getStore().get(servicioextra);
		if(aux==null)
			throw new NotFoundException("No such Servicioextra.");
		return aux;
	}

	@PUT
	@Consumes(MediaType.APPLICATION_XML)
	public Response putServicioextra(JAXBElement<Servicioextra> jaxbServicioextra) {
		Servicioextra p = jaxbServicioextra.getValue();
		return putAndGetResponse(p);
	}

	@PUT
	public Response putServicioextra(@Context HttpHeaders herders, byte[] in) {
		Map<String,String> params = ParamUtil.parse(new String(in));
		Servicioextra p = new Servicioextra(
 params.get("id"), params.get("nombre"), params.get("servicio")
);

		return putAndGetResponse(p);
	}

	private Response putAndGetResponse(Servicioextra p) {
		Response res;
		if(ServicioextraStore.getStore().containsKey(p.getid())) {
			res = Response.noContent().build();
		} else {
			res = Response.created(uriInfo.getAbsolutePath()).build();
		}
		ServicioextraStore.getStore().put(p.getid(), p);
		return res;
	}

	@DELETE
	public void deleteServicioextra() {
		Servicioextra p = ServicioextraStore.getStore().remove(servicioextra);
		if(p==null)
			throw new NotFoundException("No such Servicioextra.");
	}
}
