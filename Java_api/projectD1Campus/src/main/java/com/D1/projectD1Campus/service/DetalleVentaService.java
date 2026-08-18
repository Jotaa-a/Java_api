package com.D1.projectD1Campus.service;

import com.D1.projectD1Campus.dto.request.DetalleVentaRequest;
import com.D1.projectD1Campus.dto.response.DetalleVentaResponse;

import java.math.BigDecimal;
import java.util.List;

public interface DetalleVentaService {
    DetalleVentaResponse crear(DetalleVentaRequest dto);
    DetalleVentaResponse actualizar(Long id, DetalleVentaRequest dto);
    void eliminar(Long id);
    List<DetalleVentaResponse> listarTodos();
    DetalleVentaResponse buscarPorId(Long id);
    List<DetalleVentaResponse> buscarPorIdProducto (Long id);
    List<DetalleVentaResponse> buscarPorIdVenta(Long id);
    List<DetalleVentaResponse> filtrarPorCantidadesmenorOIgualQue(BigDecimal cantidad);
}
