package com.Xoot.CreditoParaTi.services.interfaces;

import com.Xoot.CreditoParaTi.dto.DetalleCredito;

public interface IDetalleCredito {
    DetalleCredito findByCreditID(Integer creditID);
}
