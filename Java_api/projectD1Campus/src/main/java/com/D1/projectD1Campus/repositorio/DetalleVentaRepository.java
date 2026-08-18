package com.D1.projectD1Campus.repositorio;

import com.D1.projectD1Campus.dto.request.DetalleVentaRequest;
import com.D1.projectD1Campus.modelo.DetalleVenta;
import com.D1.projectD1Campus.modelo.Venta;
import org.springframework.data.jpa.repository.JpaRepository;

import java.math.BigDecimal;
import java.util.List;

public interface DetalleVentaRepository extends JpaRepository<DetalleVenta, Long> {

    List<DetalleVenta> findByProductoId(Long id);
    List<DetalleVenta> findByVentaId(Long id);
    List<DetalleVenta> findByCantidadLessThanEqual(BigDecimal cantidad);

}
