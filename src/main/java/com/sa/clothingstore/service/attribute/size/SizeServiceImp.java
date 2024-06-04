package com.sa.clothingstore.service.attribute.size;

import com.sa.clothingstore.constant.APIStatus;
import com.sa.clothingstore.exception.BusinessException;
import com.sa.clothingstore.exception.ObjectAlreadyExistsException;
import com.sa.clothingstore.exception.ObjectNotFoundException;
import com.sa.clothingstore.dto.request.attribute.SizeRequest;
import com.sa.clothingstore.dto.response.attribute.SizeResponse;
import com.sa.clothingstore.model.attribute.Size;
import com.sa.clothingstore.repository.attribute.SizeRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class SizeServiceImp implements SizeService{
    private final SizeRepository sizeRepository;
    private final ModelMapper modelMapper;
    @Override
    public List<Size> getAllSize() {
        return sizeRepository.findAll();
    }
    @Override
    public SizeResponse createSize(SizeRequest sizeRequest) {
        Optional<Size> existingSize = sizeRepository.findByName(sizeRequest.getName());
        if (existingSize.isPresent()) {
            throw new BusinessException(APIStatus.SIZE_ALREADY_EXISTED);
        }
        return modelMapper
                .map(sizeRepository
                                .save(modelMapper
                                        .map(sizeRequest, Size.class))
                        , SizeResponse.class);
    }

    @Override
    public Size modifySize(Integer id, SizeRequest sizeRequest) {
        Size size = sizeRepository.findById(id)
                .orElseThrow(() -> new BusinessException(APIStatus.SIZE_NOT_FOUND)
                );
        String newName = sizeRequest.getName();
        if(sizeRequest.getName().equals(newName)){
            throw new BusinessException(APIStatus.SIZE_ALREADY_EXISTED);
        }
        size.setName(sizeRequest.getName());
        return sizeRepository.save(size);
    }

    @Override
    public void deleteSize(Integer id) {
        Size size = sizeRepository.findById(id)
                .orElseThrow(() -> new BusinessException(APIStatus.SIZE_NOT_FOUND)
                );
        sizeRepository.delete(size);
    }
}
