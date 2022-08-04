package com.example.redisexample;

import com.example.redisexample.domain.Product;
import com.example.redisexample.domain.ProductRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertTrue;

@DisplayName("Redis CRUD Test")
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class RedisCrudTest2 {

    @Autowired
    private ProductRepository productRepository;

    private Product product;

    @BeforeEach
    void setUp() {
        product = Product.builder()
                .id("P0001")
                .name("테스트_상품")
                .price(20000L)
                .build();
    }

    @AfterEach
    void teardown() {
        productRepository.deleteById(product.getId());
    }

    @Test
    @DisplayName("Redis 에 데이터를 저장하면 정상적으로 조회되어야 한다")
    void redis_save_test() {
        productRepository.save(product);

        Product persistProduct = productRepository.findById(product.getId())
                .orElseThrow(RuntimeException::new);

        assertThat(persistProduct.getId()).isEqualTo(product.getId());
        assertThat(persistProduct.getName()).isEqualTo(product.getName());
        assertThat(persistProduct.getPrice()).isEqualTo(product.getPrice());
    }

    @Test
    @DisplayName("Redis 에 데이터를 수정하면 정상적으로 수정되어야 한다")
    void redis_update_test() {
        productRepository.save(product);

        Product persistProduct = productRepository.findById(product.getId())
                .orElseThrow(RuntimeException::new);

        persistProduct.changePrice(35000L);
        productRepository.save(persistProduct);

        assertThat(persistProduct.getPrice()).isEqualTo(35000L);
    }

    @Test
    @DisplayName("Redis 에 데이터를 삭제하면 정상적으로 삭제되어야 한다")
    void redis_delete_test() {
        productRepository.save(product);

        productRepository.delete(product);

        Optional<Product> deletedProduct = productRepository.findById(product.getId());

        assertTrue(deletedProduct.isEmpty());
    }
}
