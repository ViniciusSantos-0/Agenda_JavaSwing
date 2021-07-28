package ui.repository

import ui.entity.ContactEntity

// tratamento de armazenamento
class ContactRepository {
    // responsável por manter os dados e não sobrescrever e salvando
    companion object {
        private val contactList = mutableListOf<ContactEntity>();

        fun save(contact: ContactEntity) {
            contactList.add(contact);
        }
        // deletando o contato selecionado
        fun delete(contact: ContactEntity) {
        var index = 0;
            for (item in contactList.withIndex()){
                if(item.value.name == contact.name && item.value.phone == contact.phone) {
                    index = item.index;
                    break;
                }
            }
            contactList.removeAt(index);
        }
        //lista de contato
        fun getList(): List<ContactEntity>{
            return contactList
        }
    }
}