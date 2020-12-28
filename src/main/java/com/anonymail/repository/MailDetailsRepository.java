package com.anonymail.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.anonymail.model.MailDetails;

@Repository
public interface MailDetailsRepository extends JpaRepository<MailDetails, Long> {

}
