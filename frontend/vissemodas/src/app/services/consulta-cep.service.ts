import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { of } from 'rxjs'

@Injectable({
  providedIn: 'root'
})
export class ConsultaCepService {

  constructor(private http: HttpClient) { }

 consultaCEP(cep: string){

    // verificação se há somente dígitos no cep
    cep = cep.replace(/\D/g, '')

    if (cep !== ''){
      // expressão regular para validar o cep
      const validacao = /^[0-9]{8}$/;

      // validação do cep informado
      if(validacao.test(cep)){

        return this.http.get(`//viacep.com.br/ws/${cep}/json`);

      }
    }
    return of({});


  }
}
