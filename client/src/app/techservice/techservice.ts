import { TechmanagerProfile } from '../techmanager-profile/techmanager-profile';

export class Techservice {
    stoId: number;
    name: string;
    address: string;
    workers: []; // array of Worker
    dealer: any; // Dealer type
    techManager: TechmanagerProfile;
    rating: number;

    constructor() {
        this.stoId = -1;
        this.name = null;
        this.address = null;
        this.workers = [];
        this.dealer = null;
        this.techManager = null;
        this.rating = 0;
    }
}