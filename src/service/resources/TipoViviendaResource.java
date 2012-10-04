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


import service.beans.Tipovivienda;
import service.storage.TipoviviendaStore;
import service.util.ParamUtil;

import com.sun.jersey.api.NotFoundException;

public class TipoviviendaResource {
	@Context
	UriInfo uriInfo;
	@Context
	Request request;
	String tipovivienda;

	public TipoviviendaResource(UriInfo uriInfo, Request request, String tipovivienda) {
		this.uriInfo = uriInfo;
		this.request = request;
		this.tipovivienda = tipovivienda;
	}

	@GET
	@Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
	public Tipovivienda getTipovivienda() {
		Tipovivienda aux = TipoviviendaStore.getStore().get(tipovivienda);
		if(aux==null)
			throw new NotFoundException("No such Tipovivienda.");
		return aux;
	}

	@PUT
	@Consumes(MediaType.APPLICATION_XML)
	public Response putTipovivienda(JAXBElement<Tipovivienda> jaxbTipovivienda) {
		Tipovivienda p = jaxbTipovivienda.getValue();
		return putAndGetResponse(p);
	}

	@PUT
	public Response putTipovivienda(@Context HttpHeaders herders, byte[] in) {
		Map<String,String> params = ParamUtil.parse(new String(in));
		Tipovivienda p = new Tipovivienda(
 params.get("id"), params.get("nombre "), params.get("servicio")
);

		return putAndGetResponse(p);
	}

	private Response putAndGetResponse(Tipovivienda p) {
		Response res;
		if(TipoviviendaStore.getStore().containsKey(p.getid())) {
			res = Response.noContent().build();
		} else {
			res = Response.created(uriInfo.getAbsolutePath()).build();
		}
		TipoviviendaStore.getStore().put(p.getid(), p);
		return res;
	}

	@DELETE
	public void deleteTipovivienda() {
		Tipovivienda p = TipoviviendaStore.getStore().remove(tipovivienda);
		if(p==null)
			throw new NotFoundException("No such Tipovivienda.");
	}
}
