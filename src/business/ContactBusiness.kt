package business

import ui.entity.ContactEntity
import ui.repository.ContactRepository

class ContactBusiness{
    fun validate(name: String, phone: String){
        if(name == ""){
            throw Exception("Nome é obrigatório");
        }
        if(phone == ""){
            throw Exception("Telefone é obrigatório");
        }
    }
    fun validateDelete(name: String, phone: String){
        if(name == "" || phone == ""){
            throw Exception("É necesário selecionar um contato antes de remover.");
        }
    }

    fun save(name : String, phone: String){
        validate(name,phone);
        val contact = ContactEntity(name,phone);
        ContactRepository.save(contact);
    }
    fun delete(name: String, phone: String){
        validateDelete(name,phone);
        ContactRepository.delete(contact);
    }


}