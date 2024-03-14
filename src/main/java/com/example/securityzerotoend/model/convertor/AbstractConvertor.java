package com.example.securityzerotoend.model.convertor;

import com.example.securityzerotoend.exceptionhandlers.exceptions.ServiceException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.stream.Collectors;

public interface AbstractConvertor<E, D, SR> {
    E convertDto(D d);

    D convertEntity(E e);

    SR pagingSrv(E e);

    SR convertSrv(E e);

    default List<E> convertDto(List<D> dList) {
        if (dList != null) {
            return dList.stream()
                    .map(this::convertDto)
                    .collect(Collectors.toList());
        }
        return null;
    }

    default List<D> convertEntity(List<E> dList) {
        if (dList != null) {
            return dList.stream()
                    .map(this::convertEntity)
                    .collect(Collectors.toList());
        }
        return null;
    }

    default List<SR> convertSrv(List<E> sList) {
        if (sList != null) {
            return sList.stream()
                    .map(this::convertSrv)
                    .collect(Collectors.toList());
        }
        return null;
    }

    default Page<SR> pagingSrv(List<SR> sList, Pageable pageable) throws ServiceException {
        if (sList != null) {
            int totalElements = sList.size();
            int start = (int) pageable.getOffset();
            int end = Math.min((start + pageable.getPageSize()), totalElements);
            try {
                List<SR> convertedList = sList.subList(start, end);
                return new PageImpl<>(convertedList, pageable, totalElements);
            } catch (IllegalArgumentException e) {
                throw new ServiceException("page-is-empty");
            }

        }
        return null;
    }
}
