import { IAgence } from 'app/shared/model/agence.model';

export interface IClient {
  id?: number;
  codeClient?: string;
  denomination?: string;
  domaineActivite?: string;
  siteWeb?: string;
  compteFaceBook?: string;
  compteTwitter?: string;
  agences?: IAgence[];
  userLogin?: string;
  userId?: number;
}

export class Client implements IClient {
  constructor(
    public id?: number,
    public codeClient?: string,
    public denomination?: string,
    public domaineActivite?: string,
    public siteWeb?: string,
    public compteFaceBook?: string,
    public compteTwitter?: string,
    public agences?: IAgence[],
    public userLogin?: string,
    public userId?: number
  ) {}
}
