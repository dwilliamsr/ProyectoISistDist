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


import service.beans.Instalacion;
import service.storage.InstalacionStore;
import service.util.ParamUtil;

import com.sun.jersey.api.NotFoundException;

public class InstalacionResource {
	@Context
	UriInfo uriInfo;
	@Context
	Request request;
	String instalacion;

	public InstalacionResource(UriInfo uriInfo, Request request, String instalacion) {
		this.uriInfo = uriInfo;
		this.request = request;
		this.instalacion = instalacion;
	}

	@GET
	@Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
	public Instalacion getInstalacion() {
		Instalacion aux = InstalacionStore.getStore().get(instalacion);
		if(aux==null)
			throw new NotFoundException("No such Instalacion.");
		return aux;
	}

	@PUT
	@Consumes(MediaType.APPLICATION_XML)
	public Response putInstalacion(JAXBElement<Instalacion> jaxbInstalacion) {
		Instalacion p = jaxbInstalacion.getValue();
		return putAndGetResponse(p);
	}

	@PUT
	public Response putInstalacion(@Context HttpHeaders herders, byte[] in) {
		Map<String,String> params = ParamUtil.parse(new String(in));
		Instalacion p = new Instalacion(
 params.get("id"), params.get("numconcent"), params.get("cantmetcable"), params.get("inspropia"), params.get("canttel")
);

		return putAndGetResponse(p);
	}

	private Response putAndGetResponse(Instalacion p) {
		Response res;
		if(InstalacionStore.getStore().containsKey(p.getid())) {
			res = Response.noContent().build();
		} else {
			res = Response.created(uriInfo.getAbsolutePath()).build();
		}
		InstalacionStore.getStore().put(p.getid(), p);
		return res;
	}

	@DELETE
	public void deleteInstalacion() {
		Instalacion p = InstalacionStore.getStore().remove(instalacion);
		if(p==null)
			throw new NotFoundException("No such Instalacion.");
	}
}
