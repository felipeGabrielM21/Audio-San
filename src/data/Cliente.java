package data;

public class Cliente {
    private int id;
    private String nomeCliente;
    private String carro;
    private int anoCarro;
    private String endereco;
    private int telefone;

    // Construtor com todos os campos
    public Cliente(int id, String nomeCliente, String carro, int anoCarro, String endereco, int telefone) {
        this.id = id;
        this.nomeCliente = nomeCliente;
        this.carro = carro;
        this.anoCarro = anoCarro;
        this.endereco = endereco;
        this.telefone = telefone;
    }

    // Construtor sem o campo 'id'
    public Cliente(String nomeCliente, String carro, int anoCarro, String endereco, int telefone) {
        this.nomeCliente = nomeCliente;
        this.carro = carro;
        this.anoCarro = anoCarro;
        this.endereco = endereco;
        this.telefone = telefone;
    }

    // Métodos getters e setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNomeCliente() {
        return nomeCliente;
    }

    public void setNomeCliente(String nomeCliente) {
        this.nomeCliente = nomeCliente;
    }

    public String getCarro() {
        return carro;
    }

    public void setCarro(String carro) {
        this.carro = carro;
    }

    public int getAnoCarro() {
        return anoCarro;
    }

    public void setAnoCarro(int anoCarro) {
        this.anoCarro = anoCarro;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public int getTelefone() {
        return telefone;
    }

    public void setTelefone(int telefone) {
        this.telefone = telefone;
    }
}
