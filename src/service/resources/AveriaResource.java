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


import service.beans.Averia;
import service.storage.AveriaStore;
import service.util.ParamUtil;

import com.sun.jersey.api.NotFoundException;

public class AveriaResource {
	@Context
	UriInfo uriInfo;
	@Context
	Request request;
	String averia;

	public AveriaResource(UriInfo uriInfo, Request request, String averia) {
		this.uriInfo = uriInfo;
		this.request = request;
		this.averia = averia;
	}

	@GET
	@Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
	public Averia getAveria() {
		Averia aux = AveriaStore.getStore().get(averia);
		if(aux==null)
			throw new NotFoundException("No such Averia.");
		return aux;
	}

	@PUT
	@Consumes(MediaType.APPLICATION_XML)
	public Response putAveria(JAXBElement<Averia> jaxbAveria) {
		Averia p = jaxbAveria.getValue();
		return putAndGetResponse(p);
	}

	@PUT
	public Response putAveria(@Context HttpHeaders herders, byte[] in) {
		Map<String,String> params = ParamUtil.parse(new String(in));
		Averia p = new Averia(
 params.get("id"), params.get("descripcion"), params.get("fecha"), params.get("servicio")
);

		return putAndGetResponse(p);
	}

	private Response putAndGetResponse(Averia p) {
		Response res;
		if(AveriaStore.getStore().containsKey(p.getid())) {
			res = Response.noContent().build();
		} else {
			res = Response.created(uriInfo.getAbsolutePath()).build();
		}
		AveriaStore.getStore().put(p.getid(), p);
		return res;
	}

	@DELETE
	public void deleteAveria() {
		Averia p = AveriaStore.getStore().remove(averia);
		if(p==null)
			throw new NotFoundException("No such Averia.");
	}
}
