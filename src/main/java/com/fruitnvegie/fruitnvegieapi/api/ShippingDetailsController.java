package com.fruitnvegie.fruitnvegieapi.api;

import com.fruitnvegie.fruitnvegieapi.dto.AddShippingDetailsDto;
import com.fruitnvegie.fruitnvegieapi.dto.UpdateShippingDetailsDto;
import com.fruitnvegie.fruitnvegieapi.models.DeliveryMethods;
import com.fruitnvegie.fruitnvegieapi.models.PaymentMethods;
import com.fruitnvegie.fruitnvegieapi.models.ShippingDetails;
import com.fruitnvegie.fruitnvegieapi.models.api.ApiResponse;
import com.fruitnvegie.fruitnvegieapi.services.DeliveryMethodsService;
import com.fruitnvegie.fruitnvegieapi.services.PaymentMethodsService;
import com.fruitnvegie.fruitnvegieapi.services.ShippingDetailsService;
import com.fruitnvegie.fruitnvegieapi.services.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping(value = "/api/v1/shippingDetails", produces = MediaType.APPLICATION_JSON_VALUE)
@Api(value = "/api/v1/shippingDetails", produces = MediaType.APPLICATION_JSON_VALUE)
public class ShippingDetailsController {
    private final ShippingDetailsService shippingDetailsService;
    private final DeliveryMethodsService deliveryMethodsService;
    private final PaymentMethodsService paymentMethodsService;
    private final UserService userService;
    private final ModelMapper modelMapper;

    @Autowired
    public ShippingDetailsController(ShippingDetailsService shippingDetailsService, DeliveryMethodsService deliveryMethodsService, PaymentMethodsService paymentMethodsService, UserService userService, ModelMapper modelMapper) {
        this.shippingDetailsService = shippingDetailsService;
        this.deliveryMethodsService = deliveryMethodsService;
        this.paymentMethodsService = paymentMethodsService;
        this.userService = userService;
        this.modelMapper = modelMapper;
    }

    @GetMapping("/")
    @ApiOperation(value = "Get all user Shipping Details", response = ApiResponse.class)
    public ApiResponse getAllShippingDetails(){
        return new ApiResponse(200, "SUCCESS", shippingDetailsService.getAll());
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "Get Shipping Details of a user by their id. Takes id as a path variable", response = ApiResponse.class)
    public ApiResponse getShippingDetailsByUserID(@PathVariable("id") int id){
        return new ApiResponse(200, "SUCCESS", shippingDetailsService.findByUserId(id));
    }

    @DeleteMapping("/delete/{id}")
    @ApiOperation(value = "Delete Shipping Details of a user by id. Takes id as a path variable", response = ApiResponse.class)
    public ApiResponse deleteShippingDetails(@PathVariable("id") Long id){
        return new ApiResponse(200, "SUCCESS", shippingDetailsService.delete(id));
    }

    @GetMapping("/by-country")
    @ApiOperation(value = "Get Shipping Details of users by their country. Takes country as a request parameter",
            response = ApiResponse.class)
    public ApiResponse getShippingDetailsByCountry(@RequestParam("country") String country){
        return new ApiResponse(200, "SUCCESS", shippingDetailsService.findAllByCountry(country));
    }


    @GetMapping("/by-city")
    @ApiOperation(value = "Get Shipping Details of users by their city. Takes city as a request parameter",
            response = ApiResponse.class)
    public ApiResponse getShippingDetailsByCity(@RequestParam("city") String city){
        return new ApiResponse(200, "SUCCESS",
                shippingDetailsService.findAllByCity(city));
    }

    @GetMapping("/by-job-payment-method/{payment-method-id}")
    @ApiOperation(value = "Get Shipping Details of users by their Payment Method. Takes paymentMethodId as a path variable",
            response = ApiResponse.class)
    public ApiResponse getShippingDetailsByPaymentMethod(@PathVariable("payment-method-id") Long paymentMethodId){
        PaymentMethods paymentMethods = paymentMethodsService.getOne(paymentMethodId);
        return new ApiResponse(200, "SUCCESS", shippingDetailsService.findAllByPaymentMethods(paymentMethods));
    }

    @GetMapping("/by-job-delivery-method/{delivery-method-id}")
    @ApiOperation(value = "Get Shipping Details of users by their Delivery Method. Takes deliveryMethodId as a path variable",
            response = ApiResponse.class)
    public ApiResponse getShippingDetailsByDeliveryMethod(@PathVariable("delivery-method-id") Long deliveryMethodId){
        DeliveryMethods deliveryMethods = deliveryMethodsService.getOne(deliveryMethodId);
        return new ApiResponse(200, "SUCCESS", shippingDetailsService.findAllByDeliveryMethods(deliveryMethods));
    }

    @PostMapping("/add/{user-id}/{delivery-method-id}/{payment-method-id}")
    @ApiOperation(value = "Add a new Shipping Details entry for a user. Takes userId, deliveryMethodId and paymentMethodId as path variables",
            response = ApiResponse.class)
    public ApiResponse addNewShippingDetailsEntry(@RequestBody AddShippingDetailsDto shippingDetailsDto,
                                      @PathVariable("user-id") Integer userId,
                                      @PathVariable("delivery-method-id") Long deliveryMethodId,
                                      @PathVariable("payment-method-id") Long paymentMethodId){

        ShippingDetails shippingDetails = modelMapper.map(shippingDetailsDto, ShippingDetails.class);
        shippingDetails.setUser(userService.getOne(userId));
        shippingDetails.setDeliveryMethods(deliveryMethodsService.getOne(deliveryMethodId));
        shippingDetails.setPaymentMethods(paymentMethodsService.getOne(paymentMethodId));


        return new ApiResponse(201, "SUCCESS", shippingDetailsService.add(shippingDetails));
    }

    @PutMapping("/edit/{delivery-method-id}/{payment-method-id}")
    @ApiOperation(value = "Update an existing Shipping Details entity. Takes deliveryMethodId and paymentMethodId as path variables",
            response = ApiResponse.class)
    public ApiResponse updateAnExistingEmployee(@RequestBody UpdateShippingDetailsDto shippingDetailsDto,
                                                @PathVariable("delivery-method-id") Long statusId,
                                                @PathVariable("payment-method-id") Long titleId){

        ShippingDetails shippingDetails = modelMapper.map(shippingDetailsDto, ShippingDetails.class);
        shippingDetails.setPaymentMethods(paymentMethodsService.getOne(titleId));
        shippingDetails.setDeliveryMethods(deliveryMethodsService.getOne(statusId));

        // Get old record to get the userId
        ShippingDetails oldRecord = shippingDetailsService.getOne(shippingDetails.getId());

        shippingDetails.setUser(oldRecord.getUser());
        return new ApiResponse(200, "SUCCESS", shippingDetailsService.update(shippingDetails));
    }
}
