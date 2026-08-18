package com.D1.projectD1Campus.mapper;

import com.D1.projectD1Campus.dto.request.DetalleVentaRequest;
import com.D1.projectD1Campus.dto.response.DetalleVentaResponse;
import com.D1.projectD1Campus.dto.response.ProductoReponse;
import com.D1.projectD1Campus.dto.response.VentaResponse;
import com.D1.projectD1Campus.modelo.DetalleVenta;
import com.D1.projectD1Campus.modelo.Producto;
import com.D1.projectD1Campus.modelo.Venta;
import org.springframework.stereotype.Component;

@Component
public class DetalleVentaMapper {
    public DetalleVentaResponse entityToDto(DetalleVenta detalleVenta, VentaResponse ventaDto, ProductoReponse productoDto){
        if(detalleVenta == null) return null;
        return new DetalleVentaResponse(
                detalleVenta.getId(),
                ventaDto,
                productoDto,
                detalleVenta.getCantidad(),
                detalleVenta.getSubtotal()
        );
    }

    public DetalleVenta dtoToEntity (DetalleVentaRequest dto, Producto producto, Venta venta){
        if(dto == null || producto == null || venta == null) return null;
        DetalleVenta dv = new DetalleVenta();
        dv.setVenta(venta);
        dv.setProducto(producto);
        dv.setCantidad(dto.cantidad());
        dv.setCantidad(dto.cantidad());
        return  dv;
    }

    public void updateEntityToDto (DetalleVenta dv, DetalleVentaRequest dto, Venta venta, Producto producto){
        if(dv == null || dto == null || producto == null || venta == null) return;
        dv.setVenta(venta);
        dv.setProducto(producto);
        dv.setCantidad(dto.cantidad());
        dv.setCantidad(dto.cantidad());
    }
}
