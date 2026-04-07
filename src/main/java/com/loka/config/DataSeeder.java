package com.loka.config;

import com.loka.model.Product;
import com.loka.model.User;
import com.loka.repository.ProductRepository;
import com.loka.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
@RequiredArgsConstructor
@Slf4j
public class DataSeeder implements CommandLineRunner {

    private final UserRepository userRepository;
    private final ProductRepository productRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public void run(String... args) {
        seedUsers();
        seedProducts();
    }

    private void seedUsers() {
        if (!userRepository.existsByEmail("admin@loka.com")) {
            User admin = User.builder()
                    .name("Admin")
                    .email("admin@loka.com")
                    .password(passwordEncoder.encode("admin123"))
                    .role(User.Role.ADMIN)
                    .build();
            userRepository.save(admin);
            log.info("Admin user created: admin@loka.com / admin123");
        }

        if (!userRepository.existsByEmail("user@loka.com")) {
            User user = User.builder()
                    .name("Demo User")
                    .email("user@loka.com")
                    .password(passwordEncoder.encode("user123"))
                    .role(User.Role.USER)
                    .build();
            userRepository.save(user);
            log.info("Demo user created: user@loka.com / user123");
        }
    }

    private void seedProducts() {
        if (productRepository.count() == 0) {
            productRepository.save(Product.builder()
                    .name("Cashmere Silk Scarf")
                    .description("Luxurious hand-woven cashmere and silk blend scarf. Timeless elegance for every season.")
                    .price(new BigDecimal("185.00"))
                    .originalPrice(new BigDecimal("240.00"))
                    .category("Accessories")
                    .imageUrl("https://images.unsplash.com/photo-1601924994987-69e26d50dc26?w=600")
                    .stockQuantity(25)
                    .featured(true)
                    .build());

            productRepository.save(Product.builder()
                    .name("Italian Leather Handbag")
                    .description("Premium full-grain Italian leather handbag with gold-tone hardware. Handcrafted in Florence.")
                    .price(new BigDecimal("895.00"))
                    .originalPrice(new BigDecimal("1100.00"))
                    .category("Bags")
                    .imageUrl("https://images.unsplash.com/photo-1548036328-c9fa89d128fa?w=600")
                    .stockQuantity(12)
                    .featured(true)
                    .build());

            productRepository.save(Product.builder()
                    .name("Swiss Automatic Watch")
                    .description("Swiss-made automatic movement with sapphire crystal. Water resistant to 100m.")
                    .price(new BigDecimal("2450.00"))
                    .originalPrice(new BigDecimal("2900.00"))
                    .category("Watches")
                    .imageUrl("https://images.unsplash.com/photo-1523275335684-37898b6baf30?w=600")
                    .stockQuantity(8)
                    .featured(true)
                    .build());

            productRepository.save(Product.builder()
                    .name("Diamond Stud Earrings")
                    .description("0.5ct total weight VS1 clarity diamonds set in 18k white gold. Certified and hallmarked.")
                    .price(new BigDecimal("1250.00"))
                    .originalPrice(new BigDecimal("1600.00"))
                    .category("Jewelry")
                    .imageUrl("https://images.unsplash.com/photo-1535632066927-ab7c9ab60908?w=600")
                    .stockQuantity(15)
                    .featured(true)
                    .build());

            productRepository.save(Product.builder()
                    .name("Merino Wool Blazer")
                    .description("Tailored merino wool blazer with satin lining. Perfect for business and formal occasions.")
                    .price(new BigDecimal("420.00"))
                    .originalPrice(new BigDecimal("550.00"))
                    .category("Clothing")
                    .imageUrl("https://images.unsplash.com/photo-1507679799987-c73779587ccf?w=600")
                    .stockQuantity(20)
                    .featured(false)
                    .build());

            productRepository.save(Product.builder()
                    .name("Silk Evening Dress")
                    .description("Pure silk evening dress with hand-sewn beading. Dry clean only.")
                    .price(new BigDecimal("680.00"))
                    .originalPrice(new BigDecimal("850.00"))
                    .category("Clothing")
                    .imageUrl("https://images.unsplash.com/photo-1566479179817-60fee5f31a89?w=600")
                    .stockQuantity(10)
                    .featured(false)
                    .build());

            productRepository.save(Product.builder()
                    .name("Leather Oxford Shoes")
                    .description("Genuine calfskin leather Oxfords with leather sole. Handmade in England.")
                    .price(new BigDecimal("395.00"))
                    .originalPrice(new BigDecimal("495.00"))
                    .category("Shoes")
                    .imageUrl("https://images.unsplash.com/photo-1449505278894-297fdb3edbc1?w=600")
                    .stockQuantity(18)
                    .featured(false)
                    .build());

            productRepository.save(Product.builder()
                    .name("Gold Chain Necklace")
                    .description("18k yellow gold chain necklace, 45cm length, 2.5mm width. Lobster clasp closure.")
                    .price(new BigDecimal("750.00"))
                    .originalPrice(new BigDecimal("920.00"))
                    .category("Jewelry")
                    .imageUrl("https://images.unsplash.com/photo-1599643478518-a784e5dc4c8f?w=600")
                    .stockQuantity(22)
                    .featured(false)
                    .build());

            productRepository.save(Product.builder()
                    .name("Crocodile Leather Wallet")
                    .description("Genuine crocodile leather slim wallet with 8 card slots and bill compartment.")
                    .price(new BigDecimal("320.00"))
                    .originalPrice(new BigDecimal("400.00"))
                    .category("Accessories")
                    .imageUrl("https://images.unsplash.com/photo-1627123424574-724758594e93?w=600")
                    .stockQuantity(30)
                    .featured(false)
                    .build());

            productRepository.save(Product.builder()
                    .name("Cashmere Overcoat")
                    .description("Double-faced cashmere overcoat with horn buttons. Made in Italy from 100% pure cashmere.")
                    .price(new BigDecimal("1850.00"))
                    .originalPrice(new BigDecimal("2200.00"))
                    .category("Clothing")
                    .imageUrl("https://images.unsplash.com/photo-1544022613-e87ca75a784a?w=600")
                    .stockQuantity(7)
                    .featured(true)
                    .build());

            productRepository.save(Product.builder()
                    .name("Pearl Bracelet")
                    .description("Freshwater pearl bracelet with 14k gold clasp. 8mm pearls, 18cm length.")
                    .price(new BigDecimal("380.00"))
                    .originalPrice(new BigDecimal("480.00"))
                    .category("Jewelry")
                    .imageUrl("https://images.unsplash.com/photo-1611591437281-460bfbe1220a?w=600")
                    .stockQuantity(16)
                    .featured(false)
                    .build());

            productRepository.save(Product.builder()
                    .name("Suede Chelsea Boots")
                    .description("Premium suede Chelsea boots with elastic gore panel. Rubber sole for durability.")
                    .price(new BigDecimal("285.00"))
                    .originalPrice(new BigDecimal("360.00"))
                    .category("Shoes")
                    .imageUrl("https://images.unsplash.com/photo-1638247025967-b4e38f787b76?w=600")
                    .stockQuantity(14)
                    .featured(false)
                    .build());

            log.info("12 luxury products seeded successfully");
        }
    }
}
