package com.mialquiler.demo.repository;

import com.mialquiler.demo.entity.Contrato;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ContratoRepository extends JpaRepository<Contrato, Long> {
}
