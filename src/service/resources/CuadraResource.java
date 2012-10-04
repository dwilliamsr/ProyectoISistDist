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


import service.beans.Cuadra;
import service.storage.CuadraStore;
import service.util.ParamUtil;

import com.sun.jersey.api.NotFoundException;

public class CuadraResource {
	@Context
	UriInfo uriInfo;
	@Context
	Request request;
	String cuadra;

	public CuadraResource(UriInfo uriInfo, Request request, String cuadra) {
		this.uriInfo = uriInfo;
		this.request = request;
		this.cuadra = cuadra;
	}

	@GET
	@Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
	public Cuadra getCuadra() {
		Cuadra aux = CuadraStore.getStore().get(cuadra);
		if(aux==null)
			throw new NotFoundException("No such Cuadra.");
		return aux;
	}

	@PUT
	@Consumes(MediaType.APPLICATION_XML)
	public Response putCuadra(JAXBElement<Cuadra> jaxbCuadra) {
		Cuadra p = jaxbCuadra.getValue();
		return putAndGetResponse(p);
	}

	@PUT
	public Response putCuadra(@Context HttpHeaders herders, byte[] in) {
		Map<String,String> params = ParamUtil.parse(new String(in));
		Cuadra p = new Cuadra(
 params.get("id"), params.get("nombre"), params.get("region")
);

		return putAndGetResponse(p);
	}

	private Response putAndGetResponse(Cuadra p) {
		Response res;
		if(CuadraStore.getStore().containsKey(p.getid())) {
			res = Response.noContent().build();
		} else {
			res = Response.created(uriInfo.getAbsolutePath()).build();
		}
		CuadraStore.getStore().put(p.getid(), p);
		return res;
	}

	@DELETE
	public void deleteCuadra() {
		Cuadra p = CuadraStore.getStore().remove(cuadra);
		if(p==null)
			throw new NotFoundException("No such Cuadra.");
	}
}
