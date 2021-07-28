package business

import ui.entity.ContactEntity
import ui.repository.ContactRepository

class ContactBusiness{
    private fun validate(name: String, phone: String){
        if(name == ""){
            throw Exception("Nome é obrigatório");
        }
        if(phone == ""){
            throw Exception("Telefone é obrigatório");
        }
    }
    private fun validateDelete(name: String, phone: String){
        if(name == "" || phone == ""){
            throw Exception("É necesário selecionar um contato antes de remover.");
        }
    }

    fun getCount(): String{
        val list = getList();
        return when {
            list.isEmpty() -> "0 contatos";
            list.size == 1 -> "1 contato";
            else -> "${list.size} contatos";

        }
    }

    fun save(name : String, phone: String){
        validate(name,phone);
        val contact = ContactEntity(name,phone);
        ContactRepository.save(contact);
    }
    fun delete(name: String, phone: String){
        validateDelete(name,phone);
        val contact = ContactEntity(name,phone);
        ContactRepository.save(contact);
    }
    fun getList(): List<ContactEntity>{
        return ContactRepository.getList();
    }

}