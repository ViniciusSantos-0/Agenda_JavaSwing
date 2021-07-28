package ui.repository

import ui.entity.ContactEntity


class ContactRepository {
    // responsável por manter os dados e não sobrescrever
    companion object {
        private val contactList = mutableListOf<ContactEntity>();

        fun save(contact: ContactEntity) {
            contactList.add(contact);
        }

        fun delete(contact: ContactEntity) {

        }
    }
}