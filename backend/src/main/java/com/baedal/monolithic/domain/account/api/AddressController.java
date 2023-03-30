package com.baedal.monolithic.domain.account.api;

import com.baedal.monolithic.domain.account.application.AddressService;
import com.baedal.monolithic.domain.account.dto.AddressDto;
import com.baedal.monolithic.global.util.AccountId;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/users/my/address")
@RequiredArgsConstructor
public class AddressController {

    private final AddressService addressService;

    @GetMapping
    public ResponseEntity<AddressDto.GetRes> findAll(@AccountId Long accountId) {
        return ResponseEntity.ok()
                .body(new AddressDto.GetRes(addressService.findAllAddress(accountId)));
    }

    @GetMapping("/search")
    public ResponseEntity<AddressDto.SearchRes> search(@RequestParam String addressKeyword) {
        return ResponseEntity.ok()
                .body(new AddressDto.SearchRes(addressService.searchAllAddress(addressKeyword)));
    }

    @PutMapping
    public ResponseEntity<Void> update(@AccountId Long accountId,
                                                    @RequestBody AddressDto.PutReq addressPutReq) {
        addressService.updateAddress(accountId, addressPutReq);
        return ResponseEntity.noContent().build();
    }

    @PostMapping
    public ResponseEntity<Void> create(@AccountId Long accountId,
                                                    @RequestBody AddressDto.PostReq addressPutReq) {
        Long userAddressId = addressService.createUserAddress(accountId, addressPutReq);

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest().path("/{id}")
                .buildAndExpand(userAddressId).toUri();

        return ResponseEntity.created(location).build();
    }

    @DeleteMapping
    public ResponseEntity<Void> delete(@AccountId Long accountId, @AccountId Long userAddressId) {
        addressService.deleteUserAddress(accountId, userAddressId);
        return ResponseEntity.noContent().build();
    }

}
