import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';

import { IClient, Client } from 'app/shared/model/client.model';
import { ClientService } from './client.service';
import { IUser } from 'app/core/user/user.model';
import { UserService } from 'app/core/user/user.service';
import {DynamicDialogConfig, DynamicDialogRef} from "primeng/dynamicdialog";
import {MessageService} from "primeng/api";
import {IAgence} from "../../shared/model/agence.model";

@Component({
  selector: 'jhi-client-update',
  templateUrl: './client-update.component.html',
  styleUrls: ['./client.component.scss'],
  providers:[MessageService]
})
export class ClientUpdateComponent implements OnInit {
  isSaving = false;
  users: IUser[] = [];
  client?:IClient;

  editForm = this.fb.group({
    id: [],
    codeClient: [],
    denomination: [],
    domaineActivite: [],
    siteWeb: [],
    compteFaceBook: [],
    compteTwitter: [],
    userId: [],
  });

  constructor(
    protected clientService: ClientService,
    protected userService: UserService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder,
    public dynamicDialogConfig : DynamicDialogConfig,
    public messageService: MessageService,
    public dynamicDialogRef: DynamicDialogRef
  ) {}

  ngOnInit(): void {
     this.client = this.dynamicDialogConfig.data.client;
     this.dynamicDialogConfig.header?.small();
     if(this.client?.id !== undefined)
     {
       this.updateForm(this.client);
       //this.dynamicDialogConfig.header="Modifier un client";
     }
     else {
       const monClient=new Client();
       this.updateForm(monClient);
       //this.dynamicDialogConfig.header="Créer un client";
     }
    /*this.activatedRoute.data.subscribe(({ client }) => {
      this.updateForm(client);

      this.userService.query().subscribe((res: HttpResponse<IUser[]>) => (this.users = res.body || []));
    });*/

  }

  updateForm(client: IClient): void {
    this.editForm.patchValue({
      id: client.id,
      codeClient: client.codeClient,
      denomination: client.denomination,
      domaineActivite: client.domaineActivite,
      siteWeb: client.siteWeb,
      compteFaceBook: client.compteFaceBook,
      compteTwitter: client.compteTwitter,
      userId: client.userId,
    });
  }

  previousState(): void {
    this.dynamicDialogRef.close();
  }

  save(): void {
    this.isSaving = true;
    const client = this.createFromForm();
    if (client.id !== undefined) {
      this.subscribeToSaveResponse(this.clientService.update(client));
    } else {
      this.subscribeToSaveResponse(this.clientService.create(client));
    }
  }

  private createFromForm(): IClient {
    return {
      ...new Client(),
      id: this.editForm.get(['id'])!.value,
      codeClient: this.editForm.get(['codeClient'])!.value,
      denomination: this.editForm.get(['denomination'])!.value,
      domaineActivite: this.editForm.get(['domaineActivite'])!.value,
      siteWeb: this.editForm.get(['siteWeb'])!.value,
      compteFaceBook: this.editForm.get(['compteFaceBook'])!.value,
      compteTwitter: this.editForm.get(['compteTwitter'])!.value,
      userId: this.editForm.get(['userId'])!.value,
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IClient>>): void {
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

  trackById(index: number, item: IUser): any {
    return item.id;
  }

}
