package com.jhernandez.backend.bazaar.infrastructure.configuration;

/*
 * Class containing constant values used across the application.
 */
public class Values {

    // Swagger UI for API documentation
    public static final String SWAGGER_UI = "/swagger-ui/**";
    public static final String SWAGGER_UI_HTML = "/swagger-ui.html";
    public static final String API_DOCS = "/v3/api-docs/**";

    public static final String UPLOAD_DIR = "uploads/";
    public static final String CONTENT_TYPE_OCTET = "application/octet-stream";
    public static final String CONTENT_TYPE_JSON = "application/json";
    public static final String[] ALLOWED_METHODS = {"GET", "POST", "PUT", "DELETE"};
    public static final String[] ALLOWED_ORIGINS = {"*"};
    public static final String[] ALLOWED_HEADERS = {"Authorization", "Content-Type"};
    public static final String DISABLED_ITEM = "(DISABLED) ";
    public static final String PUT_REQUEST = "PUT";
    public static final String ARG_PRODUCT = "product";
    public static final String ARG_CATEGORY = "category";
    public static final String ARG_IMAGE = "image";

    // Backup Scripts Paths
    public static final String DB_BACKUP_SCRIPT = "src/main/resources/static/scripts/backup_db.sh";
    public static final String IMG_BACKUP_SCRIPT = "src/main/resources/static/scripts/backup_images.sh";
    public static final String DB_RESTORE_SCRIPT = "src/main/resources/static/scripts/restore_db.sh";
    public static final String IMG_RESTORE_SCRIPT = "src/main/resources/static/scripts/restore_images.sh";
    
    // API Endpoints
    public static final String VALIDATE_TOKEN = "/validate-token";
    public static final String PING = "/ping";
    
    public static final String USERS = "/api/users";
    public static final String USER_ID = USERS + "/{id}";
    public static final String REGISTER = USERS + "/register";

    public static final String ROLES = "/api/roles";

    public static final String CATEGORIES = "/api/categories";
    public static final String CATEGORIES_ENABLED = CATEGORIES + "/enabled";
    public static final String CATEGORIES_RANDOM = CATEGORIES + "/random";
    public static final String CATEGORY_ID = CATEGORIES + "/{id}";

    public static final String PRODUCTS = "/api/products";
    public static final String PRODUCTS_ENABLED = PRODUCTS + "/enabled";
    public static final String PRODUCT_ID = PRODUCTS + "/{id}";
    public static final String PRODUCTS_USER_ID = PRODUCTS + "/user/{userId}";
    public static final String PRODUCTS_CATEGORY_ID = PRODUCTS + "/category/{categoryId}";
    public static final String PRODUCTS_SEARCH_NAME = PRODUCTS + "/search/{name}";
    public static final String PRODUCTS_RECENT = PRODUCTS + "/recent";
    public static final String PRODUCTS_TOP_SELLING = PRODUCTS + "/top-selling";
    public static final String PRODUCTS_TOP_RATED = PRODUCTS + "/top-rated";
    public static final String PRODUCTS_DISCOUNTED = PRODUCTS + "/discounted";

    public static final String IMAGES = "/api/images";
    public static final String IMAGE_ID = IMAGES + "/{filename:.+}";

    public static final String ORDERS = "/api/orders";

    public static final String CART =  USER_ID + "/cart";

    public static final String PREFERENCES = USER_ID + "/preferences";

    public static final String STATUSES = "/api/statuses";

    public static final String PAYMENTS = "/api/payments";

    public static final String BACKUPS = "/api/backups";

    public static final String REVIEWS = "/api/reviews";
    public static final String REVIEW_PRODUCT_ID = REVIEWS + "/product/{productId}";
    public static final String REVIEW_USER_ID = REVIEWS + "/user/{userId}";

    public static final String MESSAGES = "/api/messages";
    public static final String MESSAGE_ID = MESSAGES + "/{id}";
    public static final String MESSAGES_RECIPIENT_ID = MESSAGES + "/recipient/{recipientId}";

}