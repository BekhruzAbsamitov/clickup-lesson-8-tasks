package uz.pdp.clickuplesson8tasks.service;

import uz.pdp.clickuplesson8tasks.entity.Space;
import uz.pdp.clickuplesson8tasks.entity.View;

import java.util.List;

public interface ViewService {

    List<View> getViews(Long spaceId);
}
