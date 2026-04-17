package com.fixit.server.domain.customer;

import com.fixit.server.domain.shop.Shop;
import com.fixit.server.domain.shop.ShopRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CustomerService {

    private final CustomerRepository customerRepository;
    private final ShopRepository shopRepository;

    @Transactional
    public void register(Long shopId, CustomerRequestDto dto) {
        Shop shop = shopRepository.findById(shopId)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 업체입니다."));

        Customer customer = Customer.builder()
                .shop(shop)
                .name(dto.getName())
                .phone(dto.getPhone())
                .build();

        customerRepository.save(customer);
    }

    @Transactional(readOnly = true)
    public Optional<Customer> findByPhone(Long shopId, String phone) {
        return customerRepository.findByPhoneAndShopId(phone, shopId);
    }
}