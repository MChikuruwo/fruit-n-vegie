package com.fruitnvegie.fruitnvegieapi.api;

import com.fruitnvegie.fruitnvegieapi.dto.AddPaymentMethodsDto;
import com.fruitnvegie.fruitnvegieapi.dto.UpdatePaymentMethodsDto;
import com.fruitnvegie.fruitnvegieapi.models.PaymentMethods;
import com.fruitnvegie.fruitnvegieapi.models.api.ApiResponse;
import com.fruitnvegie.fruitnvegieapi.services.PaymentMethodsService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping(value = "/api/v1/paymentMethods", produces = MediaType.APPLICATION_JSON_VALUE)
@Api(value = "/api/v1/paymentMethods", produces = MediaType.APPLICATION_JSON_VALUE)
public class PaymentMethodsController {
    private final PaymentMethodsService paymentMethodsService;
    private final ModelMapper modelMapper;

    @Autowired
    public PaymentMethodsController(PaymentMethodsService paymentMethodsService, ModelMapper modelMapper) {
        this.paymentMethodsService = paymentMethodsService;
        this.modelMapper = modelMapper;
    }

    @GetMapping("/")
    @ApiOperation(value = "Get all Payment Methods", response = ApiResponse.class)
    public ApiResponse getAllPaymentMethods(){
        return new ApiResponse(200, "SUCCESS", paymentMethodsService.getAll());
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "Get a Payment Method by id", response = ApiResponse.class)
    public ApiResponse getPaymentMethodByID(@PathVariable("id") Long id){
        return new ApiResponse(200, "SUCCESS", paymentMethodsService.getOne(id));

    }

    @GetMapping("/role-by-name")
    @ApiOperation(value = "Get a Payment Method by its name", response = ApiResponse.class)
    public ApiResponse getPaymentMethodByName(@RequestParam("name") String name){
        return new ApiResponse(200, "SUCCESS", paymentMethodsService.findByPaymentMethod(name));
    }
    @DeleteMapping("/delete/{id}")
    @ApiOperation(value = "Delete a Payment Method by its id. Takes id as a path variable", response = ApiResponse.class)
    public ApiResponse deletePaymentMethod(@PathVariable("id") Long id){
        return new ApiResponse(200, "SUCCESS", paymentMethodsService.delete(id));
    }
    @PostMapping("/add")
    @ApiOperation(value = "Add a new Payment Method", response = ApiResponse.class)
    public ApiResponse addPaymentMethod(@RequestBody AddPaymentMethodsDto paymentMethodsDto){
        PaymentMethods paymentMethod = modelMapper.map(paymentMethodsDto, PaymentMethods.class);
        return new ApiResponse(201, "SUCCESS", paymentMethodsService.add(paymentMethod));
    }

    @PutMapping("/edit")
    @ApiOperation(value = "Update an existing Payment Method", response = ApiResponse.class)
    public ApiResponse updatePaymentMethod(@RequestBody UpdatePaymentMethodsDto paymentMethodsDto){
        PaymentMethods paymentMethod = modelMapper.map(paymentMethodsDto, PaymentMethods.class);
        return new ApiResponse(200, "SUCCESS", paymentMethodsService.update(paymentMethod));
    }
}
