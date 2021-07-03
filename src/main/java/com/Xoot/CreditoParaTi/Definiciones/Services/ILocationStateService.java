package com.Xoot.CreditoParaTi.Definiciones.Services;

import java.util.List;

import com.Xoot.CreditoParaTi.entity.LocationState;

public interface ILocationStateService {

	LocationState save(LocationState locationState);

	List<LocationState> findByListName(List<String> lstNameState);
}
