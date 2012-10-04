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


import service.beans.Estadoserv;
import service.storage.EstadoservStore;
import service.util.ParamUtil;

import com.sun.jersey.api.NotFoundException;

public class EstadoservResource {
	@Context
	UriInfo uriInfo;
	@Context
	Request request;
	String estadoserv;

	public EstadoservResource(UriInfo uriInfo, Request request, String estadoserv) {
		this.uriInfo = uriInfo;
		this.request = request;
		this.estadoserv = estadoserv;
	}

	@GET
	@Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
	public Estadoserv getEstadoserv() {
		Estadoserv aux = EstadoservStore.getStore().get(estadoserv);
		if(aux==null)
			throw new NotFoundException("No such Estadoserv.");
		return aux;
	}

	@PUT
	@Consumes(MediaType.APPLICATION_XML)
	public Response putEstadoserv(JAXBElement<Estadoserv> jaxbEstadoserv) {
		Estadoserv p = jaxbEstadoserv.getValue();
		return putAndGetResponse(p);
	}

	@PUT
	public Response putEstadoserv(@Context HttpHeaders herders, byte[] in) {
		Map<String,String> params = ParamUtil.parse(new String(in));
		Estadoserv p = new Estadoserv(
 params.get("id"), params.get("nombre"), params.get("servicio")
);

		return putAndGetResponse(p);
	}

	private Response putAndGetResponse(Estadoserv p) {
		Response res;
		if(EstadoservStore.getStore().containsKey(p.getid())) {
			res = Response.noContent().build();
		} else {
			res = Response.created(uriInfo.getAbsolutePath()).build();
		}
		EstadoservStore.getStore().put(p.getid(), p);
		return res;
	}

	@DELETE
	public void deleteEstadoserv() {
		Estadoserv p = EstadoservStore.getStore().remove(estadoserv);
		if(p==null)
			throw new NotFoundException("No such Estadoserv.");
	}
}
