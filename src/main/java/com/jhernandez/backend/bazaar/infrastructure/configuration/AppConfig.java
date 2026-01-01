package com.jhernandez.backend.bazaar.infrastructure.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.jhernandez.backend.bazaar.application.port.*;
import com.jhernandez.backend.bazaar.application.service.*;

/**
 * Spring configuration class for defining beans and application properties.
 * This class is responsible for creating and configuring the application services.
 */
@Configuration
@PropertySource("classpath:messages.properties")
public class AppConfig {

    @Bean
    UserServicePort userServicePort(
        UserRepositoryPort userRepositoryPort, PasswordEncoder passwordEncoder,
        UserRoleRepositoryPort userRoleRepositoryPort) {
        return new UserService(userRepositoryPort, passwordEncoder, userRoleRepositoryPort);
    }

    @Bean
    ProductServicePort productServicePort(
        ProductRepositoryPort productRepositoryPort, UserRepositoryPort userRepositoryPort,
        ImageServicePort imageServicePort) {
        return new ProductService(productRepositoryPort, userRepositoryPort, imageServicePort);
    }

    @Bean
    CategoryServicePort categoryServicePort(
        CategoryRepositoryPort categoryRepositoryPort, ProductRepositoryPort productRepositoryPort,
        ImageServicePort imageServicePort) {
        return new CategoryService(categoryRepositoryPort, productRepositoryPort, imageServicePort);
    }

    @Bean
    UserRoleServicePort userRoleServicePort(UserRoleRepositoryPort userRoleRepositoryPort) {
        return new UserRoleService(userRoleRepositoryPort);
    }

    @Bean
    ImageServicePort imageServicePort(ImageStoragePort imageStoragePort) {
        return new ImageService(imageStoragePort);
    }

    @Bean
    OrderServicePort orderServicePort(UserRepositoryPort userRepositoryPort,
     OrderRepositoryPort orderRepositoryPort, ItemRepositoryPort itemRepositoryPort,
     ProductRepositoryPort productRepositoryPort, MessageRepositoryPort messageRepositoryPort,
     MessageLocalizationPort messageLocalizationPort) {
        return new OrderService(userRepositoryPort, orderRepositoryPort, itemRepositoryPort,
            productRepositoryPort, messageRepositoryPort, messageLocalizationPort);
    }

    @Bean
    CartServicePort cartServicePort(UserRepositoryPort userRepositoryPort, ProductRepositoryPort productRepositoryPort) {
        return new CartService(userRepositoryPort, productRepositoryPort);
    }

    @Bean
    ReviewServicePort reviewServicePort(ReviewRepositoryPort reviewRepositoryPort,
        OrderRepositoryPort orderRepositoryPort, ProductRepositoryPort productRepositoryPort) {
        return new ReviewService(reviewRepositoryPort, orderRepositoryPort, productRepositoryPort);
    }

    @Bean
    PreferencesServicePort preferencesServicePort(UserRepositoryPort userRepositoryPort, ProductRepositoryPort productRepositoryPort) {
        return new PreferencesService(userRepositoryPort, productRepositoryPort);
    }

    @Bean
    PaymentServicePort paymentServicePort(PaymentProviderPort paymentProviderPort) {
        return new PaymentService(paymentProviderPort);
    }

    @Bean
    BackupServicePort backupServicePort(BackupStoragePort backupStoragePort, BackupRepositoryPort backupRepositoryPort) {
        return new BackupService(backupStoragePort, backupRepositoryPort);
    }

    @Bean
    MessageServicePort messageServicePort(MessageRepositoryPort messageRepositoryPort) {
        return new MessageService(messageRepositoryPort);
    }

}
