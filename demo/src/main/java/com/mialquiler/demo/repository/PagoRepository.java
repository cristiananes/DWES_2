package com.mialquiler.demo.repository;

import com.mialquiler.demo.entity.Pago;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PagoRepository extends JpaRepository<Pago, Long> {
}
