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


import service.beans.Suscriptor;
import service.storage.SuscriptorStore;
import service.util.ParamUtil;

import com.sun.jersey.api.NotFoundException;

public class SuscriptorResource {
	@Context
	UriInfo uriInfo;
	@Context
	Request request;
	String suscriptor;

	public SuscriptorResource(UriInfo uriInfo, Request request, String suscriptor) {
		this.uriInfo = uriInfo;
		this.request = request;
		this.suscriptor = suscriptor;
	}

	@GET
	@Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
	public Suscriptor getSuscriptor() {
		Suscriptor aux = SuscriptorStore.getStore().get(suscriptor);
		if(aux==null)
			throw new NotFoundException("No such Suscriptor.");
		return aux;
	}

	@PUT
	@Consumes(MediaType.APPLICATION_XML)
	public Response putSuscriptor(JAXBElement<Suscriptor> jaxbSuscriptor) {
		Suscriptor p = jaxbSuscriptor.getValue();
		return putAndGetResponse(p);
	}

	@PUT
	public Response putSuscriptor(@Context HttpHeaders herders, byte[] in) {
		Map<String,String> params = ParamUtil.parse(new String(in));
		Suscriptor p = new Suscriptor(
 params.get("id"), params.get("nombre"), params.get("identificacion"), params.get("telefono"), params.get("direccion")
);

		return putAndGetResponse(p);
	}

	private Response putAndGetResponse(Suscriptor p) {
		Response res;
		if(SuscriptorStore.getStore().containsKey(p.getid())) {
			res = Response.noContent().build();
		} else {
			res = Response.created(uriInfo.getAbsolutePath()).build();
		}
		SuscriptorStore.getStore().put(p.getid(), p);
		return res;
	}

	@DELETE
	public void deleteSuscriptor() {
		Suscriptor p = SuscriptorStore.getStore().remove(suscriptor);
		if(p==null)
			throw new NotFoundException("No such Suscriptor.");
	}
}
