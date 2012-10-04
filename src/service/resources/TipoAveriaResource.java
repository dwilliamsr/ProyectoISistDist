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


import service.beans.Tipoaveria;
import service.storage.TipoaveriaStore;
import service.util.ParamUtil;

import com.sun.jersey.api.NotFoundException;

public class TipoaveriaResource {
	@Context
	UriInfo uriInfo;
	@Context
	Request request;
	String tipoaveria;

	public TipoaveriaResource(UriInfo uriInfo, Request request, String tipoaveria) {
		this.uriInfo = uriInfo;
		this.request = request;
		this.tipoaveria = tipoaveria;
	}

	@GET
	@Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
	public Tipoaveria getTipoaveria() {
		Tipoaveria aux = TipoaveriaStore.getStore().get(tipoaveria);
		if(aux==null)
			throw new NotFoundException("No such Tipoaveria.");
		return aux;
	}

	@PUT
	@Consumes(MediaType.APPLICATION_XML)
	public Response putTipoaveria(JAXBElement<Tipoaveria> jaxbTipoaveria) {
		Tipoaveria p = jaxbTipoaveria.getValue();
		return putAndGetResponse(p);
	}

	@PUT
	public Response putTipoaveria(@Context HttpHeaders herders, byte[] in) {
		Map<String,String> params = ParamUtil.parse(new String(in));
		Tipoaveria p = new Tipoaveria(
 params.get("id"), params.get("nombre"), params.get("averia")
);

		return putAndGetResponse(p);
	}

	private Response putAndGetResponse(Tipoaveria p) {
		Response res;
		if(TipoaveriaStore.getStore().containsKey(p.getid())) {
			res = Response.noContent().build();
		} else {
			res = Response.created(uriInfo.getAbsolutePath()).build();
		}
		TipoaveriaStore.getStore().put(p.getid(), p);
		return res;
	}

	@DELETE
	public void deleteTipoaveria() {
		Tipoaveria p = TipoaveriaStore.getStore().remove(tipoaveria);
		if(p==null)
			throw new NotFoundException("No such Tipoaveria.");
	}
}
