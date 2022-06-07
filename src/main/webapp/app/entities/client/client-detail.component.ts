import { Component, OnInit } from '@angular/core';
import {ActivatedRoute, ActivatedRouteSnapshot, Router} from '@angular/router';

import { IClient } from 'app/shared/model/client.model';
import {Agence, IAgence} from "../../shared/model/agence.model";
import {ClientService} from "./client.service";
import {Observable} from "rxjs";
import {HttpHeaders, HttpResponse} from "@angular/common/http";
import {DialogService, DynamicDialogRef} from "primeng/dynamicdialog";
import {ConfirmationService, MessageService} from "primeng/api";
import {AgenceUpdateComponent} from "../agence/agence-update.component";
import {AgenceService} from "../agence/agence.service";

@Component({
  selector: 'jhi-client-detail',
  templateUrl: './client-detail.component.html',
  styleUrls:['./client.component.scss'],
  providers:[DialogService, MessageService, ConfirmationService]
})
export class ClientDetailComponent implements OnInit {
  client: IClient | null = null;
  agences?: IAgence[] | null = null;
  agence?:IAgence | null = null;
  ref?:DynamicDialogRef;
  idClient=0;

  constructor(protected activatedRoute: ActivatedRoute,
              protected clientService: ClientService,
              public dialogService: DialogService,
              public messageService: MessageService,
              public confirmationService : ConfirmationService,
              public agenceService: AgenceService,
              protected router: Router,
  ) {}

  loadPage(page?: number, dontNavigate?: boolean): void {
    this.clientService
      .agencesOfClient(this.idClient)
      .subscribe(
        (res: HttpResponse<IClient[]>) => this.onSuccess(res.body, !dontNavigate),
        () => this.onError()
      );
  }
  protected onSuccess(data: IClient[] | null, navigate: boolean): void {
    if (navigate) {
      this.router.navigate([`/client/${this.idClient}/view`]);

    }
    this.agences = data || [];
  }
  onError(): void{

  }

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ client }) => (this.client = client));
    this.activatedRoute.data.subscribe(({id})=>(this.idClient=id));
     //this.clientService.agencesOfClient(idClient).subscribe((res:HttpResponse<Agence[]>)=> (this.agences=res.body || []));
    this.activatedRoute.data.subscribe(({ agence })=>(this.agences = agence));
  }


  delete(agence:IAgence): void {
    this.confirmationService.confirm({
      message:"Etes-vous sure de vouloir supprimer ce client ?",
      header:"Supprimer",
      rejectIcon:"pi pi-times",
      rejectLabel:"Non",
      rejectButtonStyleClass:"p-button-rounded",
      acceptIcon:"pi pi-check",
      acceptLabel:"Oui",
      acceptButtonStyleClass:"p-button-rounded p-button-danger",
      accept:()=>{
        if (agence.id)
        {
          this.agenceService.delete(agence.id).subscribe(()=>{
            this.messageService.add({severity:'success', summary:'Success', detail:'Agence supprimé avec succès'});
            this.loadPage();
          })
        }

      },

    })

  }
  edite(agence:IAgence): void {
    this.ref = this.dialogService.open(AgenceUpdateComponent, {
      data:{agence},
      header:"Modifier une agence",
      width: '70%',
      transitionOptions:"200ms",
      contentStyle: {"max-height": "700px", "overflow": "auto"},
      baseZIndex: 10000,

    });
    this.ref.onClose.subscribe((result) =>{
      if (result ==="success")
      {
        this.messageService.add({severity:'success', summary:'Success', detail:'Agence modifiée avec succès'});
        this.loadPage();
      }

    });

  }
  create(): void {
    this.ref = this.dialogService.open(AgenceUpdateComponent, {
      header:"Créer une agence",
      width: '70%',
      transitionOptions:"200ms",
      contentStyle: {"max-height": "700px", "overflow": "auto"},
      baseZIndex: 10000,
      data:{agence:this.agence}
    });
    this.ref.onClose.subscribe((result) =>{
      if (result ==="success")
      {
        this.messageService.add({severity:'success', summary:'Success', detail:'Agence ajouté avec succès'});
        this.loadPage();
      }

    });

  }

  previousState(): void {
    window.history.back();
  }
}
