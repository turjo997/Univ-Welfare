package com.suffixit.kieb.repository;

import com.suffixit.kieb.entities.AddressBook;
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;

@Repository
public interface AddressBookRepository extends JpaRepository<AddressBook,Integer> {

    AddressBook findAddressBookById(Integer addressBookId);
}
