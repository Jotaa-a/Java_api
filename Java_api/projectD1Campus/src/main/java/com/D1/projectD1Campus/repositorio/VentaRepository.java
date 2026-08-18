package com.D1.projectD1Campus.repositorio;

import com.D1.projectD1Campus.modelo.Venta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;

@Repository
public interface VentaRepository extends JpaRepository<Venta, Long> {
    @Query("""
            Select v from Venta v
            where month(v.fecha) = :mes
            and year(v.fecha) = :anio
            """)
    List<Venta> findByMesYAnio (
            @Param("mes") int mes,
            @Param("anio") int anio
    );

    List<Venta>findByTotalGreaterThanEqual (BigDecimal precio);
    List<Venta> findByFechaBetween(String fechaInicio, String fechaFin);
}
