package com.example.sdui.pagelayout.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.sdui.pagelayout.domain.PageLayout;

public interface PageLayoutRepository extends JpaRepository<PageLayout, Long> {
}
