package com.D1.projectD1Campus.modelo;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Entity
@Table(name = "detalle_venta")
@Getter @Setter
@AllArgsConstructor @NoArgsConstructor
public class DetalleVenta {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @ManyToOne
    @JoinColumn(name = "venta_fk", nullable = false)
    private Venta venta;

    @ManyToOne
    @JoinColumn(name = "produco_fk", nullable = false)
    private Producto producto;

    @Column(nullable = false)
    private BigDecimal cantidad;

    @Column(nullable = false)
    private BigDecimal subtotal;
}
