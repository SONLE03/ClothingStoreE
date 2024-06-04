package com.sa.clothingstore.controller.attribute;

import com.sa.clothingstore.constant.APIConstant;
import com.sa.clothingstore.dto.request.attribute.SizeRequest;
import com.sa.clothingstore.model.attribute.Color;
import com.sa.clothingstore.model.attribute.Size;
import com.sa.clothingstore.service.attribute.size.SizeService;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(APIConstant.SIZES)
public class SizeController {
    private final SizeService sizeService;
    @GetMapping
    public List<Size> getAll() {
        return sizeService.getAllSize();
    }
    @PostMapping
    @ResponseStatus(HttpStatus.OK)
    public String createSize(@RequestBody @Valid SizeRequest SizeRequest)  {
        sizeService.createSize(SizeRequest);
        return "Size was created successfully";
    }
    @PutMapping(APIConstant.SIZE_ID)
    @ResponseStatus(HttpStatus.OK)
    public String modifySize(@PathVariable Integer id, @RequestBody @Valid SizeRequest SizeRequest){
        sizeService.modifySize(id, SizeRequest);
        return "Size was modified successfully";
    }
    @DeleteMapping(APIConstant.SIZE_ID)
    @ResponseStatus(HttpStatus.OK)
    public String deleteSize(@PathVariable Integer id) {
        sizeService.deleteSize(id);
        return "Size was deleted successfully";
    }
}
