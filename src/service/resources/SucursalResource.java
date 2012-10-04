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


import service.beans.Sucursal;
import service.storage.SucursalStore;
import service.util.ParamUtil;

import com.sun.jersey.api.NotFoundException;

public class SucursalResource {
	@Context
	UriInfo uriInfo;
	@Context
	Request request;
	String sucursal;

	public SucursalResource(UriInfo uriInfo, Request request, String sucursal) {
		this.uriInfo = uriInfo;
		this.request = request;
		this.sucursal = sucursal;
	}

	@GET
	@Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
	public Sucursal getSucursal() {
		Sucursal aux = SucursalStore.getStore().get(sucursal);
		if(aux==null)
			throw new NotFoundException("No such Sucursal.");
		return aux;
	}

	@PUT
	@Consumes(MediaType.APPLICATION_XML)
	public Response putSucursal(JAXBElement<Sucursal> jaxbSucursal) {
		Sucursal p = jaxbSucursal.getValue();
		return putAndGetResponse(p);
	}

	@PUT
	public Response putSucursal(@Context HttpHeaders herders, byte[] in) {
		Map<String,String> params = ParamUtil.parse(new String(in));
		Sucursal p = new Sucursal(
 params.get("id"), params.get("telefono"), params.get("ciudad"), params.get("direccion"), params.get("encargado")
);

		return putAndGetResponse(p);
	}

	private Response putAndGetResponse(Sucursal p) {
		Response res;
		if(SucursalStore.getStore().containsKey(p.getid())) {
			res = Response.noContent().build();
		} else {
			res = Response.created(uriInfo.getAbsolutePath()).build();
		}
		SucursalStore.getStore().put(p.getid(), p);
		return res;
	}

	@DELETE
	public void deleteSucursal() {
		Sucursal p = SucursalStore.getStore().remove(sucursal);
		if(p==null)
			throw new NotFoundException("No such Sucursal.");
	}
}
