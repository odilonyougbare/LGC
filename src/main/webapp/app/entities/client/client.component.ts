import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpHeaders, HttpResponse } from '@angular/common/http';
import { ActivatedRoute, ParamMap, Router, Data } from '@angular/router';
import { Subscription, combineLatest } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';

import { IClient } from 'app/shared/model/client.model';

import { ITEMS_PER_PAGE } from 'app/shared/constants/pagination.constants';
import { ClientService } from './client.service';
import { ClientDeleteDialogComponent } from './client-delete-dialog.component';
import {IAgence} from "../../shared/model/agence.model";
import {DialogService, DynamicDialogRef} from "primeng/dynamicdialog";
import {ConfirmationService, MessageService} from "primeng/api";
import {ClientUpdateComponent} from "./client-update.component";
import {reject} from "q";

@Component({
  selector: 'jhi-client',
  templateUrl: './client.component.html',
  styleUrls:['./client.component.scss'],
  providers: [DialogService, MessageService, ConfirmationService]
})
export class ClientComponent implements OnInit, OnDestroy {
  clients?: IClient[];
  eventSubscriber?: Subscription;
  totalItems = 0;
  itemsPerPage = ITEMS_PER_PAGE;
  page!: number;
  predicate!: string;
  ascending!: boolean;
  ngbPaginationPage = 1;
  ref?: DynamicDialogRef;
  client?: IClient;


  constructor(
    protected dialogService: DialogService,
    protected messageService: MessageService,
    protected clientService: ClientService,
    public confirmationService : ConfirmationService,
    protected activatedRoute: ActivatedRoute,
    protected router: Router,
    protected eventManager: JhiEventManager,
    protected modalService: NgbModal,

  ) {}

  loadPage(page?: number, dontNavigate?: boolean): void {
    const pageToLoad: number = page || this.page || 1;

    this.clientService
      .query({
        page: pageToLoad - 1,
        size: this.itemsPerPage,
        sort: this.sort(),
      })
      .subscribe(
        (res: HttpResponse<IClient[]>) => this.onSuccess(res.body, res.headers, pageToLoad, !dontNavigate),
        () => this.onError()
      );
  }

  ngOnInit(): void {
    this.handleNavigation();
    this.registerChangeInClients();
  }

  protected handleNavigation(): void {
    combineLatest(this.activatedRoute.data, this.activatedRoute.queryParamMap, (data: Data, params: ParamMap) => {
      const page = params.get('page');
      const pageNumber = page !== null ? +page : 1;
      const sort = (params.get('sort') ?? data['defaultSort']).split(',');
      const predicate = sort[0];
      const ascending = sort[1] === 'asc';
      if (pageNumber !== this.page || predicate !== this.predicate || ascending !== this.ascending) {
        this.predicate = predicate;
        this.ascending = ascending;
        this.loadPage(pageNumber, true);
      }
    }).subscribe();
  }

  ngOnDestroy(): void {
    if (this.eventSubscriber) {
      this.eventManager.destroy(this.eventSubscriber);
    }
  }

  trackId(index: number, item: IClient): number {
    // eslint-disable-next-line @typescript-eslint/no-unnecessary-type-assertion
    return item.id!;
  }

  registerChangeInClients(): void {
    this.eventSubscriber = this.eventManager.subscribe('clientListModification', () => this.loadPage());
  }

  delete(client: IClient): void {
    /*const modalRef = this.modalService.open(ClientDeleteDialogComponent, { size: 'lg', backdrop: 'static' });
    modalRef.componentInstance.client = client;*/
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
        if (client.id)
        {
          this.clientService.delete(client.id).subscribe(()=>{
            this.messageService.add({severity:'success', summary:'Success', detail:'Client supprimé avec succès'});
            this.loadPage();
          })
        }

    },

    })
  }

  sort(): string[] {
    const result = [this.predicate + ',' + (this.ascending ? 'asc' : 'desc')];
    if (this.predicate !== 'id') {
      result.push('id');
    }
    return result;
  }

  protected onSuccess(data: IClient[] | null, headers: HttpHeaders, page: number, navigate: boolean): void {
    this.totalItems = Number(headers.get('X-Total-Count'));
    this.page = page;
    if (navigate) {
      this.router.navigate(['/client'], {
        queryParams: {
          page: this.page,
          size: this.itemsPerPage,
          sort: this.predicate + ',' + (this.ascending ? 'asc' : 'desc'),
        },
      });
    }
    this.clients = data || [];
    this.ngbPaginationPage = this.page;
  }

  protected onError(): void {
    this.ngbPaginationPage = this.page ?? 1;
  }

  editer(client: IClient): void{
    this.ref= this.dialogService.open(ClientUpdateComponent,{
      header:"Modifier un client",
      width: '70%',
      transitionOptions:"200ms",
      contentStyle: {"max-height": "700px", "overflow": "auto", "header":"red"},
      baseZIndex: 10000,
      styleClass:"p-dialog-titlebar p-dialog-title p-dialog-titlebar-icon p-dialog-footer",
      data: {client},
    }
      );
    this.ref.onClose.subscribe((result) =>{
      if (result ==="success")
      {
        this.messageService.add({severity:'success', summary:'Success', detail:'Client Modifier avec succès'});
        this.loadPage();
      }

    });

  }
  create():void{
    this.ref= this.dialogService.open(ClientUpdateComponent, {
      header:"Créer un client",
      width:'70%',
      transitionOptions:"200ms",
      contentStyle:{"max-height":"700px", "overflow":"auto"},
      baseZIndex:10000,
      data:{client:this.client}
    });
    this.ref.onClose.subscribe((result) =>{
      if (result ==="success")
      {
        this.messageService.add({severity:'success', summary:'Success', detail:'Client Ajouté avec succès'});
        this.loadPage();
      }



    });
  }
}
