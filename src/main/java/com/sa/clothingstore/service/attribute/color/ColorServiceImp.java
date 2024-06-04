package com.sa.clothingstore.service.attribute.color;

import com.sa.clothingstore.constant.APIStatus;
import com.sa.clothingstore.exception.BusinessException;
import com.sa.clothingstore.exception.ObjectAlreadyExistsException;
import com.sa.clothingstore.exception.ObjectNotFoundException;
import com.sa.clothingstore.dto.request.attribute.ColorRequest;
import com.sa.clothingstore.dto.response.attribute.ColorResponse;
import com.sa.clothingstore.model.attribute.Color;
import com.sa.clothingstore.repository.attribute.ColorRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ColorServiceImp implements ColorService{
    private final ColorRepository colorRepository;
    private final ModelMapper modelMapper;
    @Override
    public ColorResponse createColor(ColorRequest colorRequest) {
        Optional<Color> existingColor = colorRepository.findByName(colorRequest.getName());
        if (existingColor.isPresent()) {
            throw new BusinessException(APIStatus.COLOR_ALREADY_EXISTED);
        }
        return modelMapper
                .map(colorRepository
                                .save(modelMapper
                                        .map(colorRequest, Color.class))
                        , ColorResponse.class);
    }

    @Override
    public Color modifyColor(Integer id, ColorRequest colorRequest) {
        Color color = colorRepository.findById(id)
                .orElseThrow(() -> new BusinessException(APIStatus.COLOR_NOT_FOUND)
                );
        String newName = colorRequest.getName();
        if(colorRequest.getName().equals(newName)){
            throw new BusinessException(APIStatus.COLOR_ALREADY_EXISTED);
        }
        color.setName(newName);
        return colorRepository.save(color);
    }

    @Override
    public void deleteColor(Integer id) {
        Color color = colorRepository.findById(id)
                .orElseThrow(() -> new BusinessException(APIStatus.COLOR_NOT_FOUND)
                );
        colorRepository.delete(color);
    }

    @Override
    public List<Color> getAllColor() {
        return colorRepository.findAll();
    }
}
