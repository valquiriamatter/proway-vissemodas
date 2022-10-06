import { BinaryOperator } from '@angular/compiler';
import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { ToastrService } from 'ngx-toastr';
import { Endereco } from 'src/app/models/endereco.model';
import { ConsultaCepService } from 'src/app/services/consulta-cep.service';
import { EnderecoService } from 'src/app/services/endereco.service';

@Component({
  selector: 'app-add-endereco',
  templateUrl: './add-endereco.component.html',
  styleUrls: ['./add-endereco.component.css'],
})
export class AddEnderecoComponent implements OnInit {
  enderecoForm: FormGroup;

  listEnderecos: Endereco[] = [];

  title: string = 'Cadastrar';
  btn_text: string = "Cadastrar";
  id: any;

  constructor(
    private _fb: FormBuilder,
    private CepService: ConsultaCepService,
    private _service: EnderecoService,
    private _toastr: ToastrService,
    private _router: Router,
    private _aRouter: ActivatedRoute,
    private _serviceEndereco: EnderecoService,
  ) {
    this.enderecoForm = _fb.group({
      cep: ['', Validators.required],
      logradouro: ['', Validators.required],
      cidade: ['', Validators.required],
      estado: ['', Validators.required],
      bairro: ['', Validators.required],
      numero: ['', Validators.required],
    });
    this.id = this._aRouter.snapshot.paramMap.get('id');
  }

  ngOnInit(): void {
    this.isEditing();
  }

  // Fazendo o edit vir já preenchido com o endereço do ID
  getLastEnderecoId(): void {
    this._serviceEndereco.getEnderecos().subscribe({
      next: (data) => {
        this.listEnderecos = data;
        this.id = this.listEnderecos.length;
      },
      error: (e) => console.log(e),
    });
  }

  populaFormEndereco(dados: any) {
    console.log(dados);
    this.enderecoForm.patchValue({
      cep: dados.cep,
      logradouro: dados.logradouro,
      cidade: dados.localidade,
      estado: dados.uf,
      bairro: dados.bairro,
      numero: dados.numero,
    });
  }
  consultaCEP() {
    let cep = this.enderecoForm.get('cep')?.value;
    if (cep != null && cep !== '') {
      this.CepService.consultaCEP(cep)?.subscribe((dados) =>
        this.populaFormEndereco(dados)
      );
    }
  }

  addEndereco() {
    console.log(this.enderecoForm.value);
    const ENDERECO: Endereco = {
      cep: this.enderecoForm.get('cep')?.value,
      logradouro: this.enderecoForm.get('logradouro')?.value,
      cidade: this.enderecoForm.get('cidade')?.value,
      estado: this.enderecoForm.get('estado')?.value,
      bairro: this.enderecoForm.get('bairro')?.value,
      numero: this.enderecoForm.get('numero')?.value,
    };

    if (this.id !== null) {
      // Atualizar
      this._service.putEndereco(this.id, ENDERECO).subscribe({
        next: (data) => {
          this._toastr.info('Editado com sucesso', 'Endereco');
          this._router.navigate(['/list-cliente']);
        },
        error: (e) => console.log(e),
      });
    } else {
      // Cadastrar endereço e redireciona para cadastrar cliente
      this._service.postEndereco(ENDERECO).subscribe({
        next: (data) => {
          console.log('Endereco cadastrado'); 
          this._toastr.success('Cadastrado com sucesso', 'Endereco');
          this._router.navigate(['/add-cliente']);
        },
        error: (e) => console.log(e),
      });
    }
  }

  isEditing() {
    if (this.id !== null) {
      this.title = 'Editar';
      this.btn_text = 'Editar';

      this._service.getOneEndereco(this.id).subscribe({
        next: (data) => {
          this.enderecoForm.patchValue({
            cep: data.cep,
            logradouro: data.logradouro,
            cidade: data.cidade,
            estado: data.estado,
            bairro: data.bairro,
            numero: data.numero,
          });
        },
        error: (e) => console.log(e),
      });
    }
  }

}
