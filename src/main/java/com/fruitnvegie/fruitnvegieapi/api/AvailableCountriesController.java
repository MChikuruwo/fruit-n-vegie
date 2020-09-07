package com.fruitnvegie.fruitnvegieapi.api;

import com.fruitnvegie.fruitnvegieapi.dto.AddCountriesDto;
import com.fruitnvegie.fruitnvegieapi.dto.UpdateCountriesDto;
import com.fruitnvegie.fruitnvegieapi.models.AvailableCountries;
import com.fruitnvegie.fruitnvegieapi.models.api.ApiResponse;
import com.fruitnvegie.fruitnvegieapi.services.AvailableCountriesService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping(value = "/api/v1/availableCountries", produces = MediaType.APPLICATION_JSON_VALUE)
@Api(value = "/api/v1/availableCountries", produces = MediaType.APPLICATION_JSON_VALUE)
public class AvailableCountriesController {

    private final AvailableCountriesService availableCountriesService;
    private final ModelMapper modelMapper;

    @Autowired
    public AvailableCountriesController(AvailableCountriesService availableCountriesService, ModelMapper modelMapper) {
        this.availableCountriesService = availableCountriesService;
        this.modelMapper = modelMapper;
    }

    @GetMapping("/")
    @ApiOperation(value = "Get all Available Countries", response = ApiResponse.class)
    public ApiResponse getAllCountries(){
        return new ApiResponse(200, "SUCCESS", availableCountriesService.getAll());
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "Get a Country by its id", response = ApiResponse.class)
    public ApiResponse getCountryByID(@PathVariable("id") Long id){
        return new ApiResponse(200, "SUCCESS", availableCountriesService.getOne(id));

    }

    @GetMapping("/byName")
    @ApiOperation(value = "Get available country  by its name", response = ApiResponse.class)
    public ApiResponse getDeliveryMethodByName(@RequestParam(value="country") String countryName){
        return new ApiResponse(200, "SUCCESS", availableCountriesService.findByCountryName(countryName));
    }
    @DeleteMapping("/delete/{id}")
    @ApiOperation(value = "Delete a country by its id. Takes id as a path variable", response = ApiResponse.class)
    public ApiResponse deleteCountryByID(@PathVariable("id") Long id){
        return new ApiResponse(200, "SUCCESS", availableCountriesService.delete(id));
    }

    @PostMapping("/add")
    @ApiOperation(value = "Add a new country", response = ApiResponse.class)
    public ApiResponse addDeliveryMethod(@RequestBody AddCountriesDto countriesDto){
        AvailableCountries availableCountries = modelMapper.map(countriesDto, AvailableCountries.class);
        return new ApiResponse(201, "SUCCESS", availableCountriesService.add(availableCountries));
    }

    @PutMapping("/edit")
    @ApiOperation(value = "Update an existing country", response = ApiResponse.class)
    public ApiResponse updateCountry(@RequestBody UpdateCountriesDto countriesDto){
        AvailableCountries availableCountries = modelMapper.map(countriesDto, AvailableCountries.class);
        return new ApiResponse(200, "SUCCESS", availableCountriesService.update(availableCountries));
    }


}
