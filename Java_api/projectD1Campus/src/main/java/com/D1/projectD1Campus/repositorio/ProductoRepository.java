package com.D1.projectD1Campus.repositorio;

import com.D1.projectD1Campus.modelo.Producto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Repository
public interface ProductoRepository extends JpaRepository<Producto, Long> {
    List<Producto> findByNombre(String nombre);
    List<Producto> findByPrecioVentaGreaterThanEqual(BigDecimal precio);
    List<Producto> findByPrecioVentaLessThanEqual(BigDecimal precio);
    List<Producto> findByPrecioVentaBetween(BigDecimal precio1, BigDecimal precio2);
    List<Producto> findByNombreAndPrecioVentaGreaterThanEqual(String nombre, BigDecimal precio);

}
