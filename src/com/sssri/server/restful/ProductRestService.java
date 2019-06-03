package com.sssri.server.restful;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Response;

import com.alibaba.fastjson.JSONObject;
import com.sssri.server.restful.resource.Product;

/**
 * REST service, operating resource for Product.
 * 
 * @author huangjw(huangjw@primeton.com)
 *
 */
@Path("/product")
@Consumes({"application/json", "application/x-www-form-urlencoded"})
public class ProductRestService {

	/**
	 * 资源获取操作
	 * @return
	 */
	@GET
	@Path("/appleProduct")
	@Produces("application/json;charset=utf-8")
	public Product[] getProduct(){
		Product product = new Product();
		product.setName("iPhone 7");
		product.setStoreTime(5);
		product.setSeqNo("353682390230");
		
		Product product2 = new Product();
		product2.setName("iPhone 6s");
		product2.setStoreTime(6);
		product2.setSeqNo("353682390230");
		return new Product[]{product, product2};
	}
	
	/**
	 * 创建资源
	 * @param newOne
	 * @return
	 */
	@POST
	@Path("/appleProduct/{productID}")
	@Produces("application/json;charset=UTF-8")
	public Response createProduct(@PathParam("productID")String productid, Product data){
		String result = "Product created : "+productid;
		System.out.println("【测试状态码】");
		JSONObject returnJson = new JSONObject();
		returnJson.put("huangjw", result);
		return Response.status(Response.Status.OK).entity(returnJson)
//				.type(MediaType.APPLICATION_JSON)
				.build();
	}
	
	
	/**
	 * 资源更新操作
	 * @return
	 */
	@PUT
	@Path("/appleProduct")
	public Response updateProductInJson(Product newOne){
		System.out.println(newOne);
		return Response.status(Response.Status.OK).build();
	}
	
	/**
	 * 资源删除
	 * @return
	 */
	@DELETE
	@Path("/appleProduct/{productID}")
	public Response deleteProductInJson(@PathParam("productID") String productID){
		System.out.println("[product ]执行删除操作： "+productID);
		return Response.status(Response.Status.OK).build();
	}
	
	
	/**
	 * @param name
	 * @param seqNo
	 * @return
	 */
	@GET
	@Path("/androidProduct")
	@Produces("application/json;charset=utf-8")
	public Product addProductInString(@QueryParam("name")String name, @QueryParam("seqno")String seqNo){
		Product product = new Product();
		product.setName(name);
		product.setSeqNo(seqNo);
		return product;
	}
	
}
