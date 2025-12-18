package com.jhernandez.backend.bazaar.infrastructure.adapter.api.mapper.helper;

import static com.jhernandez.backend.bazaar.infrastructure.configuration.Values.DISABLED_ITEM;

import org.mapstruct.Mapper;
import org.mapstruct.Named;

import com.jhernandez.backend.bazaar.domain.model.Category;
import com.jhernandez.backend.bazaar.domain.model.Product;
import com.jhernandez.backend.bazaar.domain.model.User;

/**
 * Utility class to adjust the name of an item based on its enabled status.
 * If the item is disabled, it prefixes the name with a specific string.
 */
@Mapper(componentModel = "spring")
public class NameDisabler {

    @Named("adjustCategoryName")
    public static String adjustCategoryName(Category category) {
        return category.getEnabled() ? category.getName() : DISABLED_ITEM + category.getName();
    }

    @Named("adjustProductName")
    public static String adjust(Product product) {
        return product.getEnabled() ? product.getName() : DISABLED_ITEM + product.getName();
    }

    @Named("adjustUserName")
    public static String adjust(User user) {
        return user.getEnabled() ? user.getName() : DISABLED_ITEM + user.getName();
    }

}

