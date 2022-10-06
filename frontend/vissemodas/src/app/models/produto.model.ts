export class Produto{

    id?: number;
    descricao: string;
    tamanho: string;
    valorUnitario: number;
    status: boolean;
    imagem: string;
    categoria: string;

    constructor(descricao: string, tamanho: string,
                valorUnitario: number, status: boolean,
                imagem: string, categoria: string){
        this.descricao = descricao;
        this.tamanho = tamanho;
        this.valorUnitario = valorUnitario;
        this.status = true;
        this.imagem = imagem;
        this.categoria = categoria;
    }

}