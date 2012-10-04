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


import service.beans.Estadoaveria;
import service.storage.EstadoaveriaStore;
import service.util.ParamUtil;

import com.sun.jersey.api.NotFoundException;

public class EstadoaveriaResource {
	@Context
	UriInfo uriInfo;
	@Context
	Request request;
	String estadoaveria;

	public EstadoaveriaResource(UriInfo uriInfo, Request request, String estadoaveria) {
		this.uriInfo = uriInfo;
		this.request = request;
		this.estadoaveria = estadoaveria;
	}

	@GET
	@Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
	public Estadoaveria getEstadoaveria() {
		Estadoaveria aux = EstadoaveriaStore.getStore().get(estadoaveria);
		if(aux==null)
			throw new NotFoundException("No such Estadoaveria.");
		return aux;
	}

	@PUT
	@Consumes(MediaType.APPLICATION_XML)
	public Response putEstadoaveria(JAXBElement<Estadoaveria> jaxbEstadoaveria) {
		Estadoaveria p = jaxbEstadoaveria.getValue();
		return putAndGetResponse(p);
	}

	@PUT
	public Response putEstadoaveria(@Context HttpHeaders herders, byte[] in) {
		Map<String,String> params = ParamUtil.parse(new String(in));
		Estadoaveria p = new Estadoaveria(
 params.get("id"), params.get("nombre"), params.get("averia")
);

		return putAndGetResponse(p);
	}

	private Response putAndGetResponse(Estadoaveria p) {
		Response res;
		if(EstadoaveriaStore.getStore().containsKey(p.getid())) {
			res = Response.noContent().build();
		} else {
			res = Response.created(uriInfo.getAbsolutePath()).build();
		}
		EstadoaveriaStore.getStore().put(p.getid(), p);
		return res;
	}

	@DELETE
	public void deleteEstadoaveria() {
		Estadoaveria p = EstadoaveriaStore.getStore().remove(estadoaveria);
		if(p==null)
			throw new NotFoundException("No such Estadoaveria.");
	}
}
