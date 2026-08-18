package com.D1.projectD1Campus.service.impl;

import com.D1.projectD1Campus.dto.request.VentaRequest;
import com.D1.projectD1Campus.dto.response.VentaResponse;
import com.D1.projectD1Campus.mapper.VentaMapper;
import com.D1.projectD1Campus.modelo.Producto;
import com.D1.projectD1Campus.modelo.Venta;
import com.D1.projectD1Campus.repositorio.VentaRepository;
import com.D1.projectD1Campus.service.VentaService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
@RequiredArgsConstructor
public class VentaServiceImpl implements VentaService {
    private final VentaRepository ventaRepository;
    private final VentaMapper ventaMapper;

    @Override
    public VentaResponse guardar(VentaRequest dto) {
        Venta venta = ventaMapper.dtoToEntity(dto);
        return ventaMapper.entityToDto(ventaRepository.save(venta));
    }

    @Override
    public List<VentaResponse> obtenerTodas() {
        List<Venta> ventas = ventaRepository.findAll();
        return ventas.stream().map(ventaMapper::entityToDto).toList();
    }

    @Override
    public VentaResponse obtenerPorId(Long id) {
        Venta venta = ventaRepository.findById(id).orElseThrow(() -> new RuntimeException("No se encontró la venta con id"));
        return ventaMapper.entityToDto(venta);
    }

    @Override
    public VentaResponse actualizar(Long id, VentaRequest dto) {
        Venta venta = ventaRepository.findById(id).orElseThrow(() ->  new RuntimeException("No se encontro venta a actualizar"));
        ventaMapper.updateDtoToEntity(venta, dto);
        return ventaMapper.entityToDto(ventaRepository.save(venta));
    }

    @Override
    public void eliminarVenta(Long id) {
        Venta venta = ventaRepository.findById(id).orElseThrow(() -> new RuntimeException("No se encontró ninguna venta"));
        ventaRepository.delete(venta);
    }

    @Override
    public List<VentaResponse> findByMesYAnio(Integer mes, Integer anio) {
        List<Venta> ventas = ventaRepository.findByMesYAnio(mes, anio);
        return ventas.stream().map(ventaMapper::entityToDto).toList();
    }

    @Override
    public List<VentaResponse> findByTotalGreaterThanEqual(BigDecimal precio) {
        List<Venta> ventas = ventaRepository.findByTotalGreaterThanEqual(precio);
        return ventas.stream().map(ventaMapper::entityToDto).toList();
    }

    @Override
    public List<VentaResponse> findByFechaBetween(String fechaInicio, String fechaFin) {
        List<Venta> ventas = ventaRepository.findByFechaBetween(fechaInicio, fechaFin);
       return ventas.stream().map(ventaMapper::entityToDto).toList();
    }
}
