package com.D1.projectD1Campus.mapper;

import com.D1.projectD1Campus.dto.request.ProductoRequest;
import com.D1.projectD1Campus.dto.response.ProductoReponse;
import com.D1.projectD1Campus.modelo.Producto;
import org.springframework.stereotype.Component;

@Component
public class ProductoMapper {
    public ProductoReponse entityToDto(Producto producto){
        if(producto == null) return null;
        return new ProductoReponse(
                producto.getId(),
                producto.getNombre(),
                producto.getPrecioCompra(),
                producto.getPrecioVenta()
        );
    }

    public Producto dtoToEntity(ProductoRequest dto){
        if(dto==null) return null;
        Producto producto = new Producto();
        producto.setNombre(dto.nombre());
        producto.setDescripcion(dto.descripcion());
        producto.setPrecioCompra(dto.precioCompra());
        producto.setPrecioVenta(dto.precioVenta());
        return producto;
    }

    public void updateEntityToDto(Producto producto, ProductoRequest dto){
        if(dto==null || producto == null) return;
        producto.setNombre(dto.nombre());
        producto.setDescripcion(dto.descripcion());
        producto.setPrecioCompra(dto.precioCompra());
        producto.setPrecioVenta(dto.precioVenta());
    }
}
