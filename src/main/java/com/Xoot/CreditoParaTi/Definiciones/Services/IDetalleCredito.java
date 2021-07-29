package com.Xoot.CreditoParaTi.Definiciones.Services;

import com.Xoot.CreditoParaTi.entity.DTO.DetalleCredito;

public interface IDetalleCredito {
    DetalleCredito findByCreditID(Integer creditID);
}
