package com.sa.clothingstore.service.category.branch;

import com.sa.clothingstore.constant.APIStatus;
import com.sa.clothingstore.dto.request.category.BranchRequest;
import com.sa.clothingstore.dto.response.category.BranchResponse;
import com.sa.clothingstore.exception.BusinessException;
import com.sa.clothingstore.exception.ObjectNotFoundException;
import com.sa.clothingstore.model.category.Branch;
import com.sa.clothingstore.repository.category.BranchRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class BranchServiceImp implements BranchService{
    private final BranchRepository branchRepository;
    @Autowired
    private final ModelMapper modelMapper;
    @Override
    public List<Branch> getAllBranch() {
        return branchRepository.findAll();
    }

    @Override
    public BranchResponse createBranch(BranchRequest branchRequest) {
        return modelMapper
                .map(branchRepository
                    .save(modelMapper
                        .map(branchRequest, Branch.class))
                    , BranchResponse.class);
    }

    @Override
    public Branch modifyBranch(UUID id, BranchRequest branchRequest) {
        Branch branch = branchRepository.findById(id)
                .orElseThrow(() -> new BusinessException(APIStatus.BRANCH_NOT_FOUND)
        );
        branch.setName(branchRequest.getName());
        return branchRepository.save(branch);
    }

    @Override
    public void deleteBranch(UUID id) {
        Branch branch = branchRepository.findById(id)
                .orElseThrow(() -> new BusinessException(APIStatus.BRANCH_NOT_FOUND)
                );
        branchRepository.delete(branch);
    }

    @Override
    public List<Branch> searchBranch(String keyword) {
        return branchRepository.searchBranch(keyword);
    }
}
