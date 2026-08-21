package com.D1.projectD1Campus.service.impl;

import com.D1.projectD1Campus.dto.request.ProductoRequest;
import com.D1.projectD1Campus.dto.response.ProductoReponse;
import com.D1.projectD1Campus.excepcion.BusinessRuleException;
import com.D1.projectD1Campus.mapper.ProductoMapper;
import com.D1.projectD1Campus.modelo.Producto;
import com.D1.projectD1Campus.repositorio.ProductoRepository;
import com.D1.projectD1Campus.service.ProductoService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
@RequiredArgsConstructor //inyeccion de dependencias por constructor
public class ProdutoServiceImpl implements ProductoService {
    private final ProductoRepository productoRepository;
    private final ProductoMapper productoMapper;

    @Override
    public ProductoReponse guardar(ProductoRequest dto) {
        Producto producto = productoMapper.dtoToEntity(dto);
        if(producto.getPrecioCompra().compareTo(producto.getPrecioVenta()) > 0){
            throw  new BusinessRuleException(
                    "El precio de compra no puede superar el precio de venta"
            );
        }
        return productoMapper.entityToDto(productoRepository.save(producto));
    }

    @Override
    public List<ProductoReponse> obtenerTodas() {
        List<Producto> productos = productoRepository.findAll();
        return productos.stream().map(productoMapper::entityToDto).toList();
    }

    @Override
    public ProductoReponse obtenerPorId(Long id) {
        Producto producto = productoRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("No se encontró el producto con id"));
        return productoMapper.entityToDto(producto);
    }

    @Override
    public ProductoReponse actualizar(Long id, ProductoRequest dto) {
        Producto producto = productoRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("No se encontró ninguna entidad"));
        productoMapper.updateEntityToDto(producto, dto);
        if(producto.getPrecioCompra().compareTo(producto.getPrecioVenta()) > 0){
            throw new BusinessRuleException(
                    "El precio de compra no puede ser mayor al precio de venta"
            );
        }
        return productoMapper.entityToDto(productoRepository.save(producto));
    }

    @Override
    public void eliminarProducto(Long id) {
        Producto producto = productoRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("No se encontró ningun producto"));
        productoRepository.delete(producto);
    }

    @Override
    public List<ProductoReponse> buscarPorNombre(String nombre) {
        List<Producto> productos = productoRepository.findByNombre(nombre);
        return productos.stream().map(productoMapper::entityToDto).toList();
    }

    @Override
    public List<ProductoReponse> filtrarPrecioDeVentaMayorOIgualQue(BigDecimal precio) {
        List<Producto> productos = productoRepository.findByPrecioVentaGreaterThanEqual(precio);
        return productos.stream().map(productoMapper::entityToDto).toList();
    }

    @Override
    public List<ProductoReponse> filtrarPrecioDeVentaMenorOIgualQue(BigDecimal precio) {
        List<Producto> productos = productoRepository.findByPrecioVentaLessThanEqual(precio);
        return productos.stream().map(productoMapper::entityToDto).toList();
    }

    @Override
    public List<ProductoReponse> filtrarPrecioDeVentaEntre(BigDecimal precio1, BigDecimal precio2) {
        List<Producto> productos = productoRepository.findByPrecioVentaBetween(precio1, precio2);
        return productos.stream().map(productoMapper::entityToDto).toList();
    }

    @Override
    public List<ProductoReponse> diltrarPorNombreAndPrecioVentaMayos(String nombre, BigDecimal precio) {
        List<Producto> productos = productoRepository.findByNombreAndPrecioVentaGreaterThanEqual(nombre, precio);
        return productos.stream().map(productoMapper::entityToDto).toList();
    }
}
