package com.sa.clothingstore.controller.category;

import com.sa.clothingstore.constant.APIConstant;
import com.sa.clothingstore.dto.request.category.BranchRequest;
import com.sa.clothingstore.model.category.Branch;
import com.sa.clothingstore.model.user.User;
import com.sa.clothingstore.service.category.branch.BranchService;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping(APIConstant.BRANCHES)
public class BranchController {
    private final BranchService branchService;
    @GetMapping
    public List<Branch> getAll() {
        return branchService.getAllBranch();
    }
    @GetMapping(APIConstant.SEARCH)
    public List<Branch> searchBranch(@RequestParam("keyword") String keyword){
        return branchService.searchBranch(keyword);
    }
    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public String createBranch(@RequestBody @Valid BranchRequest branchRequest) {
        branchService.createBranch(branchRequest);
        return "Branch was created successfully";
    }
    @PutMapping(APIConstant.BRANCH_ID)
    @ResponseStatus(HttpStatus.OK)
    public String modifyBranch(@PathVariable UUID branchId, @RequestBody @Valid BranchRequest branchRequest){
        branchService.modifyBranch(branchId, branchRequest);
        return "Branch was modified successfully";
    }
    @DeleteMapping(APIConstant.BRANCH_ID)
    @ResponseStatus(HttpStatus.OK)
    public String deleteBranch(@PathVariable UUID branchId){
        branchService.deleteBranch(branchId);
        return "Branch was delete successfully";
    }
}
