package com.fruitnvegie.fruitnvegieapi.api;

import com.fruitnvegie.fruitnvegieapi.models.api.ApiResponse;
import com.fruitnvegie.fruitnvegieapi.services.ProductsService;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.io.InputStream;

@RestController
@RequestMapping("api/products")
public class ProductsController {

    private final ProductsService productsService;

    @Autowired
    public ProductsController(ProductsService productsService){
        this.productsService = productsService;
    }

    @GetMapping("/")
    @ApiOperation(value = "Get all products in the inventory", response = ApiResponse.class)
    public ApiResponse getAllProducts(){
        return new ApiResponse(200, "SUCCESS", productsService.getAllProducts());

    }
    @GetMapping(value = "/{product-id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Get a particular product by its Id, taking Id as path variable.", response = ApiResponse.class)
    public ApiResponse getProductById(@PathVariable("product-id") long productId){

        return new ApiResponse(200, "SUCCESS",productsService.getOne(productId));

    }
    @GetMapping("/product-by-name")
    @ApiOperation(value = "Get available product by its name", response = ApiResponse.class)
    public ApiResponse getProductByName(@RequestParam(value = "productName") String name){
        return new ApiResponse(200, "SUCCESS", productsService.findByProductName(name));
    }

    @GetMapping( value = "/getimage/{img_name}",produces = MediaType.IMAGE_JPEG_VALUE)
    public @ResponseBody byte[] getImageWithMediaType(@PathVariable("img_name") String img_name) throws IOException {
        InputStream in = getClass().getResourceAsStream("/images/"+img_name);
        return IOUtils.toByteArray(in);
    }
}