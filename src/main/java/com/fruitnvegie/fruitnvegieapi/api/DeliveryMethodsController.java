package com.fruitnvegie.fruitnvegieapi.api;

import com.fruitnvegie.fruitnvegieapi.dto.AddDeliveryMethodsDto;
import com.fruitnvegie.fruitnvegieapi.dto.UpdateDeliveryMethodsDto;
import com.fruitnvegie.fruitnvegieapi.dto.UpdateShippingDetailsDto;
import com.fruitnvegie.fruitnvegieapi.models.DeliveryMethods;
import com.fruitnvegie.fruitnvegieapi.models.api.ApiResponse;
import com.fruitnvegie.fruitnvegieapi.services.DeliveryMethodsService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping(value = "/api/v1/deliveryMethods", produces = MediaType.APPLICATION_JSON_VALUE)
@Api(value = "/api/v1/deliveryMethods", produces = MediaType.APPLICATION_JSON_VALUE)
public class DeliveryMethodsController {
    private final DeliveryMethodsService deliveryMethodsService;
    private final ModelMapper modelMapper;

    @Autowired
    public DeliveryMethodsController(DeliveryMethodsService deliveryMethodsService, ModelMapper modelMapper) {
        this.deliveryMethodsService = deliveryMethodsService;
        this.modelMapper = modelMapper;
    }

    @GetMapping("/")
    @ApiOperation(value = "Get all Delivery Methods", response = ApiResponse.class)
    public ApiResponse getAllDeliveryMethods(){
        return new ApiResponse(200, "SUCCESS", deliveryMethodsService.getAll());
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "Get a Delivery Method by its id", response = ApiResponse.class)
    public ApiResponse getDeliveryMethodByID(@PathVariable("id") Long id){
        return new ApiResponse(200, "SUCCESS", deliveryMethodsService.getOne(id));

    }

    @GetMapping("/delivery-method-by-name")
    @ApiOperation(value = "Get a Delivery Method by its name", response = ApiResponse.class)
    public ApiResponse getDeliveryMethodByName(@RequestParam("name") String name){
        return new ApiResponse(200, "SUCCESS", deliveryMethodsService.findByDeliveryMethod(name));
    }
    @DeleteMapping("/delete/{id}")
    @ApiOperation(value = "Delete a Delivery Method by its id. Takes id as a path variable", response = ApiResponse.class)
    public ApiResponse deleteDeliveryMethod(@PathVariable("id") Long id){
        return new ApiResponse(200, "SUCCESS", deliveryMethodsService.delete(id));
    }

    @PostMapping("/add")
    @ApiOperation(value = "Add a new Delivery Method", response = ApiResponse.class)
    public ApiResponse addDeliveryMethod(@RequestBody AddDeliveryMethodsDto deliveryMethodsDto){
        DeliveryMethods deliveryMethods = modelMapper.map(deliveryMethodsDto, DeliveryMethods.class);
        return new ApiResponse(201, "SUCCESS", deliveryMethodsService.add(deliveryMethods));
    }

    @PutMapping("/edit")
    @ApiOperation(value = "Update an existing Delivery Method", response = ApiResponse.class)
    public ApiResponse updateDeliveryMethod(@RequestBody UpdateDeliveryMethodsDto deliveryMethodsDto){
        DeliveryMethods deliveryMethods = modelMapper.map(deliveryMethodsDto, DeliveryMethods.class);
        return new ApiResponse(200, "SUCCESS", deliveryMethodsService.update(deliveryMethods));
    }
}
