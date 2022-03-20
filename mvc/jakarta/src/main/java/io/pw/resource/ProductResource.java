package io.pw.resource;

import io.pw.ProductBean;
import io.pw.model.Product;
import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

import java.util.List;

@Path("/products")
public class ProductResource {

   @Inject
   private ProductBean pb;

   @GET
   @Produces(MediaType.APPLICATION_JSON)
   public List<Product> getProducts() {
      return pb.getProducts();
   }

   @GET
   @Produces(MediaType.APPLICATION_JSON)
   @Path("/{id}")
   public Product getProduct(@PathParam("id") Integer id) {
      return pb.getProducts().get(id);
   }

}