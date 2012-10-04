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


import service.beans.Servicio;
import service.storage.ServicioStore;
import service.util.ParamUtil;

import com.sun.jersey.api.NotFoundException;

public class ServicioResource {
	@Context
	UriInfo uriInfo;
	@Context
	Request request;
	String servicio;

	public ServicioResource(UriInfo uriInfo, Request request, String servicio) {
		this.uriInfo = uriInfo;
		this.request = request;
		this.servicio = servicio;
	}

	@GET
	@Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
	public Servicio getServicio() {
		Servicio aux = ServicioStore.getStore().get(servicio);
		if(aux==null)
			throw new NotFoundException("No such Servicio.");
		return aux;
	}

	@PUT
	@Consumes(MediaType.APPLICATION_XML)
	public Response putServicio(JAXBElement<Servicio> jaxbServicio) {
		Servicio p = jaxbServicio.getValue();
		return putAndGetResponse(p);
	}

	@PUT
	public Response putServicio(@Context HttpHeaders herders, byte[] in) {
		Map<String,String> params = ParamUtil.parse(new String(in));
		Servicio p = new Servicio(
 params.get("id"), params.get("fechainst"), params.get("direccion"), params.get("region"), params.get("suscriptor")
);

		return putAndGetResponse(p);
	}

	private Response putAndGetResponse(Servicio p) {
		Response res;
		if(ServicioStore.getStore().containsKey(p.getid())) {
			res = Response.noContent().build();
		} else {
			res = Response.created(uriInfo.getAbsolutePath()).build();
		}
		ServicioStore.getStore().put(p.getid(), p);
		return res;
	}

	@DELETE
	public void deleteServicio() {
		Servicio p = ServicioStore.getStore().remove(servicio);
		if(p==null)
			throw new NotFoundException("No such Servicio.");
	}
}
