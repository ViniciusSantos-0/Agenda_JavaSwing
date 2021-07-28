package business

import ui.entity.ContactEntity
import ui.repository.ContactRepository
// classe destinada para a regra de negocio
class ContactBusiness{
    //validando de os campos não estão vazio na tela de adicionar
    private fun validate(name: String, phone: String){
        if(name == ""){
            throw Exception("Nome é obrigatório");
        }
        if(phone == ""){
            throw Exception("Telefone é obrigatório");
        }
    }
    // Verificando se tem algum item selecionado para ser deletado
    private fun validateDelete(name: String, phone: String){
        if(name == "" || phone == ""){
            throw Exception("É necesário selecionar um contato antes de remover.");
        }
    }
    // contando os contatos
    fun getCount(): String{
        val list = getList();
        return when {
            list.isEmpty() -> "0 contatos";
            list.size == 1 -> "1 contato";
            else -> "${list.size} contatos";

        }
    }
    // tratamento para salvar
    fun save(name : String, phone: String){
        validate(name,phone);
        val contact = ContactEntity(name,phone);
        ContactRepository.save(contact);
    }
    //tratamento para deletar
    fun delete(name: String, phone: String){
        validateDelete(name,phone);
        val contact = ContactEntity(name,phone);
        ContactRepository.delete(contact);
    }
    //mandando a lista para o repositório
    fun getList(): List<ContactEntity>{
        return ContactRepository.getList();
    }

}