import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { IClient } from 'app/shared/model/client.model';
import {IAgence} from "../../shared/model/agence.model";

type EntityResponseType = HttpResponse<IClient>;
type EntityArrayResponseType = HttpResponse<IClient[]>;

@Injectable({ providedIn: 'root' })
export class ClientService {
  public resourceUrl = SERVER_API_URL + 'api/clients';

  constructor(protected http: HttpClient) {}

  create(client: IClient): Observable<EntityResponseType> {
    return this.http.post<IClient>(this.resourceUrl, client, { observe: 'response' });
  }

  update(client: IClient): Observable<EntityResponseType> {
    return this.http.put<IClient>(this.resourceUrl, client, { observe: 'response' });
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http.get<IClient>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<IClient[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  clientAndListAgences():Observable<EntityArrayResponseType>
  {
    return this.http.get<IClient[]>(`${this.resourceUrl}/clientsAndAgences`, {observe:'response'});
  }
  agencesOfClient(id: number): Observable<HttpResponse<IAgence[]>>
  {
    window.console.log("===================");
    window.console.log(id);
    window.console.log("===================");

    return this.http.get<IAgence[]>(`${this.resourceUrl}/agencesClient/${id}`, {observe:'response'});
  }
}
