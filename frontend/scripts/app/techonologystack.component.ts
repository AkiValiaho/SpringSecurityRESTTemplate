import {Component} from "angular2/core";
@Component({
    selector: 'technologies',
    inputs: ['myname'],
    template: `
    <div class="col-sm-6 col-md-6">
<div class="jumbotron">
<div class="container">
        <div><h1>{{title}}</h1></div>
<div class="breadtext">
<div class="col-md-12 col-sm-12 centering"><img src="avatar38939_0.png" width="10%">
<img src="pic_angular.jpg" width="10%">
<img src="Java_logo.png" width="10%">
<img src="docker.png" width="10%">
<img src="nodejs.png" width="10%">
</div>
<div class="breadtext breadtext-technology" (click)="fireCustomEvent()">I'm proficient with JVM-family tools like Java EE and its framework Spring. I'm able to work with traditional SQL and modern NoSQL-based database technologies. I love to see well-structured, properly tested and easy to read -code. I'm able to code and test Node.js based code. I know Angular.js enough to get by in a full-stack business environments.
{{myname}}
 </div>
</div>
</div>
</div>
</div>
    `
})
export class TechnologyStackComponent {
    public myname:String;

    fireCustomEvent() {
        if (!(this.myname === "")) {
            this.myname = "";
        } else {
            this.myname = "Hello World!";
        }
    }
}