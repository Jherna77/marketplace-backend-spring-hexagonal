package com.jhernandez.backend.bazaar.infrastructure.adapter.persistence.repository.mysql;

import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jhernandez.backend.bazaar.application.port.ItemRepositoryPort;
import com.jhernandez.backend.bazaar.domain.model.Item;
import com.jhernandez.backend.bazaar.infrastructure.adapter.persistence.entity.ItemEntity;
import com.jhernandez.backend.bazaar.infrastructure.adapter.persistence.mapper.ItemEntityMapper;
import com.jhernandez.backend.bazaar.infrastructure.adapter.persistence.repository.JpaItemRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/*
 * Repository adapter for Item.
 * Implements ItemRepositoryPort to provide persistence operations using JpaItemRepository.
 * Uses ItemEntityMapper to convert between domain model and entity.
 */
@Service
@RequiredArgsConstructor
@Slf4j
public class MysqlItemRepositoryAdapter implements ItemRepositoryPort{

    private final JpaItemRepository itemRepository;
    private final ItemEntityMapper itemEntityMapper;

    @Transactional
    @Override
    public Optional<Item> saveItem(Item item) {
        log.info("Saving item");
        ItemEntity itemEntity = itemEntityMapper.toEntity(item);
        return Optional.of(itemEntityMapper.toDomain(
                itemRepository.save(itemEntity)));
    }


}
