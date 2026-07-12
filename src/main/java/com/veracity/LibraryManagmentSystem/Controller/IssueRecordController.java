package com.veracity.LibraryManagmentSystem.Controller;

import com.veracity.LibraryManagmentSystem.DTO.IssueRequest;
import com.veracity.LibraryManagmentSystem.DTO.IssueResponse;
import com.veracity.LibraryManagmentSystem.Entity.IssueRecord;
import com.veracity.LibraryManagmentSystem.Service.IssueRecordService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/issues")
public class IssueRecordController {

    private final IssueRecordService issueRecordService;

    public IssueRecordController(IssueRecordService issueRecordService) {
        this.issueRecordService = issueRecordService;
    }



    @GetMapping
    public ResponseEntity<List<IssueResponse>> getAllIssueRecords(){

        return ResponseEntity.ok(issueRecordService.getAllIssueRecord());

    }


    @GetMapping("/{issueId}")
    public ResponseEntity<IssueResponse> getIssuesById(@PathVariable Long issueId){

        return ResponseEntity.ok(issueRecordService.getIssueRecordById(issueId));

    }

   @PostMapping
    public ResponseEntity<IssueRecord> issueBook( @RequestBody IssueRequest issueRequest){

       IssueRecord issueRecord = issueRecordService.issueBook(issueRequest);
        return new ResponseEntity<>(issueRecord, HttpStatus.CREATED);

    }

    @PutMapping("/{issueId}/return")
    public ResponseEntity<IssueRecord> returnBook(@PathVariable Long issueId) {

        IssueRecord issueRecord = issueRecordService.returnBook(issueId);
        return ResponseEntity.ok(issueRecord);


    }



}
