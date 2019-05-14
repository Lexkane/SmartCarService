import { Skill } from './skill/skill';

export class Worker {
    id: number = null;
    userName: string = null;
    fullName: string = null;
    password: string = null;
    email: string = null;
    numberPhone: string = null;
    skill: Skill = null;
    rating: number = null;

    constructor() {
        this.id = null;
        this.userName = null;
        this.fullName = null;
        this.password = null;
        this.email = null;
        this.numberPhone = null;
        this.skill = new Skill();
        this.rating = null;
    }
 }