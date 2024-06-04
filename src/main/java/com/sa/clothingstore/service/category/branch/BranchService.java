package com.sa.clothingstore.service.category.branch;

import com.sa.clothingstore.dto.request.category.BranchRequest;
import com.sa.clothingstore.dto.response.category.BranchResponse;
import com.sa.clothingstore.model.category.Branch;

import java.util.List;
import java.util.UUID;

public interface BranchService {
    List<Branch> getAllBranch();
    BranchResponse createBranch(BranchRequest branchRequest);
    Branch modifyBranch(UUID id, BranchRequest branchRequest);
    void deleteBranch(UUID id);
    List<Branch> searchBranch(String keyword);
}
