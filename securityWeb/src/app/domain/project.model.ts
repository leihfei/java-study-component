export class Project {
    id?: number;
    name: string;
    desc: string;
    coverImg: string;

    constructor(name: string, desc: string, coverImg: string){
    this.name = name;
    this.desc = desc;
    this.coverImg = coverImg;
}
}