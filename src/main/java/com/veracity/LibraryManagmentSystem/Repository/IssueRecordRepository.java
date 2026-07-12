package com.veracity.LibraryManagmentSystem.Repository;

import com.veracity.LibraryManagmentSystem.Entity.IssueRecord;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IssueRecordRepository extends JpaRepository<IssueRecord,Long> {
}
