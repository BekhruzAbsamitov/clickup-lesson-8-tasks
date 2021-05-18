package uz.pdp.clickuplesson8tasks.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import uz.pdp.clickuplesson8tasks.entity.Space;
import uz.pdp.clickuplesson8tasks.entity.View;
import uz.pdp.clickuplesson8tasks.repository.ViewRepository;

import java.util.List;

@Service
public class ViewServiceImpl implements ViewService {
    final
    ViewRepository viewRepository;

    public ViewServiceImpl(ViewRepository viewRepository) {
        this.viewRepository = viewRepository;
    }

    @Override
    public List<View> getViews(Long spaceId) {
        return viewRepository.getAllBySpace(spaceId);
    }
}
