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


import service.beans.Region;
import service.storage.RegionStore;
import service.util.ParamUtil;

import com.sun.jersey.api.NotFoundException;

public class RegionResource {
	@Context
	UriInfo uriInfo;
	@Context
	Request request;
	String region;

	public RegionResource(UriInfo uriInfo, Request request, String region) {
		this.uriInfo = uriInfo;
		this.request = request;
		this.region = region;
	}

	@GET
	@Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
	public Region getRegion() {
		Region aux = RegionStore.getStore().get(region);
		if(aux==null)
			throw new NotFoundException("No such Region.");
		return aux;
	}

	@PUT
	@Consumes(MediaType.APPLICATION_XML)
	public Response putRegion(JAXBElement<Region> jaxbRegion) {
		Region p = jaxbRegion.getValue();
		return putAndGetResponse(p);
	}

	@PUT
	public Response putRegion(@Context HttpHeaders herders, byte[] in) {
		Map<String,String> params = ParamUtil.parse(new String(in));
		Region p = new Region(
 params.get("id"), params.get("encargado"), params.get("sucursal")
);

		return putAndGetResponse(p);
	}

	private Response putAndGetResponse(Region p) {
		Response res;
		if(RegionStore.getStore().containsKey(p.getid())) {
			res = Response.noContent().build();
		} else {
			res = Response.created(uriInfo.getAbsolutePath()).build();
		}
		RegionStore.getStore().put(p.getid(), p);
		return res;
	}

	@DELETE
	public void deleteRegion() {
		Region p = RegionStore.getStore().remove(region);
		if(p==null)
			throw new NotFoundException("No such Region.");
	}
}
