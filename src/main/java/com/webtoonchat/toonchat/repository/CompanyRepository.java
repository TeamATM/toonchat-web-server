package com.webtoonchat.toonchat.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.webtoonchat.toonchat.domain.chat.Company;

public interface CompanyRepository extends JpaRepository<Company, Long> {
}
