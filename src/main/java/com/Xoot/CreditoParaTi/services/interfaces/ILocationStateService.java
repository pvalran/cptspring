package com.Xoot.CreditoParaTi.services.interfaces;

import java.util.List;

import com.Xoot.CreditoParaTi.entity.app.LocationState;

public interface ILocationStateService {

	LocationState save(LocationState locationState);

	List<LocationState> findAllActive();

	List<LocationState> findByListName(List<String> lstNameState);
}
