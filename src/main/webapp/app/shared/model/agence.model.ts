export interface IAgence {
  id?: number;
  codeAgence?: string;
  denominationAgence?: string;
  typeAgence?: string;
  telephone?: string;
  numeroWhatsapp?: string;
  email?: string;
  autreContact?: string;
  quartier?: string;
  arrondissement?: string;
  commune?: string;
  province?: string;
  region?: string;
  userLogin?: string;
  userId?: number;
  clientDenomination?: string;
  clientId?: number;
}

export class Agence implements IAgence {
  constructor(
    public id?: number,
    public codeAgence?: string,
    public denominationAgence?: string,
    public typeAgence?: string,
    public telephone?: string,
    public numeroWhatsapp?: string,
    public email?: string,
    public autreContact?: string,
    public quartier?: string,
    public arrondissement?: string,
    public commune?: string,
    public province?: string,
    public region?: string,
    public userLogin?: string,
    public userId?: number,
    public clientDenomination?: string,
    public clientId?: number
  ) {}
}
