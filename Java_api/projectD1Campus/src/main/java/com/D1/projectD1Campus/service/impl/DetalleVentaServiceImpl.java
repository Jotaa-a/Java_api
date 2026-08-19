package com.D1.projectD1Campus.service.impl;

import com.D1.projectD1Campus.dto.request.DetalleVentaRequest;
import com.D1.projectD1Campus.dto.response.DetalleVentaResponse;
import com.D1.projectD1Campus.excepcion.BusinessRuleException;
import com.D1.projectD1Campus.mapper.DetalleVentaMapper;
import com.D1.projectD1Campus.mapper.ProductoMapper;
import com.D1.projectD1Campus.mapper.VentaMapper;
import com.D1.projectD1Campus.modelo.DetalleVenta;
import com.D1.projectD1Campus.repositorio.DetalleVentaRepository;
import com.D1.projectD1Campus.repositorio.ProductoRepository;
import com.D1.projectD1Campus.repositorio.VentaRepository;
import com.D1.projectD1Campus.service.DetalleVentaService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import com.D1.projectD1Campus.modelo.Venta;
import com.D1.projectD1Campus.modelo.Producto;

import java.math.BigDecimal;
import java.util.List;

@Service
@RequiredArgsConstructor
public class DetalleVentaServiceImpl implements DetalleVentaService {

    private final DetalleVentaRepository detalleVentaRepository;
    private final DetalleVentaMapper detalleVentaMapper;
    private final VentaRepository ventaRepository;
    private final ProductoRepository productoRepository;
    private final ProductoMapper productoMapper;
    private final VentaMapper ventaMapper;
    @Override
    public DetalleVentaResponse crear(DetalleVentaRequest dto) {
        Producto producto = productoRepository.findById(dto.productoId()).orElseThrow(() -> new EntityNotFoundException("No se encontro el producto"));
        Venta venta = ventaRepository.findById(dto.ventaId()).orElseThrow(() -> new RuntimeException("No existe la venta a relacionar"));
        DetalleVenta detalleVenta = detalleVentaMapper.dtoToEntity(dto, producto, venta);
        return detalleVentaMapper.entityToDto(detalleVentaRepository.save(detalleVenta), ventaMapper.entityToDto(venta), productoMapper.entityToDto(producto));
    }

    @Override
    public DetalleVentaResponse actualizar(Long id, DetalleVentaRequest dto) {
        DetalleVenta detalleVenta = detalleVentaRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("No se encuentra el detalle a actualizar"));
        Producto producto = productoRepository.findById(dto.productoId()).orElseThrow(() -> new RuntimeException("No se encontro el producto"));
        Venta venta = ventaRepository.findById(dto.ventaId()).orElseThrow(() -> new RuntimeException("No existe la venta a relacionar"));
        detalleVentaMapper.updateEntityToDto(detalleVenta, dto, venta, producto);
        return detalleVentaMapper.entityToDto(detalleVentaRepository.save(detalleVenta), ventaMapper.entityToDto(venta), productoMapper.entityToDto(producto));
    }

    @Override
    public void eliminar(Long id) {
        DetalleVenta detalleVenta = detalleVentaRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Error, detalle de venta a eliminar no encontrado"));
        detalleVentaRepository.delete(detalleVenta);
    }

    @Override
    public List<DetalleVentaResponse> listarTodos() {
        List<DetalleVenta> detalleVentas = detalleVentaRepository.findAll();
        return detalleVentas.stream().map(
                p -> detalleVentaMapper.entityToDto(p,
                        ventaMapper.entityToDto(p.getVenta()),
                        productoMapper.entityToDto(p.getProducto())
                )
        ).toList();
    }

    @Override
    public DetalleVentaResponse buscarPorId(Long id) {
        DetalleVenta detalleVenta = detalleVentaRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Error, codigo de detalle venta no existe"));
        return detalleVentaMapper.entityToDto(detalleVenta,
                ventaMapper.entityToDto(detalleVenta.getVenta()),
                productoMapper.entityToDto(detalleVenta.getProducto())
        );
    }

    @Override
    public List<DetalleVentaResponse> buscarPorIdProducto(Long id) {
        return detalleVentaRepository.findByProductoId(id).stream().map(
                p -> detalleVentaMapper.entityToDto(p,
                        ventaMapper.entityToDto(p.getVenta()),
                        productoMapper.entityToDto(p.getProducto()))
        ).toList();
    }

    @Override
    public List<DetalleVentaResponse> buscarPorIdVenta(Long id) {
        return detalleVentaRepository.findByVentaId(id).stream().map(
                p -> detalleVentaMapper.entityToDto(p,
                        ventaMapper.entityToDto(p.getVenta()),
                        productoMapper.entityToDto(p.getProducto()))
        ).toList();
    }

    @Override
    public List<DetalleVentaResponse> filtrarPorCantidadesmenorOIgualQue(BigDecimal cantidad) {
        return detalleVentaRepository.findByCantidadLessThanEqual(cantidad).stream().map(
                p -> detalleVentaMapper.entityToDto(p,
                        ventaMapper.entityToDto(p.getVenta()),
                        productoMapper.entityToDto(p.getProducto()))
        ).toList();
    }
}
