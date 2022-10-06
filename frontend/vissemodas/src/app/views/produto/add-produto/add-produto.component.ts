import { Component, OnInit } from '@angular/core';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { ToastrService } from 'ngx-toastr';
import { Produto } from 'src/app/models/produto.model';
import { ProdutoService } from 'src/app/services/produto.service';

@Component({
  selector: 'app-add-produto',
  templateUrl: './add-produto.component.html',
  styleUrls: ['./add-produto.component.css'],
})
export class AddProdutoComponent implements OnInit {
  produtoForm: FormGroup;

  title: string = 'Cadastrar';
  btn_text: string = 'Cadastrar';
  id: any;
  isEditingProduct: boolean = false;

  constructor(
    private _fb: FormBuilder,
    private _toastr: ToastrService,
    private _router: Router,
    private _service: ProdutoService,
    private _aRouter: ActivatedRoute
  ) {
    this.produtoForm = this._fb.group({
      // Para cada input do nosso formulário
      descricao: ['', Validators.required],
      tamanho: ['', Validators.required],
      valorUnitario: ['', Validators.required],
      status: ['', Validators.required],
      imagem: ['', Validators.required],
      categoria: ['', Validators.required],
    });
    this.id = this._aRouter.snapshot.paramMap.get('id');
    if(this.id !== null) this.isEditingProduct = true;
  }

  ngOnInit(): void {
    this.isEditing();
    //console.log('Edição?',this.isEditingProduct);
  }

  addProduto() {
    console.log(this.produtoForm.value);
    const PRODUTO: Produto = {
      descricao: this.produtoForm.get('descricao')?.value,
      tamanho: this.produtoForm.get('tamanho')?.value,
      valorUnitario: this.produtoForm.get('valorUnitario')?.value,
      status: this.produtoForm.get('status')?.value,
      imagem:
        'assets/produtos/' + this.produtoForm.get('imagem')?.value + '.png',
      categoria: this.produtoForm.get('categoria')?.value,
    };

    if (this.id !== null) {
      // Atualizar
      this._service.putProduto(this.id, PRODUTO).subscribe({
        next: (data) => {
          this._toastr.info('Editado com sucesso', 'Produto');
          this._router.navigate(['/admin-list-produto']);
        },
        error: (e) => console.log(e),
      });
    } else {
      this._service.postProduto(PRODUTO).subscribe({
        next: (data) => {
          console.log('Produto cadastrado'); 
          this._toastr.success('Cadastrado com sucesso', 'Produto');
          this._router.navigate(['']);
        },
        error: (e) => console.log(e),
      });
    }
  }

  isEditing() {
    if (this.id !== null) {
      this.title = 'Editar';
      this.btn_text = 'Editar';     

      this._service.getOneProduto(this.id).subscribe({
        next: (data) => {
          // Atualizando os valores no produtoForm
          this.produtoForm.patchValue({
            descricao: data.descricao,
            tamanho: data.tamanho,
            valorUnitario: data.valorUnitario,
            status: data.status,
            imagem: data.imagem,
            categoria: data.categoria,
          });
        },
        error: (e) => console.log(e),
      });
    }
  }
}
