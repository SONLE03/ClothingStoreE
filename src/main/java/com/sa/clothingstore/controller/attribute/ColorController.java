package com.sa.clothingstore.controller.attribute;

import com.sa.clothingstore.constant.APIConstant;
import com.sa.clothingstore.dto.request.attribute.ColorRequest;
import com.sa.clothingstore.model.attribute.Color;
import com.sa.clothingstore.service.attribute.color.ColorService;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping(APIConstant.COLORS)
public class ColorController {
    private final ColorService colorService;
    @GetMapping
    public List<Color> getAll() {
        return colorService.getAllColor();
    }
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public String createColor(@RequestBody @Valid ColorRequest colorRequest) {
        colorService.createColor(colorRequest);
        return "Color was created successfully";
    }
    @PutMapping(APIConstant.COLOR_ID)
    @ResponseStatus(HttpStatus.OK)
    public String modifyColor(@PathVariable Integer id, @RequestBody @Valid ColorRequest colorRequest) {
        colorService.modifyColor(id, colorRequest);
        return "Color was modified successfully";
    }
    @DeleteMapping(APIConstant.COLOR_ID)
    @ResponseStatus(HttpStatus.OK)
    public String deleteColor(@PathVariable Integer id) {
        colorService.deleteColor(id);
        return "Color was deleted successfully";
    }
}
