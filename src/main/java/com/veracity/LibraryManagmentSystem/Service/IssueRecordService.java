package com.veracity.LibraryManagmentSystem.Service;


import com.veracity.LibraryManagmentSystem.DTO.IssueRequest;
import com.veracity.LibraryManagmentSystem.DTO.IssueResponse;
import com.veracity.LibraryManagmentSystem.Entity.Book;
import com.veracity.LibraryManagmentSystem.Entity.IssueRecord;
import com.veracity.LibraryManagmentSystem.Entity.User;
import com.veracity.LibraryManagmentSystem.Repository.BookRepository;
import com.veracity.LibraryManagmentSystem.Repository.IssueRecordRepository;
import com.veracity.LibraryManagmentSystem.Repository.UserRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class IssueRecordService {

    private final UserRepository userRepository;
    private final BookRepository bookRepository;
    private  final IssueRecordRepository issueRecordRepository;


    public IssueRecordService(IssueRecordRepository issueRecordRepository, UserRepository userRepository, BookRepository bookRepository) {
        this.issueRecordRepository = issueRecordRepository;
        this.userRepository = userRepository;
        this.bookRepository = bookRepository;
    }


    public IssueRecord issueBook(IssueRequest issueRequest){

        User user = userRepository.findById(issueRequest.getUserId())
                .orElseThrow(()->new RuntimeException("User Not Found"));

        Book book = bookRepository.findById(issueRequest.getBookId())
                .orElseThrow(()->new RuntimeException("Book Not found"));

        IssueRecord issueRecord = new IssueRecord();
        issueRecord.setUser(user);
        issueRecord.setBook(book);
        issueRecord.setIssueDate(LocalDate.now());
        issueRecord.setDueDate(LocalDate.now().plusDays(7));
        issueRecord.setReturned(false);

        return issueRecordRepository.save(issueRecord);

    }

    public IssueRecord returnBook(Long issueId) {

        IssueRecord issueRecord = issueRecordRepository.findById(issueId)
                .orElseThrow(() -> new RuntimeException("Issue Record Not Found"));

        issueRecord.setReturned(true);

        return issueRecordRepository.save(issueRecord);
    }

    public List<IssueResponse> getAllIssueRecord(){
        return issueRecordRepository.findAll()
                .stream()
                .map(issueRecord -> new IssueResponse(
                        issueRecord.getId(),
                        issueRecord.getIssueDate(),
                        issueRecord.getDueDate(),

                        issueRecord.getReturned(),

                        issueRecord.getUser().getId(),
                        issueRecord.getUser().getUsername(),
                        issueRecord.getUser().getEmail(),

                        issueRecord.getBook().getId(),
                        issueRecord.getBook().getTitle(),
                        issueRecord.getBook().getAuthor()
                ))
                .toList();

    }

    public IssueResponse getIssueRecordById(Long issueId){
        IssueRecord issueRecord = issueRecordRepository.findById(issueId).orElseThrow(
                ()->new RuntimeException("Issue Record Not found"));

        return new IssueResponse(

                issueRecord.getId(),
                issueRecord.getIssueDate(),
                issueRecord.getDueDate(),
                issueRecord.getReturned(),

                issueRecord.getUser().getId(),
                issueRecord.getUser().getUsername(),
                issueRecord.getUser().getEmail(),

                issueRecord.getBook().getId(),
                issueRecord.getBook().getTitle(),
                issueRecord.getBook().getAuthor()
        );

    }

}
