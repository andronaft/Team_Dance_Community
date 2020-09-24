package com.zuk.repository;

import com.zuk.model.DeepLink;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DeepLinkRepository extends JpaRepository<DeepLink, Long> {
List<DeepLink> findAllByFrom(String from);
}
