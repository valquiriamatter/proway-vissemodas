export class Endereco{
    id?: number;
    cep: string;
    estado: string;
    cidade: string;
    bairro: string;
    logradouro: string;
    numero: number;

    constructor(cep: string, estado: string, cidade: string, 
                bairro: string, logradouro: string, numero: number){
                    this.cep = cep;
                    this.estado = estado;
                    this.cidade = cidade;
                    this.bairro = bairro;
                    this.logradouro = logradouro;
                    this.numero = numero;
                }
}