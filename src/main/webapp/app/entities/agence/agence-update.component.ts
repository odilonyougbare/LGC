import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';

import { IAgence, Agence } from 'app/shared/model/agence.model';
import { AgenceService } from './agence.service';
import { IUser } from 'app/core/user/user.model';
import { UserService } from 'app/core/user/user.service';
import { IClient } from 'app/shared/model/client.model';
import { ClientService } from 'app/entities/client/client.service';
import {DynamicDialogConfig, DynamicDialogRef} from "primeng/dynamicdialog";
import {MessageService} from "primeng/api";

type SelectableEntity = IUser | IClient;

@Component({
  selector: 'jhi-agence-update',
  templateUrl: './agence-update.component.html',
  providers:[MessageService]
})
export class AgenceUpdateComponent implements OnInit {
  isSaving = false;
  users: IUser[] = [];
  clients: IClient[] = [];
  agence?:IAgence;
  idClient=0;

  editForm = this.fb.group({
    id: [],
    codeAgence: [],
    denominationAgence: [],
    typeAgence: [],
    telephone: [],
    numeroWhatsapp: [],
    email: [],
    autreContact: [],
    quartier: [],
    arrondissement: [],
    commune: [],
    province: [],
    region: [],
    userId: [],
    clientId: [],
  });

  constructor(
    protected agenceService: AgenceService,
    protected userService: UserService,
    protected clientService: ClientService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder,
    public dynamicDialogConfig: DynamicDialogConfig,
    public dynamicDialogRef: DynamicDialogRef,
    public messageService: MessageService
  ) {}

  ngOnInit(): void {
     /*this.activatedRoute.data.subscribe(({ agence }) => {
      this.updateForm(agence);

      this.userService.query().subscribe((res: HttpResponse<IUser[]>) => (this.users = res.body || []));

      this.clientService.query().subscribe((res: HttpResponse<IClient[]>) => (this.clients = res.body || []));

    });*/
    this.activatedRoute.data.subscribe(({id})=>(this.idClient=id));
    this.agence=this.dynamicDialogConfig.data.agence;
    if (this.agence?.id !== undefined)
    {
      this.updateForm(this.agence)
    }
    else {
      const newAgence = new Agence();
      this.updateForm(newAgence);
    }
  }

  updateForm(agence: IAgence): void {
    this.editForm.patchValue({
      id: agence.id,
      codeAgence: agence.codeAgence,
      denominationAgence: agence.denominationAgence,
      typeAgence: agence.typeAgence,
      telephone: agence.telephone,
      numeroWhatsapp: agence.numeroWhatsapp,
      email: agence.email,
      autreContact: agence.autreContact,
      quartier: agence.quartier,
      arrondissement: agence.arrondissement,
      commune: agence.commune,
      province: agence.province,
      region: agence.region,
      userId: agence.userId,
      clientId: agence.clientId,
    });
  }

  previousState(): void {
    //window.history.back();
    this.dynamicDialogRef.close();
  }

  save(): void {
    this.isSaving = true;
    const agence = this.createFromForm();
    if (agence.id !== undefined) {
      this.subscribeToSaveResponse(this.agenceService.update(agence));
    } else {
      this.subscribeToSaveResponse(this.agenceService.create(agence));
    }
  }

  private createFromForm(): IAgence {
    return {
      ...new Agence(),
      id: this.editForm.get(['id'])!.value,
      codeAgence: this.editForm.get(['codeAgence'])!.value,
      denominationAgence: this.editForm.get(['denominationAgence'])!.value,
      typeAgence: this.editForm.get(['typeAgence'])!.value,
      telephone: this.editForm.get(['telephone'])!.value,
      numeroWhatsapp: this.editForm.get(['numeroWhatsapp'])!.value,
      email: this.editForm.get(['email'])!.value,
      autreContact: this.editForm.get(['autreContact'])!.value,
      quartier: this.editForm.get(['quartier'])!.value,
      arrondissement: this.editForm.get(['arrondissement'])!.value,
      commune: this.editForm.get(['commune'])!.value,
      province: this.editForm.get(['province'])!.value,
      region: this.editForm.get(['region'])!.value,
      userId: this.editForm.get(['userId'])!.value,
      clientId: this.idClient
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IAgence>>): void {
    result.subscribe(
      () => this.onSaveSuccess(),
      () => this.onSaveError()
    );
  }

  protected onSaveSuccess(): void {
    this.isSaving = false;
    //this.previousState();
    this.dynamicDialogRef.close("success");
  }

  protected onSaveError(): void {
    this.isSaving = false;
  }

  trackById(index: number, item: SelectableEntity): any {
    return item.id;
  }
}
