package com.D1.projectD1Campus.service;

import com.D1.projectD1Campus.dto.request.ProductoRequest;
import com.D1.projectD1Campus.dto.response.ProductoReponse;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.List;


public interface ProductoService  {
    ProductoReponse guardar(ProductoRequest dto);
    List<ProductoReponse> obtenerTodas();
    ProductoReponse obtenerPorId(Long id);
    ProductoReponse actualizar(Long id, ProductoRequest dto);
    void eliminarProducto(Long id);
    List<ProductoReponse> buscarPorNombre(String nombre);
    List<ProductoReponse> filtrarPrecioDeVentaMayorOIgualQue(BigDecimal precio);
    List<ProductoReponse> filtrarPrecioDeVentaMenorOIgualQue(BigDecimal precio);
    List<ProductoReponse> filtrarPrecioDeVentaEntre(BigDecimal precio1, BigDecimal precio2);
    List<ProductoReponse> diltrarPorNombreAndPrecioVentaMayos(String nombre, BigDecimal precio);
}
