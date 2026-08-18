package com.D1.projectD1Campus.service;

import com.D1.projectD1Campus.dto.request.VentaRequest;
import com.D1.projectD1Campus.dto.response.VentaResponse;
import com.D1.projectD1Campus.modelo.Venta;

import java.math.BigDecimal;
import java.util.List;

public interface VentaService {
    VentaResponse guardar(VentaRequest dto);
    List<VentaResponse> obtenerTodas();
    VentaResponse obtenerPorId(Long id);
    VentaResponse actualizar(Long id, VentaRequest dto);
    void eliminarVenta(Long id);
    List<VentaResponse> findByMesYAnio(Integer mes, Integer anio);
    List<VentaResponse> findByTotalGreaterThanEqual(BigDecimal precio);
    List<VentaResponse> findByFechaBetween(String fechaInicio, String fechaFin);
}
